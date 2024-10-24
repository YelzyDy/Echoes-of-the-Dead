package EOD.scenes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer; // -z

import java.awt.event.ActionEvent; // -z
import java.awt.event.ActionListener; // -z
import java.util.ArrayList;

import EOD.worlds.*;
import EOD.utils.*;
import EOD.characters.*;
import EOD.objects.*;
import EOD.animator.Animator;
/**
 *
 * @author Joana
 */
public class SceneBuilder extends JPanel{ 
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private ImageList sceneList;
    private int currentSceneIndex;
    private Timer gameLoopTimer ;           // Timer for animating the portal -z

    private World world;

    public ArrayList<EchoesObjects> objList;

    public ArrayList<Npc> npcList;

    public ArrayList<Enemy> enemyList;

    private Protagonist player;

    private BattleUI battle;

    public SceneBuilder(World world){
        this.world = world;
        this.setBackground(Color.black);
        this.setLayout(null); // Using null layout for absolute positioning
        this.setBounds(0, 0, screenSize.width, (int)(screenSize.height * 0.4)); 
        this.setVisible(false);
        currentSceneIndex = 0;
    }

    public SceneBuilder(){
        this.setBackground(Color.black);
        this.setLayout(null); // Using null layout for absolute positioning
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        this.setVisible(true);
        currentSceneIndex = 0;
    }

    public Protagonist getplayer(){
        return player;
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

    public void setPlayer(Protagonist player){
        this.player = player;
    }
    public void setWorld(World world){
        this.world = world;
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

    public void createSelectionScene(){
        sceneList = new ImageList();
        sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/knight.png")).getImage(), 0);
        sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/priest.png")).getImage(), 0);
        sceneList.add(new ImageIcon(getClass().getResource("/character_selection_assets/wizard.png")).getImage(), 0);
        sceneList.resizeImageList(screenSize.width, screenSize.height);     
    }
    
    public void createWorldScene() {
        if (world.getTitle().equals("world1")) {
            sceneList = new ImageList();
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 1);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 2);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/graveyard.jpg")).getImage(), 3); // added scene for inside the minion portal background :> -z
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/pillars.png")).getImage(), 4); // added scene for inside the mini boss portal background :> -z
            sceneList.add(new ImageIcon(getClass().getResource("/shop_assets/shopbg.png")).getImage(), 5); // added shop pop up - sheen
            sceneList.resizeImageList((int)(screenSize.width), screenSize.height * 0.4);
        } else if (world.getTitle().equals("world2")){
            sceneList = new ImageList();
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 0);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 1);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/forest.png")).getImage(), 2);
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/graveyard.jpg")).getImage(), 3); // added scene for inside the minion portal background :> -z
            sceneList.add(new ImageIcon(getClass().getResource("/world1_assets/pillars.png")).getImage(), 4); // added scene for inside the mini boss portal background :> -z
            sceneList.add(new ImageIcon(getClass().getResource("/shop_assets/shopbg.png")).getImage(), 5); // added shop pop up - sheen
            sceneList.resizeImageList((int)(screenSize.width), screenSize.height * 0.4);
        }
    }
    
    public void initializeGameLoop() {
        gameLoopTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameState();
                updateBattleState();
                repaint();
            }
        });
        gameLoopTimer.start();
    }

    public void configureBattle(Enemy enemy, EchoesObjects portal){
        battle = new BattleUI(player, enemy);
        battle.setPortal(portal);
        battle.displayDialogues();
    }

    private double getEnemyDeathPosY(Enemy enemy){
        if(enemy.getName().equals("Skeleton")){
            return 0.3;
        }else if(enemy.getName().equals("Necromancer")){
            return 4;
        }else if(enemy.getName().equals("Skeleton1")){
            return 0.5;
        }
        return 0.0;
    }

    private int getPortalIndex(String name){
        if(name.equals("portal")){
            return 3;
        }else if(name.equals("portalMiniBoss")){
            return 4;
        }
        return -1;
    }

    private void updateBattleState(){
        if(battle != null){
            battle.updateCooldowns();
            Enemy enemy = battle.getBattleExperiment().getEnemy();
            int enemyHp = enemy.getHp();
            int playerHp = player.getAttributes().getHp();

            System.out.println("Player HP: " + playerHp + " Player Mana: " + player.getAttributes().getMana() + " Enemy HP: " + enemyHp 
            + "Skill3 cd: " + player.getSkill3CD() + "Skill4 cd: " + player.getSkill4CD());  
             
            double enemyDeathY = getEnemyDeathPosY(enemy);
            String portalName = battle.getPortal().getName();
            int portalIndex = getPortalIndex(portalName);
            if(enemyHp <= 0){
                player.getAnimator().setIsInBattle(false);
                player.getAnimator().setMoving(true);
                battle.getStoryDialog().dispose();
                battle.getPortal().setIndex(portalIndex);
                world.setIsBattleStopped(true);
                battle = null;
                System.out.println("You won");

                Timer deathAnimationTimer = new Timer(1800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        enemy.getAnimator().triggerDeathAnimation(enemy.getPosY() + enemy.getPosY() * enemyDeathY);
                    }
                });
                deathAnimationTimer.setRepeats(false); // Ensure the timer only fires once
                deathAnimationTimer.start();
            }
            if(playerHp <= 0){
                player.getAnimator().setIsInBattle(false);
                player.getAnimator().setMoving(true);
                battle.getStoryDialog().dispose();
                battle = null;
                System.out.println("You lose");
            }
        }
    }
    
    private void updateGameState() {
        if (player != null) {
            Animator animator = player.getAnimator();
            animator.updateAnimation();
            animator.updateMovement();
            animator.updateBounds();
        }

        if(world != null){

            if(player.getAttributes().skillEffectsRandom!= null) player.getAttributes().skillEffectsRandom.updateEffect();
            if(player.getAttributes().skillEffects2!= null) player.getAttributes().skillEffects2.updateEffect();
            if(player.getAttributes().skillEffects3!= null) player.getAttributes().skillEffects3.updateEffect();
            if(player.getAttributes().skillEffects4!= null) player.getAttributes().skillEffects4.updateEffect();

            for(EchoesObjects obj : objList){
                if(obj != null){
                    obj.updateAnimation();
                }
            }


            for (Npc npc : npcList) {
                Animator animator = npc.getAnimator();
                if(npc != null){
                    animator.updateAnimation(); 
                    animator.updateMovement();
                    animator.updateBounds();
                }
            }

            for (Enemy enemy : enemyList) {
                Animator animator = enemy.getAnimator();
                if(enemy != null){
                    animator.updateAnimation(); 
                    animator.updateMovement();
                    animator.updateBounds();
                }
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentSceneIndex < sceneList.getSize()) { 
            g.drawImage(sceneList.get(currentSceneIndex), (int) (sceneList.getX(currentSceneIndex)), 0, this);
        }
        if(world == null){
            return;
        }
        if (world.getTitle().equals("world1")) {
            // fixed nga if mo balik siya sa index 0, naa gihapon ang shop and portals when dapat wala -z

            if(player.getAttributes().skillEffects3 != null && player.getCharacterType().equals("knight"))player.getAttributes().skillEffects3.setVisible(player.getSkill3CD() != 0);
            if(player.getAttributes().skillEffectsRandom != null && player.getCharacterType().equals("priest"))player.getAttributes().skillEffectsRandom.setVisible(player.getSkill4CD() != 0);
            for (EchoesObjects obj : objList) {
                if(obj.getName().equals("portal") || obj.getName().equals("portalMiniBoss") || obj.getName().equals("portalNextWorld")){
                    obj.setVisible(obj.getIndex() == currentSceneIndex && 
                        (obj.getName().equals("portalNextWorld") ? world.getIsMiniBossDefeated() : true));
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
        } else if (world.getTitle().equals("world2")){
            for (EchoesObjects obj : objList) {        
                obj.setVisible(obj.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
            }
            for (Npc npc : npcList) {
                npc.setVisible(npc.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
            }
            for (Enemy enemy : enemyList) {
                enemy.setVisible(enemy.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
            }
        }
    }
}