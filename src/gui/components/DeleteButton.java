package gui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
/*
    A Button that deletes selected rows from a LineItemTable
 */
public class DeleteButton extends TableButton {
    public DeleteButton(final HashMap<String,Object> settings, final LineItemTable itemList) {
        super(settings,itemList);
        this.setIcon((ImageIcon)settings.get("B1_DeleteIcon"));
        this.setToolTipText("Delete Selected Entry/Entries");
        this.setBorder(new LineBorder((Color)settings.get("UI_Border")));
        this.setPreferredSize(new Dimension(35,35));
    }
    public void removeSelected(){
        if(this.itemList != null && this.itemList.isEditing()){
            this.itemList.getCellEditor().stopCellEditing();
        }
        if(this.itemList != null && this.itemList.getSelectedRows().length > 0){
            int[] selected = this.itemList.getSelectedRows();
            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();
            for(int i = selected.length-1;i >= 0;i--){
                ((DefaultTableModel)this.itemList.getModel()).removeRow(this.itemList.convertRowIndexToModel(selected[i]));
            }

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        removeSelected();
    }
}
