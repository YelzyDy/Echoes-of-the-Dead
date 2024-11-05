package EOD.worlds;

import EOD.characters.*;
import EOD.characters.enemies.Death;
import EOD.characters.enemies.Enemy;
import EOD.characters.enemies.Skeleton2;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Joana
 */
public class World2 extends World{
    public World2(Player player, ArrayList<Player> playerList) {
        super("world2");
        scene = new SceneBuilder(this);
        bgmPlayer = new BGMPlayer();
        bgmPlayer.playBGM("src/audio_assets/world1.wav");
        
        // Set up main player
        this.player = player;
        this.playerList = playerList;
        
        // Configure player position and visibility
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
        
        Welcome();
    }

    @Override
    public void initializeAllyProfiles() {
        player.getAllyProfiles().setWorld(this);
        player.getAllyProfiles().setPlayer(playerList);
    }

    @Override
    public void initializePlayerProfile(){
        player.getPlayerProfile().setPanel(getLayeredPane());
    }

    @Override
    public void initializeProtagonist(){
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
            scene.objList.add(new EchoesObjects("shop",
            (int)(screenSize.width * 0.78),
            (int)(screenSize.height * 0.037),
            (int)(screenSize.width * 0.22),
            (int)(screenSize.height * 0.32),
            "shop", false, true, 2));

            scene.objList.add(new EchoesObjects("world2", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portal", true, false, 29));
            scene.objList.add(new EchoesObjects("world2", (int)(screenSize.width * 0.3), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portalMiniBoss", true, false, 47));
            scene.objList.add(new EchoesObjects("world2", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portalNextWorld", true, false, 27));
            for (EchoesObjects obj : scene.objList) {
                /*if (obj.getName().equals("portalNextWorld") && !isMiniBossDefeated){
                    obj.setVisible(false);
                    continue;
                }*/
                scene.add(obj);
                if (obj.getName().equals("portal")) {
                    obj.setIndex(1); 
                } else if (obj.getName().equals("portalMiniBoss") || (obj.getName().equals("shop"))) {
                    obj.setIndex(2);
                } else if (obj.getName().equals("portalNextWorld")){
                    obj.setIndex(2);
                }
                obj.addMouseListener(new MouseClickListener(this));;
            }
    }

    @Override
    public void initializeWorldChars(){
        scene.npcList = new ArrayList<>();
        scene.npcList.add(new Npc("Faithful", "faithful", (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.4));
        scene.npcList.add(new Npc("Miggins", "miggins",  (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.25), screenSize.width * 0.5, screenSize.width * 0.62));
        scene.npcList.add(new Npc("Ruby", "ruby",  (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.4), screenSize.width * 0.4, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Renegald", "renegald", (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.4), screenSize.width * 0.2, screenSize.width * 0.6));
        scene.npcList.add(new Npc("Constance", "missC", (int)(screenSize.width * 0.6), (int)(screenSize.height * 0.25), screenSize.width * 0.6, screenSize.width * 0.8));
        
        for (Npc npc : scene.npcList) {
            npc.setPosY((int)(screenSize.height * 0.21));
            scene.add(npc);
            scene.setComponentZOrder(npc, 0);
            npc.setWorld(this);
            if (npc.getName().equals("Constance")) {
                npc.setIndex(0);
            }else if (npc.getName().equals("Faithful")) {
                npc.setIndex(1);
            } else if (npc.getName().equals("Miggins")) {
                npc.setIndex(2);
            } else if (npc.getName().equals("Renegald")){
                npc.setIndex(1);
            } else if (npc.getName().equals("Ruby")){
                npc.setIndex(0);
            }
        }
    }

    @Override
    public void initializeEnemies(){
        scene.enemyList = new ArrayList<>();
        scene.enemyList.add(new Skeleton2("Skeleton2",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.25), screenSize.width * 0.4, screenSize.width * 0.8, player));
        // scene.enemyList.add(new Gorgon("Gorgon",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.1), screenSize.width * 0.4, screenSize.width * 0.8, player));
        scene.enemyList.add(new Death("Death",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.0), screenSize.width * 0.4, screenSize.width * 0.8, player));
        for(Enemy enemy : scene.enemyList){
            enemy.setWorld(this);
            scene.add(enemy);
            scene.setComponentZOrder(enemy, 0);
            if (enemy.getName().equals("Skeleton2")) {
                enemy.setIndex(3);
            }else if(enemy.getName().equals("Death")) {
                enemy.setIndex(4);
            }
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        super.onClick(e);
        Object source = e.getSource();
        if(scene.objList == null) return;
        for (EchoesObjects obj : scene.objList) {
            if (source == obj && obj.getName().equals("portal")){
                System.out.println("Enemy: " + scene.enemyList.get(0).getName() + scene.enemyList.get(0).getIsDefeated());
                if(scene.enemyList != null && !scene.enemyList.get(0).getIsDefeated()){
                    bgmPlayer.stopBGM(); 
                    bgmPlayer.playBGM("src/audio_assets/fightscene.wav");
                    scene.setCurrentSceneIndex(3);
                    System.out.println(scene.getCurrentSceneIndex());
                }else{
                    scene.setCurrentSceneIndex(1);
                    bgmPlayer.stopBGM();
                    bgmPlayer.playBGM("src/audio_assets/world1.wav");
                    battle.toggleTextListOff();
                }
            }else if (source == obj && obj.getName().equals("portalMiniBoss")) {
                if (scene.enemyList != null && !scene.enemyList.get(1).getIsDefeated()) {
                    scene.setCurrentSceneIndex(4);
                    bgmPlayer.stopBGM();
                    bgmPlayer.playBGM("src/audio_assets/fightscene.wav");
                } else {
                    scene.setCurrentSceneIndex(2);
                    bgmPlayer.stopBGM();
                    bgmPlayer.playBGM("src/audio_assets/world1.wav");
                    battle.toggleTextListOff();
                }
            }else if(source == obj && obj.getName().equals("shop")){
                shop.makeElementsVisible();
            } else if (source == obj && obj.getName().equals("portalNextWorld")){
                scene.gameLoopTimer.stop();
                World window = new World3(player, playerList);
                window.setVisible(true);
                this.setVisible(false);
            }
        }    
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
       
    }
}
