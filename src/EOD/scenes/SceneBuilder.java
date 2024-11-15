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
import EOD.gameInterfaces.Freeable;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.Component;

/**
 *
 * @author Joana
 */
public class SceneBuilder extends JPanel implements Freeable{ 
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private ImageList sceneList;
    private int currentSceneIndex;
    public Timer gameLoopTimer ;           // Timer for animating the portal -z
    public Npc ally;
    
    private World world;

    public ArrayList<EchoesObjects> objList;

    public ArrayList<Npc> npcList;

    public ArrayList<Enemy> enemyList;

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

    private void freeLists(){
        ArrayList<Freeable> free = new ArrayList<>();
        if(npcList != null) free.addAll(npcList);
        if(objList != null) free.addAll(objList);
        if(enemyList != null) free.addAll(enemyList);
        if(sceneList != null) free.add(sceneList);
        // if(world != null) free.add(world);
        for(Freeable item : free){
            if(item != null) {
                item.free();
                item = null;
            }
        }

    }

    @Override
    public void free(){
        freeLists();
        this.transitionHandler = null;
        this.gameLoopTimer = null;
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

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setPlayer(ArrayList<Player> playerList, int index) {
        player.setPosX(player.getPosX());
        player.getAnimator().updateBounds();
        if (this.player != null) {
            int previousX = (int)this.player.getPosX();
            
            // First set positions for all players while they're hidden
            for(Player player : playerList) {
                player.setVisible(false);
                player.setPosX(previousX);  
                player.getAnimator().updateBounds(); // Update bounds before showing
                setComponentZOrder(player, 0); // Set Z-order for all players
                System.out.println(player.getCharacterType() + " new pos: " + player.getPosX());
            }
            
            // Setup the new active player
            Player newPlayer = playerList.get(index);
            newPlayer.getAnimator().updateBounds(); // Ensure bounds are up to date
            newPlayer.setVisible(true);
            System.out.println(newPlayer.getCharacterType() + " new pos: " + newPlayer.getPosX());
            
            this.player = newPlayer;
        } else {
            // First time initialization
            this.player = playerList.get(index);
            this.player.getAnimator().updateBounds(); // Initial bounds update
            setComponentZOrder(this.player, 0);
            this.player.setVisible(true);
        }
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
            sceneList.add(ImageIO.read(getClass().getResource("/world1_assets/landOfDeath.png")), 4);
            sceneList.add(ImageIO.read(getClass().getResource("/shop_assets/shopbg.png")), 5);
            sceneList.resizeImageList((int)(screenSize.width), screenSize.height * 0.4);
        } else if (world.getTitle().equals("world2")) {
            // Load images for world2
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/city1.png")), 0);
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/city1.png")), 1);
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/city1.png")), 2);
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/cemetary.png")), 3);
            sceneList.add(ImageIO.read(getClass().getResource("/world2_assets/pillars1.png")), 4);
            sceneList.add(ImageIO.read(getClass().getResource("/shop_assets/shopbg.png")), 5);
            sceneList.resizeImageList((int)(screenSize.width), screenSize.height * 0.4);
        } else if (world.getTitle().equals("world3")) {
            // Load images for world3
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/forest.png")), 0);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/forest.png")), 1);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/forest.png")), 2);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/cemetary.png")), 3);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/misty.png")), 4);
            sceneList.add(ImageIO.read(getClass().getResource("/world3_assets/killerScene.png")), 5);
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
                updateDynamicQuests();
                repaint();
            }
        });
        gameLoopTimer.start();
    }

    public void updateDynamicQuests(){
        if(world == null) return;
        Quests quests = world.getQuests();
        boolean yooDone = false;
        boolean constanceDone = false;
        boolean faithfulDone = false;
        boolean nattyDone = false;

        for(Npc npc : npcList) {
            if ((npc.getName().equals("Yoo") || npc.getName().equals("Constance") || npc.getName().equals("Faithful") || npc.getName().equals("Natty")) 
                && npc.doneQuest) {
                    npc.onExit(null);
                    npc.dialogues.askButton.setVisible(true);
                    switch (npc.getName()) {
                        case "Yoo" -> yooDone = true;
                        case "Constance" -> constanceDone = true;
                        case "Faithful" -> faithfulDone = true;
                        case "Natty" -> nattyDone = true;
                    }
            }
        }

        if(quests.ifActive == 0){
            if((int)player.getPosX() == (int)quests.targetX){
                quests.setQuestStatus(++quests.ifActive);  // Increment quest status
                quests.addQuests();
                quests.targetX = -15;
                for(Npc npc : npcList){
                    npc.setStatic(false);
                }
            }
        }

        if(quests.ifActive == 1){
            if (yooDone && constanceDone && faithfulDone && nattyDone) {
                objList.get(1).setIsActivated(true);
                quests.setQuestStatus(++quests.ifActive);  // Increment quest status
                quests.addQuests();
                quests.targetX = -15;
            }
        }

        if(quests.ifActive == 2){
            if(currentSceneIndex == 1){
                if((int)player.getPosX() == (int)quests.targetX){
                    clickObject(objList.get(1));
                }
            }

            if(currentSceneIndex == 3){
                quests.setQuestStatus(3);
                quests.addQuests();
                quests.targetX = -15;
            }
        }

        if(quests.ifActive == 3){
            Enemy enemy = enemyList.get(0);
            if(enemy.getIsDefeated()){
                quests.setQuestStatus(4);
                quests.addQuests();
            }
        }

        if(quests.ifActive == 4){
            if(npcList.get(3).doneQuest){
                quests.setQuestStatus(5);
                quests.addQuests();
                quests.targetX = -15;
            }
        }

        if(quests.ifActive == 5){
            if(currentSceneIndex != 2)return;
            if((int)player.getPosX() == (int)quests.targetX){  
                clickObject(objList.get(0));
                quests.setQuestStatus(6);
                quests.addQuests();
                quests.targetX = -15;
                objList.get(2).setIsActivated(true);
            }
        }

        if(quests.ifActive == 6){
            if(currentSceneIndex != 2)return;
            if((int)player.getPosX() == (int)quests.targetX){  
                clickObject(objList.get(2));
                quests.setQuestStatus(7);
                quests.addQuests();
                quests.targetX = -15;
            }
        }

        if(quests.ifActive == 7){
            Enemy enemy = enemyList.get(1);
            if(enemy.getIsDefeated()){
                quests.setQuestStatus(8);
                quests.addQuests();
                ally.setStatic(false);
            }
        }

        if(quests.ifActive == 8){
            if(currentSceneIndex != 2)return;
            if((int)player.getPosX() == (int)quests.targetX){  
                clickObject(objList.get(3));
                quests.setQuestStatus(9);
                quests.addQuests();
                quests.targetX = -15;
            }
        }

    }

    private void clickObject(Component obj){
        Component targetComponent = obj;
        // Create a fake MouseEvent targeting the desired component
        MouseEvent fakeClickEvent = new MouseEvent(
            targetComponent,                 // Target component
            MouseEvent.MOUSE_CLICKED,        // Event type
            System.currentTimeMillis(),      // Event time
            0,                               // Modifiers (no modifiers here)
            targetComponent.getX(),          // X position
            targetComponent.getY(),          // Y position
            1,                               // Click count
            false                            // Not a popup trigger
        );
        world.onClick(fakeClickEvent);
    }

    public void updateStats(){
        int playerHp = player.getAttributes().getHp();
        int playerMana = player.getAttributes().getMana();
        int playerBaseHp = player.getAttributes().getBaseHp();
        int playerBaseMana = player.getAttributes().getBaseMana();
        if(player.getAnimator().getIsInBattle()){
        Enemy enemy = world.getBattle().getBattleExperiment().getEnemy();
            int enemyHp = enemy.getHp();
            world.getBattle().updateBars(playerHp, playerMana, enemyHp);
        };
        
        if(world != null){ 
            world.getBattle().updatePlayerBaseStats(playerBaseHp, playerBaseMana);
            world.getBattle().updatePlayerBars(playerHp, playerMana);
        }
    }

    public void configureBattle(Enemy enemy, EchoesObjects portal){
        for(Player player : world.getPlayerList()){
            player.getAnimator().setIsInBattle(true);
            player.setPosX(screenSize.width * 0.35);
            player.getAnimator().setMovingRight(true);
        }
        enemy.setPlayer(player);
        player.setEnemy(enemy);
        world.getBattle().setEnemy(enemy);
        world.getBattle().startBattle();
        world.getBattle().setPortal(portal);
    }

    private void updateBattleState(){
        if(player.getAnimator().getIsInBattle()){
            if(!world.getBattle().getBattleExperiment().getEnemy().getIsDefeated()){
                world.getBattle().updateCooldowns();
            }
            world.closeQuests();
        }
    }
    
    private void updateGameState() {
        if (player != null) {
            Animator animator = player.getAnimator();
            animator.updateAnimation();
            animator.updateMovement();
            animator.updateBounds();
            // System.out.println("Player to update: " + player.getCharacterType());
            
            if(world != null )world.updatePlayerMoneyLabel();

            if(transitionHandler == null) return;
                 // Check if we're at a transition point
                transitionHandler.handleSceneTransition(this, player.getPosX(), world.getPlayerList(), objList, npcList, enemyList);

            if (transitionHandler.isAtNonTransitionPoint(player.getPosX())) {
                transitionHandler.setIsInTransition(false);
            }else{
                transitionHandler.setIsInTransition(transitionHandler.shouldTransitionAtEdge(player, currentSceneIndex));
            }
        }


        if(ally != null){
            ally.getAnimator().updateAnimation();
        }

        if (world != null) {
            // Update skill effects
            if (world.getPlayerList().get(1).getAttributes().skillEffectsRandom != null){
                world.getPlayerList().get(1).getAttributes().skillEffectsRandom.updateEffect();
                double offsetX = 0, offsetY = 0;
                if(player.getCharacterType().equals("priest")){
                   offsetX =  0.42;
                   offsetY =  0.15;
                }else if(player.getCharacterType().equals("wizard")){
                    offsetX =  0.42;
                    offsetY =  0.15;
                }else{
                    offsetX =  0.4;
                    offsetY =  0.15;
                }
                SkillEffects effect = world.getPlayerList().get(1).getAttributes().skillEffectsRandom;
                effect.bindToTarget(player, -effect.getWidth() * offsetX, -effect.getHeight() * offsetY);
            }
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
        debugWorldEnding();
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
        if(world.getPlayerList().get(1).getAttributes().skillEffectsRandom != null)  world.getPlayerList().get(1).getAttributes().skillEffectsRandom.setVisible(world.getPlayerList().get(1).getSkill4CD() != 0);
        for (EchoesObjects obj : objList) {
            if(obj.getName().equals("portal") || obj.getName().equals("portalMiniBoss")){
                obj.setVisible(obj.getIndex() == currentSceneIndex && obj.getIsActivated());
                   
            }else if(obj.getName().equals("portalNextWorld")){
                obj.setVisible(obj.getIndex() == currentSceneIndex && enemyList.get(1).getIsDefeated());
            }
            else{
                obj.setVisible(obj.getIndex() == currentSceneIndex);
            }
        }
        
        if(ally != null) ally.setVisible(ally.getIndex() == currentSceneIndex);
        for (Npc npc : npcList) {
            npc.setVisible(npc.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
        }
        for (Enemy enemy : enemyList) {
            enemy.setVisible(enemy.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
        }
    }

    public void debugWorldEnding() {
        if (world.getTitle().equals("worldEnding")) {
            System.out.println("CurrentSceneIndex: " + getCurrentSceneIndex());
        }
    }
}