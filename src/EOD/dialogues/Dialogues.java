package EOD.dialogues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import EOD.worlds.World;

public class Dialogues{
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private StoryLine story = new StoryLine();
    private AskDialogues askDialogues =  new AskDialogues();
    private JDialog storyDialogue;
    private JButton skipButton, askButton;
    private JLabel textBox;
    private ImageIcon askButtonIcon, askButtonHoverIcon, skipButtonIcon, skipButtonHoverIcon;
    private JPanel buttonPanel;
    private final int width = (int) (screenSize.width * 0.99);
    private final int height = (int) (screenSize.height * 0.55);
    private final int x = 6;
    private final int y = (int) (screenSize.height * 0.44);
    private int size;
    private int ID;

    public void displayDialogues(int ID, World world) {

        // LOAD NPC

        this.ID = ID;
        switch (ID) {
            case 1:
                story.missConstanceIntro();
                break;
            case 3:
                story.nattyIntro();
                break;
            case 5:
                story.yooIntro();
                break;
            case 7:
                story.migginsIntro();
                break;
            case 9:
                story.faithfulIntro();
                break;
            default:
                break;
        }

        // TEXT WINDOW

        storyDialogue = new JDialog(world, "ECHOES OF THE DEAD", Dialog.ModalityType.MODELESS);
        storyDialogue.setUndecorated(true);
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());

        textBox = new JLabel("", SwingConstants.CENTER);
        textBox.setFont(new Font("Monospaced", Font.PLAIN, 28));
        textBox.setForeground(Color.WHITE);
        textBox.setVerticalAlignment(SwingConstants.NORTH);

        storyDialogue.getContentPane().setBackground(Color.BLACK);
        storyDialogue.add(textBox, BorderLayout.CENTER);
        storyDialogue.setLocation(x, y);

        // SKIP BUTTON

        skipButtonIcon = scaleImageIcon("src/button_assets/skipButton.png");
        skipButtonHoverIcon = scaleImageIcon("src/button_assets/skipButtonHover.png");

        skipButton = new JButton(skipButtonIcon);
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);
        skipButton.setPreferredSize(new Dimension(width, height));

        skipButton.setBackground(Color.BLACK);
        skipButton.setFocusPainted(false);
        skipButton.setBorderPainted(false);
        skipButton.setContentAreaFilled(false);

        // ASK BUTTON

        askButtonIcon = scaleImageIcon("src/button_assets/askButton.png");
        askButtonHoverIcon = scaleImageIcon("src/button_assets/askButtonHover.png");

        askButton = new JButton(askButtonIcon);
        askButton.setPreferredSize(new Dimension(width, height));

        askButton.setBackground(Color.BLACK);
        askButton.setFocusPainted(false);
        askButton.setBorderPainted(false);
        askButton.setContentAreaFilled(false);

        // BUTTON PANEL

        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(skipButton, BorderLayout.EAST);
        buttonPanel.add(askButton, BorderLayout.WEST);

        storyDialogue.add(buttonPanel, BorderLayout.NORTH);
        
        this.size = story.getSize();
        textBox.setText(story.getLine(0));
        addMouseListenerForMultipleLines(story, textBox, storyDialogue, this.size);

        // SKIP BUTTON EVENT LISTENERS

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

        // ASK BUTTON EVENT LISTENERS

        askButton.addActionListener(e -> {
            storyDialogue.dispose();
            this.ID++;
            buttonPanel.setVisible(false);
            textBox.setText(null);
            askDialogues.openScrollableOptions(this.ID, storyDialogue, textBox);
        });

        askButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                askButton.setIcon(askButtonHoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                askButton.setIcon(askButtonIcon);
            }
        });

        // DISPLAY

        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);
    }

    // THE METHODS

    public ImageIcon scaleImageIcon(String path) {
        int width = (int) (screenSize.width * 0.15);
        int height = (int) (screenSize.height * 0.15);

        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();

        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }

    private void addMouseListenerForMultipleLines(StoryLine story, JLabel textBox, JDialog storyDialogue, int size) {
        storyDialogue.addMouseListener(new MouseAdapter() {
            int i = 0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (i < size) {
                    textBox.setText(story.getLine(i));
                    i++;
                } else {
                    storyDialogue.dispose();
                }
            }
        });
    }  

}

// Ask me nalang if naa mo questions with the Dialogues, giremove nako ang comments temporarily para easier debugging - Blair