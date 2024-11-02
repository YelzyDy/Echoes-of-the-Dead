package EOD.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.Timer; // -z

import java.awt.event.ActionEvent; // -z
import java.awt.event.ActionListener; // -z
import java.util.ArrayList;

import EOD.worlds.*;
import EOD.utils.*;
import EOD.characters.*;
import EOD.characters.enemies.Enemy;
import EOD.objects.*;
import EOD.animator.Animator;

import javax.imageio.ImageIO;
import java.io.IOException;


/**
 *
 * @author Joana
 */
public class SceneBuilder extends JPanel{ 
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private ImageList sceneList;
    private int currentSceneIndex;
    public Timer gameLoopTimer ;           // Timer for animating the portal -z
    
    private World world;

    public ArrayList<EchoesObjects> objList;

    public ArrayList<Npc> npcList;

    public ArrayList<Enemy> enemyList;

    private Player knight;

    private Player priest;

    private Player wizard;

    private Player player;

    private SceneTransitionHandler transitionHandler;

    public SceneBuilder(World world){
        this.world = world;
        this.setBackground(Color.black);
        this.setLayout(null); // Using null layout for absolute positioning
        this.setBounds(0, 0, screenSize.width, (int)(screenSize.height * 0.4)); 
        this.setVisible(false);
        this.transitionHandler = new SceneTransitionHandler(screenSize);
        currentSceneIndex = 0;
        createWorldScene();
    }

    public SceneBuilder(){
        this.setBackground(Color.black);
        this.setLayout(null); 
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        this.setVisible(true);
        currentSceneIndex = 0;
    }

    public Player getKnight(){
        return knight;
    }

    public Player getWizard(){
        return wizard;
    }

    public Player getPriest(){
        return priest;
    }

    public ImageList getSceneList(){
        return sceneList;
    }


    public int getNumOfScenes(){
        return sceneList.getSize();
    }

    public int getCurrentSceneIndex(){
        return currentSceneIndex;
    }

    public void setPlayer(Player player){
        this.player = player;
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

    public void createSelectionScene() {
        sceneList = new ImageList();
        try {

            // Add images to the list with their x positions
            sceneList.add(ImageIO.read(getClass().getResource("/character_selection_assets/knight.png")), 0);
            sceneList.add(ImageIO.read(getClass().getResource("/character_selection_assets/priest.png")), 1);
            sceneList.add(ImageIO.read(getClass().getResource("/character_selection_assets/wizard.png")), 2);

            // Resize image list based on screen size
            sceneList.resizeImageList(screenSize.width, screenSize.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void createWorldScene() {
    sceneList = new ImageList();
    try {
        if (world.getTitle().equals("world1")) {
            // Load images for world1 using getClass().getResource()
            sceneList.add(ImageIO.read(getClass().getResource("/world1_assets/city1.jpg")), 0);
            sceneList.add(ImageIO.read(getClass().getResource("/world1_assets/city2.jpg")), 1);
            sceneList.add(ImageIO.read(getClass().getResource("/world1_assets/city3.jpg")), 2);
            sceneList.add(ImageIO.read(getClass().getResource("/world1_assets/graveyard.jpg")), 3);
            sceneList.add(ImageIO.read(getClass().getResource("/world1_assets/pillars.png")), 4);
            sceneList.add(ImageIO.read(getClass().getResource("/shop_assets/shopbg.png")), 5);
            sceneList.resizeImageList((int)(screenSize.width), screenSize.height * 0.4);
        } else if (world.getTitle().equals("world2")) {
            // Load images for world2
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/city1.png")), 0);
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/city1.png")), 1);
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/city1.png")), 2);
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/cemetary.png")), 3);
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/cemetary.png")), 4);
            sceneList.add(ImageIO.read(getClass().getResource("/shop_assets/shopbg.png")), 5);
            sceneList.resizeImageList((int)(screenSize.width), screenSize.height * 0.4);
        } else if (world.getTitle().equals("world3")) {
            // Load images for world3
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/forest.png")), 0);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/forest.png")), 1);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/forest.png")), 2);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/cemetary.png")), 3);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/cemetary.png")), 4);
            sceneList.add(ImageIO.read(getClass().getResource("/shop_assets/shopbg.png")), 5);
            sceneList.resizeImageList((int)(screenSize.width), screenSize.height * 0.4);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void initializeGameLoop() {
        gameLoopTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameState();
                updateBattleState();
                updateStats();
                repaint();
            }
        });
        gameLoopTimer.start();
    }

    public void updateStats(){
        int playerHp = player.getAttributes().getHp();
        int playerMana = player.getAttributes().getMana();
        if(player.getAnimator().getIsInBattle()){
        Enemy enemy = world.getBattle().getBattleExperiment().getEnemy();
            int enemyHp = enemy.getHp();
            world.getBattle().updateBars(playerHp, playerMana, enemyHp);
        };

        if(world != null) world.getBattle().updatePlayerBars(playerHp, playerMana);
    }

    public void configureBattle(Enemy enemy, EchoesObjects portal){
        world.getBattle().setEnemy(enemy);
        world.getBattle().startBattle();
        world.getBattle().setPortal(portal);
    }

    private void updateBattleState(){
        if(player.getAnimator().getIsInBattle()){
            world.getBattle().updateCooldowns();
            // for debugging
            // System.out.println("Player HP: " + playerHp + " Player Mana: " + player.getAttributes().getMana() + " Enemy HP: " + enemyHp 
            // + "Skill3 cd: " + player.getSkill3CD() + "Skill4 cd: " + player.getSkill4CD());  
        }
    }
    
    private void updateGameState() {
        if (player != null) {
            Animator animator = player.getAnimator();
            animator.updateAnimation();
            animator.updateMovement();
            animator.updateBounds();
            if(world != null )world.updatePlayerMoneyLabel();

            if(transitionHandler == null) return;
                 // Check if we're at a transition point
                transitionHandler.handleSceneTransition(this, player, objList, npcList, enemyList);
            if (transitionHandler.isAtTransitionPoint(player.getPosX(),  getCurrentSceneIndex(), getNumOfScenes() - 3)) {
                animator.stopMovement(); // Stop movement when reaching transition point
            }

            if (transitionHandler.isAtNonTransitionPoint(player.getPosX())) {
                transitionHandler.setIsInTransition(false);
            }

        }

        if (world != null) {
            // Update skill effects
            if (player.getAttributes().skillEffectsRandom != null) player.getAttributes().skillEffectsRandom.updateEffect();
            if (player.getAttributes().skillEffects1 != null) player.getAttributes().skillEffects1.updateEffect();
            if (player.getAttributes().skillEffects2 != null) player.getAttributes().skillEffects2.updateEffect();
            if (player.getAttributes().skillEffects3 != null) player.getAttributes().skillEffects3.updateEffect();
            if (player.getAttributes().skillEffects4 != null) player.getAttributes().skillEffects4.updateEffect();

            if (objList == null) return;

            // Update animations for all game objects
            for (EchoesObjects obj : objList) {
                obj.updateAnimation();
            }

            if (npcList == null) return;

            for (Npc npc : npcList) {
                Animator animator = npc.getAnimator();
                animator.updateAnimation(); 
                animator.updateMovement();
                animator.updateBounds();
            }

            if (enemyList == null) return;

            for (Enemy enemy : enemyList) {
                Animator animator = enemy.getAnimator();
                animator.updateAnimation();
                animator.updateMovement();
                animator.updateBounds();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
        if (currentSceneIndex < sceneList.getSize()) { 
            g.drawImage(sceneList.get(currentSceneIndex), 0, 0, this);
        }

        if(world == null){
            return;
        }
    
        if(player == null) return;
        if(player.getAttributes().skillEffects3 != null && player.isKnight())player.getAttributes().skillEffects3.setVisible(player.getShieldBuffRemaining() != 0);
        if(player.getAttributes().skillEffectsRandom != null && player.isPriest())player.getAttributes().skillEffectsRandom.setVisible(player.getSkill4CD() != 0);
        for (EchoesObjects obj : objList) {
            if(obj.getName().equals("portal") || obj.getName().equals("portalMiniBoss") || obj.getName().equals("portalNextWorld")){
                obj.setVisible(obj.getIndex() == currentSceneIndex && 
                    (obj.getName().equals("portalNextWorld") ? enemyList.get(1).getIsDefeated() : true));
            }else{
                obj.setVisible(obj.getIndex() == currentSceneIndex);
            }
        }
        
        for (Npc npc : npcList) {
            npc.setVisible(npc.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
        }
        for (Enemy enemy : enemyList) {
            enemy.setVisible(enemy.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
        }
    }
}