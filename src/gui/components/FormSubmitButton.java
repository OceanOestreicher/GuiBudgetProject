package gui.components;

import gui.components.interfaces.Form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class FormSubmitButton extends TableButton{
    private Form form;
    public FormSubmitButton(HashMap<String,Object> settings, LineItemTable itemList, Form newForm){
        super(settings,itemList);
        this.setBackground((Color)settings.get("B1_SubmitBackground"));
        this.setPreferredSize(new Dimension(100,30));
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setText("Submit");
        this.setName("SubmitFormButton");
        form = newForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(form.hasValidFormData()){
            this.itemList.addEntry(form.getFormData());
            if(form instanceof JFrame){
                ((JFrame) form).dispatchEvent(new WindowEvent((JFrame)form, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}
