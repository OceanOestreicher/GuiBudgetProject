package gui.frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class TitleBarButton extends JButton {

    public TitleBarButton(HashMap<String,Object> settings, ActionListener rootFrame, String displayName,String name, Color mousePressed,Color mouseEntered){
        super();
        setText(displayName);
        setFont((Font)settings.get("TB_ButtonFont"));
        setName(name);
        addActionListener(rootFrame);
        setBackground((Color)settings.get("UI_Background"));
        setForeground((Color)settings.get("UI_Foreground"));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(mousePressed);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(mouseEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground((Color)settings.get("UI_Background"));
            }
        });
        setBorder(new EmptyBorder(1,20,1,20));
    }
}
