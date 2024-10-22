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
    private BattleExperiment battleSample;
    private JDialog storyDialogue;
    private EchoesObjects portal;
    private JButton skillA, skillB, skillC, skillD;
    private JLabel turnIndicator;
    private JLabel actionDisplay;
    private JPanel statusPanel;

    public BattleUI(Protagonist protag, Enemy minion) {
        battleSample = new BattleExperiment(protag, minion);
        battleSample.setBattleUI(this);
        // Setup main dialog
        storyDialogue = new JDialog(this, "ECHOES OF THE DEAD", Dialog.ModalityType.APPLICATION_MODAL);
        storyDialogue.setUndecorated(true);
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());

        // Turn Indicator
        turnIndicator = new JLabel("Your Turn", SwingConstants.CENTER);
        turnIndicator.setFont(new Font("Arial", Font.BOLD, 24));
        turnIndicator.setForeground(Color.WHITE);

        // Action Display
        actionDisplay = new JLabel("", SwingConstants.CENTER);
        actionDisplay.setFont(new Font("Arial", Font.PLAIN, 18));
        actionDisplay.setForeground(Color.WHITE);

        // Status Panel
        statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(Color.BLACK);
        statusPanel.add(turnIndicator, BorderLayout.NORTH);
        statusPanel.add(actionDisplay, BorderLayout.CENTER);

        // Add status panel to the dialog
        storyDialogue.add(statusPanel, BorderLayout.NORTH);
    }

    public void setPortal(EchoesObjects portal) {
        this.portal = portal;
    }

    public EchoesObjects getPortal() {
        return portal;
    }

    public BattleExperiment getBattleExperiment() {
        return battleSample;
    }

    public JDialog getStoryDialog() {
        return storyDialogue;
    }

    public void displayDialogues() {
        story.skillDetails();

        JLabel textBox = new JLabel("", SwingConstants.CENTER);
        textBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        textBox.setForeground(Color.WHITE);
        textBox.setVerticalAlignment(SwingConstants.CENTER);

        // Background for the dialog
        storyDialogue.getContentPane().setBackground(Color.BLACK);
        storyDialogue.setLocation(x, y);

        // Skill button icons
        ImageIcon skillAIcon = scaleImageIcon("src/button_assets/basicSkill0.png");
        ImageIcon skillAHoverIcon = scaleImageIcon("src/button_assets/basicSkill1.png");

        ImageIcon skillBIcon = scaleImageIcon("src/button_assets/1stskill0.png");
        ImageIcon skillBHoverIcon = scaleImageIcon("src/button_assets/1stskill1.png");

        ImageIcon skillCIcon = scaleImageIcon("src/button_assets/2ndskill0.png");
        ImageIcon skillCHoverIcon = scaleImageIcon("src/button_assets/2ndskill1.png");

        ImageIcon skillDIcon = scaleImageIcon("src/button_assets/3rdskill0.png");
        ImageIcon skillDHoverIcon = scaleImageIcon("src/button_assets/3rdskill1.png");

        // Create a panel for skill buttons
        JPanel skillButtonsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        skillButtonsPanel.setBackground(Color.BLACK);
        skillButtonsPanel.setPreferredSize(new Dimension((int) (screenSize.width * 0.3), (int) (screenSize.height * 0.4)));

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

        // Add skill buttons to the west side
        storyDialogue.add(skillButtonsPanel, BorderLayout.WEST);
        storyDialogue.pack();
        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);
    }

    public void setSkillButtonsEnabled(boolean enabled) {
        skillA.setEnabled(enabled);
        skillB.setEnabled(enabled);
        skillC.setEnabled(enabled);
        skillD.setEnabled(enabled);
    }

    public void updateTurnIndicator(String text) {
        turnIndicator.setText(text);
    }

    public void showEnemyAction(String text) {
        actionDisplay.setText(text);
        // Clear the message after 3 seconds
        Timer clearTimer = new Timer(3000, e -> actionDisplay.setText(""));
        clearTimer.setRepeats(false);
        clearTimer.start();
    }

    private JButton createSkillButton(StoryLine story, ImageIcon defaultIcon, ImageIcon hoverIcon, 
                                      ActionListener action, JLabel textBox, int defaultIndex, int hoverIndex) {
        JButton button = new JButton(defaultIcon);
        button.setPreferredSize(new Dimension(150, 150));
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(action);

        // Cooldown text styling
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 24));

        // Mouse hover effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setIcon(hoverIcon);
                    textBox.setText(story.getLine(hoverIndex));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button.isEnabled()) {
                    button.setIcon(defaultIcon);
                    textBox.setText(story.getLine(defaultIndex));
                }
            }
        });

        return button;
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
