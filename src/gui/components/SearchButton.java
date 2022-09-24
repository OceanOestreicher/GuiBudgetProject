package gui.components;

import gui.components.interfaces.Searchable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchButton extends TableButton {
    private JLabel filterIcon;
    private List<Searchable> searchableList;

    public SearchButton(HashMap<String,Object> settings, LineItemTable itemList,JLabel filterIcon){
        super(settings,itemList);
        this.setText("Search");
        this.searchableList = new ArrayList<>();
        this.setPreferredSize(new Dimension(100,30));
        this.setBackground((Color)settings.get("SB_Background"));
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setForeground((Color)settings.get("SB_FontColor"));
        this.setMouseStateColor(GeneralButton.MOUSE_PRESSED,"SB_ButtonPressed");
        this.setMouseStateColor(GeneralButton.MOUSE_RELEASED,"SB_Background");
        this.setMouseStateColor(GeneralButton.MOUSE_ENTERED,"SB_ButtonEntered");
        this.setMouseStateColor(GeneralButton.MOUSE_EXITED,"SB_Background");
        this.filterIcon = filterIcon;
    }
    public void addSearchableComponent(Searchable c){
        this.searchableList.add(c);
    }
    public void addSearchableComponents(Searchable[] c){
        for(Searchable s: c) this.searchableList.add(s);
    }
    //Process results instead?
    @Override
    public void actionPerformed(ActionEvent e) {
        //True only if table was filtered?
        if(itemList.getRowCount()>0){
            ArrayList<RowFilter<Object,Object>> filters = new ArrayList<>();
            for(Searchable s: searchableList){
                RowFilter<Object,Object> result = s.getResults();
                if(result != null) filters.add(result);
            }
            if(filters.size() > 0){
                filterIcon.setVisible(true);
                TableRowSorter<DefaultTableModel> trs = (TableRowSorter<DefaultTableModel>) itemList.getRowSorter();
                trs.setRowFilter(RowFilter.andFilter(filters));
            }

        }
    }
}
