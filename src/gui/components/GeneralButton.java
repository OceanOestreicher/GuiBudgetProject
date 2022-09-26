package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
/*
Represents any button in the application. Provides general purpose
utility for default behaviors such as mouse events or default looks
 */
public abstract class GeneralButton extends JButton implements ActionListener {
    public static final int MOUSE_PRESSED = 1;
    public static final int MOUSE_RELEASED = 2;
    public static final int MOUSE_ENTERED = 3;
    public static final int MOUSE_EXITED= 4;
    protected final HashMap<String,Object> settings;
    private Color mousePressed = null, mouseReleased=null, mouseEntered=null, mouseExited=null;

    public GeneralButton(final HashMap<String,Object> settings){
        super();
        this.settings = settings;
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setBackground((Color)settings.get("GB_Background"));
        this.setForeground((Color)settings.get("GB_FontColor"));
        this.setFont((Font)settings.get("UI_DefaultFont"));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                setBackground(mousePressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(mouseReleased);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                setBackground(mouseEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(mouseExited);
            }
        });
        this.setFocusable(false);
        this.addActionListener(this);
    }
    public void setMouseStateColor(int mouseState,String settingsKey){
        switch (mouseState) {
            case 1 -> this.mousePressed = (Color) this.settings.get(settingsKey);
            case 2 -> this.mouseReleased = (Color) this.settings.get(settingsKey);
            case 3 -> this.mouseEntered = (Color) this.settings.get(settingsKey);
            case 4 -> this.mouseExited = (Color) this.settings.get(settingsKey);
            default -> throw new IllegalArgumentException("Invalid Mouse State Parameter");
        }

    }
}
