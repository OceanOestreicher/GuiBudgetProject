package gui.components;

import gui.components.interfaces.Searchable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
/*
A component that allows a user to type in text that will be searched in a JTable
 */
public class SearchBar extends JTextField implements Searchable {

    private static int U_ID = 1;
    private final String defaultText;

    public SearchBar(final HashMap<String,Object> settings,final String defaultText){
        super();
        this.defaultText = defaultText;
        this.setBackground((Color)settings.get("SE_Background"));
        this.setFont((Font)settings.get("SE_Font"));
        this.setCursor((Cursor)settings.get("UI_TextCursor"));
        this.setForeground((Color)settings.get("SE_FontColor"));
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setPreferredSize(new Dimension(300,25));
        //Changes cursor color
        this.setCaretColor((Color)settings.get("SE_FontColor"));
        this.setName("EntryField_"+U_ID);
        this.setText(defaultText);
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(defaultText))setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().equals(""))setText(defaultText);
            }
        });
        U_ID++;
    }
    @Override
    public RowFilter<Object, Object> getResults() {
        RowFilter<Object,Object>filter = null;
        if(!this.getText().equals(this.defaultText)&&!this.getText().isEmpty()){
            filter = RowFilter.regexFilter("(?i)"+this.getText()+"+", 0, 1, 2, 3, 4);//(?i) case insensitive flag
        }
        this.setText(this.defaultText);
        return filter;
    }

}
