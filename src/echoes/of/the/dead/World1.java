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
    SceneBuilder scene;
    TransparentPanel btn_shop;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //new variables - sheena
    //
    EchoesObjects promptPanel;
    // int width = screenSize.width;
    // int height = screenSize.height;
    // EchoesObjects btn_select;
    // EchoesObjects btn_ok;

    public World1(String characterType, String playerName){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        this.add(panel);
        this.characterType = characterType;
        this.playerName = playerName;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("World 1");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        addScene();
    }
    //jian's addScene orig
    /* 
    public void addScene(){  
        scene = new SceneBuilder("world1");
        scene.createScene();
        scene.initializeCharacter(characterType, playerName);
        this.add(scene);
        this.setVisible(true);
        JOptionPane.showMessageDialog(null, "Welcome to Echoes of the Dead!\n" + playerName, "", JOptionPane.INFORMATION_MESSAGE);
    }
   */
    
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
        promptPanel = createObj("world1",(int) (screenSize.width * 0.30), 
        (int) (screenSize.height * 0.013), 
        (int) (screenSize.width * 0.40), 
        (int) (screenSize.height * 0.40), 
        "welcomePrompt", false, false, 1);

        scene.add(promptPanel);  
        promptPanel.setVisible(true);
    
        scene.setComponentZOrder(promptPanel, 1);
    }

    // private void addOkButton(int panelHeight, int panelWidth){
    //     btn_ok = createObj(
    //             "button", (int) (panelWidth * 0.35),
    //             (int) (panelHeight * 0.45),
    //             (int) (panelWidth * 0.25),
    //             (int) (panelHeight * 0.098),
    //             "ok_button", false, true, 2
    //         );
    //         btn_ok.setVisible(true);
    //         btn_ok.addMouseListener(new MouseClickListener(this));
    //         promptPanel.add(btn_ok);
    // }

    private void addOkButton(int panelHeight, int panelWidth) {
        EchoesObjects btn_ok = createObj(
                "button", (int) (panelWidth * 0.35),
                (int) (panelHeight * 0.45),
                (int) (panelWidth * 0.05),
                (int) (panelHeight * 0.098),
                "ok_button", false, true, 2
            );
        btn_ok.setVisible(true);
        
        // Add a mouse listener to handle button click
        btn_ok.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Remove the prompt panel when OK button is clicked
                scene.remove(promptPanel);
                scene.repaint();
            }
        });
    
        promptPanel.add(btn_ok);
    }
    
    //Object source = e.getSource();

    public void addScene(){  
        scene = new SceneBuilder("world1");
        scene.createScene();
        addPromptNamePanel();
        addOkButton(promptPanel.getHeight(), promptPanel.getWidth());
        
        scene.initializeCharacter(characterType, playerName);
        this.add(scene);
        this.setVisible(true);
        
        //JOptionPane.showMessageDialog(null, "Welcome to Echoes of the Dead!\n" + playerName, "", JOptionPane.INFORMATION_MESSAGE);
        //dialogues.displayDialogues(40, 50, 1, 1); // Remove this dialogue box
    }


    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        
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
