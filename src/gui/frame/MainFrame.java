package gui.frame;

import gui.view.DashboardView;
import gui.view.ViewFactory;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
/*
The root frame of the application. This handles the setup of all modules and views
and initializes application settings
 */
public class MainFrame extends JFrame implements ActionListener {
    private HashMap<String,Object> settings;

    public MainFrame(){
        super();
        initializeSettings();
        List<DashboardView> views = ViewFactory.getViews(settings);

        TitleBar tb = new TitleBar(this,settings);

        JTabbedPane jtb = new JTabbedPane();
        jtb.setFont((Font)this.settings.get("TP_Font"));
        jtb.setBorder(new MatteBorder(0,1,1,1,(Color)settings.get("UI_Border")));
        for(DashboardView dbv: views){
            jtb.addTab(dbv.getDisplayName(),dbv.createView());
        }

        JPanel surface = new JPanel();
        surface.setLayout(new BorderLayout());
        surface.setBackground((Color)settings.get("UI_Background"));
        surface.add(tb,BorderLayout.NORTH);
        surface.add(jtb,BorderLayout.CENTER);

        setUndecorated(true);//Removes the default windows minimize,close options
        getContentPane().add(surface);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize((Dimension)settings.get("UI_Dimensions"));
        setIconImage((Image)settings.get("UI_Icon"));
        setTitle((String) settings.get("UI_TitleString"));
        pack();
        setVisible(true);
    }
    /*
    Initializes all settings for use in the application and sets some defaults
    in the UIManager. No component should ever set its own settings, they should
    be configured here.

    Settings are stored as a unique 2 character identifier followed by an underscore
    and a descriptive name. Multiple settings for the same subsystem/component
    should begin with the same identifier.
     */
    private void initializeSettings(){
        settings = new HashMap<>();
        //UI Main Settings
        settings.put("UI_TitleString","Budget Application");
        settings.put("UI_Background",Color.darkGray);//64, 64, 64
        settings.put("UI_Midground",new Color(100,100,100));
        settings.put("UI_Foreground",Color.white);
        settings.put("UI_Border",Color.black);
        settings.put("UI_Icon",new ImageIcon("budget.png").getImage());
        settings.put("UI_TextCursor",createTextCursor());
        settings.put("UI_DefaultFont",new Font("Dialog",Font.PLAIN,12));
        settings.put("UI_Dimensions",new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-20),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-50)));
        //Tabbed Pane Settings
        settings.put("TP_Selected",new Color(115,115,115));
        settings.put("TP_Unselected",new Color(75,75,75));
        settings.put("TP_Font",new Font("Dialog",Font.PLAIN,15));
        //Title Bar Settings
        settings.put("TB_ButtonFont",new Font("Dialog",Font.PLAIN,15));
        settings.put("TB_MenuFont",new Font("Dialog",Font.PLAIN,13));
        settings.put("TB_SeparatorColor",new Color(115,115,115));
        settings.put("TB_SelectionBackground",new Color(115,115,115));
        settings.put("TB_ExitButtonPressed",new Color(255,0,0));
        settings.put("TB_ExitButtonEntered",new Color(175,0,0));
        settings.put("TB_MouseEntered",new Color(115,115,115));
        settings.put("TB_MousePressed",new Color(135,135,135));
        settings.put("TB_Icon",new ImageIcon(((Image)settings.get("UI_Icon")).getScaledInstance(35,35,Image.SCALE_SMOOTH)));
        //Search Bar Settings
        settings.put("SE_Background",new Color(83,104,120));
        settings.put("SE_FontColor", new Color(200,200,200));
        settings.put("SE_Font",new Font("Dialog",Font.PLAIN,15));
        //General Button Settings
        settings.put("GB_Background",new Color(125,125,125));
        settings.put("GB_ButtonPressed",new Color(175,175,175));
        settings.put("GB_ButtonEntered",new Color(150,150,150));
        settings.put("GB_FontColor", new Color(225,225,225));
        //Button Group 1: Add/Reset/Delete/Submit Button Settings
        settings.put("B1_ResetIcon","reset.png");
        settings.put("B1_DeleteIcon",new ImageIcon(new ImageIcon("delete.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        settings.put("B1_Font",new Font("Dialog",Font.PLAIN,35));
        settings.put("B1_SubmitBackground",new Color(135,135,135));
        //Filter Bar Settings
        settings.put("FB_FontColor", new Color(200,200,200));
        settings.put("FB_Font",new Font("Dialog",Font.PLAIN,15));
        settings.put("FB_Background",new Color(115,115,115));
        settings.put("FB_ArrowBackground",new Color(85,85,85));
        settings.put("FB_ArrowForeground",new Color(135,135,135));
        settings.put("FB_Border",Color.black);
        settings.put("FB_ListBackground",new Color(50,50,50));
        settings.put("FB_ListSelectedTextColor",Color.BLACK);
        settings.put("FB_SeparatorColor",new Color(115,115,115));
        settings.put("FB_FilterIcon",new ImageIcon(new ImageIcon("filter.png").getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        //Search Button Settings
        settings.put("SB_Background",new Color(115,115,115));
        settings.put("SB_ButtonPressed",new Color(150,150,150));
        settings.put("SB_ButtonEntered",new Color(125,125,125));
        settings.put("SB_FontColor", new Color(200,200,200));
        //Table Settings(Item List)
        settings.put("IL_FontColor",new Color(200,200,200));
        settings.put("IL_UnselectedRow",new Color(85,85,85));
        settings.put("IL_SelectedRowBackground",new Color(115,115,115));
        settings.put("IL_SelectedCell",Color.WHITE);
        settings.put("IL_UnselectedCell",Color.BLACK);
        settings.put("IL_SelectedRowForeground",Color.BLACK);
        settings.put("IL_HeaderRowBackground",new Color(150,150,150));
        settings.put("IL_HeaderFontColor",Color.BLACK);
        settings.put("IL_Font",new Font("Dialog",Font.PLAIN,13));
        //Add Line Item Form Settings
        settings.put("IF_Background",new Color(100,100,100));
        settings.put("IF_FontColor",Color.BLACK);
        settings.put("IF_EntryBackground",new Color(130,130,130));
        settings.put("IF_HeaderBackground", new Color(50,50,50));
        settings.put("IF_FrameBorder", Color.BLACK);
        //Scroll Bar Settings
        settings.put("SC_Background",new Color(25,25,25));
        settings.put("SC_Thumb",new Color(125,125,125));
        //----------------------------------------------------------------------
        //UI Manager Settings
        UIManager.put("ToolTip.background",new Color(150,150,150));
        UIManager.put("ToolTip.foreground",Color.WHITE);
        UIManager.put("ToolTip.border",new LineBorder(Color.BLACK));
        UIManager.put("TabbedPane.contentBorderInsets",new Insets(0,0,0,0));
        UIManager.put("TabbedPane.selected", this.settings.get("TP_Selected"));
        UIManager.put("TabbedPane.foreground", this.settings.get("UI_Foreground"));
        UIManager.put("TabbedPane.unselectedBackground",this.settings.get("TP_Unselected"));
        UIManager.put("TabbedPane.darkShadow",this.settings.get("UI_Border"));
        UIManager.put("TabbedPane.light",this.settings.get("UI_Background"));
        UIManager.put("TabbedPane.shadow",this.settings.get("UI_Background"));
        UIManager.put("TabbedPane.selectHighlight",this.settings.get("UI_Background"));
        UIManager.put("TabbedPane.borderHightlightColor",this.settings.get("UI_Border"));
        UIManager.put("TabbedPane.focus",this.settings.get("TP_Selected"));
    }
    /*
    Creates a new text cursor for working with the dark palette
     */
    private Cursor createTextCursor(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getBestCursorSize(8,8);
        Image image = toolkit.getImage("Text_Cursor.png");//.getScaledInstance(32,32,Image.SCALE_SMOOTH);
        return toolkit.createCustomCursor(image,new Point(d.width/2,d.height/2),"TextCursor");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().getClass().getSimpleName().equals("JMenuItem")){
            JMenuItem source = (JMenuItem)e.getSource();
            if(source.getName().equals("ExitApplicationMenu")){
                this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}
