package EOD.dialogues;

import EOD.characters.Npc;
import EOD.gameInterfaces.Freeable;
import EOD.objects.Quests;
import EOD.utils.SFXPlayer;
import EOD.worlds.World;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
public class Dialogues implements Freeable{
    private SFXPlayer sfxPlayer = SFXPlayer.getInstance();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private StoryLine story = new StoryLine();
    private AskDialogues askDialogues =  new AskDialogues();
    private JDialog storyDialogue;
    private JButton skipButton, askButton;
    private JLabel textBox;
    private ImageIcon askButtonIcon, askButtonHoverIcon, skipButtonIcon, skipButtonHoverIcon;
    private JPanel buttonPanel;
    private final int width = (int) (screenSize.width * 0.99);
    private final int height = (int) (screenSize.height * 0.6);
    private final int x = 6;
    private final int y = (int) (screenSize.height * 0.4);
    private int size;
    private int ID;
    private int i = 0;
    private World world;
    private String playerType;
    private boolean isClickableDialogue = true;
    private JLabel pressToContinueLabel;
    private Quests quests;
    private Npc npc;
    public void free() {
        // Dispose of the JDialog to release system resources
        if (storyDialogue != null && storyDialogue.isVisible()) {
            storyDialogue.dispose();
        }
    
        // Nullify references to large objects
        storyDialogue = null;
        textBox = null;
        buttonPanel = null;
        skipButton = null;
        askButton = null;
    
        // Nullify images (Icons) to release memory
        askButtonIcon = null;
        askButtonHoverIcon = null;
        skipButtonIcon = null;
        skipButtonHoverIcon = null;
    
        // If the story object holds resources, you may want to add a free method there too
        if (story != null) {
            story.free();  // Assuming StoryLine class has a free method that frees its resources
        }
    
        // Reset the ID and other state-related variables
        ID = -1;
        i = 0;
        isClickableDialogue = true;
    }

    public void setNpc(Npc npc){
        this.npc = npc;
    }

    public void setPlayerType(String playerType){
        this.playerType = playerType;
    }

    public void setCoordinates(double x, double y){
        storyDialogue.setLocation((int)x, (int)y);
    }

    public void setDimension(double width, double height){
        storyDialogue.setSize((int)width, (int)height);
    }

    public void setFontSize(double size){
        textBox.setFont(new Font("Monospaced", Font.PLAIN, (int)size));
    }

    public void setQuests(Quests quests){
        this.quests = quests;
    }

    public void displayDialogues(int ID, World world) {
        this.world = world;
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
            case 11:
                story.knightIntro(playerType);
                break;
            case 13:
                story.wizardIntro(playerType);
                break;
            case 15:
                story.priestIntro(playerType);
                break;
            case 17:
                story.skeleton1Corpse();
                isClickableDialogue = false;
                break;
            case 19:
            story.necromancerCorpse();
                isClickableDialogue = false;
                break;
            case 21:
            story.skeleton2Corpse();
                isClickableDialogue = false;
                break;
            case 23:
            story.gorgonCorpse();
                isClickableDialogue = false;
                break;
            case 25:
                story.rubyIntro();
                break;
            case 27:
                story.reginaldIntro();
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
        textBox.setFont(new Font("Monospaced", Font.PLAIN, (int)(screenSize.width * 0.02)));
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
        if(!(ID == 17 || ID == 19 || ID == 21 || ID == 23))
        buttonPanel.add(skipButton, BorderLayout.EAST);
        if(!(ID == 11 || ID == 13 || ID == 15 || ID == 17 || ID == 19 || ID == 21 || ID == 23))
        buttonPanel.add(askButton, BorderLayout.WEST);

        storyDialogue.add(buttonPanel, BorderLayout.NORTH);
        
        this.size = story.getSize();
        textBox.setText(story.getLine(0));
        addMouseListenerForMultipleLines(story, textBox, storyDialogue, this.size);

        // SKIP BUTTON EVENT LISTENERS

        skipButton.addActionListener(e -> {
            System.out.println("click");
            sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");
            storyDialogue.dispose();
            if(!npc.doneQuest  && ID == 7){
                npc.doneQuest = true;
            }
            
            if (!npc.doneQuest && (ID == 5 || ID == 1 || ID == 3 || ID == 9)){
                quests.incQ2Count();
                npc.doneQuest = true;
            }
            if(ID == 11 || ID == 13 || ID == 15){
                world.getScene().remove(world.getScene().ally);
                world.getScene().ally = null;
            }
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
            sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");
            storyDialogue.dispose();
            this.ID++;
            buttonPanel.setVisible(false);
            textBox.setText(null);
            askDialogues.setPlayerType(playerType);
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

        // NEW PRESS TO CONTINUE LABEL
        pressToContinueLabel = new JLabel("Press to Continue", SwingConstants.CENTER);
        pressToContinueLabel.setFont(new Font("Monospaced", Font.PLAIN, (int)(screenSize.width * 0.01)));
        pressToContinueLabel.setForeground(Color.WHITE);
        pressToContinueLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(screenSize.height * 0.1), 0));
        if(!(ID == 17 || ID == 19 || ID == 21 || ID == 23))
        storyDialogue.add(pressToContinueLabel, BorderLayout.SOUTH);  // Adding label to bottom

        pressToContinueLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // When clicked, proceed with the next dialogue
                handleSetText();
            }
        });
        this.size = story.getSize();
        textBox.setText(story.getLine(0));
        addMouseListenerForMultipleLines(story, textBox, storyDialogue, this.size);
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
        i = 0;
        storyDialogue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isClickableDialogue)
                handleSetText();
            }
        });
    }  

    public void handleSetText(){
        if (i < size){
            textBox.setText(story.getLine(i));
            i++;
        }else{
            if(ID == 17 || ID == 19 || ID == 21 || ID == 23) return;
            storyDialogue.dispose();
            if(!npc.doneQuest  && ID == 7){
                npc.doneQuest = true;
            }

            if (!npc.doneQuest && (ID == 5 || ID == 1 || ID == 3 || ID == 9)){
                quests.incQ2Count();
                npc.doneQuest = true;
            }
                    if(ID == 11 || ID == 13 || ID == 15){
                        world.getScene().remove(world.getScene().ally);
                        world.getScene().ally = null;
            }
        }
    }

    public String getText(){
        return textBox.getText();
    }

    public void setVisible(boolean isVisible){
        storyDialogue.setVisible(isVisible);
    }


    public JDialog getStoryJDialog(){
        return storyDialogue;
    }

}

// Ask me nalang if naa mo questions with the Dialogues, giremove nako ang comments temporarily para easier debugging - Blair