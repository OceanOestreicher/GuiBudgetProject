package gui.components.interfaces;

import javax.swing.*;

public interface Searchable {
    RowFilter<Object,Object> getResults();
}
