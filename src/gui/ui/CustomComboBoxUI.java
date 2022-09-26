package gui.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.HashMap;
/*
Renders combo boxes with the correct colors for the
application
 */
public class CustomComboBoxUI extends BasicComboBoxUI {
    private final HashMap<String,Object> settings;
    public CustomComboBoxUI(HashMap<String,Object>settings){
        super();
        this.settings=settings;
    }
    @Override
    protected JButton createArrowButton() {
        return new BasicArrowButton(BasicArrowButton.SOUTH,
                (Color) this.settings.get("FB_ArrowBackground"),
                (Color) this.settings.get("FB_Border"),
                (Color) this.settings.get("FB_ArrowForeground"),//Main part of button
                (Color) this.settings.get("FB_Border"));
    }
    @Override
    public void installUI( JComponent c ){
        super.installUI(c);
        listBox.setBackground((Color)this.settings.get("FB_ListBackground"));
        listBox.setForeground((Color)this.settings.get("FB_FontColor"));
        listBox.setSelectionBackground((Color)this.settings.get("FB_Background"));
        listBox.setSelectionForeground((Color)this.settings.get("FB_ListSelectedTextColor"));
    }
}
