package gui.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class ExitButton extends TitleBarButton{

    public ExitButton(HashMap<String,Object> settings, JFrame rootFrame){
        super(settings,rootFrame);
        setText("X");
        setName("ExitApplicationButton");
        this.setMouseStateColor(GeneralButton.MOUSE_PRESSED,"TB_ExitButtonPressed");
        this.setMouseStateColor(GeneralButton.MOUSE_ENTERED,"TB_ExitButtonEntered");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.rootFrame.dispatchEvent(new WindowEvent(rootFrame,WindowEvent.WINDOW_CLOSING));
    }
}
