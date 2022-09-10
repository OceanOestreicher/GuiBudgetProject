package gui.components;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public abstract class TableButton extends GeneralButton  {
    protected LineItemTable itemList;

    public TableButton(HashMap<String,Object> settings, LineItemTable itemList){
        super(settings);
        this.itemList = itemList;
        this.setMouseStateColor(GeneralButton.MOUSE_PRESSED,"GB_ButtonPressed");
        this.setMouseStateColor(GeneralButton.MOUSE_RELEASED,"GB_Background");
        this.setMouseStateColor(GeneralButton.MOUSE_ENTERED,"GB_ButtonEntered");
        this.setMouseStateColor(GeneralButton.MOUSE_EXITED,"GB_Background");

    }
}
