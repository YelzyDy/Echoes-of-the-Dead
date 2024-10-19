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
    private int currentSceneIndex = 0;
    private EchoesObjects shop;     // shop png -z
    private EchoesObjects portal;   // portal sa minions -z
    private EchoesObjects portalMB; // portal sa mini boss -z
    private Minions minions1; // minions -z
    private MiniBoss miniBoss1;
    private Npc yoo;
    private Npc miggins;
    private Npc faithful;
    private Npc natty;
    String type;
    private Timer gameLoopTimer ;           // Timer for animating the portal -z
    private boolean isTransportedToSwamp = false; // boolean to know if na transport ba siya sa fight scene -z
    private boolean isTransportedToPillars = false; // same stuff but sa mini boss na portal -z
    
    // added shop variables: -sheen
    private EchoesObjects shopBg; //shop bg image
    private boolean isTransportedToShop = false; 

    public SceneBuilder(String type){
        this.type = type;
        this.setBackground(Color.black);
        this.setLayout(null); // Using null layout for absolute positioning
        this.setBounds(0, 0, screenSize.width, (int)(screenSize.height * 0.4)); 
    }
   
    
    public int getNumOfScenes(){
        return sceneList.getSize();
    }

    public int getCurrentSceneIndex(){
        return currentSceneIndex;
    }

    public void setCurrentSceneIndex(int value){
        this.currentSceneIndex = value;
    }

    public void decCurrentScene(){
        this.currentSceneIndex--;
    }

    public void incCurrentScene(){
        this.currentSceneIndex++;
    }

    public void initializeWorld1Chars(){

            yoo = new Npc("Yoo", "yoo", this, (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.8);
            yoo.setPosY((int)(screenSize.height * 0.21));
            this.add(yoo);

            faithful = new Npc("Faithful", "faithful", this, (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.4); // Add Faithful NPC
            faithful.setPosY((int)(screenSize.height * 0.21)); // Adjust position for Faithful
            this.add(faithful);
            
            miggins = new Npc("Miggins", "miggins", this, (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.25), screenSize.width * 0.5, screenSize.width * 0.62);
            miggins.setPosY((int)(screenSize.height * 0.21));
            this.add(miggins);

            natty = new Npc("Natty", "natty", this, (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.4), screenSize.width * 0.4, screenSize.width * 0.8);
            natty.setPosY((int)(screenSize.height * 0.21));
            this.add(natty);

            miniBoss1 = new MiniBoss("MiniBoss", "gorgon", this, (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.1), screenSize.width * 0.4, screenSize.width * 0.8, character);
            this.add(miniBoss1);

            minions1 = new Minions("Minions", "slime", this, (int) (screenSize.width * 0.65), (int)((screenSize.height * 0.22)-40), screenSize.width * 0.4, screenSize.width * 0.8, character);
            this.add(minions1);
            
            this.setComponentZOrder(yoo, 2);
            this.setComponentZOrder(faithful, 2);
            this.setComponentZOrder(miniBoss1, 1);
            this.setComponentZOrder(minions1, 1);
            this.setComponentZOrder(miggins, 2);
            this.setComponentZOrder(natty, 2);
    }
    
    public void initializeCharacter(String charType, String playerName) {
        if(type.equals("world1")){
            character = new Protagonist(playerName, charType, this, 0, (int)(screenSize.height * 0.24));
            this.addMouseListener(new MouseClickListener(character));
            this.add(character);
            this.setComponentZOrder(character, 0);
            initializeWorld1Chars();
        }else if (type.equals("chooseCharacter")){
            character = new Protagonist("name", charType, this, (int)(screenSize.width * 0.32), (int)(screenSize.height * 0.51));
            character.animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.017), 6);
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
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/swamp.jpg")).getImage(), 3); // added scene for inside the minion portal background :> -z
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/pillars.png")).getImage(), 4); // added scene for inside the mini boss portal background :> -z
            sceneList.add(new ImageIcon(getClass().getResource("/shop_assets/shopbg.png")).getImage(), 5); // added shop pop up - sheen
            
            sceneList.resizeImageList((int)(screenSize.width), screenSize.height * 0.4);
            shop = new EchoesObjects("world1",(int)(screenSize.width * 0.78), (int)(screenSize.height * 0.037), (int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32), "shop", false, true, 2);
            this.add(shop);
            portal = new EchoesObjects("world1", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portal", true, false, 29);
            this.add(portal); // portal minions added -z
            portalMB = new EchoesObjects("world1", (int)(screenSize.width * 0.3), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portalMiniBoss", true, false, 47);
            this.add(portalMB); // portal mini boss added -z
            portal.addMouseListener(new MouseClickListener(this)); // attempted to add mouselistener sa portals -z
            portalMB.addMouseListener(new MouseClickListener(this)); // (up) -z
        
            //sheena add
            shop.addMouseListener(new MouseClickListener(this)); // adds shop mouse listener - sheen
            // shopBg = new EchoesObjects("shop_assets",(int)(screenSize.width * 0.78), (int)(screenSize.height * 0.037), (int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32), "shop0-bg", false, true, 2);
            // this.add(shopBg);

            this.setComponentZOrder(shop, 2);
            this.setComponentZOrder(portal, 2);
            this.setComponentZOrder(portalMB, 2);

            
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
            character.animator.updateAnimation();
            character.animator.updateProtagMovement();
            character.animator.updateBounds();
        }
        if (portal != null && portal.isAnimated()) {
            portal.updateAnimation();
        }
        if (portal != null && portalMB.isAnimated()){
            portalMB.updateAnimation();
        }
        if (shop != null && shop.isAnimated()){ // added shop animation here - sheen
            shop.updateAnimation();
        }
        if (minions1 != null) {
            minions1.animator.updateAnimation(); 
            minions1.animator.updateNPCMovement();
            minions1.animator.updateBounds();
        }
        if (miniBoss1 != null){
            miniBoss1.animator.updateAnimation(); 
            miniBoss1.animator.updateNPCMovement();
            miniBoss1.animator.updateBounds();
        }
        if(yoo != null){
            yoo.animator.updateAnimation(); 
            yoo.animator.updateNPCMovement();
            yoo.animator.updateBounds();
        }

        if(miggins != null){
            miggins.animator.updateAnimation();
            miggins.animator.updateNPCMovement();
            miggins.animator.updateBounds();
        }

        if (faithful != null) {  // Update Faithful NPC
            faithful.animator.updateAnimation();
            faithful.animator.updateNPCMovement();
            faithful.animator.updateBounds();
        }

        if (natty != null){
            natty.animator.updateAnimation();
            natty.animator.updateNPCMovement();
            natty.animator.updateBounds();
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
            shop.setVisible(currentSceneIndex == 2 && !isTransportedToShop); // visibility will base sa current sceneIndex if true - j will add other comments later //added if clicked will be transported to shop -sheen
            portal.setVisible(currentSceneIndex == 1 && !isTransportedToSwamp);  // Hide portal after transport
            portalMB.setVisible(currentSceneIndex == 2 && !isTransportedToPillars);  // Hide portal after transport
            yoo.setVisible(currentSceneIndex == 0);
            miggins.setVisible(currentSceneIndex == 2);
            miniBoss1.setVisible(currentSceneIndex == 4);
            faithful.setVisible(currentSceneIndex == 1);
            natty.setVisible(currentSceneIndex == 1);
            minions1.setVisible(currentSceneIndex == 3);
        }    
    }


    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();

        if(source == portal){
            System.out.println(source);
            currentSceneIndex = 3;
            isTransportedToSwamp = true;
        }else if (source == portalMB) {
            System.out.println(source);
            currentSceneIndex = 4; 
            isTransportedToPillars = true;
        }else if (source == shop) {
            currentSceneIndex = 5;
            isTransportedToShop = true;
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

    /*Ako pa i fix ang bug nga if ma transport siya, 
    dapat di siya maka balik sa forest unless na patay na ang enemy. 
    Also need to fix nga layo kaayo ang character pero maka sud ra siya sa portal,
    dapat di siya maka sud unless duol duol*/
}