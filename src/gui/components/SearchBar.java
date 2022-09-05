package gui.components;

import gui.components.interfaces.Searchable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class SearchBar extends JTextField implements Searchable {

    private static int U_ID = 1;
    private String defaultText;

    public SearchBar(final HashMap<String,Object> settings,String defaultText){
        super();
        this.defaultText = defaultText;
        this.setBackground((Color)settings.get("SE_Background"));
        this.setFont((Font)settings.get("SE_Font"));
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
    public String[] getResults() {
        String[] result = {"<SB_T>"+(this.getText().equals("Search")?"":this.getText())};
        this.setText(this.defaultText);
        return result;
    }
}
