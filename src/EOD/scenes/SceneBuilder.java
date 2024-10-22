/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
    // added shop variables: -sheen
    private EchoesObjects shopBg; //shop bg image

    private World world;

    public ArrayList<EchoesObjects> objList;

    public ArrayList<Npc> npcList;

    public ArrayList<Enemy> enemyList;

    private Protagonist protag;

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

    public Protagonist getProtag(){
        return protag;
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

    public void setProtag(Protagonist protag){
        this.protag = protag;
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
        battle = new BattleUI(protag, enemy);
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
            Enemy enemy = battle.getBattleExperiment().getEnemy();
            int enemyHp = enemy.getHp();
            int playerHp = protag.getHp();
            System.out.println(enemyHp);   
            double enemyDeathY = getEnemyDeathPosY(enemy);
            String portalName = battle.getPortal().getName();
            int portalIndex = getPortalIndex(portalName);
            if(enemyHp <= 0){
                protag.getAnimator().setIsInBattle(false);
                protag.getAnimator().setMoving(true);
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
                protag.getAnimator().setIsInBattle(false);
                protag.getAnimator().setMoving(true);
                battle.getStoryDialog().dispose();
                battle = null;
                System.out.println("You lose");
            }
        }
    }
    
    private void updateGameState() {
        if (protag != null) {
            Animator animator = protag.getAnimator();
            animator.updateAnimation();
            animator.updateMovement();
            animator.updateBounds();
        }

        if(world != null){
            for(EchoesObjects obj : objList){
                if(obj != null)
                obj.updateAnimation();;
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
            for (EchoesObjects obj : objList) {
                if( obj.getName().equals("portal") || obj.getName().equals("portalMiniBoss")){
                    obj.setVisible(obj.getIndex() == currentSceneIndex);
                }else{
                    obj.setVisible(obj.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
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
                if( obj.getName().equals("portal") || obj.getName().equals("portalMiniBoss")){
                    obj.setVisible(obj.getIndex() == currentSceneIndex);
                }else{
                    obj.setVisible(obj.getIndex() == currentSceneIndex); // i fix pa nang mo hide if na transport
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
}