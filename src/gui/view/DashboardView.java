package gui.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
/*
Generic class for each of the different dashboard views. A view represents a module
within the application and each view is responsible for its configuration and functionality
 */
public abstract class DashboardView extends JPanel {
    protected final String name;
    protected final HashMap<String, Object> settings;
    public DashboardView(final String name,final HashMap<String, Object> settings){
        this.name = name;
        this.settings = settings;
    }

    public String getDisplayName(){return this.name;}
    public abstract void updateView();
    public abstract DashboardView createView();
}
