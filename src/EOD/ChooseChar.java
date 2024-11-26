/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD;

import EOD.animator.Animator;
import EOD.characters.Player;
import EOD.gameInterfaces.Freeable;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.scenes.SceneBuilder;
import EOD.utils.*;
import EOD.worlds.World;
import EOD.worlds.World1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 *
 * @author Joana
 */
public class ChooseChar extends javax.swing.JFrame implements MouseInteractable {
    private SceneBuilder scene;
    private TransparentPanel btn_knight;
    private TransparentPanel btn_wizard;
    private TransparentPanel btn_priest;
    private EchoesObjects btn_select;
    private EchoesObjects btn_ok;
    private EchoesObjects btn_cancel;
    private EchoesObjects promptPanel;
    private String charType;
    private JTextField nameField;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = screenSize.width;
    private int height = screenSize.height;
    private boolean selectButtonIsEnable = true;

    private Player player;
    private BGMPlayer bgmPlayer;
    private SFXPlayer sfxPlayer;

    //sheena
    private EchoesObjects noNamePrompt; //sheen
    private EchoesObjects noNameOkButton; //sheen


    // CONSTRUCTOR
    public ChooseChar() {
        bgmPlayer = BGMPlayer.getInstance();
        sfxPlayer = sfxPlayer.getInstance();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setTitle("Choose Character"); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false); 
        this.setLocationRelativeTo(null);
        scene = new SceneBuilder();
        // but we have no choice unless we want to draw everything in our ui manually using hard code --jian
        charType = "knight"; // the default charType is "knight" as observed when we open this window, the first background Image is the knight / lawyer --jian
        addScene(); // calling the method addScene. addScene is a method we created ourselves for this class line 58 --jian
        initiializeProtagonist();
        // echoesObjects instances explained in Main class --jian
        btn_select = createObj(
                "button", (int) (width * 0.7),
                (int) (height * 0.82),
                (int) (width * 0.2),
                (int) (height * 0.14),
                "select_button", false, true, 2
            );
        btn_select.setVisible(true);
        btn_select.addMouseListener(new MouseClickListener(this)); // attaching a mouseListener to our btn_select --jian
        // the MouseClickListener class's parameter is a class that implements MouseInteractable, which is this class  --jian
        scene.add(btn_select);

        addTransparentButton();
    }

    private void free(){
        ArrayList <Freeable> list = new ArrayList<>();
        list.add(btn_select);
        list.add(btn_ok);
        list.add(btn_cancel);
        list.add(promptPanel);
        //sheen
        list.add(noNameOkButton);
        list.add(noNamePrompt); 
        list.add(scene);
        for(Freeable item : list){
            if(item != null)item.free();
            item = null;
        }
        charType = null;
        nameField = null;
        screenSize = null;
    }

    // SETTERS - NONE

    // GETTERS - NONE

    // LOCAL METHODS
    public void initiializeProtagonist(){
        player = new Player(charType, (int)(screenSize.width * 0.32), (int)(screenSize.height * 0.45));
        player.setpanel(scene);
        scene.add(player);
        scene.setPlayer(player);
        player.getAnimator().importSprites("character_asset", "idle", (int)(screenSize.height * 0.02), 6);
        scene.initializeGameLoop();
    }
    public void addScene() {
        //method called in line 43; creating an instance or object from the SceneBuilder. Note that SceneBuilder extends JPanel, so SceneBuilder can access methods from JPanel, we can treat it as JPanel --jian
        scene.createSelectionScene(); // calling method from SceneBuilder to createScene which includes displaying background and other objects --jian
        this.add(scene); // again, do not forget to add SwingComponents to a parent Container for it to be visible (in this case, the parent Container is this Frame) --jian
        this.setVisible(true); 
    }
    public void addTransparentButton() {
        btn_knight = new TransparentPanel((int) (width * 0.03), (int) (height * 0.046), (int) (width * 0.158), (int) (height * 0.28));
        btn_knight.addMouseListener(new MouseClickListener(this));
        btn_knight.setVisible(true);
        scene.add(btn_knight); // again, do not forget to add swing components to a parent container, if you don't it will never be visible --jian

        btn_wizard = new TransparentPanel((int) (width * 0.03), (int) (height * 0.359), (int) (width * 0.158), (int) (height * 0.28));
        btn_wizard.addMouseListener(new MouseClickListener(this));
        btn_wizard.setVisible(true);
        scene.add(btn_wizard);

        btn_priest = new TransparentPanel((int) (width * 0.03), (int) (height * 0.674), (int) (width * 0.158), (int) (height * 0.28));
        btn_priest.addMouseListener(new MouseClickListener(this));
        btn_priest.setVisible(true);
        scene.add(btn_priest);
    }

    private java.awt.Font createDynamicFont(int baseFontSize) {
        // Adjust the font size based on window height
        int dynamicFontSize = (int) (height * 0.05); // 5% of the window height --jian
        return new java.awt.Font("SansSerif", java.awt.Font.PLAIN, Math.max(baseFontSize, dynamicFontSize));
    }

    private EchoesObjects createObj(String assetPackage, int x, int y, double width, double height, 
    String type, boolean isAnimated, boolean isState, int numOfSprites){
        // a method that will return EchoesObjects... there's not really much difference if we just call EchoesObjects --jian
        // without this method but it saves us two lines each instantiation :D --jian
        EchoesObjects object = new EchoesObjects(assetPackage, x, y, (int)width, (int)height, type, isAnimated, isState, numOfSprites);
        object.setVisible(true);
        return object;
    }

    private void addPromptNamePanel(){
        promptPanel = createObj(
        "world1",(int) (screenSize.width * 0.20), 
        (int) (screenSize.height * 0.12), 
        (int) (screenSize.width * 0.58), 
        (int) (screenSize.height * 0.7), 
        "namePromptPanel", false, false, 1
        );
        scene.add(promptPanel);  
    }

    //sheen
    private void addNoNamePrompt(){
        // Hide the original promptPanel first
        if (promptPanel != null) {
            promptPanel.setVisible(false);
        }
    
        noNamePrompt = createObj(
            "world1", (int) (screenSize.width * 0.25), (int) (screenSize.height * 0.05), 
            (int) (screenSize.width * 0.50), (int) (screenSize.height * 0.85), 
            "nameEmpty", false, false, 1
        );
        scene.add(noNamePrompt);
        // Ensure noNamePrompt is on top
        scene.revalidate();
        scene.repaint(); 
    }

    private void addNameField(int panelHeight, int panelWidth){
        // Create and add a transparent JTextField to the promptPanel --jian
        nameField = new JTextField(20);
        nameField.setBounds((int) (panelWidth * 0.490), 
                (int) (panelHeight * 0.29), 
                (int) (panelWidth * 0.47), 
                (int) (panelHeight * 0.10));
        nameField.setFont(createDynamicFont(40)); // Font styling --jian
        nameField.setForeground(new Color(238,218,180,255));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        // Make the JTextField transparent
        nameField.setOpaque(false); // This makes the background transparent --jian
        nameField.setBorder(null); 
        nameField.setBackground(new Color(0, 0, 0, 0)); // Optional: Ensure no background color is applied --jian
        promptPanel.add(nameField); 
    }

    private void addOkButton(int panelHeight, int panelWidth){
        btn_ok = createObj(
                "button", (int) (panelWidth * 0.35),
                (int) (panelHeight * 0.45),
                (int) (panelWidth * 0.25),
                (int) (panelHeight * 0.098),
                "ok_button", false, true, 2
            );
            btn_ok.setVisible(true);
            btn_ok.addMouseListener(new MouseClickListener(this));
            promptPanel.add(btn_ok);
    }

    //sheena
    private void addNoNameOK(int panelHeight, int panelWidth){
        noNameOkButton = createObj(
            "button", (int) (panelWidth * 0.96),  // horizontal position
            (int) (panelHeight * 0.65),   // vertical position
            (int) (panelWidth * 0.18),   // Width
            (int) (panelHeight * 0.09),   // Height
            "ok_button",
            false,true,2
        );
        noNameOkButton.setVisible(true);
        noNameOkButton.addMouseListener(new MouseClickListener(this));
        noNamePrompt.add(noNameOkButton);
        scene.setComponentZOrder(noNameOkButton, 0);
    }

    public void addBtnCancel(int panelHeight, int panelWidth){
        btn_cancel = createObj(
                "button", (int) (panelWidth * 0.89),
                (int) (panelHeight * 0.45),
                (int) (panelWidth * 0.25),
                (int) (panelHeight * 0.098),
                "cancel_button", false, true, 2
            );
            btn_cancel.setVisible(true);
            btn_cancel.addMouseListener(new MouseClickListener(this));
            promptPanel.add(btn_cancel);
    }


    // OVERRIDDEN METHODS
    @Override
    public void onClick(MouseEvent e) {
        // Since we have many EchoesObjects (buttons) that is attached with a MouseListener, we have to listen --jian
        // which of them caused the event using e.getSource() --jian
        Object source = e.getSource();
        Animator animator = player.getAnimator();
        sfxPlayer.playSFX("src/audio_assets/sfx/general/click.wav");
        if (source == btn_select) {
            if(!selectButtonIsEnable){ // this prevents the execution of the following lines of code --jian
                // when our promptPanel is Visible --jian
                return;
            }
            selectButtonIsEnable = false;
            addPromptNamePanel(); // if btn_select is clicked, we create the PromptNamePannel using this method --jian
            int width = promptPanel.getWidth();
            int height = promptPanel.getHeight();
            addNameField(width, height);
            addOkButton(width, height);
            addBtnCancel(width, height);
            
            scene.setComponentZOrder(promptPanel, 0);
        } else if (source == btn_knight) {
            charType = "knight";
            player.setCharacterType(charType);
            animator.importSprites("character_asset", "idle",  (int)(screenSize.height * 0.02), 6);
            player.setPosX(width * 0.32); // Update position based on width
            player.setPosY(height * 0.45); // Update position based on height
            scene.setCurrentSceneIndex(0);
        } else if (source == btn_priest) {
            charType = "priest";
            player.setCharacterType(charType);
            animator.importSprites("character_asset", "idle",  (int)(screenSize.height * 0.02), 6);
            player.setPosX(width * 0.349); // Update position based on width
            player.setPosY(height * 0.43); // Update position based on height
            scene.setCurrentSceneIndex(1);
        } else if (source == btn_wizard) {
            charType = "wizard";
            player.setCharacterType(charType);
            animator.importSprites("character_asset", "idle",   (int)(screenSize.height * 0.02), 6);
            player.setPosX(width * 0.34); // Update position based on width
            player.setPosY(height * 0.45); // Update position based on height
            scene.setCurrentSceneIndex(2);
        }else if(source == btn_ok){
            if (nameField != null && nameField.getText().trim().isEmpty()) {
                sfxPlayer.playSFX("src/audio_assets/sfx/general/invalid.wav");
                addNoNamePrompt();
                int width = noNamePrompt.getWidth();
                int height = noNamePrompt.getHeight();
                addNoNameOK(width, height);
                scene.setComponentZOrder(noNamePrompt, 1);
            } else {
                // Handle normal OK button behavior
                scene.gameLoopTimer.stop();
                World window = new World1(charType, nameField.getText());
                window.setVisible(true);
                window.setBGMPlayer(bgmPlayer);
                this.setVisible(false);
                this.free();
            }

        }else if(source == btn_cancel || source == noNameOkButton){ 
            if (noNamePrompt != null && noNamePrompt.isVisible()) {
                scene.remove(noNamePrompt);
                addPromptNamePanel();
                int width = promptPanel.getWidth();
                int height = promptPanel.getHeight();
                addNameField(width, height);
                addOkButton(width, height);
                addBtnCancel(width, height);
                scene.setComponentZOrder(promptPanel, 0);
            } else {
                promptPanel.setVisible(false);
                selectButtonIsEnable = true;
            } 
        }
    }

    @Override
    public void onHover(MouseEvent e) {
       
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
