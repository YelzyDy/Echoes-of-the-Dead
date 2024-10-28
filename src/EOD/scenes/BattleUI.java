package EOD.scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import EOD.characters.*;
import EOD.dialogues.*;
import EOD.objects.EchoesObjects;
public class BattleUI extends JFrame {
    private JPanel textPanel = new JPanel(new GridLayout(3, 1));
    private StoryLine story = new StoryLine();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) (screenSize.width * 0.99);
    private final int height = (int) (screenSize.height * 0.55);
    private final int topTextHeight = (int) (screenSize.height * 0.1);
    private final int middleTextHeight = (int) (screenSize.height * 0.2);
    private final int bottomTextHeight = (int) (screenSize.height * 0.25);
    private final int x = 6;
    private final int y = (int) (screenSize.height * 0.44);
    private JButton skillA, skillB, skillC, skillD;
    private ImageIcon skillAIcon, skillAHoverIcon, skillBIcon, skillBHoverIcon, skillCIcon, skillCHoverIcon, skillDIcon, skillDHoverIcon;
    private BattleExperiment battleSample;
    private JDialog storyDialogue;
    private EchoesObjects portal;
    private Protagonist player;
    private JPanel skillButtonsPanel ;
    private JLabel topTextBox, middleTextBox, bottomTextBox;
    private BattleBars battleBars = new BattleBars();
    private int turns;

    public BattleUI(Protagonist player, Enemy minion){
        battleSample = new BattleExperiment(player, minion);
        battleSample.setBattleUI(this);
        this.player = player;
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

    public JDialog getStoryDialog(){
        return storyDialogue;
    }
    public void displayDialogues(){

        // THE WINDOW & DIALOGUES

        story.skillDetails();

        storyDialogue = new JDialog(this, "ECHOES OF THE DEAD", Dialog.ModalityType.APPLICATION_MODAL);
        storyDialogue.setUndecorated(true);
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());

        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.BLACK);

        topTextBox = new JLabel("", SwingConstants.CENTER);
        topTextBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        topTextBox.setForeground(Color.WHITE);
        topTextBox.setVerticalAlignment(SwingConstants.CENTER);
        topTextBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        topTextBox.setMaximumSize(new Dimension(width, topTextHeight));
        textPanel.add(topTextBox);

        middleTextBox = new JLabel("", SwingConstants.CENTER);
        middleTextBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        middleTextBox.setForeground(Color.WHITE);
        middleTextBox.setVerticalAlignment(SwingConstants.CENTER);
        middleTextBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        middleTextBox.setMaximumSize(new Dimension(width, middleTextHeight));
        textPanel.add(middleTextBox);

        bottomTextBox = new JLabel("", SwingConstants.CENTER);
        bottomTextBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        bottomTextBox.setForeground(Color.WHITE);
        bottomTextBox.setVerticalAlignment(SwingConstants.CENTER);
        bottomTextBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        bottomTextBox.setMaximumSize(new Dimension(width, bottomTextHeight));
        textPanel.add(bottomTextBox);

        storyDialogue.getContentPane().setBackground(Color.BLACK);
        storyDialogue.add(textPanel, BorderLayout.CENTER);
        storyDialogue.setLocation(x, y);

        topTextBox.setText("Your Turn");
        middleTextBox.setText("An enemy has appeared!");
        bottomTextBox.setText("<html><center>Prepare to use your skills to defeat them in this battle!<html><center>");

        // BATTLE BARS

        battleBars.setStats(battleSample.getPlayer().getAttributes().getBaseHp(), battleSample.getPlayer().getAttributes().getBaseMana(), battleSample.getEnemy().getHp());

        // THE BUTTONS

        skillButtonsPanel = new JPanel(new GridLayout(2, 2, 0, 0));
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

        storyDialogue.add(skillButtonsPanel, BorderLayout.EAST);

        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);

    }

    // THE METHODS

    public void setPlayerHealth(int HP) {
        battleBars.setPlayerHealth(HP);
    }

    public void setPlayerMana(int MP) {
        battleBars.setPlayerMana(MP);
    }

    public void setEnemyHealth(int HP) {
        battleBars.setEnemyHealth(HP);
    }

    public void setSkillButtonsEnabled(boolean enabled) {
        skillA.setEnabled(enabled);
        skillB.setEnabled(enabled && player.getSkill2CD() == 0);
        skillC.setEnabled(enabled && player.getSkill3CD() == 0);
        skillD.setEnabled(enabled && player.getSkill4CD() == 0);
    }

    public void updateTurnIndicator(String text){
        topTextBox.setText(text);
    }


    public void showAction(String text) {
        middleTextBox.setText(text);
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
        updateSkillCooldown(1, player.getSkill2CD());
        updateSkillCooldown(2, player.getSkill3CD());
        updateSkillCooldown(3, player.getSkill4CD());
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
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.width * 0.15);

        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }

}