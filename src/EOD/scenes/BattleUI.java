package EOD.scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import EOD.characters.*;
import EOD.characters.enemies.Enemy;
import EOD.dialogues.*;
import EOD.objects.EchoesObjects;
import EOD.objects.Rewards;
import EOD.objects.bars.BattleBars;

public class BattleUI extends JPanel{
    // Constants
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) (screenSize.width * 0.99);
    private final int topTextHeight = (int) (screenSize.height * 0.07);
    private final int bottomTextHeight = (int) (screenSize.height * 0.06);

    // Core Components
    private final JPanel textPanel;
    private final StoryLine story;
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
    private EchoesObjects portal;
    private String temp;
    private JPanel bottomPanel;

    public BattleUI(Player player){
        this.player = player;
        this.story = new StoryLine();
        story.skillDetails();
        this.textPanel = new JPanel(new GridLayout(3, 1));
        this.battleBars = new BattleBars();
        initializeUI();
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
        battleSample.setRewards(new Rewards(battleSample));
        enemyWrapper.setVisible(true);
        setSkillButtonsEnabled(true);
        topTextBox.setText("Turn 1: Your Turn");
        textList.setVisible(true);
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
        skillA = createSkillButton(story, skillAIcon, skillAHoverIcon, 
            e -> battleSample.skill1(), bottomTextBox, 0, 1);
        skillB = createSkillButton(story, skillBIcon, skillBHoverIcon, 
            e -> battleSample.skill2(), bottomTextBox, 0, 2);
        skillC = createSkillButton(story, skillCIcon, skillCHoverIcon, 
            e -> battleSample.skill3(), bottomTextBox, 0, 3);
        skillD = createSkillButton(story, skillDIcon, skillDHoverIcon, 
            e -> battleSample.skill4(), bottomTextBox, 0, 4);

        // Add buttons to panel
        skillButtonsPanel.add(skillA);
        skillButtonsPanel.add(skillB);
        skillButtonsPanel.add(skillC);
        skillButtonsPanel.add(skillD);
    }

    private void loadSkillIcons() {
        skillAIcon = scaleImageIcon("src/button_assets/basicSkill0.png");
        skillAHoverIcon = scaleImageIcon("src/button_assets/basicSkill1.png");
        skillBIcon = scaleImageIcon("src/button_assets/1stskill0.png");
        skillBHoverIcon = scaleImageIcon("src/button_assets/1stskill1.png");
        skillCIcon = scaleImageIcon("src/button_assets/2ndskill0.png");
        skillCHoverIcon = scaleImageIcon("src/button_assets/2ndskill1.png");
        skillDIcon = scaleImageIcon("src/button_assets/3rdskill0.png");
        skillDHoverIcon = scaleImageIcon("src/button_assets/3rdskill1.png");
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

    public void setPortal(EchoesObjects portal){
        this.portal = portal;
    }

    public EchoesObjects getPortal(){
        return portal;
    }

    public BattleExperiment getBattleExperiment(){
        return battleSample;
    }


    public JPanel getSidePanel(){
        return sidePanel;
    }


    public void toggleTextListOff(){
        if(player.getAnimator().getIsInBattle()) temp = topTextBox.getText();
        else temp = "Echoes Of The Dead";
        topTextBox.setText("Inventory");
        textList.setVisible(false);
    }

    public void toggleTextListOn(){
        topTextBox.setText(temp);
        if(player.getAnimator().getIsInBattle()) textList.setVisible(true);
        else textList.setVisible(false);
    }

    public void updateTextDetail(String detail){
        bottomTextBox.setText(detail);
    }

    public void setPlayer(Player player){
        this.player = player;
        if (battleSample != null)battleSample.setPlayer(player);
        // handleSkillVisibility();
        if(player.getAnimator().getIsInBattle()){
            enemy.setPlayer(player);
            player.setEnemy(enemy);
        }
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

    // THE METHODS

    public void updateTurnIndicator(String text){
        topTextBox.setText(text);
    }


    public void showAction(String text) {
        textListModel.addElement(text);
    }

    private JButton createSkillButton(StoryLine story, ImageIcon defaultIcon, ImageIcon hoverIcon, 
        ActionListener action, JLabel textBox, int defaultIndex, int hoverIndex) {
       
        JButton button = new JButton(defaultIcon);
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);
        
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(action);

        // Add cooldown text styling
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(hoverIcon);
                textBox.setText(story.getLine(hoverIndex));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(defaultIcon);
            }
        });

        return button;
    }


    public void updateCooldowns() {
        // Update each cooldown label
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
        skillB.setEnabled(battleSample.getPlayer().getSkill2CD() == 0);
        skillC.setEnabled(battleSample.getPlayer().getSkill3CD() == 0);
        skillD.setEnabled(battleSample.getPlayer().getSkill4CD() == 0);
    }

    public void setSkillButtonsEnabled(boolean enabled) {
        skillA.setEnabled(enabled);
        skillB.setEnabled(enabled &&  battleSample.getPlayer().getSkill2CD() == 0);
        skillC.setEnabled(enabled &&  battleSample.getPlayer().getSkill3CD() == 0);
        skillD.setEnabled(enabled &&  battleSample.getPlayer().getSkill4CD() == 0);
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
        int width = (int) (screenSize.width * 0.05);
        int height = (int) (screenSize.width * 0.05);

        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }

}