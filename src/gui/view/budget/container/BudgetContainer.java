package gui.view.budget.container;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class BudgetContainer extends JPanel implements ActionListener {
    protected HashMap<String,Object>settings;
    public BudgetContainer(HashMap<String,Object> settings){
        this.settings = settings;
    }
}
