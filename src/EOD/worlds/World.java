package EOD.worlds;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.*;

import EOD.MouseInteractable;
import EOD.objects.*;
import EOD.scenes.*;
import EOD.characters.*;
import EOD.listeners.*;


public class World extends javax.swing.JFrame implements MouseInteractable{ // this is the superclass for all 3 worlds -- jian
    private String protagType; // variable for the protagType knight/priest/wizard
    private String playerName; // variable for player name -- jian
    //removed btn_shop. Let's create a class for shop so we can implement it easier. -- jian

    private EchoesObjects promptPanel;
    protected EchoesObjects btn_ok;
    private JTextField name;  
    private String worldType;  

    protected SceneBuilder scene;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected Protagonist protag; 
    private JLayeredPane layeredPane;

    public Minions minions1; // minions -z
    public MiniBoss miniBoss1; // this is just temporary... this should be a list of enemeies. 
    // create a class for enemies. Preferrably in different classes. Must have one superclass for polymorphsism
    // so that we will be able to iterate our enemies using the super class for example Enemy minions1 Enemy miniboss2
    // j

    public World(String protagType, String playerName, String worldType){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(worldType);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.protagType = protagType;
        this.playerName = playerName;   
        this.worldType = worldType;

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        
        JPanel basePanel = new JPanel();
        basePanel.setBackground(Color.BLACK);
        basePanel.setBounds(0, 0, screenSize.width, screenSize.height);
        
        // Add the base panel to the bottom layer
        layeredPane.add(basePanel, Integer.valueOf(0));

        this.setContentPane(layeredPane);
        
    }

    public JLayeredPane getPane(){
        return layeredPane;
    }

    public Protagonist getProtag(){
        return protag;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getProtagType(){
        return protagType;
    }

    public EchoesObjects createObj(String assetPackage, int x, int y, double width, double height, 
    String type, boolean isAnimated, boolean isState, int numOfSprites){
        EchoesObjects object = new EchoesObjects(assetPackage, x, y, (int)width, (int)height, type, isAnimated, isState, numOfSprites);
        object.setVisible(true);
        return object;
    }

    private void addPromptNamePanel(){
        promptPanel = createObj(worldType,(int) (screenSize.width * 0.1), 
        (int) (screenSize.height * 0.1), 
        (int) (screenSize.width * 0.80), 
        (int) (screenSize.height * 0.80), 
        "welcomePrompt", false, false, 1);
        promptPanel.setLayout(null);
        layeredPane.add(promptPanel, Integer.valueOf(1));
    }

    private void addOkButton(int panelHeight, int panelWidth) {
        btn_ok = createObj(
            "button", 
            (int) (panelWidth * 0.82),  // Position relative to promptPanel
            (int) (panelHeight * 0.35),  // Position relative to promptPanel
            (int) (panelWidth* 0.2),
            (int) (panelHeight* 0.058),
            "ok_button", false, true, 2
        );
        promptPanel.add(btn_ok);
        btn_ok.addMouseListener(new MouseClickListener(this));  
    }

    public void addPlayerName(int panelHeight, int panelWidth){
        name = new JTextField(playerName); // Create a JTextField with text
        name.setFont(createDynamicFont(50));
        name.setForeground(new Color(238,218,180,255));
        name.setBackground(new Color(0, 0, 0, 0)); // Set background to transparent
        name.setEditable(false); // Make it non-editable
        name.setBorder(null); // Remove the border
        name.setHorizontalAlignment(JTextField.CENTER); // Center the text horizontally
        name.setBounds((int) (panelWidth * 0.675), 
                (int) (panelHeight* 0.24), 
                (int) (panelWidth * 0.47), 
                (int) (panelHeight* 0.10));
        promptPanel.add(name); // Add textField to the panel
    }

    public void Welcome(){  
        addPromptNamePanel();
        int width = promptPanel.getWidth();
        int height = promptPanel.getHeight();
        addOkButton(width, height);
        addPlayerName(width, height);
        this.setVisible(true);
    }

    private java.awt.Font createDynamicFont(int baseFontSize) {
        int dynamicFontSize = (int) (screenSize.height * 0.05); 
        return new java.awt.Font("SansSerif", java.awt.Font.PLAIN, Math.max(baseFontSize, dynamicFontSize));
    }
    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if(source == btn_ok){
            promptPanel.setVisible(false);
            layeredPane.add(scene, Integer.valueOf(1));
            scene.setVisible(true);
            scene.createWorldScene();  
        }
    }

    @Override
    public void onHover(MouseEvent e) {
    }

    @Override
    public void onExit(MouseEvent e) {
       
    }


}
