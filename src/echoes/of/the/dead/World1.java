/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.*;
/**
 *
 * @author Joana
 */
public class World1 extends javax.swing.JFrame implements MouseInteractable{
    private String characterType;
    private String playerName;
    private SceneBuilder scene;
    private TransparentPanel btn_shop;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panel;
    //new variables - sheena
    //
    private EchoesObjects promptPanel;
    private EchoesObjects btn_ok;
    private JTextField name;

    public World1(String characterType, String playerName){
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        panel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        panel.setLayout(null); // Set layout for the panel

        this.add(panel);

        this.characterType = characterType;
        this.playerName = playerName;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("World 1");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel); // Set the panel as the content pane
        Welcome();
    }
    
    //tried to add welcome message prompt - sheen 
    private EchoesObjects createObj(String assetPackage, int x, int y, double width, double height, 
    String type, boolean isAnimated, boolean isState, int numOfSprites){
        // a method that will return EchoesObjects... there's not really much difference if we just call EchoesObjects --jian
        // without this method but it saves us two lines each instantiation :D --jian
        EchoesObjects object = new EchoesObjects(assetPackage, x, y, (int)width, (int)height, type, isAnimated, isState, numOfSprites);
        object.setVisible(true);
        return object;
    }

    private void addPromptNamePanel(){
        promptPanel = createObj("world1",(int) (screenSize.width * 0.1), 
        (int) (screenSize.height * 0.1), 
        (int) (screenSize.width * 0.80), 
        (int) (screenSize.height * 0.80), 
        "welcomePrompt", false, false, 1);
        promptPanel.setLayout(null);
        panel.add(promptPanel);
    }

    private void addOkButton(int panelHeight, int panelWidth) {
        btn_ok = createObj(
            "button", 
            (int) (panelWidth * 0.82),  // Position relative to promptPanel
            (int) (panelHeight * 0.35),  // Position relative to promptPanel
            (int) (panelWidth* 0.2),
            (int) (panelHeight* 0.058),
            "ok_button", false, true, 2
        );
        promptPanel.add(btn_ok);
        btn_ok.addMouseListener(new MouseClickListener(this));  // Add mouse listener
    }

    private java.awt.Font createDynamicFont(int baseFontSize) {
        int dynamicFontSize = (int) (screenSize.height * 0.05); 
        return new java.awt.Font("SansSerif", java.awt.Font.PLAIN, Math.max(baseFontSize, dynamicFontSize));
    }
    
    public void addPlayerName(int panelHeight, int panelWidth){
        name = new JTextField(playerName); // Create a JTextField with text
        name.setFont(createDynamicFont(50));
        name.setForeground(new Color(238,218,180,255));
        name.setBackground(new Color(0, 0, 0, 0)); // Set background to transparent
        name.setEditable(false); // Make it non-editable
        name.setBorder(null); // Remove the border
        name.setHorizontalAlignment(JTextField.CENTER); // Center the text horizontally
        name.setBounds((int) (panelWidth * 0.675), 
                (int) (panelHeight* 0.24), 
                (int) (panelWidth * 0.47), 
                (int) (panelHeight* 0.10));
        promptPanel.add(name); // Add textField to the panel
    }

    public void Welcome(){  
        addPromptNamePanel();
        int width = promptPanel.getWidth();
        int height = promptPanel.getHeight();
        addOkButton(width, height);
        addPlayerName(width, height);
        this.setVisible(true);
    }

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if(source == btn_ok){
            promptPanel.setVisible(false);
            scene = new SceneBuilder("world1");
            scene.initializeCharacter(characterType, playerName);
            panel.add(scene);
            scene.createScene();
            
        }
    }

    @Override
    public void onHover(MouseEvent e) {
        Object source = e.getSource();
        
    }

    @Override
    public void onExit(MouseEvent e) {
        Object source = e.getSource();
       
    }
}
