package echoes.of.the.dead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Dialogues extends JFrame {
    StoryLine story = new StoryLine();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public void displayDialogues(int firstIndex, int finalIndex, int order, int boxSize) {
        int width, height, x, y;

        if (boxSize == 0) {
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

        JButton button = new JButton("SKIP");
        button.setFont(new Font("Monospaced", Font.PLAIN, 28));
        button.setPreferredSize(new Dimension(500, 50));
        
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        button.addActionListener(e -> {
            storyDialogue.dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(button);

        storyDialogue.add(buttonPanel, BorderLayout.SOUTH);

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
}

// Ask me nalang if naa mo questions with the Dialogues, giremove nako ang comments temporarily para easier debugging - Blair