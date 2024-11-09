package EOD.worlds;

import EOD.characters.*;
import EOD.dialogues.Dialogues;
import EOD.gameInterfaces.Freeable;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.*;
import EOD.objects.*;
import EOD.objects.shop.Shop;
import EOD.scenes.*;
import EOD.utils.BGMPlayer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;;

public abstract class World extends javax.swing.JFrame implements MouseInteractable, Freeable{ // this is the superclass for all 3 worlds -- jian
    private EchoesObjects promptPanel;
    protected EchoesObjects btn_ok;
    private EchoesObjects victoryBanner;
    private EchoesObjects defeatBanner;
    private EchoesObjects soulShard;
    private JLabel name;  
    private String worldType;  
    protected BattleUI battle;

    protected SceneBuilder scene;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected Player player; 

    private EchoesObjects btn_settings;
    private EchoesObjects bag;
    private JLayeredPane layeredPane;
    protected BGMPlayer bgmPlayer;
    protected Shop shop;
    protected JProgressBar progressBar;
    public Quests quests;
    private Timer bannerTimer;
    private JLabel counterLabel;
    private JLabel moneyLabel;
    private int money;
    private JPanel moneyPanel;
    protected String playerName;
    protected Player knight;
    protected Player priest;
    protected Player wizard;
    protected ArrayList<Player> playerList;
    private boolean initiateBattleUi = true;

    //public Enemy skeleton; // minions -z
    //public Enemy necromancer; // this is just temporary... this should be a list of enemeies. 
    // create a class for enemies. Preferrably in different classes. Must have one superclass for polymorphsism
    // so that we will be able to iterate our enemies using the super class for example Enemy minions1 Enemy miniboss2
    // j

    
    public World(String worldType){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(worldType);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.worldType = worldType;
        this.addMouseListener(new MouseClickListener(this));
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        
        JPanel basePanel = new JPanel();
        basePanel.setBackground(Color.BLACK);
        basePanel.setBounds(0, 0, screenSize.width, screenSize.height);
        
        // Add the base panel to the bottom layer
        layeredPane.add(basePanel, Integer.valueOf(0));
        addSettingsButton();
        addBagIcon();
        addMoneyPanel();
        addSoulShard();
        configureBanners();
        this.setContentPane(layeredPane);
        progressBar = new JProgressBar(0, 100); // range from 0 to 100
        progressBar.setStringPainted(true); // Shows progress as a percentage
        progressBar.setVisible(false); // Hide initially
        progressBar.setBounds((int)(screenSize.width * 0.27), (int)(screenSize.height * 0.85), (int)(screenSize.width * 0.5), (int)(screenSize.height * 0.05));
        progressBar.setBackground(new Color(238,218,180,255));
        progressBar.setForeground(new Color(6,57,112));
        progressBar.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5)); // Border for aesthetics
        progressBar.setFont(new Font("SansSerif", Font.BOLD, 16)); 
        playerList = new ArrayList<>();
        // Add progress bar to the UI (e.g., at the bottom of your frame)
        layeredPane.add(progressBar, Integer.valueOf(1));
    }

    public Quests getQuests(){
        return quests;
    }

    private void freeObjects(){
        try{
        ArrayList <Freeable> freeList = new ArrayList<>();
        freeList.add(btn_ok);
        freeList.add(victoryBanner);
        freeList.add(defeatBanner);
        freeList.add(soulShard);
        freeList.add(scene);
        freeList.add(battle);

        for(Freeable item : freeList){
            if (item != null){
                item.free();
                item = null;
            }
        }
    }catch(Exception e){
        e.printStackTrace();
    }
    }

    public void free(){
        try{
            freeObjects();
            name = null;
            worldType = null;
            layeredPane = null;
            progressBar = null;
            bannerTimer = null;
            counterLabel = null;
            moneyLabel = null;
            playerName = null;
            promptPanel = null;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Player> getPlayerList(){
        return playerList;
    }

    public void setMoneyLabel(String moneyLabel){
        this.moneyLabel.setText(moneyLabel);
    }

    public void setMoney(int money){
        player.getAttributes().setMoney(money);
    }

    public int getMoney(){
        money = player.getAttributes().getMoney();
        return money;
    }

    public BattleUI getBattle(){
        return battle;
    }

    public abstract void initializeProtagonist();

    
    public void updatePlayerMoneyLabel(){
        moneyLabel.setText(player.getAttributes().getMoney() + "");
    }

    public void setInitiateBattleUi (boolean initiateBattleUi){
        this.initiateBattleUi = initiateBattleUi;
    }

    public void initializeBattleUI(){
        if (initiateBattleUi){
            System.out.println("called>");
            battle = new BattleUI(player);
            layeredPane.add(battle, Integer.valueOf(1));
        } else {
            return;
        }
    }

    public void openQuests() {
        this.quests = new Quests();
        quests.setPlayer(player);
        layeredPane.add(this.quests, Integer.valueOf(1));
    }

    public void reopenQuests() {
        this.quests.setVisible(true);
    }

    public void closeQuests() {
        this.quests.setVisible(false);
    }

    public abstract void initializeAllyProfiles();

    public abstract void initializePlayerProfile();

    public void configureBanners(){
        victoryBanner = new EchoesObjects("banner", (int)(screenSize.width * 0.1),(int)(screenSize.width * 0.01), (int)(screenSize.width * 0.8),(int)(screenSize.width * 0.3), "win", false, false, 1);
        defeatBanner = new EchoesObjects("banner", (int)(screenSize.width * 0.1),(int)(screenSize.width * 0.01), (int)(screenSize.width * 0.8),(int)(screenSize.width * 0.3), "lose", false, false, 1);
        victoryBanner.addMouseListener(new MouseClickListener(this));
        defeatBanner.addMouseListener(new MouseClickListener(this));
        layeredPane.add(victoryBanner, Integer.valueOf(1));
        layeredPane.add(defeatBanner, Integer.valueOf(1));

        counterLabel = new JLabel("Closes in 5 seconds"); // Starting with 5 seconds
        counterLabel.setForeground(Color.WHITE); // Set text color
        counterLabel.setBounds((int)(screenSize.width * 0.1), (int)(screenSize.width * 0.26), (int)(screenSize.width * 0.8), 30); // Position below the banner
        counterLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
        counterLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
        counterLabel.setVisible(false); // Initially hidden
        layeredPane.add(counterLabel, Integer.valueOf(1)); // Add to the layered pane
    }

    public void callVictory() {
        victoryBanner.setVisible(true); // Show the banner immediately
        counterLabel.setVisible(true); // Show the counter label
    
        int countdownTime = 5; // 5 seconds countdown
        counterLabel.setText("Closes in " + countdownTime + " seconds"); // Display initial counter
    
        // Create a Timer to update the counter and hide the banner after 5 seconds
        bannerTimer = new Timer(1000, new ActionListener() {
            private int remainingTime = countdownTime; // Keep track of remaining time
    
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                counterLabel.setText("Closes in " + remainingTime + " seconds");  // Update the counter display
    
                if (remainingTime <= 0) {
                    victoryBanner.setVisible(false); // Hide the banner
                    counterLabel.setVisible(false); // Hide the counter label
                    ((Timer) e.getSource()).stop(); // Stop the timer
                }
            }
        });
    
        bannerTimer.setInitialDelay(0); // Start immediately
        bannerTimer.start(); // Start the timer
    }
    

    public void callDefeat() {
        defeatBanner.setVisible(true);
        counterLabel.setVisible(true); // Show the counter label
    
        int countdownTime = 5; // 5 seconds countdown
        counterLabel.setText("Closes in " + countdownTime + " seconds"); // Display initial counter
    
        // Create a Timer to update the counter and hide the banner after 5 seconds
        bannerTimer = new Timer(1000, new ActionListener() {
            private int remainingTime = countdownTime; // Keep track of remaining time
    
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                counterLabel.setText("Closes in " + remainingTime + " seconds"); // Update the counter display
    
                if (remainingTime <= 0) {
                    defeatBanner.setVisible(false); // Hide the banner
                    counterLabel.setVisible(false); // Hide the counter label
                    ((Timer) e.getSource()).stop(); // Stop the timer
                }
            }
        });
    
        bannerTimer.setInitialDelay(0); // Start immediately
        bannerTimer.start(); // Start the timer
    }
    

    public void configureShopAndInventory(){
        shop = new Shop(this);
        player.getInventory().setWorld(this);
        shop.setInventory(player.getInventory());
    }

    public void setBGMPlayer(BGMPlayer bgmPlayer){
        this.bgmPlayer = bgmPlayer;
    }

    public JLayeredPane getPane(){
        return layeredPane;
    }

    public Player getPlayer(){
        return player;
    }

    public String getPlayerType(){
        return player.getCharacterType();
    }


    public abstract void initializeObjects();

    public abstract void initializeWorldChars();

    public abstract void initializeEnemies();

    private class InitializationWorker extends SwingWorker<Void, Integer> {
        @Override
        protected Void doInBackground() {
            try {
                System.out.println("Starting Initialization");
                publish(0); // Start progress
                initializeWorldChars();
                publish(25);

                initializeObjects();
                publish(50);
                System.out.println("Objects initialized");
                
                initializeProtagonist();
                publish(75);
                System.out.println("World characters initialized");
    
                initializeEnemies();
                publish(100);
                System.out.println("Enemies initialized");
    
                // Check if all initialization steps were successful
                assert scene != null : "Scene was not initialized!";
                assert battle != null : "BattleUI was not initialized!";
                
                scene.createWorldScene();
            } catch (Exception e) {
                System.err.println("Error during initialization: " + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void process(java.util.List<Integer> chunks) {
            for (int value : chunks) {
                progressBar.setValue(value);
            }
        }

        @Override
        protected void done() {
            progressBar.setString("Loading Complete");
            removeWelcome();
            openQuests();
            initializeBattleUI();
            initializeAllyProfiles();
            initializePlayerProfile();
            player.getAllyProfiles().showAllProfiles();
            scene.initializeGameLoop();
        }
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
                (int) (screenSize.width * 0.27),
                (int) (screenSize.height * 0.85),
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

    public void addSoulShard(){
        soulShard = new EchoesObjects(
                "shop",
                (int) (screenSize.width * 0.02),
                (int) (screenSize.height * 0.0),
                (int) (screenSize.width * 0.1),
                (int) (screenSize.height * 0.15),
                "soulShard",
                false,
                false,
                1
            );
        layeredPane.add(soulShard, Integer.valueOf(1));
    }

    private void addMoneyPanel() {
        // Create a panel for better layout control
        moneyPanel = new JPanel();
        moneyPanel.setLayout(null);
        moneyPanel.setOpaque(false); // Make panel transparent
        moneyPanel.setBounds((int)(screenSize.width * 0.02), (int)(screenSize.height * 0.05), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.05)); // Position at top

        // Create money label with formatted text
        moneyLabel = new JLabel("" + money);
        moneyLabel.setFont(new Font("Arial", Font.BOLD, (int)(screenSize.height * 0.02))); // Scaled font size
        moneyLabel.setForeground(new Color(238,218,180,255)); // Gold color
        moneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        // Set the bounds for the label within the panel
        moneyLabel.setBounds(-2, 0, (int)(screenSize.width * 0.08), (int)(screenSize.height * 0.05));
        moneyPanel.setVisible(false);
        moneyPanel.add(moneyLabel);
        layeredPane.add(moneyPanel, Integer.valueOf(1));
    }


    public void addBagIcon() {
        bag = new EchoesObjects(
                "inventory",
                (int) (screenSize.width * 0.66), 
                (int) (screenSize.height * 0.85),
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

    public SceneBuilder getScene(){
        return scene;
    }

    public void addPlayerName(int panelHeight, int panelWidth){
        name = new JLabel(playerName); // Create a JTextField with text
        name.setFont(createDynamicFont(50));
        name.setForeground(new Color(238,218,180,255));
        name.setBackground(new Color(0, 0, 0, 0)); // Set background to transparent
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

    public void removeWelcome(){
        progressBar.setVisible(false);
        promptPanel.setVisible(false);
        layeredPane.remove(promptPanel);
        layeredPane.add(scene, Integer.valueOf(1));
        scene.setVisible(true);
        btn_settings.setVisible(true);
        bag.setVisible(true);
        soulShard.setVisible(true);
        moneyPanel.setVisible(true);
    }
    private int btn_okCount = 0;

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if (source == btn_ok && btn_okCount == 0) {
            progressBar.setVisible(true);
            progressBar.setValue(0);
            // Start the initialization in a background thread
            InitializationWorker worker = new InitializationWorker();
            worker.execute();
            btn_okCount += 1;
        }
        if(scene.objList == null) return;
        for (EchoesObjects obj : scene.objList) {
            if (source == obj && ((obj.getName ().equals("portalMiniBoss") || obj.getName().equals("portal")) && 
                (scene.enemyList.get(1).getIsDefeated() || scene.enemyList.get(0).getIsDefeated()))){
                battle.getEnemyWrapper().setVisible(false);
                battle.toggleInventoryOff();
                quests.setVisible(true);
                for(Player player : playerList){
                    player.getAnimator().setIsInBattle(false);
                }
                Dialogues dialogues = battle.getBattleExperiment().getEnemy().getDialogues();
                    if(dialogues != null && dialogues.getStoryJDialog() != null) dialogues.getStoryJDialog().dispose();
            }
        }

        if(source == btn_settings){
            SettingsWindow settingsWindow = SettingsWindow.getInstance(bgmPlayer);
            settingsWindow.setVisible(true); // Display the window

        }else if(source == bag){
            if (player.getInventory().isVisible()) {
                player.getInventory().setVisible(false);
                battle.toggleInventoryOff();
            } else {
                player.getInventory().setVisible(true);
                battle.toggleInventoryOn();
            }
        }else if(source == this){
            if (bannerTimer != null && bannerTimer.isRunning()) {
                bannerTimer.stop(); // Stop the timer if it's running
            }
            
            // Hide victory banner if it's visible
            if (victoryBanner.isVisible()) {
                victoryBanner.setVisible(false);
                System.out.println("victoryBanner hidden");
            }
            
            // Hide defeat banner if it's visible
            if (defeatBanner.isVisible()) {
                defeatBanner.setVisible(false);
                System.out.println("defeatBanner hidden");
            }
            counterLabel.setVisible(false);
        }else if(source == victoryBanner){
            if (bannerTimer != null && bannerTimer.isRunning()) {
                bannerTimer.stop(); // Stop the timer if it's running
            }
            victoryBanner.setVisible(false);
            counterLabel.setVisible(false);
        }else{
            if (bannerTimer != null && bannerTimer.isRunning()) {
                bannerTimer.stop(); // Stop the timer if it's running
            }
            defeatBanner.setVisible(false);
            counterLabel.setVisible(false);
        }
    }

    @Override
    public void onHover(MouseEvent e) {
    }

    @Override
    public void onExit(MouseEvent e) {
       
    }
}
