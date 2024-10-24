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
    }

    @Override
    public void onClick(MouseEvent e) {
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {

       
    }

    // Implement other necessary methods, similar to World1
}