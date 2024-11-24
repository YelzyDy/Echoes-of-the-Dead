package EOD.dialogues;

import EOD.characters.Npc;
import EOD.gameInterfaces.*;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.scenes.ChoiceUI;
import EOD.scenes.SceneBuilder;
import EOD.utils.SFXPlayer;
import EOD.worlds.World;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Dialogues implements Freeable, MouseInteractable {
    private SFXPlayer sfxPlayer = SFXPlayer.getInstance();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private StoryLine story;
    private AskDialogues askDialogues = new AskDialogues();
    private QuestsDialogues questsDialogues = new QuestsDialogues();
    private JDialog storyDialogue, portraitDialog;
    public EchoesObjects skipButton, askButton, questsButton;
    protected JLabel textBox;
    protected JPanel buttonPanel;
    private final int width = (int) (screenSize.width * 1);
    private final int height = (int) (screenSize.height * 0.6);
    private final int x = 6;
    private final int y = (int) (screenSize.height * 0.4);
    private int size;
    private int ID;
    protected int i = 0;
    private World world;
    private String playerType;
    private String playerName;
    private String portraitPath;
    private boolean isClickableDialogue = true;
    protected JLabel pressToContinueLabel, portraitBox;
    protected Npc npc;
    private SceneBuilder scene;
    private String worldType;
    protected volatile boolean isTyping = false;
    private Thread typewriterThread = null;
    private final int PORTRAIT_WIDTH = (int)(screenSize.width * 0.2);
    private final int PORTRAIT_HEIGHT = (int)(screenSize.height * 0.35);

    public Dialogues() {
        // TEXT WINDOW
        story = new StoryLine();
        storyDialogue = new JDialog(world, "ECHOES OF THE DEAD", Dialog.ModalityType.MODELESS);
        storyDialogue.setUndecorated(true);
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());   

        // Create a panel for the top section that will hold buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.BLACK);
        
        // Text Box setup
        textBox = new JLabel("", SwingConstants.CENTER);
        textBox.setFont(new Font("Monospaced", Font.PLAIN, (int) (screenSize.width * 0.02)));
        textBox.setForeground(Color.WHITE);
        textBox.setHorizontalAlignment(SwingConstants.LEFT);
        textBox.setVerticalAlignment(SwingConstants.TOP);

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
        skipButton.setVisible(false);

        questsButton = new EchoesObjects("button", screenSize.width * 0.8, screenSize.height * 0.4, (int)btnWidth, (int)btnHeight, "questButton", false, true, 2);
        questsButton.addMouseListener(new MouseClickListener(this));
        questsButton.setVisible(false);

        // BUTTON PANEL
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, (int)(screenSize.width * 0.01), (int)(screenSize.width * 0.01)));
        buttonPanel.setOpaque(true);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setVisible(true);

        // Configure button sizes
        Dimension buttonSize = new Dimension((int)btnWidth, (int)btnHeight);

        // Configure buttons
        askButton.setPreferredSize(buttonSize);
        questsButton.setPreferredSize(buttonSize);
        skipButton.setPreferredSize(buttonSize);

        // Add all buttons to the panel
        buttonPanel.add(askButton);
        buttonPanel.add(questsButton);
        buttonPanel.add(skipButton);

        // Add components to top panel
        topPanel.add(buttonPanel, BorderLayout.WEST);

        // Add the top panel to the main dialogue
        storyDialogue.add(topPanel, BorderLayout.NORTH);

        // PRESS TO CONTINUE LABEL
        pressToContinueLabel = new JLabel("Press to Continue", SwingConstants.CENTER);
        pressToContinueLabel.setFont(new Font("Monospaced", Font.PLAIN, (int) (screenSize.width * 0.01)));
        pressToContinueLabel.setForeground(Color.WHITE);
        pressToContinueLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int) (screenSize.height * 0.1), 0));
        pressToContinueLabel.setVisible(true);
        
        storyDialogue.add(pressToContinueLabel, BorderLayout.SOUTH);
    }

    private void setupPortraitDialog(String path) {
        portraitDialog = new JDialog(world, "", Dialog.ModalityType.MODELESS);
        portraitDialog.setUndecorated(true);
        portraitDialog.setBackground(Color.BLACK);
        portraitDialog.setLayout(new BorderLayout());
        
        // PORTRAIT BOX
        portraitBox = new JLabel();
        portraitBox.setPreferredSize(new Dimension(PORTRAIT_WIDTH, PORTRAIT_HEIGHT));
        portraitBox.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        portraitBox.setHorizontalAlignment(SwingConstants.CENTER);
        portraitBox.setVerticalAlignment(SwingConstants.CENTER);
        portraitBox.setOpaque(true);
        portraitBox.setBackground(Color.BLACK);
        
        // Load image and set it to the portraitBox
        try {
            ImageIcon portraitImage = new ImageIcon(getClass().getResource(path));
            // Scale image to fit the label dimensions
            Image scaledImage = portraitImage.getImage().getScaledInstance(PORTRAIT_WIDTH, PORTRAIT_HEIGHT, Image.SCALE_SMOOTH);
            portraitBox.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            e.printStackTrace();
            portraitBox.setText("Finish Quest First!");
            portraitBox.setForeground(Color.RED);
        }
    
        portraitDialog.add(portraitBox);
        portraitDialog.pack();
        
        // Position portrait dialog in top right corner
        int portraitX = (int)(screenSize.width - PORTRAIT_WIDTH - 10); // 20 pixels from right edge
        int portraitY = 30; // 30 pixels from top
        portraitDialog.setLocation(portraitX, portraitY);
        portraitDialog.setVisible(true);
    }    

    public QuestsDialogues getQuestsDialogues(){
        return questsDialogues;
    }

    public void free() {
        // Dispose of the JDialog to release system resources
        if (storyDialogue != null && storyDialogue.isVisible()) {
            storyDialogue.dispose();
            portraitDialog.dispose();
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
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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
        story.setPlayerName(playerName);
        buttonPanel.setVisible(true);
        if(npc != null && npc.activateQuest) questsButton.setVisible(true);
        else questsButton.setVisible(false);
        startVoiceLine(ID, 0);

        // LOAD NPC
        this.ID = ID;
        i = 0;
        switch (ID) {
            case 0:
                story.questNotComplete(); break;
            case 1:
                story.missConstanceIntro(playerType, worldType);
                this.portraitPath = "/portrait_assets/carefree_constance.png";
                break;
            case 2:
                story.nattyIntro();
                this.portraitPath = "/portrait_assets/nanno.png";
                break;
            case 3:
                story.yooIntro();
                this.portraitPath = "/portrait_assets/yoo.png";
                break;
            case 4:
                story.migginsIntro(playerType, worldType);
                this.portraitPath = "/portrait_assets/miggins.png";
                break;
            case 5:
                story.faithfulIntro();
                this.portraitPath = "/portrait_assets/faithful.png";
                break;
            case 6:
                story.knightIntro(playerType);
                this.portraitPath = "/portrait_assets/lawyer.png";
                break;
            case 7:
                story.wizardIntro(playerType);
                this.portraitPath = "/portrait_assets/cs.png";
                break;
            case 8:
                story.priestIntro(playerType);
                this.portraitPath = "/portrait_assets/nurse.png";
                break;
            case 9:
                story.skeleton1Corpse();
                isClickableDialogue = false;
                break;
            case 10:
                story.necromancerCorpse();
                isClickableDialogue = false;
                break;
            case 11:
                story.skeleton2Corpse();
                isClickableDialogue = false;
                break;
            case 12:
                story.gorgonCorpse();
                isClickableDialogue = false;
                break;
            case 13:
                story.rubyIntro();
                this.portraitPath = "/portrait_assets/ruby.png";
                break;
            case 14:
                story.reginaldIntro();
                this.portraitPath = "/portrait_assets/reginald.png";
                break;
            case 15:
                story.akifayIntro();
                this.portraitPath = "/portrait_assets/akifay.png";
                break;
            case 16:
                story.asrielIntro();
                this.portraitPath = "/portrait_assets/asriel.png";
                break;
            case 17:
                story.cheaIntro();
                this.portraitPath = "/portrait_assets/chea.png";
                break;
            case 18:
                story.preEnding(); break;
            case 19:
                story.ending1(); break;
            case 20:
                story.ending2(); break;
            case 21:
                story.ending3(); break;
            case 22:
                story.badEnding(); break;
            case 23:
                story.monoIntro(playerType, worldType);
                this.portraitPath = "/portrait_assets/mono.png";
                break;
            default: break;
        }

        setupPortraitDialog(this.portraitPath);

        if ((ID == 4 || ID == 1 || ID == 2 || ID == 3 || ID == 5) && npc.activateQuest)
            questsButton.setVisible(true);
    

        if (!(ID == 9 || ID == 10 || ID == 11 || ID == 12 || ID == 0))
            skipButton.setVisible(true);

        // if (!(ID == 6 || ID == 7 || ID == 8 || ID == 9 || ID == 10 || ID == 11 || ID == 12))
        //     askButton.setVisible(true);

        this.size = story.getSize();

        if (size > 0) {
            typewriterEffect(story.getLine(i));
            i++; // Increment to point to the next line
        }

        // DISPLAY
        storyDialogue.setFocusable(true);
        storyDialogue.requestFocusInWindow();
        storyDialogue.setVisible(true);

        if (!(ID == 17 || ID == 19 || ID == 21 || ID == 23))
            storyDialogue.add(pressToContinueLabel, BorderLayout.SOUTH); // Adding label to bottom
            
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
    
    public synchronized void typewriterEffect(String text) {
        resetDialogueState(); // Safely terminate any existing thread
        
        isTyping = true;
        typewriterThread = new Thread(() -> {
            try {
                StringBuilder displayText = new StringBuilder();
                int currentIndex = 0;
                boolean inTag = false;
                StringBuilder currentTag = new StringBuilder();
                
                while (currentIndex < text.length()) {
                    if (!isTyping) {
                        return; // Exit if interrupted
                    }
                    
                    char currentChar = text.charAt(currentIndex);
                    
                    if (currentChar == '<') {
                        inTag = true;
                        currentTag.append(currentChar);
                    } else if (currentChar == '>') {
                        inTag = false;
                        currentTag.append(currentChar);
                        displayText.append(currentTag);
                        currentTag.setLength(0); // Reset the tag buffer
                    } else if (inTag) {
                        currentTag.append(currentChar);
                    } else {
                        displayText.append(currentChar);
                        // Only sleep for actual text characters, not HTML tags
                        final String currentText = displayText.toString();
                        SwingUtilities.invokeLater(() -> textBox.setText(currentText));
                        Thread.sleep(30); // Delay between characters
                    }
                    
                    currentIndex++;
                }
                
                isTyping = false; // Mark typing as complete
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Safely handle interruption
            }
        });
        typewriterThread.start();
    }
    
    
    public void handleSetText() {
        
        if (isTyping) {
            resetDialogueState();
            if (i < size + 1) {
                i -= 1;
                textBox.setText(story.getLine(i++));
            }
            return;
        }
    
        if (i < size) {
            // Start the typewriter effect for the next line
            startVoiceLine(ID, i);
            typewriterEffect(story.getLine(i));
            i++;
        } else {
            // Handle end of dialogue
    
            storyDialogue.dispose();
            portraitDialog.dispose();
    
            if (!npc.doneDialogues && (ID == 3 || ID == 1 || ID == 2 || ID == 5 || ID == 4 || ID == 6 ||
                ID == 7 || ID == 8 || ID == 13 || ID == 14 || ID == 15 || ID == 16 || ID == 17  || ID == 18|| ID == 23)) {
                npc.doneDialogues = true;
            }
        }
    }

    void startVoiceLine(int ID, int i){
        if (ID == 1){
            if (worldType.equals("world1") && i == 1){
                sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Constance1.wav");
            } else if (worldType.equals("world2") && i == 1){
                sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Constance2.wav");
            } else if (worldType.equals("world3") && i == 1){
                sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Constance3.wav");
            } else {
                sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Constance1.wav");
                sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Constance2.wav");
                sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Constance3.wav");
            }
        } else if (ID == 2 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Natty.wav");
        } else if (ID == 3 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Yoo.wav");
        } else if (ID == 4){
            if (worldType.equals("world1") && i == 1){
                sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Miggins1.wav");
            } else if (worldType.equals("world2") && i == 0){
                sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Miggins2.wav");
            } else if (worldType.equals("world3") && i == 1){
                sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Miggins3.wav");
            } else {
                sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Miggins1.wav");
                sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Miggins2.wav");
                sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Miggins3.wav");
            }
        } else if (ID == 5 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Faithful.wav");
        } else if (ID == 6 && i == 0){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Knight.wav");
        } else if (ID == 7 && i == 0){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Wizard.wav");
        } else if (ID == 8 && i == 0){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Priest.wav");
        } else if (ID == 9 && i == 15){
            sfxPlayer.playSFX("src/audio_assets/sfx/general/achievement.wav");
        } else if (ID == 10 && i == 15){
            sfxPlayer.playSFX("src/audio_assets/sfx/general/achievement.wav");
        } else if (ID == 11 && i == 15){
            sfxPlayer.playSFX("src/audio_assets/sfx/general/achievement.wav");
        } else if (ID == 13 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Ruby.wav");
        } else if (ID == 14 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Reginald.wav");
        } else if (ID == 15 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Akefay.wav");
        } else if (ID == 16 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Asriel.wav");
        } else if (ID == 17 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Chea.wav");
        } else if (ID == 23 && i == 1){
            sfxPlayer.playSFX("src/audio_assets/sfx/voicelines/Mono.wav");
        } else {
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Natty.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Yoo.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Faithful.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Knight.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Wizard.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Priest.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Ruby.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Reginald.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Akefay.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Asriel.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Chea.wav");
            sfxPlayer.stopSFX("src/audio_assets/sfx/voicelines/Mono.wav");
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
    
    private synchronized void resetDialogueState() {
        isTyping = false; // Signal the thread to stop typing.
        if (typewriterThread != null && typewriterThread.isAlive()) {
            try {
                typewriterThread.interrupt(); // Interrupt the thread.
                typewriterThread.join(); // Ensure the thread finishes before continuing.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status.
                e.printStackTrace();
            }
        }
        typewriterThread = null;
    }
    
    
    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");
        if(source == skipButton){
            sfxPlayer.stopAllSFX();
            storyDialogue.dispose();
            portraitDialog.dispose();
            System.out.println("o index: " + (questsDialogues.getObjectiveIndex() - 1));
            
            if(questsDialogues.isQuestDialoguesActive){
                questsDialogues.isQuestDialoguesActive = false;
                if(questsDialogues.currentDialogue == questsDialogues.story.getArr()){
                    npc.doneQDialogues = true;
                    if (questsDialogues.story.getDoneObjectiveIndex() != -1)npc.doneODialogues[questsDialogues.story.getDoneObjectiveIndex()] = true;
                }else if(questsDialogues.currentDialogue == questsDialogues.story.getOArr() && questsDialogues.story.getObjDoneArray()[questsDialogues.getObjectiveIndex() - 1]){
                    npc.doneODialogues[questsDialogues.getObjectiveIndex() - 1] = true;
                }
            }
            if (!npc.doneDialogues && (ID == 3 || ID == 1 || ID == 2 || ID == 5 || ID == 4 || ID == 6 ||
                ID == 7 || ID == 8 || ID == 13 || ID == 14 || ID == 15 || ID == 16 || ID == 17 || ID == 18|| ID == 23)) {
                npc.doneDialogues = true;
            }  
        } else if(source == askButton) {
            // Stop the typewriter
            resetDialogueState();
            // Clear the text immediately
            SwingUtilities.invokeLater(() -> {
                textBox.setText("");
                storyDialogue.dispose();
                portraitDialog.dispose();
                buttonPanel.setVisible(false);
                askDialogues.setPlayerType(playerType);
                askDialogues.setPlayerName(playerName);
                askDialogues.setWorldType(worldType);
                askDialogues.openScrollableOptions(this.ID, this, textBox);
            });
        }else if(source == questsButton){
            // Stop the typewriter
            resetDialogueState();
            // Clear the text immediately
            SwingUtilities.invokeLater(() -> {
                textBox.setText("");
                storyDialogue.dispose();
                portraitDialog.dispose();
                buttonPanel.setVisible(false);
                questsDialogues.setPlayerName(playerName);
                questsDialogues.setWorldType(worldType);
                questsDialogues.openScrollableOptions(this.ID, this, textBox);
            });
        }else if (source == scene){
            storyDialogue.dispose();
            portraitDialog.dispose();
            if(askDialogues.scrollPane != null)askDialogues.scrollPane.setVisible(false);
            if(questsDialogues.scrollPane != null)questsDialogues.scrollPane.setVisible(false);
        }else if ((source == storyDialogue || source == pressToContinueLabel) && isClickableDialogue){
            if(questsDialogues.isQuestDialoguesActive) questsDialogues.handleSetText();
            else handleSetText();
            //if else id and index size
        }
    }

    @Override
    public void onHover(MouseEvent e) {
    }

    @Override
    public void onExit(MouseEvent e) {
    }
}