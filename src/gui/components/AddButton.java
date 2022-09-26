package gui.components;

import gui.form.AddLineItemForm;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
/*
  Lets a user add a new line item to an existing LineItemTable through the use of the
  AddLineItemForm
 */
public class AddButton extends TableButton {

    public AddButton(final HashMap<String,Object> settings, final LineItemTable itemList){
        super(settings,itemList);
        setText("+");
        setToolTipText("Add A New Entry");
        setBorder(new LineBorder((Color)settings.get("UI_Border")));
        setPreferredSize(new Dimension(35,35));
        setFont((Font)settings.get("B1_Font"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddLineItemForm.getInstance(this.settings,this.itemList);
    }
}
