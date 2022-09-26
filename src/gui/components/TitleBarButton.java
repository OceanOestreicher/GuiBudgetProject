package gui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
/*
General button to represent any button that interacts with a JFrame
 */
public abstract class TitleBarButton extends GeneralButton {
    protected JFrame rootFrame;
    public TitleBarButton(final HashMap<String,Object> settings, final JFrame rootFrame){
        super(settings);
        this.rootFrame = rootFrame;
        setFont((Font)settings.get("TB_ButtonFont"));
        setBackground((Color)settings.get("UI_Background"));
        setForeground((Color)settings.get("UI_Foreground"));
        setMouseStateColor(GeneralButton.MOUSE_PRESSED,"TB_MousePressed");
        setMouseStateColor(GeneralButton.MOUSE_RELEASED,"UI_Background");
        setMouseStateColor(GeneralButton.MOUSE_ENTERED,"TB_MouseEntered");
        setMouseStateColor(GeneralButton.MOUSE_EXITED,"UI_Background");
        setBorder(new EmptyBorder(1,20,1,20));
    }

}
