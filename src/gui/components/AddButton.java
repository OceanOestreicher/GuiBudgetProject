package gui.components;

import gui.form.AddLineItemForm;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class AddButton extends TableButton {

    public AddButton(HashMap<String,Object> settings, LineItemTable itemList){
        super(settings,itemList);
        this.setText("+");
        this.setToolTipText("Add A New Entry");
        this.setBorder(new LineBorder((Color)settings.get("UI_Border")));
        this.setPreferredSize(new Dimension(35,35));
        this.setFont((Font)settings.get("B1_Font"));
    }
    public void linkForm(){
        AddLineItemForm.getInstance(this.settings,this.itemList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this)){
            linkForm();
        }
        else{
            //link form
        }

    }
}
