package gui.view;

import gui.view.budget.BudgetDashboardView;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewFactory {
    public static ArrayList<DashboardView> getViews(HashMap<String,Object> settings){
        ArrayList<DashboardView>views = new ArrayList<>();
        views.add(new BudgetDashboardView(settings));
        return views;
    }
}
