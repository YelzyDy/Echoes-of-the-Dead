/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import javax.swing.JPanel;

/**
 *
 * @author Joana
 */
public class SceneBuilder extends JPanel{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected ImageList sceneList;
    protected ImageList spriteList = new ImageList();
    protected Protagonist character;
    protected int currentSceneIndex = 0;
    private EchoesObjects shop;
    String type;

    public SceneBuilder(String type){
        this.type = type;
        this.setBackground(Color.black);
        this.setLayout(null); // Using null layout for absolute positioning
        this.setBounds(0, 0, screenSize.width, (int)(screenSize.height * 0.4));
        System.out.println(this.getSize());       
    }
   
    
    public int getNumOfScenes(){
        return sceneList.getSize();
    }
    
    public void initializeCharacter(String charType, String playerName) {
        if(type.equals("world1")){
            character = new Protagonist(playerName, charType, this, 0, (int)(screenSize.height * 0.25));
            this.addMouseListener(new MouseClickListener(character));
        }else if (type.equals("chooseCharacter")){
            character = new Protagonist("name", charType, this, (int)(screenSize.width * 0.32), (int)(screenSize.height * 0.51));
            character.initializeIdleSprites((int)(screenSize.height * 0.017));
        }
    }
    
    public void createScene() {
        if (type.equals("world1")) {
            sceneList = new ImageList();
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 0);
            sceneList.resizeImageList((int)(screenSize.width), (int) (screenSize.height * 0.4));
            shop = new EchoesObjects("world1",(int)(screenSize.width * 0.78), (int)(screenSize.height * 0.037), (int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32), "shop", false, true);
            this.add(shop);
            
        } else if (type.equals("chooseCharacter")) {
            this.setBounds(0, 0, screenSize.width, screenSize.height);
            sceneList = new ImageList();
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/knight.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/priest.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/wizard.png")).getImage(), 0);
            sceneList.resizeImageList(screenSize.width, screenSize.height);         
        }
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);      
        
        if(currentSceneIndex < sceneList.getSize()){
            g.drawImage(sceneList.get(currentSceneIndex), (int)(sceneList.getX(currentSceneIndex)), 0, this);
        }
        character.draw(g);
        if(type.equals("world1")){
            shop.setVisible(false);
            if(currentSceneIndex == 1){
                shop.setVisible(true);
            }
        }
    }
}
