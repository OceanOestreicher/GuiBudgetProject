package gui.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LineItemTable extends JTable {

    private HashMap<String,Object> settings;

    public LineItemTable(HashMap<String,Object> settings,String filterColumnName,TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm,cm,sm);
        this.settings = settings;

        setModel(new DefaultTableModel(new Object[]{filterColumnName,"Date","Name","Amount","Note"},0));
        setGridColor((Color)settings.get("UI_Background"));
        setFont((Font)settings.get("IL_Font"));
        setDefaultRenderer(Object.class, new CustomTableCellRenderer(settings));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
               JTable tab =  (JTable)e.getSource();
               tab.clearSelection();
            }
        });
        getTableHeader().setDefaultRenderer(new CustomTableCellHeaderRenderer(settings));
        getTableHeader().setReorderingAllowed(false);
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>((DefaultTableModel) getModel());
        trs.setComparator(1, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });
        trs.setSortsOnUpdates(true);
        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        trs.setSortKeys(sortKeys);
        trs.setSortable(0,false);
        trs.setSortable(2,false);
        trs.setSortable(3,false);
        trs.setSortable(4,false);
        setRowSorter(trs);
    }

    public LineItemTable(HashMap<String,Object> settings,String filterColumnName){
        this(settings,filterColumnName,null,null,null);
    }
    public LineItemTable(HashMap<String,Object> settings,String filterColumnName,TableModel dm){
        this(settings,filterColumnName,dm,null,null);
    }
    public LineItemTable(HashMap<String,Object> settings,String filterColumnName,TableModel dm, TableColumnModel cm) {
        this(settings,filterColumnName,dm,cm,null);
    }
    public LineItemTable(HashMap<String,Object> settings,String filterColumnName,int numRows, int numColumns) {
        this(settings,filterColumnName,new DefaultTableModel(numRows, numColumns));
    }
    public LineItemTable(HashMap<String,Object> settings,String filterColumnName,Vector<? extends Vector> rowData, Vector<?> columnNames) {
        this(settings,filterColumnName,new DefaultTableModel(rowData, columnNames));
    }
    public LineItemTable(HashMap<String,Object> settings,String filterColumnName,final Object[][] rowData, final Object[] columnNames){
        this(settings,filterColumnName,new DefaultTableModel(rowData,columnNames));
    }

    public void addEntry(String dataToAdd){
        String[] newRow = dataToAdd.split("<>");
        if(newRow.length > getColumnCount())throw new InputMismatchException("Invalid row data entered");
        Object[] rowData = new Object[getColumnCount()];
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        for(int i = 0; i < newRow.length;i++){
            if(i == 1 && !newRow[i].equals("<N/A>")){
                try{rowData[i] = sdf.parse(newRow[i]);}
                catch(ParseException p){}
            }
            else if(i==1){
                rowData[i] = null;
            }
            else if( newRow[i].equals("<N/A>")){
                rowData[i] = "";
            }
            else rowData[i] = newRow[i];
        }
        ((DefaultTableModel)getModel()).addRow(rowData);
    }
    public boolean isCellEditable(int row, int column){
        if(column == 1) return false;
        return true;
    }
    //Private Classes
    private static class CustomTableCellRenderer extends DefaultTableCellRenderer {
        private HashMap<String,Object> settings;

        public CustomTableCellRenderer(HashMap<String,Object> settings){
            super();
            this.settings=settings;
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column){
            JComponent c = (JComponent) super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
            if(hasFocus){

                Border border = null;
                if (isSelected) {
                    border = new CompoundBorder( new LineBorder((Color)this.settings.get("IL_SelectedCell")), new EmptyBorder(5,5,5,5));
                }
                if (border == null) {
                    border = new CompoundBorder( new LineBorder((Color)this.settings.get("IL_UnselectedCell")), new EmptyBorder(5,5,5,5));
                }
                c.setBorder(border);
            }
            else{
                c.setBorder(new CompoundBorder( new EmptyBorder(0,0,0,0), new EmptyBorder(5,5,5,5)));
            }
            if(isSelected){
                c.setBackground((Color)settings.get("IL_SelectedRowBackground"));
                c.setForeground((Color)settings.get("IL_SelectedRowForeground"));
            }
            else{
                c.setBackground((Color)settings.get("IL_UnselectedRow"));
                c.setForeground((Color)settings.get("IL_FontColor"));
            }

            if(column == 1 && table.getValueAt(row,column) != null){
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                Date date = (Date)table.getValueAt(row,column);
                JLabel dateCell = (JLabel)c;
                dateCell.setText(sdf.format(date));
            }
            return c;
        }
    }
    private static class CustomTableCellHeaderRenderer extends DefaultTableCellRenderer{
        private HashMap<String,Object> settings;

        public CustomTableCellHeaderRenderer(HashMap<String,Object>settings){
            super();
            this.settings=settings;
            this.setHorizontalAlignment(JLabel.CENTER);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column){
            JComponent c =(JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground((Color)this.settings.get("IL_HeaderRowBackground"));
            c.setForeground((Color)this.settings.get("IL_HeaderFontColor"));
            c.setBorder(new CompoundBorder( new LineBorder((Color)this.settings.get("IL_UnselectedCell")), new EmptyBorder(5,5,5,5)));
            return c;
        }
    }
}
