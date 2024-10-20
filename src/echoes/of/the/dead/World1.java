/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Joana
 */
public class World1 extends World{

    public World1(String protagType, String playerName){
        super(protagType, playerName, "world1");
        scene = new SceneBuilder(this);
        Welcome();
    }
    
    public void initializeProtagonist(){
        // this constructor automatically imports sprites so we must be careful where to put these(obj and npcs too) -- jian
        scene.protag = new Protagonist(getPlayerName(), getProtagType(), scene, 0, (int)(screenSize.height * 0.24));
        scene.addMouseListener(new MouseClickListener(scene.protag));
        scene.add(scene.protag);
        scene.setComponentZOrder(scene.protag, 0);
        motherPanel.add(scene);
    }

    public void initializeObjects(){
            scene.objList = new ArrayList<>(); // created an arrayList of Echoes Objects
            // we can replace shop with a new class -- jian I will try to create blueprint of the Shop
            scene.objList.add(new EchoesObjects("world1",(int)(screenSize.width * 0.78), (int)(screenSize.height * 0.037), (int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32), "shop", false, true, 2));
            scene.objList.add( new EchoesObjects("world1", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portal", true, false, 29));
            scene.objList.add(new EchoesObjects("world1", (int)(screenSize.width * 0.3), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portalMiniBoss", true, false, 47));
            for (EchoesObjects obj : scene.objList) {
                scene.add(obj);
                if (obj.getName().equals("portal")) {
                    obj.setIndex(1);
                } else if (obj.getName().equals("portalMiniBoss") || (obj.getName().equals("shop"))) {
                    obj.setIndex(2);
                } 
                obj.addMouseListener(new MouseClickListener(this));;
            }
            //sheena add
            // shopBg = new EchoesObjects("shop_assets",(int)(screenSize.width * 0.78), (int)(screenSize.height * 0.037), (int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32), "shop0-bg", false, true, 2);
            // this.add(shopBg);
    }
    public void initializeWorldChars(){
        scene.npcList = new ArrayList<>();
        scene.npcList.add(new Npc("Yoo", "yoo", scene, (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Faithful", "faithful", scene, (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.4));
        scene.npcList.add(new Npc("Miggins", "miggins", scene, (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.25), screenSize.width * 0.5, screenSize.width * 0.62));
        scene.npcList.add(new Npc("Natty", "natty", scene, (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.4), screenSize.width * 0.4, screenSize.width * 0.8));

        for (Npc npc : scene.npcList) {
            npc.setPosY((int)(screenSize.height * 0.21));
            scene.add(npc);
            scene.setComponentZOrder(npc, 2);
            if (npc.getName().equals("Yoo")) {
                npc.setIndex(0);
            } else if (npc.getName().equals("Faithful")) {
                npc.setIndex(1);
            } else if (npc.getName().equals("Miggins")) {
                npc.setIndex(2);
            } else if (npc.getName().equals("Natty")) {
                npc.setIndex(1);
            } 
        }

        miniBoss1 = new MiniBoss("MiniBoss", "necromancer", scene, (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.05), screenSize.width * 0.4, screenSize.width * 0.8, scene.protag);
        scene.add(miniBoss1);

        minions1 = new Minions("Minions", "skeleton", scene, (int) (screenSize.width * 0.65), (int)((screenSize.height * 0.22)), screenSize.width * 0.4, screenSize.width * 0.8, scene.protag);
        scene.add(minions1);

        scene.setComponentZOrder(miniBoss1, 1);
        scene.setComponentZOrder(minions1, 1);  
    }
    


    @Override
    public void onClick(MouseEvent e) {
        super.onClick(e);
        Object source = e.getSource();
        if(source == btn_ok){
            initializeObjects();
            initializeProtagonist();
            initializeWorldChars();
            scene.initializeGameLoop();
        }

        for (EchoesObjects obj : scene.objList) {
            if (source == obj && obj.getName().equals("portal")){
                scene.setCurrentSceneIndex(3);
            } else if (source == obj && obj.getName().equals("portalMiniBoss")) {
                scene.setCurrentSceneIndex(4);
            } 
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
}
