package gui.components;

import gui.components.interfaces.Searchable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchButton extends JButton {
    private static int U_ID = 1;
    private List<Searchable> searchableList;

    public SearchButton(HashMap<String,Object> settings){
        super("Search");
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.searchableList = new ArrayList<>();
        this.setBackground((Color)settings.get("SB_Background"));
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setForeground((Color)settings.get("SB_FontColor"));
        this.setPreferredSize(new Dimension(100,30));
        //Prevents a border from appearing around the button text
        this.setFocusable(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground((Color)settings.get("SB_ButtonPressed"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground((Color)settings.get("SB_Background"));

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground((Color)settings.get("SB_ButtonEntered"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //super.mouseExited(e);
                setBackground((Color)settings.get("SB_Background"));
            }
        });
        this.setName("SearchButton_"+U_ID);
        U_ID++;
    }
    public void addSearchableComponent(Searchable c){
        this.searchableList.add(c);
    }
    public void addSearchableComponents(Searchable[] c){
        for(Searchable s: c) this.searchableList.add(s);
    }
    //Process results instead?
    public List<String> returnResults(){
        ArrayList<String> result = new ArrayList<>();
        for(Searchable i: this.searchableList){
            for(String s: i.getResults())result.add(s);
        }
        return result;
    }

}
