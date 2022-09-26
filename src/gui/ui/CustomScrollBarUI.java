package gui.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.HashMap;
/*
Renders scroll bars with the correct colors for the
application. Removes the buttons from scroll bars
 */
public class CustomScrollBarUI extends BasicScrollBarUI {

    private final HashMap<String,Object> settings;

    public CustomScrollBarUI(HashMap<String,Object>settings){
        super();
        this.settings = settings;
    }
    @Override
    public void installUI(JComponent c){
        super.installUI(c);
        scrollbar.setBorder(new LineBorder(Color.DARK_GRAY));
        trackColor = (Color)this.settings.get("SC_Background");
        thumbColor = (Color)this.settings.get("SC_Thumb");
        thumbDarkShadowColor = (Color)this.settings.get("SC_Thumb");
        thumbHighlightColor = (Color)this.settings.get("SC_Thumb");
        thumbLightShadowColor = (Color)this.settings.get("SC_Thumb");
    }
    @Override
    protected JButton createDecreaseButton(int orientation){
        JButton emptyButton = new JButton();
        emptyButton.setPreferredSize(new Dimension(0,0));
        return emptyButton;
    }
    @Override
    protected JButton createIncreaseButton(int orientation){
        return createDecreaseButton(orientation);
    }
}
