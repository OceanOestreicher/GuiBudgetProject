package gui.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;
/*
A Button that closes the rootFrame it is attached to.
 */
public class ExitButton extends TitleBarButton{

    public ExitButton(final HashMap<String,Object> settings, final JFrame rootFrame){
        super(settings,rootFrame);
        setText("X");
        setName("ExitApplicationButton");
        setMouseStateColor(GeneralButton.MOUSE_PRESSED,"TB_ExitButtonPressed");
        setMouseStateColor(GeneralButton.MOUSE_ENTERED,"TB_ExitButtonEntered");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        rootFrame.dispatchEvent(new WindowEvent(rootFrame,WindowEvent.WINDOW_CLOSING));
    }
}
