package gui.components.interfaces;

import javax.swing.*;
/*
    Interface for any object that a user interacts with that filters a JTable.
 */
public interface Searchable {
    RowFilter<Object,Object> getResults();
}
