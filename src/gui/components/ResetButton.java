package gui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class ResetButton extends UtilityButton {
    public ResetButton(HashMap<String,Object> settings){
        super(settings);
        this.setIcon(new ImageIcon(new ImageIcon((String)settings.get("B1_ResetIcon")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH)) );
        this.setToolTipText("Reset Applied Filters");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
