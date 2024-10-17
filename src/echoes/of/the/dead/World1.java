/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
    }

    private void addOkButton() {
        btn_ok = createObj(
            "button", 
            (int) (promptPanel.getWidth() * 0.59),  // Position relative to promptPanel
            (int) (promptPanel.getHeight() * 0.78),  // Position relative to promptPanel
            (int) (promptPanel.getWidth() * 0.1),
            (int) (promptPanel.getHeight() * 0.1),
            "ok_button", false, true, 2
        );
        promptPanel.add(btn_ok);
        btn_ok.addMouseListener(new MouseClickListener(this));  // Add mouse listener
    }
    
    

    public void Welcome(){  
        addPromptNamePanel();
        addOkButton();
        panel.add(promptPanel);
        panel.setComponentZOrder(btn_ok, 0);
        promptPanel.setVisible(true);
        this.setVisible(true);
    }

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if(source == btn_ok){
            promptPanel.setVisible(false);
            btn_ok.setVisible(false);
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
