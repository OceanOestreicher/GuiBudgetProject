package gui.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Vector;

public class LineItemTable extends JTable {

    private HashMap<String,Object> settings;

    public LineItemTable(HashMap<String,Object> settings,String filterColumnName,TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm,cm,sm);
        this.settings = settings;

        setModel(new DefaultTableModel(new Object[][]{{"Bill","1-1-1900","Avista","$1400","Test Note"},{"Extra","1-1-1902","Cable","$1400","Test Note2"},{"Extra","1-1-1902","Broken","$1500","Test Note3"}},new Object[]{filterColumnName,"Date","Name","Amount","Note"}));
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
        setAutoCreateRowSorter(true);

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
