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

public class SearchButton extends TableButton {
    private JLabel filterIcon;
    private List<Searchable> searchableList;

    public SearchButton(HashMap<String,Object> settings, LineItemTable itemList,JLabel filterIcon){
        super(settings,itemList);
        this.setText("Search");
        this.searchableList = new ArrayList<>();
        this.setPreferredSize(new Dimension(100,30));
        this.setBackground((Color)settings.get("SB_Background"));
        this.setBorder(new EmptyBorder(0,0,0,0));
        this.setForeground((Color)settings.get("SB_FontColor"));
        this.setMouseStateColor(GeneralButton.MOUSE_PRESSED,"SB_ButtonPressed");
        this.setMouseStateColor(GeneralButton.MOUSE_RELEASED,"SB_Background");
        this.setMouseStateColor(GeneralButton.MOUSE_ENTERED,"SB_ButtonEntered");
        this.setMouseStateColor(GeneralButton.MOUSE_EXITED,"SB_Background");
        this.filterIcon = filterIcon;
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

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(returnResults());
        //True only if table was filtered?
        filterIcon.setVisible(true);
    }
}
