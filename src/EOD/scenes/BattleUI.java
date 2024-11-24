package EOD.scenes;

import EOD.characters.*;
import EOD.characters.enemies.Enemy;
import EOD.dialogues.*;
import EOD.gameInterfaces.Freeable;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.objects.QuestableObjects;
import EOD.objects.Rewards;
import EOD.objects.bars.BattleBars;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class BattleUI extends JPanel implements Freeable, MouseInteractable{
    // Constants
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) (screenSize.width * 0.99);
    private final int topTextHeight = (int) (screenSize.height * 0.07);
    private final int bottomTextHeight = (int) (screenSize.height * 0.06);
    protected Rewards rewards;
    // Core Components
    private final JPanel textPanel;
    private StoryLine story;
    private final BattleBars battleBars;
    
    // UI Components
    private JLabel topTextBox;
    private JLabel bottomTextBox;
    private JList<String> textList;
    private DefaultListModel<String> textListModel;
    private JPanel skillButtonsPanel;
    private JPanel sidePanel;
    private JPanel enemyWrapper;
    
    // Buttons and Icons
    private JButton skillA, skillB, skillC, skillD;
    private ImageIcon skillAIcon, skillAHoverIcon;
    private ImageIcon skillBIcon, skillBHoverIcon;
    private ImageIcon skillCIcon, skillCHoverIcon;
    private ImageIcon skillDIcon, skillDHoverIcon;
    
    // Game State
    private Player player;
    private Enemy enemy;
    private BattleExperiment battleSample;
    private QuestableObjects portal;
    private String temp;
    private JPanel bottomPanel;
    //private SFXPlayer sfxPlayer;

    public BattleUI(Player player){
        this.player = player;
        this.story = new StoryLine();
        story.skillDetails(player.getCharacterType());
        this.textPanel = new JPanel(new GridLayout(3, 1));
        this.battleBars = new BattleBars();
        initializeUI();
        //sfxPlayer = SFXPlayer.getInstance();
    }

    public void setRewards(Rewards rewards){
        this.rewards = rewards;
    }

    @Override
    public void free(){
        if (rewards != null) {
            rewards = null;
        }
        if (story != null) {
            story.free();
        }
        if (topTextBox != null) {
            topTextBox = null;
        }
        if (bottomPanel != null) {
            bottomPanel = null;
        }
        if (bottomTextBox != null) {
            bottomTextBox = null;
        }
        if (textList != null) {
            textList = null;
        }
        if (textListModel != null) {
            textListModel = null;
        }
        if (skillButtonsPanel != null) {
            skillButtonsPanel = null;
        }
        if (sidePanel != null) {
            sidePanel = null;
        }
        if (enemyWrapper != null) {
            enemyWrapper = null;
        }
        if (skillA != null) {
            skillA = null;
        }
        if (skillB != null) {
            skillB = null;
        }
        if (skillC != null) {
            skillC = null;
        }
        if (skillD != null) {
            skillD = null;
        }
        if (skillAIcon != null) {
            skillAIcon = null;
        }
        if (skillBIcon != null) {
            skillBIcon = null;
        }
        if (skillCIcon != null) {
            skillCIcon = null;
        }
        if (skillDIcon != null) {
            skillDIcon = null;
        }
        if (skillAHoverIcon != null) {
            skillAHoverIcon = null;
        }
        if (skillBHoverIcon != null) {
            skillBHoverIcon = null;
        }
        if (skillCHoverIcon != null) {
            skillCHoverIcon = null;
        }
        if (skillDHoverIcon != null) {
            skillDHoverIcon = null;
        }
        if (enemy != null) {
            enemy.free();
            enemy = null;
        }
        if (portal != null) {
            portal.free();
            portal = null;
        }
        if (temp != null) {
            temp = null;
        }
        if (battleSample != null) {   
            battleSample = null;
        }      
    }

    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }

    public void startBattle(){
        textListModel.clear();
        battleSample = new BattleExperiment();
        battleSample.setPlayer(player);
        battleSample.setEnemy(enemy);
        battleSample.setBattleUI(this);
        battleBars.setEnemyStats(
           enemy.getHp()
        );
        battleSample.battleEnded = false;
        enemyWrapper.setVisible(true);
        setSkillButtonsEnabled(true);
        topTextBox.setText("Turn 1: Your Turn");
        textList.setVisible(true);
        player.getWorld().getQuests().setVisible(false);
        for(Player player : player.getWorld().getPlayerList()){
            player.getAnimator().setIsInBattle(true);
            player.setPosX(screenSize.width * 0.35);
            player.getAnimator().setMovingRight(true);
        }
        rewards.setBattle(battleSample);
    }

    private void initializeUI() {
        // Basic panel setup
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBounds(0, (int)(screenSize.height * 0.4), 
                 (int)(screenSize.width), (int)(screenSize.height * 0.6));

        // Initialize all panels and components
        initializeTextPanel();
        initializeTopTextBox();
        initializeTextList();
        initializeBottomTextBox();
        initializeSidePanel();
        initializeSkillButtons();
        initializeBottomPanel();

        // Final setup
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
        setSkillButtonsEnabled(false);
        enemyWrapper.setVisible(false);
    }

    private void initializeTextPanel() {
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.BLACK);
        textPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, (int)(screenSize.width * 0.01), 
                                         0, (int)(screenSize.width * 0.05)),
            BorderFactory.createLineBorder(Color.WHITE, 2)
        ));
        textPanel.setPreferredSize(new Dimension(width, (int)(getHeight() * 0.5)));
        add(textPanel, BorderLayout.CENTER);
    }

    private void initializeTopTextBox() {
        topTextBox = new JLabel("Echoes Of The Dead", SwingConstants.CENTER);
        topTextBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        topTextBox.setForeground(Color.WHITE);
        topTextBox.setVerticalAlignment(SwingConstants.CENTER);
        topTextBox.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        topTextBox.setMaximumSize(new Dimension(width, topTextHeight));
        topTextBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        topTextBox.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(topTextBox);
    }

    private void initializeTextList() {
        textListModel = new DefaultListModel<>();
        textList = new JList<>(textListModel);
        textList.setBackground(Color.BLACK);
        textList.setForeground(Color.WHITE);
        textList.setFont(new Font("Monospaced", Font.PLAIN, 28));
        textList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textList.setVisibleRowCount(3);
        textList.setFixedCellHeight((int)(screenSize.height * 0.07));
        textList.setVisible(false);

        JScrollPane scrollPane = new JScrollPane(textList);
        scrollPane.getViewport().setBackground(Color.BLACK);
        textPanel.add(scrollPane);
    }

    private void initializeBottomTextBox() {
        bottomTextBox = new JLabel("", SwingConstants.CENTER);
        bottomTextBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        bottomTextBox.setForeground(Color.WHITE);
        bottomTextBox.setVerticalAlignment(SwingConstants.CENTER);
        bottomTextBox.setMaximumSize(new Dimension(width, bottomTextHeight));
        bottomTextBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomTextBox.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(bottomTextBox);
    }

    private void initializeSidePanel() {
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setPreferredSize(new Dimension(width/8, (int)(getHeight() * 0.5)));
        add(sidePanel, BorderLayout.WEST);
    }

    private void initializeSkillButtons() {
        skillButtonsPanel = new JPanel(new GridLayout(1, 4, 0, 0));
        skillButtonsPanel.setBackground(Color.BLACK);
        
        // Load all icons
        loadSkillIcons();
        
        // Create all buttons
        skillA = createSkillButton(skillAIcon);
        skillB = createSkillButton(skillBIcon);
        skillC = createSkillButton(skillCIcon);
        skillD = createSkillButton(skillDIcon);

        // Add buttons to panel
        skillButtonsPanel.add(skillA);
        skillButtonsPanel.add(skillB);
        skillButtonsPanel.add(skillC);
        skillButtonsPanel.add(skillD);
    }

    public void loadSkillIcons() {
        story.skillDetails(player.getCharacterType());

        // Clear previous icons
        clearPreviousIcons();
    
        // Load new icons based on character type
        loadCharacterSpecificIcons();
    
        // Update the button icons while preserving hover states
        if (skillA != null) {
            skillA.setIcon(skillAIcon);
            skillB.setIcon(skillBIcon);
            skillC.setIcon(skillCIcon);
            skillD.setIcon(skillDIcon);
            // Refresh the buttons
            refreshButtons();
        }
    }

    private void clearPreviousIcons() {
        if (skillAIcon != null) {
            skillAIcon.getImage().flush();
            skillAHoverIcon.getImage().flush();
            skillBIcon.getImage().flush();
            skillBHoverIcon.getImage().flush();
            skillCIcon.getImage().flush();
            skillCHoverIcon.getImage().flush();
            skillDIcon.getImage().flush();
            skillDHoverIcon.getImage().flush();
        }
    }

    private void loadCharacterSpecificIcons() {
        switch(player.getCharacterType()) {
            case "knight" -> loadKnightIcons();
            case "wizard" -> loadWizardIcons();
            case "priest" -> loadPriestIcons();
        }
    }

    private void loadKnightIcons() {
        skillAIcon = scaleImageIcon("src/button_assets/kBasicAtk0.png");
        skillAHoverIcon = scaleImageIcon("src/button_assets/kBasicAtk1.png");
        skillBIcon = scaleImageIcon("src/button_assets/k1stSkill0.png");
        skillBHoverIcon = scaleImageIcon("src/button_assets/k1stSkill1.png");
        skillCIcon = scaleImageIcon("src/button_assets/k2ndSkill0.png");
        skillCHoverIcon = scaleImageIcon("src/button_assets/k2ndSkill1.png");
        skillDIcon = scaleImageIcon("src/button_assets/k3rdSkill0.png");
        skillDHoverIcon = scaleImageIcon("src/button_assets/k3rdSkill1.png");
    }

    private void loadWizardIcons() {
        skillAIcon = scaleImageIcon("src/button_assets/mBasicAtk0.png");
        skillAHoverIcon = scaleImageIcon("src/button_assets/mBasicAtk1.png");
        skillBIcon = scaleImageIcon("src/button_assets/m1stSkill0.png");
        skillBHoverIcon = scaleImageIcon("src/button_assets/m1stSkill1.png");
        skillCIcon = scaleImageIcon("src/button_assets/m2ndSkill0.png");
        skillCHoverIcon = scaleImageIcon("src/button_assets/m2ndSkill1.png");
        skillDIcon = scaleImageIcon("src/button_assets/m3rdSkill0.png");
        skillDHoverIcon = scaleImageIcon("src/button_assets/m3rdSkill1.png");
    }

    private void loadPriestIcons() {
        skillAIcon = scaleImageIcon("src/button_assets/pBasicAtk0.png");
        skillAHoverIcon = scaleImageIcon("src/button_assets/pBasicAtk1.png");
        skillBIcon = scaleImageIcon("src/button_assets/p1stSkill0.png");
        skillBHoverIcon = scaleImageIcon("src/button_assets/p1stSkill1.png");
        skillCIcon = scaleImageIcon("src/button_assets/p2ndSkill0.png");
        skillCHoverIcon = scaleImageIcon("src/button_assets/p2ndSkill1.png");
        skillDIcon = scaleImageIcon("src/button_assets/p3rdSkill0.png");
        skillDHoverIcon = scaleImageIcon("src/button_assets/p3rdSkill1.png");
    }

    private void refreshButtons() {
        skillA.revalidate();
        skillA.repaint();
        skillB.revalidate();
        skillB.repaint();
        skillC.revalidate();
        skillC.repaint();
        skillD.revalidate();
        skillD.repaint();
    }

    private void initializeBottomPanel() {
        bottomPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.setBackground(Color.BLACK);
        
        // Player stats panel
        JPanel playerWrapper = createStatsWrapper(true);
        JPanel playerPanel = battleBars.getPlayerStats();
        playerPanel.setBackground(Color.BLACK);
        playerWrapper.add(playerPanel, BorderLayout.CENTER);
        
        // Enemy stats panel
        enemyWrapper = createStatsWrapper(false);
        JPanel enemyPanel = battleBars.getEnemyStats();
        enemyPanel.setBackground(Color.BLACK);
        enemyWrapper.add(enemyPanel, BorderLayout.CENTER);
        
        // Initialize player stats
        battleBars.setPlayerStats(
            player.getAttributes().getBaseHp(),
            player.getAttributes().getBaseMana()
        );
        
        // Add all panels
        bottomPanel.add(playerWrapper);
        bottomPanel.add(skillButtonsPanel);
        bottomPanel.add(enemyWrapper);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createStatsWrapper(boolean isPlayer) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.BLACK);
        int verticalPadding = (int)(screenSize.height * 0.05);
        int horizontalPadding = isPlayer ? (int)(screenSize.width * 0.1) : (int)(screenSize.width * 0.06);
        wrapper.setBorder(BorderFactory.createEmptyBorder(verticalPadding, horizontalPadding, verticalPadding, 0));
        return wrapper;
    }

    public JPanel getEnemyWrapper(){
        return enemyWrapper;
    }

    public void setPortal(QuestableObjects portal){
        this.portal = portal;
    }

    public QuestableObjects getPortal(){
        return portal;
    }

    public BattleExperiment getBattleExperiment(){
        return battleSample;
    }


    public JPanel getSidePanel(){
        return sidePanel;
    }

    public JPanel getBottomPanel(){
        return bottomPanel;
    }


    public void toggleInventoryOn(){
        if(player.getAnimator().getIsInBattle()) temp = topTextBox.getText();
        topTextBox.setText("Inventory");
        textList.setVisible(false);
        player.getWorld().getQuests().setVisible(false);
    }

    public void toggleInventoryOff(){
        topTextBox.setText(temp);
        if(player.getAnimator().getIsInBattle()){
            textList.setVisible(true);
            player.getWorld().getQuests().setVisible(false);
        }else{
            textList.setVisible(false);
            player.getWorld().getQuests().setVisible(true);
        }
    }

    public void updateTextDetail(String detail){
        bottomTextBox.setText(detail);
    }

    public void setPlayer(Player player){
        this.player = player;
        if (battleSample != null)battleSample.setPlayer(player);
        if(player.getAnimator().getIsInBattle()){
            enemy.setPlayer(player);
            player.setEnemy(enemy);
        }
        handleSkillVisibility();
    }

    public void handleSkillVisibility(){
        Player protagonist = player.getWorld().getPlayer();
        skillButtonsPanel.remove(skillA);
        skillButtonsPanel.remove(skillB);
        skillButtonsPanel.remove(skillC);
        skillButtonsPanel.remove(skillD);
        if(player != protagonist){
            skillButtonsPanel.add(skillB);
            skillButtonsPanel.add(skillA);
            skillButtonsPanel.add(skillD);
            skillButtonsPanel.add(skillC);
            skillB.setVisible(false);
            skillC.setVisible(false);
        }else{
            skillButtonsPanel.add(skillA);
            skillButtonsPanel.add(skillB);
            skillButtonsPanel.add(skillC);
            skillButtonsPanel.add(skillD);

            skillA.setVisible(true);
            skillB.setVisible(true);
            skillC.setVisible(true);
            skillD.setVisible(true);
        }
        this.revalidate();
        this.repaint();
    }


    public void updatePlayerBars(int hp, int mp){
        battleBars.setPlayerHealth(hp);
        battleBars.setPlayerMana(mp);
    }

    public void updateBars(int hp, int mp, int enemyHp){
        battleBars.setPlayerHealth(hp);
        battleBars.setPlayerMana(mp);
        battleBars.setEnemyHealth(enemyHp);
    }

    public void updatePlayerBaseStats(int hp, int mp){
        battleBars.setPlayerStats(hp, mp);
    }

    // THE METHODS

    public void updateTurnIndicator(String text){
        topTextBox.setText(text);
    }


    public void showAction(String text) {
        textListModel.addElement(text);
    }

    private JButton createSkillButton(ImageIcon defaultIcon) {
       
        JButton button = new JButton(defaultIcon);
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);
        
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Add cooldown text styling
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        button.addMouseListener(new MouseClickListener(this));
        button.setEnabled(false);
        return button;
    }


    public void updateCooldowns() {
        // First check if battle is active
        if (battleSample == null) return;
        
        // Update cooldown displays
        updateSkillCooldown(1, battleSample.getPlayer().getSkill2CD());
        updateSkillCooldown(2, battleSample.getPlayer().getSkill3CD());
        updateSkillCooldown(3, battleSample.getPlayer().getSkill4CD());
    }

    private void updateSkillCooldown(int skillIndex, int cooldown) {
        JButton skillButton = getSkillButton(skillIndex);
        if (skillButton != null) {
            if (cooldown > 0){
                //set cooldown text for buttons
                skillButton.setText("" + cooldown);
            } else {
                skillButton.setText("");;
            }
        }
        // Update enabled states based on cooldowns
        boolean buttonsEnabled = skillA.isEnabled(); // Get current base state
        if (buttonsEnabled) {
            skillB.setEnabled(battleSample.getPlayer().getSkill2CD() == 0);
            skillC.setEnabled(battleSample.getPlayer().getSkill3CD() == 0);
            skillD.setEnabled(battleSample.getPlayer().getSkill4CD() == 0);
        }
    }

    public void setSkillButtonsEnabled(boolean enabled) {
        // Set the base enabled state for all buttons
        skillA.setEnabled(enabled);
        
        // Only enable other skills if their cooldowns are 0
        if (enabled && battleSample != null) {
            skillB.setEnabled(battleSample.getPlayer().getSkill2CD() == 0);
            skillC.setEnabled(battleSample.getPlayer().getSkill3CD() == 0);
            skillD.setEnabled(battleSample.getPlayer().getSkill4CD() == 0);
        } else {
            skillB.setEnabled(false);
            skillC.setEnabled(false);
            skillD.setEnabled(false);
        }
    }

    private JButton getSkillButton(int index) {
        switch (index) {
            case 1: return skillB;
            case 2: return skillC;
            case 3: return skillD;
            default: return null;
        }
    }

    public ImageIcon scaleImageIcon(String path) {
        int width = (int) (screenSize.width * 0.06);
        int height = (int) (screenSize.width * 0.06);

        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if (battleSample != null && !battleSample.isProcessingTurn()) {
            if (source == skillA && skillA.isEnabled()) {
                battleSample.skill1();
            } else if (source == skillB && skillB.isEnabled()) {
                battleSample.skill2();
            } else if (source == skillC && skillC.isEnabled()) {
                battleSample.skill3();
            } else if (source == skillD && skillD.isEnabled()) {
                battleSample.skill4();
            }
        }
    }

    @Override
    public void onHover(MouseEvent e) {
        Object source = e.getSource();
        if(source == skillA){
            skillA.setIcon(skillAHoverIcon);
            bottomTextBox.setText(story.getLine(1));
        }else if(source == skillB){
            skillB.setIcon(skillBHoverIcon);
            bottomTextBox.setText(story.getLine(2));
        }else if(source == skillC){
            skillC.setIcon(skillCHoverIcon);
            bottomTextBox.setText(story.getLine(3));
        }else if(source == skillD){
            skillD.setIcon(skillDHoverIcon);
            bottomTextBox.setText(story.getLine(4));
        }
    }

    @Override
    public void onExit(MouseEvent e) {
        Object source = e.getSource();
        if(source == skillA){
            skillA.setIcon(skillAIcon);
            bottomTextBox.setText(""); 
        }else if(source == skillB){
            skillB.setIcon(skillBIcon);
            bottomTextBox.setText("");
        }else if(source == skillC){
            skillC.setIcon(skillCIcon);
            bottomTextBox.setText("");
        }else if(source == skillD){
            skillD.setIcon(skillDIcon);
            bottomTextBox.setText("");
        }
    }

}