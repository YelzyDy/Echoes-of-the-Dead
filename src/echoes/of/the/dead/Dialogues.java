package echoes.of.the.dead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Dialogues extends JFrame {
    // This class does not need a constructor
    StoryLine story = new StoryLine();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Accepts firstIndex, finalIndex, order, and boxSize
    public void displayDialogues(int firstIndex, int finalIndex, int order, int boxSize) {
        int width, height, x, y;

        // Box Size Controller
        if (boxSize == 0) {
            width = screenSize.width;
            height = screenSize.height;
            x = 0;
            y = 23;
        } else {
            width = screenSize.width;
            height = (int)(screenSize.height * 0.6);
            x = 0;
            y = (int)(screenSize.height * 0.41);
        }

        // Create a modal dialog for the story
        JDialog storyDialogue = new JDialog(this, "ECHOES OF THE DEAD", Dialog.ModalityType.APPLICATION_MODAL);
        storyDialogue.setUndecorated(true); // Remove title bar and exit button
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());

        // Set up the story and label for the text
        JLabel textLabel = new JLabel("", SwingConstants.CENTER);
        textLabel.setFont(new Font("MonoSpaced", Font.PLAIN, 28));
        textLabel.setForeground(Color.WHITE); // Set text color to white
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        storyDialogue.getContentPane().setBackground(Color.BLACK); // Set the background color of the dialogue to black
        storyDialogue.add(textLabel, BorderLayout.CENTER); // Add the text label to the dialogue
        storyDialogue.setLocation(x, y); // Set the location of the dialogue window

        // Initialize content based on the order parameter
        if (order == 0) { // Show all story lines from firstIndex to finalIndex
            textLabel.setText(story.getLine(firstIndex));
            addMouseListenerForMultipleLines(story, textLabel, storyDialogue, firstIndex, finalIndex);
        } else if (order == 1) { // Show one random story line between firstIndex and finalIndex
            int randomIndex = new Random().nextInt(finalIndex - firstIndex) + firstIndex;
            textLabel.setText(story.getLine(randomIndex));
            storyDialogue.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    storyDialogue.dispose(); // Close the dialog when clicked
                }
            });
        }

        // Add key listener for closing the dialog with ESC key
        storyDialogue.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    storyDialogue.dispose(); // Close the dialog
                }
            }
        });

        // Ensure the dialog can receive key events
        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();

        // Show the dialog box
        storyDialogue.setVisible(true);
    }

    // Helper method to handle multiple lines progression with mouse clicks
    private void addMouseListenerForMultipleLines(StoryLine story, JLabel textLabel, JDialog storyDialogue, int firstIndex, int finalIndex) {
        storyDialogue.addMouseListener(new MouseAdapter() {
            private int currentIndex = firstIndex;

            @Override
            public void mouseClicked(MouseEvent e) {
                currentIndex++;
                if (currentIndex <= finalIndex) {
                    textLabel.setText(story.getLine(currentIndex));
                } else {
                    storyDialogue.dispose(); // Close dialog if no more lines
                }
            }
        });
    }
}