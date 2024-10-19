package echoes.of.the.dead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    public void displayDialogues() {

        // THE WINDOW & DIALOGUES

        story.Exposition();

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

        // THE BUTTONS

        JPanel skillButtonsPanel = new JPanel(new GridLayout(2, 2, 1, 1));
        skillButtonsPanel.setBackground(Color.BLACK);

        // ACTION LISTENERS

        actionA = e -> {
            textBox.setText(story.getLine(1));
            storyDialogue.setVisible(true);
        };

        actionB = e -> storyDialogue.dispose();

        actionC = e -> storyDialogue.dispose();

        actionD = e -> storyDialogue.dispose();

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
        skillButtonsPanel.add(createSkillButton(skillAIcon, skillAHoverIcon, actionA, textBox, 4, 3, 0));
        skillButtonsPanel.add(createSkillButton(skillBIcon, skillBHoverIcon, actionB, textBox, 4, 3, 0));
        skillButtonsPanel.add(createSkillButton(skillCIcon, skillCHoverIcon, actionC, textBox, 4, 3, 0));
        skillButtonsPanel.add(createSkillButton(skillDIcon, skillDHoverIcon, actionD, textBox, 4, 3, 0));

        storyDialogue.add(skillButtonsPanel, BorderLayout.EAST);

        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);

    }

    // THE METHODS

    private JButton createSkillButton(ImageIcon defaultIcon, ImageIcon hoverIcon, ActionListener action, JLabel textBox, int defaultIndex, int hoverIndex, int size) {
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