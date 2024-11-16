package EOD.dialogues;

import EOD.characters.Npc;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.scenes.SceneBuilder;
import EOD.utils.SFXPlayer;
import EOD.worlds.World;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import EOD.gameInterfaces.*;
public class Dialogues implements Freeable, MouseInteractable {
    private SFXPlayer sfxPlayer = SFXPlayer.getInstance();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private StoryLine story = new StoryLine();
    private AskDialogues askDialogues = new AskDialogues();
    private JDialog storyDialogue;
    public EchoesObjects skipButton, askButton;
    private JLabel textBox;
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
    protected JLabel pressToContinueLabel;
    protected Npc npc;
    private SceneBuilder scene;
    private String worldType;

    public Dialogues() {
        // TEXT WINDOW
        storyDialogue = new JDialog(world, "ECHOES OF THE DEAD", Dialog.ModalityType.MODELESS);
        storyDialogue.setUndecorated(true);
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());
        

        textBox = new JLabel("", SwingConstants.CENTER);
        textBox.setFont(new Font("Monospaced", Font.PLAIN, (int) (screenSize.width * 0.02)));
        textBox.setForeground(Color.WHITE);
        textBox.setVerticalAlignment(SwingConstants.NORTH);

        storyDialogue.getContentPane().setBackground(Color.BLACK);
        storyDialogue.add(textBox, BorderLayout.CENTER);
        storyDialogue.setLocation(x, y);
        storyDialogue.addMouseListener(new MouseClickListener(this));
        
        // ASK BUTTON
        double btnWidth = (int) (screenSize.width * 0.1);
        double btnHeight = (int) (screenSize.height * 0.1);
        
        askButton = new EchoesObjects("button", screenSize.width * 0.1, screenSize.height * 0.4, (int)btnWidth, (int)btnHeight, "askButton", false, true, 2);
        askButton.addMouseListener(new MouseClickListener(this));
        askButton.setVisible(false);

        // SKIP BUTTON
        skipButton = new EchoesObjects("button", screenSize.width * 0.8, screenSize.height * 0.4, (int)btnWidth, (int)btnHeight, "skipButton", false, true, 2);
        skipButton.addMouseListener(new MouseClickListener(this));
        skipButton.setVisible(true);

        // BUTTON PANEL
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.BLACK);
        
        askButton.setPreferredSize(new Dimension((int) btnWidth, (int) btnHeight));
        skipButton.setPreferredSize(new Dimension((int) btnWidth, (int) btnHeight));

        
        storyDialogue.add(buttonPanel, BorderLayout.NORTH);

        // NEW PRESS TO CONTINUE LABEL
        pressToContinueLabel = new JLabel("Press to Continue", SwingConstants.CENTER);
        pressToContinueLabel.setFont(new Font("Monospaced", Font.PLAIN, (int) (screenSize.width * 0.01)));
        pressToContinueLabel.setForeground(Color.WHITE);
        pressToContinueLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (screenSize.height * 0.1), 0));
        pressToContinueLabel.setVisible(true);
    }

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

        // If the story object holds resources, you may want to add a free method there too
        if (story != null) {
            story.free(); // Assuming StoryLine class has a free method that frees its resources
        }

        // Reset the ID and other state-related variables
        ID = -1;
        i = 0;
        isClickableDialogue = true;
    }

    public void setNpc(Npc npc) {
        this.npc = npc;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public void setCoordinates(double x, double y) {
        storyDialogue.setLocation((int) x, (int) y);
    }

    public void setDimension(double width, double height) {
        storyDialogue.setSize((int) width, (int) height);
    }

    public void setFontSize(double size) {
        textBox.setFont(new Font("Monospaced", Font.PLAIN, (int) size));
    }

    public void displayDialogues(int ID, World world) {
        this.world = world;
        this.scene = world.getScene();
        this.worldType = world.getTitle();
        scene.addMouseListener(new MouseClickListener(this));
        // LOAD NPC
        this.ID = ID;
        i = 0;
        switch (ID) {
            case 0:
                story.questNotComplete();
                break;
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
            case 29:
                story.akifayIntro();
                break;
            case 31:
                story.asrielIntro();
                break;
            case 101:
                story.preEnding();
                break;
            case 103:
                story.ending1();
                break;
            case 105:
                story.ending2();
                break;
            case 107:
                story.ending3();
                break;
            case 109:
                story.badEnding();
                break;
            default:
                break;
        }
        buttonPanel.setVisible(true);
        if (!(ID == 17 || ID == 19 || ID == 21 || ID == 23 || ID == 0))
            buttonPanel.add(skipButton, BorderLayout.EAST);

        if (!(ID == 11 || ID == 13 || ID == 15 || ID == 17 || ID == 19 || ID == 21 || ID == 23))
            buttonPanel.add(askButton, BorderLayout.WEST);

        this.size = story.getSize();
        textBox.setText(story.getLine(0));

        // DISPLAY
        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);

        if (!(ID == 17 || ID == 19 || ID == 21 || ID == 23))
            storyDialogue.add(pressToContinueLabel, BorderLayout.SOUTH); // Adding label to bottom
            pressToContinueLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // When clicked, proceed with the next dialogue
                handleSetText();
            }
        });
        this.size = story.getSize();
        textBox.setText(story.getLine(0));
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
    

    public void handleSetText() {
        System.out.println("handle set text: " + i);
        if (i < size) {
            textBox.setText(story.getLine(i));
            i++;
        } else {
            if (ID == 17 || ID == 19 || ID == 21 || ID == 23) return;
            storyDialogue.dispose();
            System.out.println("Story dialogue dispose");

            if (!npc.doneQuest && (ID == 5 || ID == 1 || ID == 3 || ID == 9 || ID == 7 || ID == 11 || ID == 13 || ID == 15 || ID == 25 || ID == 27)) {
                npc.doneQuest = true;
            }
        }
    }

    public String getText() {
        return textBox.getText();
    }

    public void setVisible(boolean isVisible) {
        storyDialogue.setVisible(isVisible);
    }

    public JDialog getStoryJDialog() {
        return storyDialogue;
    }

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();

        if(source == skipButton){
            sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");
            storyDialogue.dispose();

            if (!npc.doneQuest && (ID == 5 || ID == 1 || ID == 3 || ID == 9 || ID == 7 || ID == 11 || ID == 13 || ID == 15 || ID == 25 || ID == 27)) {
                npc.doneQuest = true;
            }  
        }else if(source == askButton){
            sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");
            storyDialogue.dispose();
            this.ID++;
            buttonPanel.setVisible(false);
            textBox.setText(null);
            askDialogues.setPlayerType(playerType);
            askDialogues.openScrollableOptions(this.ID, this, textBox);
        }else if (source == scene){
            System.out.println("Dispose the dialog");
            storyDialogue.dispose();
        }else if (source == storyDialogue && isClickableDialogue && source != pressToContinueLabel){
            handleSetText();
        }
    }

    @Override
    public void onHover(MouseEvent e) {
    }

    @Override
    public void onExit(MouseEvent e) {
    }
}