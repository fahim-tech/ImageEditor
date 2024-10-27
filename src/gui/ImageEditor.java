package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import gui.actions.MenuFileActions;
import gui.actions.ButtonActions;

public class ImageEditor extends JFrame {

    public JLabel infoLabel = new JLabel("Welcome to Image Editor!       ");;
    private int id = 1;
    private final JTabbedPane Tab = new JTabbedPane();
    public void addImageTab(BufferedImage image) {
        addImageTab("Image", image);
    }
    public void addImageTab(String name, BufferedImage image) {

        // Adds a (subclass of) JScrollPane to this JTabbedPane.
        // This JScrollPane contains the image and some special functions
        Tab.add(name + "_" + (id++), new ImagePane(image));
        // As it needs to give -1 when no is tab is selected.
        Tab.setSelectedIndex(Tab.getTabCount() - 1);
    }

    public void closeTab(){
        int i = Tab.getSelectedIndex();
        if (i != -1) {
            Tab.remove(i);
        }
    }

    public BufferedImage getImageTab() {
        Component c = Tab.getSelectedComponent();
        if (c != null) {
            ImagePane image = (ImagePane) c;
            return image.getImage();
        }
        return null;
    }


    public void setTabTitle(String title) {
        Tab.setTitleAt(Tab.getSelectedIndex(), title);
    }

    public ImageEditor() {

        // setting up the frame and panels

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setTitle("Image Editor");
        ImageIcon logo = new ImageIcon("logo.png");
        this.setIconImage(logo.getImage());

        this.getContentPane().setBackground(Color.WHITE);

        JPanel header = new JPanel();
        JPanel list = new JPanel();
        JPanel footer = new JPanel();

        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));

        footer.add(infoLabel);

        //header.setBackground(new Color(8,198,171));
        //list.setBackground(new Color(8,198,171));
        //footer.setBackground(new Color(90,200,250));

        header.setPreferredSize(new Dimension(30,30));
        list.setPreferredSize(new Dimension(275,275));
        footer.setPreferredSize(new Dimension(30,30));

        this.add(list, BorderLayout.WEST);
        this.add(header, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);

        // create menus
        JMenuBar jMenuBar = new JMenuBar();
        createMenuAndButtons(jMenuBar, list);
        setJMenuBar(jMenuBar);

        add(Tab);
    }

    public static void main(String[] args) {
        ImageEditor jfImageEditor = new ImageEditor();
        jfImageEditor.setVisible(true);
    }


    private void createMenuAndButtons(JMenuBar jMenuBar, JPanel list) {

        // menu items
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JPopupMenu.Separator Separator = new JPopupMenu.Separator();
        JMenuItem quitItem = new JMenuItem("Exit");

        MenuFileActions menuFileActions = new MenuFileActions(this);
        openItem.addActionListener(menuFileActions.new OpenAction());
        saveItem.addActionListener(menuFileActions.new SaveAsAction());
        quitItem.addActionListener(menuFileActions.new ExitAction());

        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        fileMenu.setMnemonic(KeyEvent.VK_F);

        jMenuBar.add(fileMenu);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(Separator);
        fileMenu.add(quitItem);

        //Button items

        JButton Negative = new JButton("Negative");
        JButton gScreen = new JButton("Green Screen");
        JButton gScale = new JButton("Black & White");
        JButton blur = new JButton("Blur");
        JButton equalize = new JButton("Increase Brightness");
        JButton rRight = new JButton("Rotate Right");
        JButton rLeft = new JButton("Rotate Left");
        JButton fHorizontal = new JButton("Flip Horizontal");
        JButton fVertical = new JButton("Flip Vertical");
        JButton closeTab = new JButton("Close Tab  (Alt-q)");

        Negative.setBackground(new Color(220,220,230));
        gScreen.setBackground(new Color(220,220,230));
        blur.setBackground(new Color(220,220,230));
        equalize.setBackground(new Color(220,220,230));
        rRight.setBackground(new Color(220,220,230));
        rLeft.setBackground(new Color(220,220,230));
        fHorizontal.setBackground(new Color(220,220,230));
        fVertical.setBackground(new Color(220,220,230));
        gScale.setBackground(new Color(220,220,230));
        closeTab.setBackground(new Color(220,220,230));

        Negative.setFocusable(false);
        gScreen.setFocusable(false);
        blur.setFocusable(false);
        equalize.setFocusable(false);
        rRight.setFocusable(false);
        rLeft.setFocusable(false);
        fHorizontal.setFocusable(false);
        fVertical.setFocusable(false);
        gScale.setFocusable(false);
        closeTab.setFocusable(false);

        list.setLayout(new GridLayout(11,1,10,10));
        list.add(Negative);
        list.add(gScale);
        list.add(gScreen);
        list.add(blur);
        list.add(equalize);
        list.add(rRight);
        list.add(rLeft);
        list.add(fHorizontal);
        list.add(fVertical);

        JSeparator s = new JSeparator();
        list.add(s);
        list.add(closeTab);

        ButtonActions buttonActions = new ButtonActions(this);
        fHorizontal.addActionListener(buttonActions.new Button(ButtonActions.Button.HORIZONTAL));
        fVertical.addActionListener(buttonActions.new Button(ButtonActions.Button.VERTICAL));
        Negative.addActionListener(buttonActions.new Button(ButtonActions.Button.NEGATIVE));
        rRight.addActionListener(buttonActions.new Button(ButtonActions.Button.RRIGHT));
        rLeft.addActionListener(buttonActions.new Button(ButtonActions.Button.RLEFT));
        gScreen.addActionListener(buttonActions.new Button(ButtonActions.Button.GREENS));
        gScale.addActionListener(buttonActions.new Button(ButtonActions.Button.GRAYS));
        blur.addActionListener(buttonActions.new Button(ButtonActions.Button.BLUR));
        equalize.addActionListener(buttonActions.new Button(ButtonActions.Button.BRIGHT));
        closeTab.addActionListener(menuFileActions.new ExitTab());

        closeTab.setMnemonic(KeyEvent.VK_Q);

    }

}
