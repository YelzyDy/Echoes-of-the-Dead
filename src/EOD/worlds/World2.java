package EOD.worlds;

import EOD.characters.*;
import EOD.characters.enemies.Death;
import EOD.characters.enemies.Enemy;
import EOD.characters.enemies.Skeleton2;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.objects.portals.GreenPortal;
import EOD.objects.portals.PurplePortal;
import EOD.objects.portals.RedPortal;
import EOD.objects.shop.ShopExterior;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import java.util.ArrayList;


/**
 *
 * @author Joana
 */
public class World2 extends World{
    public World2(Player player, ArrayList<Player> playerList) {
        super("world2");
        scene = new SceneBuilder(this);
        bgmPlayer = BGMPlayer.getInstance();
        bgmPlayer.playBGM("src/audio_assets/bgm/world2bgm.wav");
        
        // Set up main player
        this.player = player;
        this.playerList = playerList;
        this.playerName = player.getName();
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
        scene.npcList.add(new Npc("Constance", "missC", (int)(screenSize.width * 0.6), (int)(screenSize.height * 0.21), screenSize.width * 0.6, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Ruby", "ruby",  (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.21), screenSize.width * 0.4, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Faithful", "faithful", (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.21), screenSize.width * 0.2, screenSize.width * 0.4));
        scene.npcList.add(new Npc("Renegald", "renegald", (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.21), screenSize.width * 0.2, screenSize.width * 0.6));
        scene.npcList.add(new Npc("Miggins", "miggins",  (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.21), screenSize.width * 0.5, screenSize.width * 0.62));

        for (Npc npc : scene.npcList) {
            scene.add(npc);
            scene.setComponentZOrder(npc, 0);
            npc.setWorld(this);
            npc.setStatic(false);

            if (npc.getName().equals("Ruby")){
                npc.setIndex(0);
            }else if (npc.getName().equals("Constance")) {
                npc.setIndex(0);
                npc.doneQuest = true;
            }else if (npc.getName().equals("Faithful")) {
                npc.setIndex(1);
                npc.doneQuest = true;
            }else if (npc.getName().equals("Renegald")){
                npc.setIndex(1);
            }else if (npc.getName().equals("Miggins")) {
                npc.setIndex(2);
                npc.doneQuest = true;
            }
        }
    }

    @Override
    public void initializeEnemies(){
        scene.enemyList = new ArrayList<>();
        scene.enemyList.add(new Skeleton2("Skeleton2",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.17), screenSize.width * 0.4, screenSize.width * 0.8, player));
        // scene.enemyList.add(new Gorgon("Gorgon",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.1), screenSize.width * 0.4, screenSize.width * 0.8, player));
        scene.enemyList.add(new Death("Death",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.005), screenSize.width * 0.4, screenSize.width * 0.8, player));
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
}
