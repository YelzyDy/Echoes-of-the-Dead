/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

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
    public World1(String characterType, String playerName){
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
    
    public void addScene(){  
        scene = new SceneBuilder("world1");
        scene.createScene();
        scene.initializeCharacter(characterType, playerName);
        this.add(scene);
        this.setVisible(true);
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
