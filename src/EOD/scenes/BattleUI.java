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
import EOD.objects.bars.BattleBars;
public class BattleUI extends JPanel{
    private JPanel textPanel = new JPanel(new GridLayout(3, 1));
    private StoryLine story = new StoryLine();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) (screenSize.width * 0.99);
    private final int height = (int) (screenSize.height * 0.55);
    private final int topTextHeight = (int) (screenSize.height * 0.07);
    private final int bottomTextHeight = (int) (screenSize.height * 0.06);
    private final int x = 6;
    private final int y = (int) (screenSize.height * 0.44);
    private JButton skillA, skillB, skillC, skillD;
    private ImageIcon skillAIcon, skillAHoverIcon, skillBIcon, skillBHoverIcon, skillCIcon, skillCHoverIcon, skillDIcon, skillDHoverIcon;
    private BattleExperiment battleSample;
    private EchoesObjects portal;
    private JPanel skillButtonsPanel ;
    private JLabel topTextBox, bottomTextBox;
    private BattleBars battleBars = new BattleBars();
    private JList<String> textList;
    private DefaultListModel<String> textListModel;
    private Player player;
    private Enemy enemy;
    private JPanel enemyWrapper;
    private String temp;

    public BattleUI(Player player){
        this.player = player;
        System.out.println(this.player.getName() + "adfasdf");
        displayContainerPanel();
        displayTitleLabel();
        displayGamePanel();
        displaySkillButtons();
        displayBottomPanel();
        setSkillButtonsEnabled(false);
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
        player.setEnemy(enemy);
        battleBars.setEnemyStats(
           enemy.getHp()
        );
        enemyWrapper.setVisible(true);
        setSkillButtonsEnabled(true);
        topTextBox.setText("Turn 1: Your Turn");
        textList.setVisible(true);
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

    public void displayContainerPanel(){
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.BLACK);
        textPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder((int)(screenSize.height * 0.05), (int)(screenSize.width * 0.05), 0, (int)(screenSize.width * 0.05)), 
            BorderFactory.createLineBorder(Color.WHITE, 2)  // Existing white border
        ));
        textPanel.setPreferredSize(new Dimension(width, (int) (screenSize.height * 0.3)));

    }

    public void displayTitleLabel(){
        topTextBox = new JLabel("", SwingConstants.CENTER);
        topTextBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        topTextBox.setForeground(Color.WHITE);
        topTextBox.setVerticalAlignment(SwingConstants.CENTER);
        topTextBox.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        topTextBox.setMaximumSize(new Dimension(width, topTextHeight));
        topTextBox.setAlignmentX(Component.CENTER_ALIGNMENT); 
        topTextBox.setHorizontalAlignment(SwingConstants.CENTER);
        topTextBox.setText("Echoes Of The Dead");
        textPanel.add(topTextBox);
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

    public void displayGamePanel(){

        // THE WINDOW & DIALOGUESs
        System.out.println("battle called");

        story.skillDetails();

        this.setBounds(0, (int)(screenSize.height * 0.4), (int)(screenSize.width), (int)(screenSize.height * 0.6));
        this.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        textListModel = new DefaultListModel<>();
        textList = new JList<>(textListModel);
        textList.setBackground(Color.BLACK);
        textList.setForeground(Color.WHITE);
        textList.setFont(new Font("Monospaced", Font.PLAIN, 28));
        // Set up the JList properties
        textList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        textList.setVisibleRowCount(3); // Show 3 rows at a time
        textList.setFixedCellHeight((int) (screenSize.height * 0.07)); // Set fixed cell height
        textList.setVisible(false);
            // Add a scroll pane for the list
        JScrollPane scrollPane = new JScrollPane(textList);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setVisible(true);

        textPanel.add(scrollPane); 

        bottomTextBox = new JLabel("", SwingConstants.CENTER);
        bottomTextBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        bottomTextBox.setForeground(Color.WHITE);
        bottomTextBox.setVerticalAlignment(SwingConstants.CENTER);
        bottomTextBox.setMaximumSize(new Dimension(width, bottomTextHeight));
        textPanel.add(bottomTextBox);

        bottomTextBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomTextBox.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(textPanel, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
        

    }

    public void displaySkillButtons(){
        skillButtonsPanel = new JPanel(new GridLayout(1, 4, 0, 0));
        skillButtonsPanel.setBackground(Color.BLACK);
        // ACTION LISTENERS

        ImageIcon skillAIcon = scaleImageIcon("src/button_assets/basicSkill0.png");
        ImageIcon skillAHoverIcon = scaleImageIcon("src/button_assets/basicSkill1.png");
        skillAIcon = scaleImageIcon("src/button_assets/basicSkill0.png");
        skillAHoverIcon = scaleImageIcon("src/button_assets/basicSkill1.png");

        skillBIcon = scaleImageIcon("src/button_assets/1stskill0.png");
        skillBHoverIcon = scaleImageIcon("src/button_assets/1stskill1.png");

        skillCIcon = scaleImageIcon("src/button_assets/2ndskill0.png");
        skillCHoverIcon = scaleImageIcon("src/button_assets/2ndskill1.png");

        skillDIcon = scaleImageIcon("src/button_assets/3rdskill0.png");
        skillDHoverIcon = scaleImageIcon("src/button_assets/3rdskill1.png");

        // Create and add skill buttons

        skillA = createSkillButton(story, skillAIcon, skillAHoverIcon, 
            e -> battleSample.skill1(), bottomTextBox, 0, 1);

        skillB = createSkillButton(story, skillBIcon, skillBHoverIcon, 
            e -> battleSample.skill2(), bottomTextBox, 0, 2);

        skillC = createSkillButton(story, skillCIcon, skillCHoverIcon, 
            e -> battleSample.skill3(), bottomTextBox, 0, 3);

        skillD = createSkillButton(story, skillDIcon, skillDHoverIcon, 
            e -> battleSample.skill4(), bottomTextBox, 0, 4);


        skillButtonsPanel.add(skillA);
        skillButtonsPanel.add(skillB);
        skillButtonsPanel.add(skillC);
        skillButtonsPanel.add(skillD);
    }

    public void displayBottomPanel(){
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setPreferredSize(new Dimension((int) (screenSize.width), (int) (screenSize.height * 0.2)));
        
        JPanel playerWrapper = new JPanel(new BorderLayout());  // GridBagLayout will center its contents
        playerWrapper.setBackground(Color.BLACK);
        enemyWrapper = new JPanel(new BorderLayout());   // GridBagLayout will center its contents
        enemyWrapper.setBackground(Color.BLACK);

        JPanel playerPanel = battleBars.getPlayerStats();
        playerPanel.setBackground(Color.BLACK);
        JPanel enemyPanel = battleBars.getEnemyStats();
        enemyPanel.setBackground(Color.BLACK);

        int verticalPadding = (int)(screenSize.height * 0.07);
        playerWrapper.setBorder(BorderFactory.createEmptyBorder(verticalPadding, (int)(screenSize.width * 0.1), verticalPadding, 0));
        enemyWrapper.setBorder(BorderFactory.createEmptyBorder(verticalPadding, (int)(screenSize.width * 0.06), verticalPadding, 0));
        enemyWrapper.setVisible(false);

        playerWrapper.add(playerPanel, BorderLayout.CENTER);
        enemyWrapper.add(enemyPanel,  BorderLayout.CENTER);

        battleBars.setPlayerStats(
            player.getAttributes().getBaseHp(),
            player.getAttributes().getBaseMana()
        );

        bottomPanel.add(playerWrapper);
        bottomPanel.add(skillButtonsPanel);
        bottomPanel.add(enemyWrapper);
        this.add(bottomPanel, BorderLayout.SOUTH);
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

    public void setSkillButtonsEnabled(boolean enabled) {
        skillA.setEnabled(enabled);
        skillB.setEnabled(enabled &&  battleSample.getPlayer().getSkill2CD() == 0);
        skillC.setEnabled(enabled &&  battleSample.getPlayer().getSkill3CD() == 0);
        skillD.setEnabled(enabled &&  battleSample.getPlayer().getSkill4CD() == 0);
    }

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