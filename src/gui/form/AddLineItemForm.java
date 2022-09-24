package gui.form;

import gui.components.ExitButton;
import gui.components.FormSubmitButton;
import gui.components.GeneralButton;
import gui.components.LineItemTable;
import gui.components.interfaces.Form;
import gui.utils.Date;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class AddLineItemForm extends JFrame implements Form {
    private static AddLineItemForm instance = null;
    private LineItemTable itemList;
    private JTextField[] formFields;
    private AddLineItemForm(HashMap<String,Object> settings, LineItemTable itemList){
        JPanel formSurface = new JPanel();
        JPanel headerSurface = new JPanel();
        JPanel entrySurface = new JPanel();
        JPanel submitSurface = new JPanel();
        formSurface.setBackground((Color)settings.get("IF_Background"));
        formSurface.setLayout(new BorderLayout());
        headerSurface.setBackground((Color)settings.get("IF_HeaderBackground"));
        headerSurface.setLayout(new FlowLayout(FlowLayout.RIGHT));
        entrySurface.setBackground((Color)settings.get("IF_Background"));
        entrySurface.setForeground((Color)settings.get("IF_FontColor"));
        submitSurface.setBackground((Color)settings.get("IF_Background"));
        submitSurface.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ExitButton exit = new ExitButton(settings,this);
        exit.setBackground((Color)settings.get("IF_HeaderBackground"));
        exit.setMouseStateColor(GeneralButton.MOUSE_EXITED,"IF_HeaderBackground");
        exit.setMouseStateColor(GeneralButton.MOUSE_RELEASED,"IF_HeaderBackground");
        FormSubmitButton submit = new FormSubmitButton(settings,itemList,this);
        headerSurface.add(exit);

        formFields = new JTextField[itemList.getColumnCount()];
        for(int i = 0; i < itemList.getColumnCount();i++){
            JLabel fieldName = new JLabel(itemList.getColumnName(i)+":");
            if(fieldName.getText().equals("Amount:"))fieldName.setText(fieldName.getText()+" $");
            fieldName.setForeground((Color)settings.get("IF_FontColor"));
            entrySurface.add(fieldName);
            JTextField entry = new JTextField();
            entry.setPreferredSize(new Dimension(100,30));
            entry.setName(itemList.getColumnName(i));
            entry.setBackground((Color)settings.get("IF_EntryBackground"));
            entry.setForeground((Color)settings.get("IF_FontColor"));
            entry.setCaretColor((Color)settings.get("IF_FontColor"));
            entry.setBorder(new EmptyBorder(0,0,0,0));
            formFields[i] = entry;
            entrySurface.add(entry);
        }
        submitSurface.add(submit);
        formSurface.add(headerSurface,BorderLayout.NORTH);
        formSurface.add(entrySurface, BorderLayout.CENTER);
        formSurface.add(submitSurface, BorderLayout.SOUTH);
        formSurface.setBorder(new LineBorder((Color)settings.get("IF_FrameBorder"),2));
        this.itemList = itemList;
        this.setUndecorated(true);//Removes the default windows minimize,close options
        this.getContentPane().add(formSurface);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon((String)settings.get("UI_Icon")).getImage());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);

    }

    @Override
    public String getFormData() {
        String result = "";
        for(JTextField entry: formFields){
            if(entry.getText().isBlank() || entry.getText().isEmpty())result += "<N/A><>";
            else if(entry.getName().equals("Date"))result += Date.getDate(entry)+"<>";
            else if(entry.getName().equals("Amount"))result += "$"+entry.getText() + "<>";
            else result += entry.getText() + "<>";
        }
        System.out.println(result);
        return result;
    }
    @Override
    public boolean hasValidFormData() {
        boolean valid = true;
        for(JTextField entry: formFields){
            if(entry.getName().equals("Date") &&!(entry.getText().isBlank()) && Date.invalidDate(entry.getText())){
                entry.setBorder(new LineBorder(Color.RED));
                valid= false;
            }
            else if (entry.getName().equals("Date")){
                entry.setBorder(new EmptyBorder(0,0,0,0));
            }
            if(entry.getName().equals("Amount")&&!(entry.getText().isBlank())){
                try{Double.parseDouble(entry.getText());}
                catch (Exception e){
                    entry.setBorder(new LineBorder(Color.RED));
                    valid = false;
                }
            }
            else if (entry.getName().equals("Amount")){
                entry.setBorder(new EmptyBorder(0,0,0,0));
            }
        }
        if(!valid)Toolkit.getDefaultToolkit().beep();
        return valid;
    }


    public static AddLineItemForm getInstance(HashMap<String,Object> settings, LineItemTable itemList){
        if(instance == null){
            instance = new AddLineItemForm(settings,itemList);
            return instance;
        }
        else{
            Toolkit.getDefaultToolkit().beep();
            instance.requestFocus();
            return null;
        }
    }
}
