package gui.view.budget.container;

import gui.components.*;
import gui.components.interfaces.Searchable;
import gui.ui.CustomScrollBarUI;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.HashMap;
/*
A container for tracking income/expenses
 */
public class TrackingContainer extends JPanel {

    public TrackingContainer(final HashMap<String,Object> settings,final String dropDownColumnName,final String searchBarDefaultText){
        setLayout(new BorderLayout());
        JPanel optionsSurface = new JPanel();
        JPanel searchBarButtonSurface = new JPanel();
        JPanel filterSurface = new JPanel();

        LineItemTable itemList = new LineItemTable(settings, dropDownColumnName);
        JScrollPane lineItemSurface = new JScrollPane(itemList);
        JScrollBar scb = new JScrollBar(Adjustable.VERTICAL);
        scb.setUI(new CustomScrollBarUI(settings));
        lineItemSurface.setVerticalScrollBar(scb);
        lineItemSurface.setBorder(new EmptyBorder(5,0,0,0));
        lineItemSurface.getViewport().setBackground((Color)settings.get("UI_Background"));
        lineItemSurface.setBackground((Color)settings.get("UI_Background"));

        optionsSurface.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = .1;
        gbc.weightx = .1;
        optionsSurface.setBackground((Color)settings.get("UI_Background"));
        optionsSurface.add(searchBarButtonSurface,gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        gbc.weightx = 1;
        optionsSurface.add(filterSurface,gbc);

        JLabel filterIcon = new JLabel((ImageIcon)settings.get("FB_FilterIcon"));
        searchBarButtonSurface.setBackground((Color)settings.get("UI_Background"));
        searchBarButtonSurface.setLayout(new BorderLayout());
        SearchBar sb = new SearchBar(settings,searchBarDefaultText);
        searchBarButtonSurface.add(sb,BorderLayout.CENTER);
        JPanel buttonSurface = new JPanel();
        buttonSurface.setLayout(new GridLayout(1,2));
        buttonSurface.setBackground((Color)settings.get("UI_Background"));
        AddButton ab = new AddButton(settings,itemList);
        ResetButton rb = new ResetButton(settings,itemList,filterIcon);
        DeleteButton db = new DeleteButton(settings,itemList);
        buttonSurface.add(ab);
        buttonSurface.add(rb);
        buttonSurface.add(db);
        searchBarButtonSurface.add(buttonSurface,BorderLayout.EAST);
        searchBarButtonSurface.setBorder(new EmptyBorder(5,5,5,5));

        FilterBar fb = new FilterBar(settings, dropDownColumnName,itemList.getModel());

        SearchButton se = new SearchButton(settings,itemList,filterIcon);
        se.addSearchableComponents(new Searchable[]{sb,fb});

        filterIcon.setVisible(false);
        JPanel searchButtonSurface = new JPanel();
        searchButtonSurface.setBackground((Color)settings.get("UI_Background"));
        searchButtonSurface.add(filterIcon);
        searchButtonSurface.add(se);
        filterSurface.setBackground((Color)settings.get("UI_Background"));
        filterSurface.setLayout(new BorderLayout());
        filterSurface.add(fb,BorderLayout.WEST);
        filterSurface.add(searchButtonSurface,BorderLayout.EAST);
        filterSurface.setBorder(new CompoundBorder(new MatteBorder(0,0,1,0,(Color)settings.get("FB_SeparatorColor")),new EmptyBorder(5,5,5,5)));

        add(optionsSurface,BorderLayout.PAGE_START);
        add(lineItemSurface,BorderLayout.CENTER);//data table
    }

}
