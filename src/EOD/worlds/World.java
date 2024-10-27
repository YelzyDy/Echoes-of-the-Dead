package EOD.worlds;

import EOD.MouseInteractable;
import EOD.characters.*;
import EOD.listeners.*;
import EOD.objects.*;
import EOD.objects.inventory.Inventory;
import EOD.objects.shop.Shop;
import EOD.scenes.*;
import EOD.utils.BGMPlayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class World extends javax.swing.JFrame implements MouseInteractable{ // this is the superclass for all 3 worlds -- jian
    private String playerType; // variable for the playerType knight/priest/wizard
    private String playerName; // variable for player name -- jian
    //removed btn_shop. Let's create a class for shop so we can implement it easier. -- jian

    private EchoesObjects promptPanel;
    protected EchoesObjects btn_ok;
    private JTextField name;  
    private String worldType;  

    protected SceneBuilder scene;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected Protagonist player; 

    private EchoesObjects btn_settings;
    private EchoesObjects bag;
    private JLayeredPane layeredPane;
    protected BGMPlayer bgmPlayer;
    private Inventory inventory;
    protected Shop shop;
    //public Enemy skeleton; // minions -z
    //public Enemy necromancer; // this is just temporary... this should be a list of enemeies. 
    // create a class for enemies. Preferrably in different classes. Must have one superclass for polymorphsism
    // so that we will be able to iterate our enemies using the super class for example Enemy minions1 Enemy miniboss2
    // j

    public World(String playerType, String playerName, String worldType){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(worldType);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.playerType = playerType;
        this.playerName = playerName;   
        this.worldType = worldType;

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        
        JPanel basePanel = new JPanel();
        basePanel.setBackground(Color.BLACK);
        basePanel.setBounds(0, 0, screenSize.width, screenSize.height);
        
        // Add the base panel to the bottom layer
        layeredPane.add(basePanel, Integer.valueOf(0));
        addSettingsButton();
        System.out.println("Hello!");
        addBagIcon();
        this.setContentPane(layeredPane);
    }

    public void configureShopAndInventory(){
        inventory = new Inventory(this);
        shop = new Shop(this);
        shop.setInventory(inventory);
    }

    public void setBGMPlayer(BGMPlayer bgmPlayer){
        this.bgmPlayer = bgmPlayer;
    }

    public JLayeredPane getPane(){
        return layeredPane;
    }

    public Protagonist getProtag(){
        return player;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getPlayerType(){
        return playerType;
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

    public void addSettingsButton(){
        btn_settings = new EchoesObjects(
                "settings", 
                (int) (screenSize.width * 0.01),
                (int) (screenSize.height * 0.01),
                (int) (screenSize.width * 0.07),
                (int) (screenSize.height * 0.11),
                "settings_button", 
                false, 
                true, 
                2
            );
        btn_settings.addMouseListener(new MouseClickListener(this));
        btn_settings.setName(("settings"));
        layeredPane.add(btn_settings, Integer.valueOf(1));
    }

    public void addBagIcon() {
        bag = new EchoesObjects(
                "inventory",
                (int) (screenSize.width * 0.09), // Positioned right after settings button
                (int) (screenSize.height * 0.01),
                (int) (screenSize.width * 0.07),
                (int) (screenSize.height * 0.11),
                "bag",
                false,
                true,
                2
            );
        layeredPane.add(bag, Integer.valueOf(1));
        bag.addMouseListener(new MouseClickListener(this));
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
            layeredPane.remove(promptPanel);
            layeredPane.add(scene, Integer.valueOf(1));
            scene.setVisible(true);
            scene.createWorldScene();  
            btn_settings.setVisible(true);
            bag.setVisible(true);
            System.out.println("click");
        }else if(source == btn_settings){
            SettingsWindow settings = new SettingsWindow(bgmPlayer);  // Pass BGMPlayer instance to manage music
            settings.setVisible(true);
        }else if(source == bag){
            if (inventory.isVisible()) {
                inventory.setVisible(false);
            } else {
                inventory.setVisible(true);
            }
        }
    }

    @Override
    public void onHover(MouseEvent e) {
    }

    @Override
    public void onExit(MouseEvent e) {
       
    }
}
