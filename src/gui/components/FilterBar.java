package gui.components;

import gui.interfaces.Searchable;
import gui.ui.CustomComboBoxUI;
import gui.utils.DateUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/*
A compound component that allows a user to filter a TableModel by a drop-down menu based
 on the first column of the table or by a date range
 */
public class FilterBar extends JComponent implements Searchable, TableModelListener {
    private static final int ADD = 1;
    private static final int DELETE = -1;
    private static int U_ID = 1;
    private final JComboBox<String>optionsDropDown;
    private HashMap<String,Integer> options;
    private final TableModel tableToFilter;
    private final JTextField from;
    private final JTextField to;
    public FilterBar(final HashMap<String,Object> settings, final String dropDownName, final TableModel tableToFilter){
        super();
        this.tableToFilter = tableToFilter;
        this.tableToFilter.addTableModelListener(this);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel dropDownLabel = new JLabel(dropDownName);
        dropDownLabel.setForeground((Color)settings.get("FB_FontColor"));
        dropDownLabel.setBorder(new EmptyBorder(0,0,0,10));
        add(dropDownLabel);

        optionsDropDown = new JComboBox<>();
        optionsDropDown.setUI(new CustomComboBoxUI(settings));
        optionsDropDown.setPreferredSize(new Dimension(100,30));
        optionsDropDown.setBackground((Color)settings.get("FB_Background"));
        optionsDropDown.setBorder(new LineBorder((Color)settings.get("FB_Border")));
        optionsDropDown.setForeground((Color)settings.get("FB_FontColor"));
        optionsDropDown.setFocusable(false);
        optionsDropDown.addItem("");
        options = new HashMap<>();
        //updateDropDown(FilterBar.ADD, "");
        add(optionsDropDown);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground((Color)settings.get("FB_FontColor"));
        add(fromLabel);

        from = new JTextField();
        from.setPreferredSize(new Dimension(150,30));
        from.setBackground((Color)settings.get("FB_Background"));
        from.setFont((Font)settings.get("FB_Font"));
        from.setForeground((Color)settings.get("FB_FontColor"));
        from.setBorder(new EmptyBorder(0,0,0,0));
        from.setCaretColor((Color)settings.get("FB_FontColor"));
        from.setCursor((Cursor)settings.get("UI_TextCursor"));
        add(from);

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground((Color)settings.get("FB_FontColor"));
        add(toLabel);

        to = new JTextField();
        to.setPreferredSize(new Dimension(150,30));
        to.setBackground((Color)settings.get("FB_Background"));
        to.setFont((Font)settings.get("FB_Font"));
        to.setForeground((Color)settings.get("FB_FontColor"));
        to.setBorder(new EmptyBorder(0,0,0,0));
        to.setCaretColor((Color)settings.get("FB_FontColor"));
        to.setCursor((Cursor)settings.get("UI_TextCursor"));
        setName("FB_"+U_ID);
        U_ID++;
        add(this.to);
    }
    /*
    Updates the drop-down menu whenever the first column of the table changes. New entries will be added in
    capitalized form
    ocean -> Ocean
     */
    private void updateDropDown(int operation, String value) {
        //Capitalize
        value = value.toLowerCase();
        if(value.length() > 0)value = value.substring(0,1).toUpperCase()+value.substring(1);

        switch(operation){
            case FilterBar.ADD:
                if(!options.containsKey(value)){
                    options.put(value,1);
                    optionsDropDown.addItem(value);
                }
                else options.put(value,options.get(value)+1);
                break;
            case FilterBar.DELETE:
                if(options.containsKey(value)){
                    options.put(value,options.get(value)-1);
                    if(options.get(value)==0){
                        options.remove(value);
                        optionsDropDown.removeItem(value);
                    }
                }
                break;
        }
    }
    private void updateDropDown(int operation, String[] values){
        for(String s: values){
            updateDropDown(operation,s);
        }
    }
    private void clearDropDown(){
        optionsDropDown.removeAllItems();
        optionsDropDown.addItem("");
        options = new HashMap<>();
    }
    /*
    Returns a RowFilter.andFilter object that will check for matches in the drop down column and/or dates
    that fall between or on the from/to text fields. Blank entries/incorrect entries are ignored. This also resets
    the drop-down and date range fields
     */
    @Override
    public RowFilter<Object, Object> getResults() {
        ArrayList<RowFilter<Object,Object>> filters = new ArrayList<>();
        RowFilter<Object,Object> filter = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        if(optionsDropDown.getSelectedIndex()!= 0){
            filters.add( RowFilter.regexFilter("(?i)"+optionsDropDown.getSelectedItem()+"+",0));//(?i) ignore case regex
        }
        if(!from.getText().isEmpty()){
            String date = DateUtils.getDate(from);
            if(!DateUtils.invalidDate(date)){
                try {
                    ArrayList<RowFilter<Object,Object>> fromDateFilters = new ArrayList<>();
                    fromDateFilters.add(RowFilter.dateFilter(RowFilter.ComparisonType.EQUAL,sdf.parse(date)));
                    fromDateFilters.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER,sdf.parse(date)));
                    filters.add(RowFilter.orFilter(fromDateFilters));

                }
                catch(ParseException ignored){}
            }

        }
        if(!to.getText().isEmpty()){
            String date = DateUtils.getDate(to);
            if(!DateUtils.invalidDate(date)){
                try {
                    ArrayList<RowFilter<Object,Object>> toDateFilters = new ArrayList<>();
                    toDateFilters.add(RowFilter.dateFilter(RowFilter.ComparisonType.EQUAL,sdf.parse(date)));
                    toDateFilters.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE,sdf.parse(date)));
                    filters.add(RowFilter.orFilter(toDateFilters));

                }
                catch(ParseException ignored){}
            }

        }
        if(filters.size() > 0){
            filter = RowFilter.andFilter(filters);
        }
        from.setText("");
        to.setText("");
        optionsDropDown.setSelectedIndex(0);
        return filter;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        //If we deleted every row
        if(e.getType() == TableModelEvent.DELETE && tableToFilter.getRowCount() == 0){
            clearDropDown();
            return;
        }
        //Check if the drop-down might have more or less options
        DefaultTableModel theTable = ((DefaultTableModel)e.getSource());
        if(e.getFirstRow() != e.getLastRow()){
            String[] values = new String[e.getLastRow() - e.getFirstRow()];
            for(int r = e.getFirstRow(), ai = 0; r <= e.getLastRow();r++,ai++){
                values[ai] = (String)theTable.getValueAt(r,0);
            }
            if(e.getType() == TableModelEvent.INSERT) updateDropDown(FilterBar.ADD,values);
            else if(e.getType() == TableModelEvent.DELETE)updateDropDown(FilterBar.DELETE,values);
        }
        else {
            String value = (String)theTable.getValueAt(e.getFirstRow(),0);
            if(e.getType() == TableModelEvent.INSERT) updateDropDown(FilterBar.ADD,value);
            else if(e.getType() == TableModelEvent.DELETE)updateDropDown(FilterBar.DELETE,value);
        }
    }
}
