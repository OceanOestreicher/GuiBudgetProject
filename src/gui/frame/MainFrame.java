package gui.frame;

import gui.components.TitleBarButton;
import gui.view.DashboardView;
import gui.view.ViewFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
public class MainFrame extends JFrame implements ActionListener {

    private final List<DashboardView> views;
    private HashMap<String,Object> settings;

    public MainFrame(){
        super();
        this.initializeSettings();
        this.views = ViewFactory.getViews(this.settings);
        //Title Bar
        TitleBar tb = new TitleBar(this,this.settings);
        //Main Display Surface
        JPanel surface = new JPanel();
        surface.setLayout(new BorderLayout());
        surface.setBackground((Color)this.settings.get("UI_Background"));
        //Tabbed View Pane
        JTabbedPane jtb = new JTabbedPane();
        jtb.setFont((Font)this.settings.get("TP_Font"));
        jtb.setBorder(new MatteBorder(0,1,1,1,(Color)this.settings.get("UI_Border")));
        for(DashboardView dbv: this.views){
            jtb.addTab(dbv.getDisplayName(),dbv.createView());
        }
        //Surface Construction
        surface.add(tb,BorderLayout.NORTH);
        surface.add(jtb,BorderLayout.CENTER);
        //Frame Initialization
        this.setUndecorated(true);//Removes the default windows minimize,close options
        this.getContentPane().add(surface);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize((Dimension)this.settings.get("UI_Dimensions"));
        this.setIconImage(new ImageIcon((String)this.settings.get("UI_Icon")).getImage());
        this.pack();
        this.setVisible(true);
    }
    private void initializeSettings(){
        this.settings = new HashMap<>();
        //UI Main Settings
        this.settings.put("UI_Background",Color.darkGray);//64, 64, 64
        this.settings.put("UI_Midground",new Color(100,100,100));
        this.settings.put("UI_Foreground",Color.white);
        this.settings.put("UI_Border",Color.black);
        this.settings.put("UI_Icon","budget.png");
        this.settings.put("UI_TextCursor",createTextCursor());
        this.settings.put("UI_DefaultFont",new Font("Dialog",Font.PLAIN,12));
        this.settings.put("UI_Dimensions",new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-20),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-50)));
        //Tabbed Pane Settings
        this.settings.put("TP_Selected",new Color(115,115,115));
        this.settings.put("TP_Unselected",new Color(75,75,75));
        this.settings.put("TP_Font",new Font("Dialog",Font.PLAIN,15));
        //Title Bar Settings
        this.settings.put("TB_ButtonFont",new Font("Dialog",Font.PLAIN,15));
        this.settings.put("TB_MenuFont",new Font("Dialog",Font.PLAIN,13));
        this.settings.put("TB_SeparatorColor",new Color(115,115,115));
        this.settings.put("TB_SelectionBackground",new Color(115,115,115));
        this.settings.put("TB_ExitButtonPressed",new Color(255,0,0));
        this.settings.put("TB_ExitButtonEntered",new Color(175,0,0));
        this.settings.put("TB_MouseEntered",new Color(115,115,115));
        this.settings.put("TB_MousePressed",new Color(135,135,135));
        //Search Bar Settings
        this.settings.put("SE_Background",new Color(83,104,120));
        this.settings.put("SE_FontColor", new Color(200,200,200));
        this.settings.put("SE_Font",new Font("Dialog",Font.PLAIN,15));
        //General Button Settings
        this.settings.put("GB_Background",new Color(125,125,125));
        this.settings.put("GB_ButtonPressed",new Color(175,175,175));
        this.settings.put("GB_ButtonEntered",new Color(150,150,150));
        this.settings.put("GB_FontColor", new Color(225,225,225));
        //Button Group 1: Add/Reset/Delete/Submit Button Settings
        this.settings.put("B1_ResetIcon","reset.png");
        this.settings.put("B1_DeleteIcon","delete.png");
        this.settings.put("B1_Font",new Font("Dialog",Font.PLAIN,35));
        this.settings.put("B1_SubmitBackground",new Color(135,135,135));
        //Filter Bar Settings
        this.settings.put("FB_FontColor", new Color(200,200,200));
        this.settings.put("FB_Font",new Font("Dialog",Font.PLAIN,15));
        this.settings.put("FB_Background",new Color(115,115,115));
        this.settings.put("FB_ArrowBackground",new Color(85,85,85));
        this.settings.put("FB_ArrowForeground",new Color(135,135,135));
        this.settings.put("FB_Border",Color.black);
        this.settings.put("FB_ListBackground",new Color(50,50,50));
        this.settings.put("FB_ListSelectedTextColor",Color.BLACK);
        this.settings.put("FB_SeparatorColor",new Color(115,115,115));
        this.settings.put("FB_FilterIcon","filter.png");
        //Search Button Settings
        this.settings.put("SB_Background",new Color(115,115,115));
        this.settings.put("SB_ButtonPressed",new Color(150,150,150));
        this.settings.put("SB_ButtonEntered",new Color(125,125,125));
        this.settings.put("SB_FontColor", new Color(200,200,200));
        //Table Settings(Item List)
        this.settings.put("IL_FontColor",new Color(200,200,200));
        this.settings.put("IL_UnselectedRow",new Color(85,85,85));
        this.settings.put("IL_SelectedRowBackground",new Color(115,115,115));
        this.settings.put("IL_SelectedCell",Color.WHITE);
        this.settings.put("IL_UnselectedCell",Color.BLACK);
        this.settings.put("IL_SelectedRowForeground",Color.BLACK);
        this.settings.put("IL_HeaderRowBackground",new Color(150,150,150));
        this.settings.put("IL_HeaderFontColor",Color.BLACK);
        this.settings.put("IL_Font",new Font("Dialog",Font.PLAIN,13));
        //Add Line Item Form Settings
        this.settings.put("IF_Background",new Color(100,100,100));
        this.settings.put("IF_FontColor",Color.BLACK);
        this.settings.put("IF_EntryBackground",new Color(130,130,130));
        this.settings.put("IF_HeaderBackground", new Color(50,50,50));
        this.settings.put("IF_FrameBorder", Color.BLACK);
        //Scroll Bar Settings
        this.settings.put("SC_Background",new Color(25,25,25));
        this.settings.put("SC_Thumb",new Color(125,125,125));
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
        UIManager.put("TabbedPane.focus",this.settings.get("TP_Selected"));//ScrollBar.track
/*
        UIManager.put("ScrollBar.thumbHighlight",Color.GREEN);
        UIManager.put("ScrollBar.thumbShadow",Color.GREEN);
        UIManager.put("ScrollBar.thumbDarkShadow",Color.GREEN);
        UIManager.put("ScrollBar.thumb",Color.GREEN);
        UIManager.put("ScrollBar.background",Color.YELLOW);*/
    }
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
