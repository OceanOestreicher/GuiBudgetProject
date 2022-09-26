package gui.components;

import gui.components.interfaces.Form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;
/*
A button that adds form data from a form to a linked LineItemTable
 */
public class FormSubmitButton extends TableButton{
    private final Form form;
    public FormSubmitButton(final HashMap<String,Object> settings, final LineItemTable itemList, Form newForm){
        super(settings,itemList);
        setBackground((Color)settings.get("B1_SubmitBackground"));
        setPreferredSize(new Dimension(100,30));
        setBorder(new EmptyBorder(0,0,0,0));
        setText("Submit");
        setName("SubmitFormButton");
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
