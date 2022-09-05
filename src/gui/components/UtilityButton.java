package gui.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public abstract class UtilityButton extends JButton implements ActionListener {

    private HashMap<String,Object> settings;
    protected LineItemTable table = null;

    public UtilityButton(HashMap<String,Object> settings){
        super();
        this.settings = settings;
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setBackground((Color)settings.get("GB_Background"));
        this.setForeground((Color)settings.get("GB_FontColor"));
        this.setFont((Font)settings.get("GB_Font"));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                setBackground((Color)settings.get("GB_ButtonPressed"));
                grabFocus();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground((Color)settings.get("GB_Background"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                setBackground((Color)settings.get("GB_ButtonEntered"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground((Color)settings.get("GB_Background"));
            }
        });
        this.setFocusable(false);
        this.setBorder(new LineBorder((Color)settings.get("UI_Border")));
        this.setPreferredSize(new Dimension(35,35));
    }
    public void linkLineItemTable(LineItemTable theTable){
        table = theTable;
        this.addActionListener(this);
    }
}
