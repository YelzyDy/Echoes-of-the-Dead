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
<<<<<<< HEAD
    Npc yoo;
    protected int currentSceneIndex = 0;
    private EchoesObjects shop;
=======
    protected int currentPanelIndex = 0;
    private EchoesObjects shop;     // shop png -z
    private EchoesObjects portal;   // portal png -z
>>>>>>> b958fe001969a6b31101d0f564cae1c387b4bea9
    String type;
    private ArrayList<ImageIcon> portalFrames = new ArrayList<>();  // For portal animation -z
    private int currentPortalFrame = 0;  // Track the current frame -z
    private Timer portalTimer;           // Timer for animating the portal -z

    public SceneBuilder(String type){
        this.type = type;
        this.setBackground(Color.black);
        this.setLayout(null); // Using null layout for absolute positioning
        this.setBounds(0, 0, screenSize.width, (int)(screenSize.height * 0.4));
        System.out.println(this.getSize());       
        loadPortalFrames();  // Call method to load the portal frames -z
        startPortalAnimation();  // Start the animation loop -z
    }
   
    
    public int getNumOfScenes(){
        return sceneList.getSize();
    }
    
    public void initializeCharacter(String charType, String playerName) {
        if(type.equals("world1")){
            character = new Protagonist(playerName, charType, this, 0, (int)(screenSize.height * 0.25));
            this.addMouseListener(new MouseClickListener(character));
            yoo = new Npc("yoo", this, 0, (int)(screenSize.height * 0.225));
      
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
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 0);
            sceneList.resizeImageList((int)(screenSize.width), (int) (screenSize.height * 0.4));
            shop = new EchoesObjects("world1",(int)(screenSize.width * 0.78), (int)(screenSize.height * 0.037), (int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32), "shop", false, true);
<<<<<<< HEAD
            this.add(shop);
           
=======
            this.add(shop); // shop png displayed -z
            portal = new EchoesObjects("world1", (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portal", false, true);
            this.add(portal); // portal png displayed -z
>>>>>>> b958fe001969a6b31101d0f564cae1c387b4bea9
            
        } else if (type.equals("chooseCharacter")) {
            this.setBounds(0, 0, screenSize.width, screenSize.height);
            sceneList = new ImageList();
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/knight.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/priest.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/wizard.png")).getImage(), 0);
            sceneList.resizeImageList(screenSize.width, screenSize.height);         
        }
    }
    
    // Load portal images into the portalFrames list -z
    private void loadPortalFrames() {
        for (int i = 0; i < 30; i++) {
            portalFrames.add(new ImageIcon(getClass().getResource("/world1_assets/portal/portal" + i + ".png")));
        }
    }
    
    // Method to start the portal animation using a Timer - z
    private void startPortalAnimation() {
        int delay = 100;  // Animation speed, 100ms between frames (adjust as needed)
        portalTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPortalFrame = (currentPortalFrame + 1) % portalFrames.size();  // Loop through frames
                repaint();  // Repaint the scene to reflect changes
            }
        });
        portalTimer.start();  // Start the animation timer
    }

    @Override
    protected void paintComponent(Graphics g) {
<<<<<<< HEAD
        super.paintComponent(g);      
        if(currentSceneIndex < sceneList.getSize()){
            g.drawImage(sceneList.get(currentSceneIndex), (int)(sceneList.getX(currentSceneIndex)), 0, this);
        }
        character.draw(g);
        if (yoo != null) {
            yoo.draw(g);  // Draw the NPC
        }
        if(type.equals("world1")){
            shop.setVisible(false);
            if(currentSceneIndex == 2){
                shop.setVisible(true);
            }
            if (currentPanelIndex == 1) {
                g.drawImage(portalFrames.get(currentPortalFrame).getImage(),
                        portal.getX(), portal.getY(),
                        portal.getWidth(), portal.getHeight(), this);
            }
        }
    }
}