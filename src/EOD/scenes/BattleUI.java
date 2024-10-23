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
    StoryLine story = new StoryLine();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) (screenSize.width * 0.99);
    private final int height = (int) (screenSize.height * 0.55);
    private final int x = 6;
    private final int y = (int) (screenSize.height * 0.44);
    private JButton skillA, skillB, skillC, skillD;
    private BattleExperiment battleSample;
    private JDialog storyDialogue;
    private EchoesObjects portal;
    private JLabel textBox;
    private JLabel[] cooldownLabels;
    private Protagonist player;

    public BattleUI(Protagonist player, Enemy minion){
        battleSample = new BattleExperiment(player, minion);
        battleSample.setBattleUI(this);
        cooldownLabels = new JLabel[4];
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

        textBox = new JLabel("", SwingConstants.CENTER);
        textBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        textBox.setForeground(Color.WHITE);
        textBox.setVerticalAlignment(SwingConstants.CENTER);

        storyDialogue.getContentPane().setBackground(Color.BLACK);
        storyDialogue.add(textBox, BorderLayout.CENTER);
        storyDialogue.setLocation(x, y);

        // THE BUTTONS

        JPanel skillButtonsPanel = new JPanel(new GridLayout(2, 2, 1, 1));
        skillButtonsPanel.setBackground(Color.BLACK);

        // ACTION LISTENERS
        textBox.setText(story.getLine(0));

        ImageIcon skillAIcon = scaleImageIcon("src/button_assets/basicSkill0.png");
        ImageIcon skillAHoverIcon = scaleImageIcon("src/button_assets/basicSkill1.png");

        ImageIcon skillBIcon = scaleImageIcon("src/button_assets/1stskill0.png");
        ImageIcon skillBHoverIcon = scaleImageIcon("src/button_assets/1stskill1.png");

        ImageIcon skillCIcon = scaleImageIcon("src/button_assets/2ndskill0.png");
        ImageIcon skillCHoverIcon = scaleImageIcon("src/button_assets/2ndskill1.png");

        ImageIcon skillDIcon = scaleImageIcon("src/button_assets/3rdskill0.png");
        ImageIcon skillDHoverIcon = scaleImageIcon("src/button_assets/3rdskill1.png");

        // Create and add skill buttons
        skillA = createSkillButton(story, skillAIcon, skillAHoverIcon, 
            e -> battleSample.skill1(), textBox, 0, 1);

        skillB = createSkillButton(story, skillBIcon, skillBHoverIcon, 
            e -> battleSample.skill2(), textBox, 0, 2);

        skillC = createSkillButton(story, skillCIcon, skillCHoverIcon, 
            e -> battleSample.skill3(), textBox, 0, 3);

        skillD = createSkillButton(story, skillDIcon, skillDHoverIcon, 
            e -> battleSample.skill4(), textBox, 0, 4);


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

    public void setSkillButtonsEnabled(boolean enabled) {
        skillA.setEnabled(enabled);
        skillB.setEnabled(enabled);
        skillC.setEnabled(enabled && player.getSkill3CD() == 0);
        skillD.setEnabled(enabled && player.getSkill4CD() == 0);
    }

    public void updateTurnIndicator(String text){
        textBox.setText(text);
    }

    public void showEnemyAction(String text) {
        textBox.setText(text);
        Timer clearTimer = new Timer(3000, e -> textBox.setText(""));
        clearTimer.setRepeats(false);
        clearTimer.start();
    }

    private JButton createSkillButton(StoryLine story, ImageIcon defaultIcon, ImageIcon hoverIcon, 
        ActionListener action, JLabel textBox, int defaultIndex, int hoverIndex) {

        JLayeredPane layeredPane = new JLayeredPane();
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);
        layeredPane.setPreferredSize(new Dimension(width, height));
            
        JButton button = new JButton(defaultIcon);
        button.setBounds(0, 0, width, height);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(action);
        
        JLabel cooldownLabel = new JLabel("", SwingConstants.CENTER);
        cooldownLabel.setFont(new Font("Arial", Font.BOLD, 36));
        cooldownLabel.setForeground(Color.WHITE);
        cooldownLabel.setBounds(0, 0, width, height);

        cooldownLabels[hoverIndex - 1] = cooldownLabel;

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(hoverIcon);
                textBox.setText(story.getLine(hoverIndex));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(defaultIcon);
                textBox.setText(story.getLine(defaultIndex));
            }
        });

        layeredPane.add(button, Integer.valueOf(0));  // Add button at layer 0
        layeredPane.add(cooldownLabel, Integer.valueOf(1));  // Add label at layer 1 (on top)

        return button;
    }


    public void updateCooldowns() {
        // Update each cooldown label
        updateSkillCooldown(0, 0);
        updateSkillCooldown(1, 0);
        updateSkillCooldown(2, player.getSkill3CD());
        updateSkillCooldown(3, player.getSkill4CD());
    }

    private void updateSkillCooldown(int skillIndex, int cooldown) {
        JLabel cooldownLabel = cooldownLabels[skillIndex];
        if (cooldownLabel != null) {
            if (cooldown > 0) {
                cooldownLabel.setText(String.valueOf(cooldown));
                // Disable the corresponding button
                JButton skillButton = getSkillButton(skillIndex);
                if (skillButton != null) {
                    skillButton.setEnabled(false);
                }
            } else {
                cooldownLabel.setText("");
                // Enable the corresponding button if it's the player's turn
                JButton skillButton = getSkillButton(skillIndex);
                if (skillButton != null) {
                    skillButton.setEnabled(true);
                }
            }
        }
    }

       private JButton getSkillButton(int index) {
        switch (index) {
            case 0: return skillA;
            case 1: return skillB;
            case 2: return skillC;
            case 3: return skillD;
            default: return null;
        }
    }

    public ImageIcon scaleImageIcon(String path) {
        int width = (int) (screenSize.width * 0.3);
        int height = (int) (screenSize.height * 0.3);

        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }

}