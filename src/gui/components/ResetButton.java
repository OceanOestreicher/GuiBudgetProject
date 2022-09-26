package gui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
/*
Resets all applied row filters on a LineItemTable
 */
public class ResetButton extends TableButton {
    private final JLabel filterIcon;
    public ResetButton(final HashMap<String,Object> settings, final LineItemTable itemList,final JLabel filterIcon){
        super(settings,itemList);
        this.setIcon(new ImageIcon(new ImageIcon((String)settings.get("B1_ResetIcon")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)) );
        this.setToolTipText("Reset Applied Filters");
        this.setBorder(new LineBorder((Color)settings.get("UI_Border")));
        this.filterIcon = filterIcon;
        this.setPreferredSize(new Dimension(35,35));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        filterIcon.setVisible(false);
        TableRowSorter<DefaultTableModel> trs = (TableRowSorter<DefaultTableModel>) itemList.getRowSorter();
        trs.setRowFilter(null);
    }
}
