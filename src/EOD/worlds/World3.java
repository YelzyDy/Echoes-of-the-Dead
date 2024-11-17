/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.worlds;

import EOD.characters.*;
import EOD.characters.enemies.Enemy;
import EOD.characters.enemies.Killer;
import EOD.characters.enemies.Skeleton3;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.objects.portals.GreenPortal;
import EOD.objects.portals.PurplePortal;
import EOD.objects.portals.RedPortal;
import EOD.objects.shop.ShopExterior;
import EOD.scenes.ChoiceUI;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import java.util.ArrayList;
/**
 *
 * @author zendy
 */
public class World3 extends World{
    private boolean isKillerFound;
    private ChoiceUI choicepan;
    public World3(Player player, ArrayList<Player> playerList){
        super("world3");
        scene = new SceneBuilder(this);
        bgmPlayer = BGMPlayer.getInstance();
        bgmPlayer.playBGM("src/audio_assets/bgm/world3bgm.wav");
        
        // Set up main player
        this.player = player;
        this.playerList = playerList;
        this.playerName = player.getName();
        
        Welcome();
    }

    @Override
    public void initializeAllyProfiles() {
        player.getAllyProfiles().setWorld(this);
    }
    
    @Override
    public void initializePlayerProfile(){
        player.getPlayerProfile().setPanel(getLayeredPane());
    }

    
    @Override
    public void initializeProtagonist(){
        player.setPosX(0);
        player.setPosY((int)(screenSize.height * 0.24));
        player.setVisible(true);
        player.setpanel(scene);
        player.setWorld(this);
        player.configureSkills();
        
        System.out.println("Player type in world2: " + player.getCharacterType());
        
        // Add player to scene
        scene.add(player);
        scene.setPlayer(player);
        scene.setComponentZOrder(player, 0);
        scene.addMouseListener(new MouseClickListener(player));

        // Set up player attributes
        this.playerName = player.getName();
        configureShopAndInventory();
        setMoney(player.getAttributes().getMoney());
        setMoneyLabel(player.getAttributes().getMoney() + "");
        for (Player p : playerList) {
            if (p != player) {  // Skip the main player
                p.setpanel(scene);
                p.setVisible(false);
                p.setWorld(this);
                p.configureSkills();
                p.setPosY((int)(screenSize.height * 0.24));
                scene.add(p);
                scene.addMouseListener(new MouseClickListener(p));
                scene.setComponentZOrder(p, 0);
            }
        }
    }

    @Override
    public void initializeObjects(){
        scene.objList = new ArrayList<>(); // created an arrayList of Echoes Objects
        // we can replace shop with a new class -- jian I will try to create blueprint of the Shop
          scene.objList.add(new ShopExterior());
        scene.objList.add(new GreenPortal());
        scene.objList.add(new RedPortal());
        scene.objList.add(new PurplePortal());
        for (EchoesObjects obj : scene.objList) {
            obj.setWorld(this);
            scene.add(obj);
        }
    }

    @Override
    public void initializeWorldChars(){
        scene.npcList = new ArrayList<>();
        scene.npcList.add(new Npc("Chea", "chea",  (int)(screenSize.width * 0.5), (int)(screenSize.height * 0.21), screenSize.width * 0.1, screenSize.width * 0.55));
        scene.npcList.add(new Npc("Natty", "natty", (int)(screenSize.width * 0.45), (int)(screenSize.height * 0.21), screenSize.width * 0.1, screenSize.width * 0.55));
        scene.npcList.add(new Npc("Asriel", "asriel", (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.21), screenSize.width * 0.2, screenSize.width * 0.4));
        scene.npcList.add(new Npc("Akefay", "akefay", (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.21), screenSize.width * 0.2, screenSize.width * 0.6));
        scene.npcList.add(new Npc("Miggins", "miggins",  (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.21), screenSize.width * 0.5, screenSize.width * 0.62));
        scene.npcList.add(new Npc("Yoo", "yoo", (int)(screenSize.width * 0.6), (int)(screenSize.height * 0.21), screenSize.width * 0.6, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Constance", "missC", (int)(screenSize.width * 0.6), (int)(screenSize.height * 0.21), screenSize.width * 0.6, screenSize.width * 0.8));
        scene.npcList.add(new Reaper());
        
        for (Npc npc : scene.npcList) {
            scene.add(npc);
            scene.setComponentZOrder(npc, 0);
            npc.setWorld(this);
            npc.setStatic(false);
            
            if (npc.getName().equals("Yoo")) {
                npc.setIndex(2);
                npc.doneQuest = true;
            }else if (npc.getName().equals("Akefay")) {
                npc.setIndex(1);
            } else if (npc.getName().equals("Miggins")) {
                npc.setIndex(2);
                npc.doneQuest = true;
            } else if (npc.getName().equals("Asriel")){
                npc.setIndex(1);
            } else if (npc.getName().equals("Chea")){
                npc.setIndex(0);
            } else if (npc.getName().equals("Natty")){
                npc.setIndex(0);
                npc.doneQuest = true;
            } else if (npc.getName().equals("Reaper")){
                npc.setIndex(4);
            } else if (npc.getName().equals("Constance")){
                npc.setIndex(0);
            }
        }
    }

    @Override
    public void initializeEnemies(){
        scene.enemyList = new ArrayList<>();
        scene.enemyList.add(new Skeleton3("Skeleton",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.23), screenSize.width * 0.4, screenSize.width * 0.8, player));
        scene.enemyList.add(new Killer("Killer",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.1), screenSize.width * 0.4, screenSize.width * 0.8, player));
        
        for(Enemy enemy : scene.enemyList){
            enemy.setWorld(this);
            scene.add(enemy);
            scene.setComponentZOrder(enemy, 1);
            if (enemy.getName().equals("Skeleton")) {
                enemy.setIndex(3);
            } else if(enemy.getName().equals("Killer")) {
                enemy.setIndex(5);
            }
        }
    }

    public void startKillerBattle() {
        bgmPlayer.stopBGM();
        bgmPlayer.playBGM("src/audio_assets/bgm/fightbgm.wav");
        scene.setCurrentSceneIndex(5);
        // Make sure killer is visible/active for battle
        for(Enemy enemy : scene.enemyList) {
            if(enemy.getName().equals("Killer")) {
                enemy.setVisible(true);
                break;
            }
        }
    }
}
