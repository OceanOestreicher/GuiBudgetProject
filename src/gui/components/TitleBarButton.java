package gui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public abstract class TitleBarButton extends GeneralButton {
    protected JFrame rootFrame;
    public TitleBarButton(HashMap<String,Object> settings, JFrame rootFrame){
        super(settings);
        this.rootFrame = rootFrame;
        setFont((Font)settings.get("TB_ButtonFont"));
        setBackground((Color)settings.get("UI_Background"));
        setForeground((Color)settings.get("UI_Foreground"));
        this.setMouseStateColor(GeneralButton.MOUSE_PRESSED,"TB_MousePressed");
        this.setMouseStateColor(GeneralButton.MOUSE_RELEASED,"UI_Background");
        this.setMouseStateColor(GeneralButton.MOUSE_ENTERED,"TB_MouseEntered");
        this.setMouseStateColor(GeneralButton.MOUSE_EXITED,"UI_Background");
        setBorder(new EmptyBorder(1,20,1,20));
    }

}
