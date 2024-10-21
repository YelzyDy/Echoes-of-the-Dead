/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.worlds;

import EOD.characters.*;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.objects.shop.Shop;
import EOD.scenes.SceneBuilder;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author zendy
 */
public class World2 extends World {
    private Protagonist protag;
    public World2(String protagType, String playerName, Protagonist protag) {
        this.protag = protag;
        super(protagType, playerName, "world2");
        scene = new SceneBuilder(this);
        Welcome();
    }

    // Implement the necessary methods to initialize the World2 scene
    public void initializeProtagonist() {
        scene.setProtag(protag);
        scene.addMouseListener(new MouseClickListener(protag));
        scene.add(protag);
        scene.setComponentZOrder(protag, 0);
    }

    public void initializeObjects() {
        scene.objList = new ArrayList<>(); 
        scene.objList.add(new Shop(this));
        for (EchoesObjects obj : scene.objList) {
            scene.add(obj);
            obj.addMouseListener(new MouseClickListener(this));;
        }
    }

    public void initializeWorldChars() { //ako lay add nila guys, gi check ra nako if mo open ba ang world2
        // spoiler alert: di mo open ang world2 -z
        scene.npcList = new ArrayList<>();
        scene.npcList.add(new Npc("Yoo", "yoo", scene, (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.8));
        //scene.npcList.add(new Npc("Faithful", "faithful", scene, (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.4));
        scene.npcList.add(new Npc("Miggins", "miggins", scene, (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.25), screenSize.width * 0.5, screenSize.width * 0.62));
        scene.npcList.add(new Npc("Natty", "natty", scene, (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.4), screenSize.width * 0.4, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Constance", "missC", scene, (int)(screenSize.width * 0.6), (int)(screenSize.height * 0.25), screenSize.width * 0.6, screenSize.width * 0.8));
        
        for (Npc npc : scene.npcList) {
            npc.setPosY((int)(screenSize.height * 0.21));
            scene.add(npc);
            scene.setComponentZOrder(npc, 2);
            if (npc.getName().equals("Yoo")) {
                npc.setIndex(0);
            }else if (npc.getName().equals("Constance")) {
                npc.setIndex(0);
            }else if (npc.getName().equals("Miggins")) {
                npc.setIndex(2);
            } else if (npc.getName().equals("Natty")) {
                npc.setIndex(1);
            } 
        }
    }

    public void initializeEnemies(){
        scene.enemyList = new ArrayList<>();
        scene.enemyList.add(new Necromancer("Necromancer", scene, (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.05), screenSize.width * 0.4, screenSize.width * 0.8, 50, 10, protag));
        scene.enemyList.add( new Skeleton1("Skeleton1", scene, (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.18), screenSize.width * 0.4, screenSize.width * 0.8, 7, 8, protag));
        for(Enemy enemy : scene.enemyList){
            scene.add(enemy);
            scene.setComponentZOrder(enemy, 1);
            if (enemy.getName().equals("Skeleton1")) {
                enemy.setIndex(3);
            }else if(enemy.getName().equals("Necromancer")) {
                enemy.setIndex(4);
            }
        }
    }

    @Override
    public void onClick(MouseEvent e) {
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
                    /*World2 world2 = new World2(getProtagType(), getPlayerName(), protag);
                    world2.setVisible(true);
                    this.setVisible(false);*/
                    isBattleStopped = false;
                }
            }
            // } else if (source == obj && obj.getName().equals("shop")){
            //     scene.setCurrentSceneIndex(5);
            // }
        }    
    }

    @Override
    public void onHover(MouseEvent e) {
        Object source = e.getSource();
        
    }

    @Override
    public void onExit(MouseEvent e) {
        Object source = e.getSource();
       
    }

    // Implement other necessary methods, similar to World1
}