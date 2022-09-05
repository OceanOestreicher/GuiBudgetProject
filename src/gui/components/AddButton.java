package gui.components;

import java.awt.event.ActionEvent;
import java.util.HashMap;

public class AddButton extends UtilityButton {
    public AddButton(HashMap<String,Object> settings){
        super(settings);
        this.setText("+");
        this.setToolTipText("Add A New Entry");
        this.addActionListener(this);
    }
    public void linkForm(){
        System.out.println("Equals");
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
