/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.worlds;

import EOD.characters.*;
import EOD.listeners.MouseClickListener;
import EOD.scenes.SceneBuilder;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author zendy
 */
public class World2 extends World {
    private Protagonist player;
    public World2(String playerType, String playerName, Protagonist player) {
        super(playerType, playerName, "world2");
        this.player = player;
        scene = new SceneBuilder(this);
        Welcome();
    }

    // Implement the necessary methods to initialize the World2 scene
    public void initializeProtagonist() {
        scene.setPlayer(player);
        scene.addMouseListener(new MouseClickListener(player));
        scene.add(player);
        scene.setComponentZOrder(player, 0);
    }

    public void initializeObjects() {

    }

    public void initializeWorldChars() { 
        scene.npcList = new ArrayList<>();
        scene.npcList.add(new Npc("Faithful", "faithful", scene, (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.4));
        scene.npcList.add(new Npc("Miggins", "miggins", scene, (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.25), screenSize.width * 0.5, screenSize.width * 0.62));
        scene.npcList.add(new Npc("Ruby", "ruby", scene, (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.4), screenSize.width * 0.4, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Renegald", "renegald", scene, (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.4), screenSize.width * 0.2, screenSize.width * 0.6));
        scene.npcList.add(new Npc("Constance", "missC", scene, (int)(screenSize.width * 0.6), (int)(screenSize.height * 0.25), screenSize.width * 0.6, screenSize.width * 0.8));
        
        for (Npc npc : scene.npcList) {
            npc.setPosY((int)(screenSize.height * 0.21));
            scene.add(npc);
            scene.setComponentZOrder(npc, 2);
            if (npc.getName().equals("Ruby")) {
                npc.setIndex(0);
            }else if (npc.getName().equals("Constance")) {
                npc.setIndex(0);
            }else if (npc.getName().equals("Miggins")) {
                npc.setIndex(2);
            } else if (npc.getName().equals("Faithful")) {
                npc.setIndex(1);
            } else if (npc.getName().equals("Renegald")){
                npc.setIndex(1);
            }
        }
    }

    public void initializeEnemies(){
<<<<<<< HEAD
        scene.enemyList = new ArrayList<>();
        scene.enemyList.add(new Necromancer("Necromancer", scene, (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.05), screenSize.width * 0.4, screenSize.width * 0.8, player));
        scene.enemyList.add(new Skeleton1("Skeleton1", scene, (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.22), screenSize.width * 0.4, screenSize.width * 0.8, player));
        for(Enemy enemy : scene.enemyList){
            scene.add(enemy);
            scene.setComponentZOrder(enemy, 1);
            if (enemy.getName().equals("Skeleton1")) {
                enemy.setIndex(3);
            }else if(enemy.getName().equals("Necromancer")) {
                enemy.setIndex(4);
            }
        }
=======
>>>>>>> master
    }

    @Override
    public void onClick(MouseEvent e) {
<<<<<<< HEAD
        super.onClick(e);
        Object source = e.getSource();
        if(source == btn_ok){
            initializeProtagonist();
            initializeObjects();
            initializeWorldChars();
            initializeEnemies();
            scene.initializeGameLoop();
        }

        for (EchoesObjects obj : scene.objList) {
            if (source == obj && obj.getName().equals("portal")){
                if(!isBattleStopped){
                    scene.setCurrentSceneIndex(3);
                }else{
                    scene.setCurrentSceneIndex(1);
                    isBattleStopped = false;
                }
            } else if (source == obj && obj.getName().equals("portalMiniBoss")) {
                if (!isBattleStopped) {
                    scene.setCurrentSceneIndex(4);
                } else {
                    // If the miniboss has been defeated, create a new World2 instance and make it visible
                    /*World2 world2 = new World2(getplayerType(), getPlayerName(), player);
                    world2.setVisible(true);
                    this.setVisible(false);*/
                    isBattleStopped = false;
                }
            }
            // } else if (source == obj && obj.getName().equals("shop")){
            //     scene.setCurrentSceneIndex(5);
            // }
        }    
=======
>>>>>>> master
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {

       
    }

    // Implement other necessary methods, similar to World1
}