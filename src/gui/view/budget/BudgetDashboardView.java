package gui.view.budget;

import gui.view.DashboardView;
import gui.view.budget.container.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
/*
Creates the Budget view for the application which handles different aspects of a budget

Features

Implemented: Income and Expense Tracking
TO-DO: Budget management, Savings Management, Budget Graph, Widget Container

Notes
updateView may be redundant or used once the other components are implemented
 */
public class BudgetDashboardView extends DashboardView {

    public BudgetDashboardView(final HashMap<String, Object> settings){
        super("Budget",settings);
    }

    @Override
    public void updateView() {

    }
    @Override
    public DashboardView createView() {
        //Zero  rows/cols means as many rows/cols as needed
        this.setLayout(new GridLayout(0,3));
        this.setPreferredSize((Dimension)this.settings.get("UI_Dimensions"));
        TrackingContainer Income = new TrackingContainer(this.settings, "Source","Search Income");
        Income.setBackground((Color)this.settings.get("UI_Background"));
        Income.setBorder(new LineBorder((Color)this.settings.get("UI_Border"),1));
        TrackingContainer Expenses = new TrackingContainer(this.settings, "Type","Search Expenses");
        Expenses.setBackground((Color)this.settings.get("UI_Background"));
        Expenses.setBorder(new LineBorder((Color)this.settings.get("UI_Border"),1));
        //Place holder image
        JPanel Graph1 = new JPanel(){
            //placeholder fluff
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                BufferedImage image = null;
                try {image = ImageIO.read(new File("Graph_Placeholder.jpg"));}
                catch (Exception e){}
                g.drawImage(image, 0, -100, null);
            }
        };
        Graph1.setBorder(new LineBorder((Color)this.settings.get("UI_Border"),1));
        JPanel Savings = new JPanel();
        Savings.setBackground((Color)this.settings.get("UI_Background"));
        Savings.setBorder(new LineBorder((Color)this.settings.get("UI_Border"),1));
        JPanel Budget = new JPanel();
        Budget.setBackground((Color)this.settings.get("UI_Background"));
        Budget.setBorder(new LineBorder((Color)this.settings.get("UI_Border"),1));
        JPanel Widgets = new JPanel();
        Widgets.setBackground((Color)this.settings.get("UI_Background"));
        Widgets.setBorder(new LineBorder((Color)this.settings.get("UI_Border"),1));

        this.add(Income);
        this.add(Expenses);
        this.add(Graph1);
        this.add(Budget);
        this.add(Savings);
        this.add(Widgets);
        return this;
    }
}
