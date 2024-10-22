package EOD.scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

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
    private ActionListener actionA;
    private ActionListener actionB;
    private ActionListener actionC;
    private ActionListener actionD;
    private Protagonist protag;
    private BattleExperiment battleSample;
    private JDialog storyDialogue;
    private EchoesObjects portal;
    private JButton skillA, skillB, skillC, skillD; // Add these button declarations

    private Map<JButton, Integer> cooldowns = new HashMap<>();
    private Map<JButton, Timer> cooldownTimers = new HashMap<>();
    private Map<JButton, Integer> maxCooldowns = new HashMap<>();
    
    // Constants for cooldown durations (in seconds)
    private static final int SKILL1_COOLDOWN = 0;  // Basic attack - no cooldown
    private static final int SKILL2_COOLDOWN = 3;  // 3 seconds
    private static final int SKILL3_COOLDOWN = 5;  // 5 seconds
    private static final int SKILL4_COOLDOWN = 8;  // 8 seconds


    public BattleUI(Protagonist protag, Enemy minion){
        this.protag = protag;
        battleSample = new BattleExperiment(protag, minion);
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
    
    public void displayDialogues() {
        
        story.skillDetails();

        storyDialogue = new JDialog(this, "ECHOES OF THE DEAD", Dialog.ModalityType.APPLICATION_MODAL);
        storyDialogue.setUndecorated(true);
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());

        JLabel textBox = new JLabel("", SwingConstants.CENTER);
        textBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        textBox.setForeground(Color.WHITE);
        textBox.setVerticalAlignment(SwingConstants.CENTER);

        storyDialogue.getContentPane().setBackground(Color.BLACK);
        storyDialogue.add(textBox, BorderLayout.CENTER);
        storyDialogue.setLocation(x, y);

        ImageIcon skillAIcon = scaleImageIcon("src/button_assets/basicSkill0.png");
        ImageIcon skillAHoverIcon = scaleImageIcon("src/button_assets/basicSkill1.png");

        ImageIcon skillBIcon = scaleImageIcon("src/button_assets/1stskill0.png");
        ImageIcon skillBHoverIcon = scaleImageIcon("src/button_assets/1stskill1.png");

        ImageIcon skillCIcon = scaleImageIcon("src/button_assets/2ndskill0.png");
        ImageIcon skillCHoverIcon = scaleImageIcon("src/button_assets/2ndskill1.png");

        ImageIcon skillDIcon = scaleImageIcon("src/button_assets/3rdskill0.png");
        ImageIcon skillDHoverIcon = scaleImageIcon("src/button_assets/3rdskill1.png");

        JPanel skillButtonsPanel = new JPanel(new GridLayout(2, 2, 1, 1));
        skillButtonsPanel.setBackground(Color.BLACK);

        // Create buttons with cooldown functionality
        skillA = createSkillButton(story, skillAIcon, skillAHoverIcon, 
            e -> {
                if (!isOnCooldown(skillA)) {
                    battleSample.skill1();
                    startCooldown(skillA, SKILL1_COOLDOWN);
                }
            }, textBox, 0, 1);

        skillB = createSkillButton(story, skillBIcon, skillBHoverIcon, 
            e -> {
                if (!isOnCooldown(skillB)) {
                    battleSample.skill2();
                    startCooldown(skillB, SKILL2_COOLDOWN);
                }
            }, textBox, 0, 2);

        skillC = createSkillButton(story, skillCIcon, skillCHoverIcon, 
            e -> {
                if (!isOnCooldown(skillC)) {
                    battleSample.skill3();
                    startCooldown(skillC, SKILL3_COOLDOWN);
                }
            }, textBox, 0, 3);

        skillD = createSkillButton(story, skillDIcon, skillDHoverIcon, 
            e -> {
                if (!isOnCooldown(skillD)) {
                    battleSample.skill4();
                    startCooldown(skillD, SKILL4_COOLDOWN);
                }
            }, textBox, 0, 4);

        // Initialize cooldowns for each button
        initializeCooldown(skillA, SKILL1_COOLDOWN);
        initializeCooldown(skillB, SKILL2_COOLDOWN);
        initializeCooldown(skillC, SKILL3_COOLDOWN);
        initializeCooldown(skillD, SKILL4_COOLDOWN);

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

    private void initializeCooldown(JButton button, int maxCooldown) {
        cooldowns.put(button, 0);
        maxCooldowns.put(button, maxCooldown);
    }

    private boolean isOnCooldown(JButton button) {
        return cooldowns.getOrDefault(button, 0) > 0;
    }

    private void startCooldown(JButton button, int duration) {
        if (duration <= 0) return;  // Skip for skills with no cooldown

        button.setEnabled(false);
        cooldowns.put(button, duration);

        // Create overlay for cooldown display
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(button.getPreferredSize());
        button.setLayout(new BorderLayout());
        button.add(layeredPane);

        // Create cooldown timer
        Timer timer = new Timer(1000, e -> {
            int currentCooldown = cooldowns.get(button);
            if (currentCooldown > 0) {
                currentCooldown--;
                cooldowns.put(button, currentCooldown);
                
                // Update cooldown display
                button.setText(String.valueOf(currentCooldown));
                
                if (currentCooldown == 0) {
                    button.setEnabled(true);
                    button.setText("");
                    ((Timer)e.getSource()).stop();
                }
            }
        });

        cooldownTimers.put(button, timer);
        timer.start();
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
        button.setFont(new Font("Arial", Font.BOLD, 24));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isOnCooldown(button)) {
                    button.setIcon(hoverIcon);
                    textBox.setText(story.getLine(hoverIndex));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isOnCooldown(button)) {
                    button.setIcon(defaultIcon);
                    textBox.setText(story.getLine(defaultIndex));
                }
            }
        });

        return button;
    }

    // Method to cancel all cooldowns (useful for scene transitions or game reset)
    public void cancelAllCooldowns() {
        for (Timer timer : cooldownTimers.values()) {
            timer.stop();
        }
        cooldownTimers.clear();
        cooldowns.clear();
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