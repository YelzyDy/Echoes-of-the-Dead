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
import javax.swing.Timer; // -z
import java.awt.event.ActionEvent; // -z
import java.awt.event.ActionListener; // -z
import java.awt.event.MouseEvent; // -z

/**
 *
 * @author Joana
 */
public class SceneBuilder extends JPanel implements MouseInteractable { // implemented MouseInteractable -z
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected ImageList sceneList;
    protected ImageList spriteList = new ImageList();
    protected Protagonist character;
    protected int currentSceneIndex = 0;
    private EchoesObjects shop;     // shop png -z
    private EchoesObjects portal;   // portal sa minions -z
    private EchoesObjects portalMB; // portal sa mini boss -z
    String type;
    private Timer gameLoopTimer ;           // Timer for animating the portal -z

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
            this.add(character);
            this.setComponentZOrder(character, 0);
        }else if (type.equals("chooseCharacter")){
            character = new Protagonist("name", charType, this, (int)(screenSize.width * 0.32), (int)(screenSize.height * 0.51));
            character.initializeIdleSprites((int)(screenSize.height * 0.017));
            this.add(character);
        }
        initializeGameLoop();
    }
    
    public void createScene() {
        if (type.equals("world1")) {
            sceneList = new ImageList();
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 1);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 2);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 3); // added an extension :> -z
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/swamp.jpg")).getImage(), 4); // added scene for inside the minion portal background :> -z
            sceneList.resizeImageList((int)(screenSize.width), (int) (screenSize.height * 0.4));
            shop = new EchoesObjects("world1",(int)(screenSize.width * 0.78), (int)(screenSize.height * 0.037), (int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32), "shop", false, true, 0);
            this.add(shop); 
            portal = new EchoesObjects("world1", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portal", true, false, 29);
            this.add(portal); // portal minions added -z
            portalMB = new EchoesObjects("world1", (int)(screenSize.width * 0.3), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portalMiniBoss", true, false, 47);
            this.add(portalMB); // portal mini boss added -z
            portal.addMouseListener(new MouseClickListener(this)); // attempted to add mouselistener sa portals -z
            portalMB.addMouseListener(new MouseClickListener(this)); // (up) -z
            
        } else if (type.equals("chooseCharacter")) {
            this.setBounds(0, 0, screenSize.width, screenSize.height);
            sceneList = new ImageList();
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/knight.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/priest.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/wizard.png")).getImage(), 0);
            sceneList.resizeImageList(screenSize.width, screenSize.height);      
        }
    }
    
    private void initializeGameLoop() {
        gameLoopTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameState();
                repaint();
            }
        });
        gameLoopTimer.start();
    }

    private void updateGameState() {
        if (character != null) {
            character.updateAnimation();
            character.updateBounds();
        }
        if (portal != null && portal.isAnimated()) {
            portal.updateAnimation();
        }
        // Add any other game state updates here
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentSceneIndex < sceneList.getSize()) { 
            g.drawImage(sceneList.get(currentSceneIndex), (int) (sceneList.getX(currentSceneIndex)), 0, this);
        }
        if (type.equals("world1")) {
            // fixed nga if mo balik siya sa index 0, naa gihapon ang shop and portals when dapat wala -z
            shop.setVisible(currentSceneIndex == 2);
            portal.setVisible(currentSceneIndex == 1);
            portalMB.setVisible(currentSceneIndex == 2);
        }
    }


    @Override // from MouseInteractable interface -z shet guys di siya mo scene change
    public void onClick(MouseEvent e) {
        if (portal.isVisible() && portal.getBounds().contains(e.getPoint())) {
            currentSceneIndex = 4;
            repaint();
        } else if (portalMB.isVisible() && portalMB.getBounds().contains(e.getPoint())) {
            currentSceneIndex = 4; 
            repaint();
        }
    }


    @Override // from MouseInteractable interface -z
    public void onHover(MouseEvent e) {
        //nothing -z
    }


    @Override // from MouseInteractable interface -z
    public void onExit(MouseEvent e) {
        //nothing -z
    }
}