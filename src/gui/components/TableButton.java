package gui.components;

import java.util.HashMap;
/*
General button to represent any button that interacts with a
LineItemTable
 */
public abstract class TableButton extends GeneralButton  {
    protected LineItemTable itemList;

    public TableButton(final HashMap<String,Object> settings, final LineItemTable itemList){
        super(settings);
        this.itemList = itemList;
        this.setMouseStateColor(GeneralButton.MOUSE_PRESSED,"GB_ButtonPressed");
        this.setMouseStateColor(GeneralButton.MOUSE_RELEASED,"GB_Background");
        this.setMouseStateColor(GeneralButton.MOUSE_ENTERED,"GB_ButtonEntered");
        this.setMouseStateColor(GeneralButton.MOUSE_EXITED,"GB_Background");

    }
}
