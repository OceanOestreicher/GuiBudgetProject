package gui.form;

import gui.components.LineItemTable;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class AddLineItemForm extends JFrame implements ActionListener {
    private LineItemTable itemList;
    public AddLineItemForm(HashMap<String,Object> settings, LineItemTable itemList){
        JPanel formSurface = new JPanel();
        JButton exit = new JButton("Exit");
        exit.setName("ExitAddForm");
        exit.addActionListener(this);
        formSurface.add(exit);
        this.itemList = itemList;
        this.setUndecorated(true);//Removes the default windows minimize,close options
        this.getContentPane().add(formSurface);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }
}
