package gui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class ResetButton extends TableButton {
    public ResetButton(HashMap<String,Object> settings, LineItemTable itemList){
        super(settings,itemList);
        this.setIcon(new ImageIcon(new ImageIcon((String)settings.get("B1_ResetIcon")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)) );
        this.setToolTipText("Reset Applied Filters");
        this.setBorder(new LineBorder((Color)settings.get("UI_Border")));
        this.setPreferredSize(new Dimension(35,35));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
