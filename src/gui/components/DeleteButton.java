package gui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class DeleteButton extends UtilityButton implements ActionListener {
    public DeleteButton(HashMap<String,Object> settings) {
        super(settings);
        this.setIcon(new ImageIcon(new ImageIcon((String) settings.get("B1_DeleteIcon")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        this.setToolTipText("Delete Selected Entry/Entries");

    }
    public void removeSelected(){
        if(table != null && table.isEditing()){
            table.getCellEditor().stopCellEditing();
        }
        if(table != null && table.getSelectedRows().length > 0){
            int[] selected = table.getSelectedRows();
            KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();
            for(int i = selected.length-1;i >= 0;i--){
                ((DefaultTableModel)table.getModel()).removeRow(selected[i]);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        removeSelected();
    }
}
