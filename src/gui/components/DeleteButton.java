package gui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class DeleteButton extends TableButton implements ActionListener {
    public DeleteButton(HashMap<String,Object> settings, LineItemTable itemList) {
        super(settings,itemList);
        this.setIcon(new ImageIcon(new ImageIcon((String) settings.get("B1_DeleteIcon")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
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
