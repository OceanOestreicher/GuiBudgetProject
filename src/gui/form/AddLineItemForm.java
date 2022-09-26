package gui.form;

import gui.components.ExitButton;
import gui.components.FormSubmitButton;
import gui.components.GeneralButton;
import gui.components.LineItemTable;
import gui.components.interfaces.Form;
import gui.utils.DateUtils;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
/*
Singleton form which allows a user to add a new row to a LineItemTable
 */
public class AddLineItemForm extends JFrame implements Form {
    private static AddLineItemForm instance = null;
    private final JTextField[] formFields;
    private AddLineItemForm(final HashMap<String,Object> settings, final LineItemTable itemList){
        JPanel formSurface = new JPanel();
        JPanel headerSurface = new JPanel();
        JPanel headerButtonSurface = new JPanel();
        JPanel entrySurface = new JPanel();
        JPanel submitSurface = new JPanel();

        formSurface.setBackground((Color)settings.get("IF_Background"));
        formSurface.setLayout(new BorderLayout());
        headerSurface.setBackground((Color)settings.get("IF_HeaderBackground"));
        headerSurface.setLayout(new BorderLayout());
        headerButtonSurface.setBackground((Color)settings.get("IF_HeaderBackground"));
        headerButtonSurface.setLayout(new GridLayout(1,1));
        headerSurface.add(headerButtonSurface,BorderLayout.EAST);
        entrySurface.setBackground((Color)settings.get("IF_Background"));
        entrySurface.setForeground((Color)settings.get("IF_FontColor"));
        submitSurface.setBackground((Color)settings.get("IF_Background"));
        submitSurface.setLayout(new FlowLayout(FlowLayout.RIGHT));

        ExitButton exit = new ExitButton(settings,this);
        exit.setBackground((Color)settings.get("IF_HeaderBackground"));
        exit.setMouseStateColor(GeneralButton.MOUSE_EXITED,"IF_HeaderBackground");
        exit.setMouseStateColor(GeneralButton.MOUSE_RELEASED,"IF_HeaderBackground");
        headerButtonSurface.add(exit);

        formFields = new JTextField[itemList.getColumnCount()];
        for(int i = 0; i < itemList.getColumnCount();i++){
            JLabel fieldName = new JLabel(itemList.getColumnName(i)+":");
            fieldName.setForeground((Color)settings.get("IF_FontColor"));
            if(fieldName.getText().equals("Amount:"))fieldName.setText(fieldName.getText()+" $");
            entrySurface.add(fieldName);

            JTextField entry = new JTextField();
            entry.setPreferredSize(new Dimension(100,30));
            entry.setName(itemList.getColumnName(i));
            entry.setBackground((Color)settings.get("IF_EntryBackground"));
            entry.setForeground((Color)settings.get("IF_FontColor"));
            entry.setCaretColor((Color)settings.get("IF_FontColor"));
            entry.setCursor((Cursor)settings.get("UI_TextCursor"));
            entry.setBorder(new EmptyBorder(0,0,0,0));
            formFields[i] = entry;
            entrySurface.add(entry);
        }

        FormSubmitButton submit = new FormSubmitButton(settings,itemList,this);
        submitSurface.add(submit);

        formSurface.add(headerSurface,BorderLayout.NORTH);
        formSurface.add(entrySurface, BorderLayout.CENTER);
        formSurface.add(submitSurface, BorderLayout.SOUTH);
        formSurface.setBorder(new LineBorder((Color)settings.get("IF_FrameBorder"),2));

        this.setUndecorated(true);//Removes the default windows minimize,close options
        this.getContentPane().add(formSurface);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage((Image)settings.get("UI_Icon"));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });
        setTitle((String) settings.get("UI_TitleString"));
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }
    /*
    Returns a formatted string of all data in the entry fields separated by <>. Blank entries
    are represented by <N/A>

     Example: Ocean<>12-31-22<><N/A><>$1400<><N/A>
     */
    @Override
    public String getFormData() {
        String result = "";
        for(JTextField entry: formFields){
            if(entry.getText().isBlank() || entry.getText().isEmpty())result += "<N/A><>";
            else if(entry.getName().equals("Date"))result += DateUtils.getDate(entry)+"<>";
            else if(entry.getName().equals("Amount")){
                double value = Double.parseDouble(entry.getText());
                result += "$";
                if( value < 0)result += "("+entry.getText().substring(1) + ")<>";
                else result += entry.getText() + "<>";
            }
            else result += entry.getText() + "<>";
        }
        System.out.println(result);
        return result;
    }
    /*
    Validates Date and Amount entries
     */
    @Override
    public boolean hasValidFormData() {
        boolean valid = true;
        for(JTextField entry: formFields){
            if(entry.getName().equals("Date") && DateUtils.invalidDate(entry.getText())){
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

    public static void getInstance(final HashMap<String,Object> settings, final LineItemTable itemList){
        if(instance == null){
            instance = new AddLineItemForm(settings,itemList);
        }
        else{
            Toolkit.getDefaultToolkit().beep();
            instance.requestFocus();
        }
    }
}
