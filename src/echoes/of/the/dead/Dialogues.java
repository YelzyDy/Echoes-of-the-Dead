package echoes.of.the.dead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Dialogues extends JFrame {
    private ActionListener actionA;
    private ActionListener actionB;
    private ActionListener actionC;
    private ActionListener actionD;

    StoryLine story = new StoryLine();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public void displayDialogues(int firstIndex, int finalIndex, int order, int boxType) {
        int width, height, x, y;

        if (boxType == 0) {
            width = screenSize.width;
            height = (int)(screenSize.height * 0.97);
            x = 0;
            y = 23;
        } else {
            width = (int)(screenSize.width * 0.99);
            height = (int)(screenSize.height * 0.55);
            x = 6;
            y = (int)(screenSize.height * 0.44);
        }

        // THE WINDOW & DIALOGUES

        JDialog storyDialogue = new JDialog(this, "ECHOES OF THE DEAD", Dialog.ModalityType.APPLICATION_MODAL);
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

        if (order == 0) {

            textBox.setText(story.getLine(firstIndex));
            addMouseListenerForMultipleLines(story, textBox, storyDialogue, firstIndex, finalIndex);

        } else if (order == 1) {

            int randomIndex = new Random().nextInt(finalIndex - firstIndex) + firstIndex;
            textBox.setText(story.getLine(randomIndex));
            storyDialogue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    storyDialogue.dispose();
                }

            });
            
        }

        // THE BUTTONS
        // SKIP BUTTON

        if (boxType == 0 || boxType == 1) {
            JButton skipButton = new JButton("SKIP");
            skipButton.setFont(new Font("Monospaced", Font.PLAIN, 28));
            skipButton.setPreferredSize(new Dimension(150, 50));
            
            skipButton.setBackground(Color.BLACK);
            skipButton.setForeground(Color.WHITE);

            skipButton.addActionListener(e -> {
                storyDialogue.dispose();
            });

            JPanel skipButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            skipButtonPanel.setBackground(Color.BLACK);
            skipButtonPanel.add(skipButton);

            storyDialogue.add(skipButtonPanel, BorderLayout.NORTH);

        } else if (boxType == 2) {

            JPanel skillButtonsPanel = new JPanel(new GridLayout(2, 2, 1, 1));
            skillButtonsPanel.setBackground(Color.BLACK);

            // CALL THE ENEMY OBJ HERE

            do {

                actionA = e -> {
                    textBox.setText(story.getLine(1));
                    storyDialogue.setVisible(true);
                };

                actionB = e -> {
                    storyDialogue.dispose();
                };

                actionC = e -> {
                    storyDialogue.dispose();
                };

                actionD = e -> {
                    storyDialogue.dispose();
                };

            } while (false);

            // Load both default and hover images
            ImageIcon skillAIcon = scaleImageIcon("src/button_assets/basicSkill0.png");
            ImageIcon skillAHoverIcon = scaleImageIcon("src/button_assets/basicSkill1.png");

            ImageIcon skillBIcon = scaleImageIcon("src/button_assets/1stskill0.png");
            ImageIcon skillBHoverIcon = scaleImageIcon("src/button_assets/1stskill1.png");

            ImageIcon skillCIcon = scaleImageIcon("src/button_assets/2ndskill0.png");
            ImageIcon skillCHoverIcon = scaleImageIcon("src/button_assets/2ndskill1.png");

            ImageIcon skillDIcon = scaleImageIcon("src/button_assets/3rdskill0.png");
            ImageIcon skillDHoverIcon = scaleImageIcon("src/button_assets/3rdskill1.png");

            // Create skill buttons with hover icons
            skillButtonsPanel.add(createSkillButton(skillAIcon, skillAHoverIcon, actionA, textBox, firstIndex, 220));
            skillButtonsPanel.add(createSkillButton(skillBIcon, skillBHoverIcon, actionB, textBox, firstIndex, 220));
            skillButtonsPanel.add(createSkillButton(skillCIcon, skillCHoverIcon, actionC, textBox, firstIndex, 220));
            skillButtonsPanel.add(createSkillButton(skillDIcon, skillDHoverIcon, actionD, textBox, firstIndex, 220));

            storyDialogue.add(skillButtonsPanel, BorderLayout.EAST);

        }

        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);
        
    }

    // THE METHODS

    private void addMouseListenerForMultipleLines(StoryLine story, JLabel textBox, JDialog storyDialogue, int firstIndex, int finalIndex) {
        storyDialogue.addMouseListener(new MouseAdapter() {
            private int currentIndex = firstIndex;

            @Override
            public void mouseClicked(MouseEvent e) {
                currentIndex++;
                if (currentIndex <= finalIndex) {
                    textBox.setText(story.getLine(currentIndex));
                } else {
                    storyDialogue.dispose();
                }
            }
        });
    }

    private JButton createSkillButton(ImageIcon defaultIcon, ImageIcon hoverIcon, ActionListener action, JLabel textBox, int defaultIndex, int hoverIndex) {
        JButton button = new JButton(defaultIcon);
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addActionListener(action);
        
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

// Ask me nalang if naa mo questions with the Dialogues, giremove nako ang comments temporarily para easier debugging - Blair