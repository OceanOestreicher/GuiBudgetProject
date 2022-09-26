package gui.frame;

import gui.components.ExitButton;
import gui.components.MinimizeButton;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
/*
A compound component/panel for the main application that allows
a user to do normal title bar activities such as minimizing, closing
saving, etc.
 */
public class TitleBar extends JPanel {

    private final Color separatorColor;
    public TitleBar(JFrame frame, HashMap<String,Object> settings){
        super();
        setBackground((Color)settings.get("UI_Background"));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),40));
        separatorColor = (Color)settings.get("TB_SeparatorColor");
        //Title Bar Buttons
        JPanel titleBarButtonSurface = new JPanel();
        titleBarButtonSurface.setBackground((Color)settings.get("UI_Background"));
        titleBarButtonSurface.setLayout(new GridLayout(1,2));
        ExitButton close = new ExitButton(settings,frame);
        MinimizeButton minimize = new MinimizeButton(settings,frame);
        titleBarButtonSurface.add(minimize);
        titleBarButtonSurface.add(close);
        //Title Bar Icon
        JLabel icon = new JLabel((ImageIcon)settings.get("TB_Icon"));
        UIManager.put("Menu.selectionBackground", settings.get("TB_SelectionBackground"));
        UIManager.put("Menu.selectionForeground", settings.get("UI_Foreground"));
        UIManager.put("Menu.border", settings.get("UI_Background"));
        UIManager.put("MenuItem.selectionBackground", settings.get("TB_SelectionBackground"));
        UIManager.put("MenuItem.selectionForeground", settings.get("UI_Foreground"));
        UIManager.put("PopupMenu.border", settings.get("UI_Background"));
        UIManager.put("MenuItem.background", settings.get("UI_Background"));
        UIManager.put("MenuItem.border", new LineBorder((Color)settings.get("TB_SelectionBackground")));
        UIManager.put("MenuItem.foreground", settings.get("UI_Foreground"));
        UIManager.put("Menu.font",settings.get("TB_MenuFont"));
        UIManager.put("MenuItem.font",settings.get("TB_MenuFont"));
        //Title Bar Menu
        JMenuBar jmb = new JMenuBar();
        jmb.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jmb.setBackground((Color)settings.get("UI_Background"));
        jmb.setBorder(new LineBorder((Color)settings.get("UI_Background")));
        //File Menu
        JMenu file = new JMenu("   File   ");
        file.setForeground((Color)settings.get("UI_Foreground"));
        JMenuItem newView = new JMenuItem("New   ");
        newView.setName("NewViewMenu");
        JMenuItem openFile = new JMenuItem("Open   ");
        openFile.setName("OpenFileMenu");
        JMenuItem saveFile = new JMenuItem("Save   ");
        saveFile.setName("SaveFileMenu");
        JMenuItem menuExit = new JMenuItem("Exit   ");
        menuExit.setName("ExitApplicationMenu");
        menuExit.addActionListener((ActionListener)frame);
        file.add(newView);
        file.add(openFile);
        file.add(saveFile);
        file.add(menuExit);
        jmb.add(file);
        //Edit Menu
        JMenu edit = new JMenu("   Edit   ");
        edit.setForeground((Color)settings.get("UI_Foreground"));
        edit.setBackground((Color)settings.get("UI_Background"));
        jmb.add(edit);
        //Title Bar Construction
        JPanel iconMenu = new JPanel();
        iconMenu.setBackground((Color)settings.get("UI_Background"));
        iconMenu.setLayout(new BorderLayout());
        iconMenu.add(icon,BorderLayout.WEST);
        iconMenu.add(jmb,BorderLayout.EAST);
        add(iconMenu,BorderLayout.WEST);
        add(titleBarButtonSurface,BorderLayout.EAST);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(separatorColor);
        g2d.drawLine(0,39,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),39);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.repaint();
    }

}
