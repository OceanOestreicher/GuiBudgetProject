package gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
/*
Minimizes the attached rootFrame
 */
public class MinimizeButton extends TitleBarButton{

    public MinimizeButton(final HashMap<String,Object> settings, final JFrame rootFrame){
        super(settings,rootFrame);
        setText("---");
        setName("MinimizeApplicationButton");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rootFrame.setState(Frame.ICONIFIED);
    }
}

