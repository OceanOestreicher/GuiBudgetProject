package gui.components;

import gui.components.interfaces.Searchable;
import gui.utils.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class FilterBar extends AbstractButton implements Searchable, TableModelListener {
    private static int U_ID = 1;
    private JComboBox<String>optionsDropDown;
    private LinkedList<String> optionsDropDownItemList;
    private TableModel tableToFilter;
    private TableModel unfilteredTable;
    private boolean isFiltered = false;
    private JTextField from,to;
    public FilterBar(HashMap<String,Object> settings, String dropDownName, TableModel tableToFilter){
        super();
        this.unfilteredTable = tableToFilter;
        this.unfilteredTable.addTableModelListener(this);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel dropDownLabel = new JLabel(dropDownName);
        dropDownLabel.setForeground((Color)settings.get("FB_FontColor"));
        dropDownLabel.setBorder(new EmptyBorder(0,0,0,10));
        this.add(dropDownLabel);


        this.optionsDropDown = new JComboBox<>();
        this.optionsDropDown.setUI(new CustomComboBoxUI(settings));
        this.optionsDropDown.setPreferredSize(new Dimension(100,30));
        this.optionsDropDown.setBackground((Color)settings.get("FB_Background"));
        this.optionsDropDown.setBorder(new LineBorder((Color)settings.get("FB_Border")));
        this.optionsDropDown.setForeground((Color)settings.get("FB_FontColor"));
        this.optionsDropDown.setFocusable(false);

        updateDropDown();
        this.add(this.optionsDropDown);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground((Color)settings.get("FB_FontColor"));
        this.add(fromLabel);

        this.from = new JTextField();
        this.from.setPreferredSize(new Dimension(150,30));
        this.from.setBackground((Color)settings.get("FB_Background"));
        this.from.setFont((Font)settings.get("FB_Font"));
        this.from.setForeground((Color)settings.get("FB_FontColor"));
        this.from.setBorder(new EmptyBorder(0,0,0,0));
        //Changes cursor color
        this.from.setCaretColor((Color)settings.get("FB_FontColor"));
        this.from.setCursor((Cursor)settings.get("UI_TextCursor"));
        this.add(this.from);

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground((Color)settings.get("FB_FontColor"));
        this.add(toLabel);

        this.to = new JTextField();
        this.to.setPreferredSize(new Dimension(150,30));
        this.to.setBackground((Color)settings.get("FB_Background"));
        this.to.setFont((Font)settings.get("FB_Font"));
        this.to.setForeground((Color)settings.get("FB_FontColor"));
        this.to.setBorder(new EmptyBorder(0,0,0,0));
        //Changes cursor color
        this.to.setCaretColor((Color)settings.get("FB_FontColor"));
        this.to.setCursor((Cursor)settings.get("UI_TextCursor"));
        this.setName("FB_"+U_ID);
        U_ID++;
        this.add(this.to);

    }

    private void updateDropDown() {
        /*
        If filtered, don't remove options, otherwise do
        Remove Options, Add Options, Update Drop Down
         */

        if(!this.isFiltered){
            this.optionsDropDown.removeAllItems();
            this.optionsDropDownItemList = new LinkedList<>();
            this.optionsDropDownItemList.add("");
            for(int i = 0; i < unfilteredTable.getRowCount();i++){
                if(!this.optionsDropDownItemList.contains((String)unfilteredTable.getValueAt(i,0))){
                    this.optionsDropDownItemList.add((String)unfilteredTable.getValueAt(i,0));
                }
            }
            for(String s: this.optionsDropDownItemList){
                this.optionsDropDown.addItem(s);
            }
        }
        //else if filtered and changed update
    }

    @Override
    public RowFilter<Object, Object> getResults() {
        ArrayList<RowFilter<Object,Object>> filters = new ArrayList<>();
        RowFilter<Object,Object> filter = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        if(this.optionsDropDown.getSelectedIndex()!= 0){
            filters.add( RowFilter.regexFilter(this.optionsDropDown.getSelectedItem()+"+",0));
        }
        if(!this.from.getText().isEmpty()){
            String date = Date.getDate(this.from);
            if(!Date.invalidDate(date)){
                try {
                    ArrayList<RowFilter<Object,Object>> fromDateFilters = new ArrayList<>();
                    fromDateFilters.add(RowFilter.dateFilter(RowFilter.ComparisonType.EQUAL,sdf.parse(date)));
                    fromDateFilters.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER,sdf.parse(date)));
                    filters.add(RowFilter.orFilter(fromDateFilters));

                }
                catch(ParseException p){}
            }

        }
        if(!this.to.getText().isEmpty()){
            String date = Date.getDate(this.to);
            if(!Date.invalidDate(date)){
                try {
                    ArrayList<RowFilter<Object,Object>> toDateFilters = new ArrayList<>();
                    toDateFilters.add(RowFilter.dateFilter(RowFilter.ComparisonType.EQUAL,sdf.parse(date)));
                    toDateFilters.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE,sdf.parse(date)));
                    filters.add(RowFilter.orFilter(toDateFilters));

                }
                catch(ParseException p){}
            }

        }
        if(filters.size() > 0){
            filter = RowFilter.andFilter(filters);
        }
        this.from.setText("");
        this.to.setText("");
        this.optionsDropDown.setSelectedIndex(0);
        return filter;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if(e.getColumn() == 0 || e.getColumn() == TableModelEvent.ALL_COLUMNS) updateDropDown();
    }

    private static class CustomComboBoxUI extends BasicComboBoxUI{
       private HashMap<String,Object>settings;
        public CustomComboBoxUI(HashMap<String,Object>settings){
            super();
            this.settings=settings;

        }
        @Override
        protected JButton createArrowButton() {
            JButton button = new BasicArrowButton(BasicArrowButton.SOUTH,
                    (Color)this.settings.get("FB_ArrowBackground"),
                    (Color)this.settings.get("FB_Border"),
                    (Color)this.settings.get("FB_ArrowForeground"),//Main part of button
                    (Color)this.settings.get("FB_Border"));
            return button;
        }
        @Override
        public void installUI( JComponent c ){
            super.installUI(c);
            listBox.setBackground((Color)this.settings.get("FB_ListBackground"));
            listBox.setForeground((Color)this.settings.get("FB_FontColor"));
            listBox.setSelectionBackground((Color)this.settings.get("FB_Background"));
            listBox.setSelectionForeground((Color)this.settings.get("FB_ListSelectedTextColor"));
        }

    }
}
