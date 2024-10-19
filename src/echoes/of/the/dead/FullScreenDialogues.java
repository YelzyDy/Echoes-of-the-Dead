package echoes.of.the.dead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FullScreenDialogues extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    StoryLine story = new StoryLine();
    private final int width = screenSize.width;
    private final int height = (int)(screenSize.height * 0.97);
    private final int x = 0;
    private final int y = 23;

    public void displayDialogue(int ID) {

        switch (ID) {
            case 0: story.Exposition(); 
                break;
            default:
                break;
        }

        // THE WINDOW

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

        // SKIP BUTTON

        ImageIcon skipButtonIcon = scaleImageIcon("src/button_assets/skipButton.png");
        ImageIcon skipButtonHoverIcon = scaleImageIcon("src/button_assets/skipButtonHover.png");

        JButton skipButton = new JButton(skipButtonIcon);
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);
        skipButton.setPreferredSize(new Dimension(width, height));

        skipButton.setBackground(Color.BLACK);
        skipButton.setFocusPainted(false);
        skipButton.setBorderPainted(false);
        skipButton.setContentAreaFilled(false);

        skipButton.addActionListener(e -> {
            storyDialogue.dispose();
        });

        skipButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                skipButton.setIcon(skipButtonHoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                skipButton.setIcon(skipButtonIcon);
            }
        });

        JPanel skipButtonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        skipButtonPanel.setBackground(Color.BLACK);
        skipButtonPanel.add(skipButton);

        storyDialogue.add(skipButtonPanel, BorderLayout.NORTH);

        textBox.setText(story.getLine(0));
        addMouseListenerForMultipleLines(story, textBox, storyDialogue);

        storyDialogue.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    storyDialogue.dispose();
                }
            }
        });

        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);

    }

    public ImageIcon scaleImageIcon(String path) {
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);

        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }

    private void addMouseListenerForMultipleLines(StoryLine story, JLabel textBox, JDialog storyDialogue) {
        storyDialogue.addMouseListener(new MouseAdapter() {
            int index = 0;
            int size = story.getSize();
            @Override
            public void mouseClicked(MouseEvent e) {
                index++;
                if (index < size) {
                    textBox.setText(story.getLine(index));
                } else {
                    storyDialogue.dispose();
                }
            }
        });
    }  
}

