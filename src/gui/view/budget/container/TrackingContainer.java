package gui.view.budget.container;
import gui.components.*;
import gui.components.interfaces.Searchable;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
public class TrackingContainer extends BudgetContainer {
    private JTextField searchBox;
    private SearchBar searchButton;

    public TrackingContainer(HashMap<String,Object> settings,String dropDownColumnName,String searchBarDefaultText){
        super(settings);
        this.setLayout(new BorderLayout());
        JPanel optionsSurface = new JPanel();
        JPanel searchBarButtonSurface = new JPanel();
        JPanel filterSurface = new JPanel();

        //Work in progress
        LineItemTable itemList = new LineItemTable(settings, dropDownColumnName);
        JScrollPane lineItemSurface = new JScrollPane(itemList);
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

        searchBarButtonSurface.setBackground((Color)settings.get("UI_Background"));
        searchBarButtonSurface.setLayout(new BorderLayout());
        SearchBar sb = new SearchBar(settings,searchBarDefaultText);
        searchBarButtonSurface.add(sb,BorderLayout.CENTER);
        JPanel buttonSurface = new JPanel();
        buttonSurface.setLayout(new GridLayout(1,2));
        buttonSurface.setBackground((Color)settings.get("UI_Background"));
        AddButton ab = new AddButton(settings,itemList);
        ResetButton rb = new ResetButton(settings,itemList);
        DeleteButton db = new DeleteButton(settings,itemList);
        buttonSurface.add(ab);
        buttonSurface.add(rb);
        buttonSurface.add(db);
        searchBarButtonSurface.add(buttonSurface,BorderLayout.EAST);
        searchBarButtonSurface.setBorder(new EmptyBorder(5,5,5,5));

        FilterBar fb = new FilterBar(this.settings, dropDownColumnName,itemList.getModel());
        SearchButton se = new SearchButton(this.settings,itemList);
        se.addSearchableComponents(new Searchable[]{sb,fb});
        filterSurface.setBackground((Color)settings.get("UI_Background"));
        filterSurface.setLayout(new BorderLayout());
        filterSurface.add(fb,BorderLayout.WEST);
        filterSurface.add(se,BorderLayout.EAST);
        filterSurface.setBorder(new CompoundBorder(new MatteBorder(0,0,1,0,(Color)settings.get("FB_SeparatorColor")),new EmptyBorder(5,5,5,5)));


        this.add(optionsSurface,BorderLayout.PAGE_START);
        this.add(lineItemSurface,BorderLayout.CENTER);//data table
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SearchButton s = (SearchButton)e.getSource();
        System.out.println(s.returnResults());
    }

}
