package EOD.worlds;

import EOD.characters.*;
import EOD.characters.enemies.Death;
import EOD.characters.enemies.Enemy;
import EOD.characters.enemies.Skeleton1;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.objects.portals.GreenPortal;
import EOD.objects.portals.PurplePortal;
import EOD.objects.portals.RedPortal;
import EOD.objects.profiles.AllyProfiles;
import EOD.objects.shop.ShopExterior;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class World1 extends World{
    private String characterType;
    public boolean sideQuest1 = false;


    // CONSTRUCTOR
    public World1(String characterType, String playerName){
        super("world1");
        bgmPlayer = BGMPlayer.getInstance();
        scene = new SceneBuilder(this);
        bgmPlayer.playBGM("src/audio_assets/bgm/world1bgm.wav");
        this.playerName = playerName;
        this.characterType = characterType;
        //super.setInitiateBattleUi(true);
        Welcome();
    }
    
    // SETTERS - NONE


    // GETTERS - NONE


    // LOCAL METHODS
    private void initializePlayer(){
        if(characterType.equals("knight")){
            initializeKnight();
            player = knight;
        }else if(characterType.equals("wizard")){
            initializeWizard();
            player = wizard;
        }else{
            initializePriest();
            player = priest;
        }
        initializeAllies();
    }

    
    private void initializeAllies(){
        if(characterType.equals("knight")){
            initializePriest();
            initializeWizard();
        }else if(characterType.equals("wizard")){
           initializeKnight();
           initializePriest();
        }else{
            initializeKnight();
            initializeWizard();;
        }
    }

    private void initializeKnight(){
        knight = new Player("knight", 0, (int)(screenSize.height * 0.24));
        knight.setpanel(scene);
        knight.setVisible(false);
        knight.setWorld(this);
        knight.configureSkills();
        knight.setWorld(this);
        knight.setName(playerName);
        knight.setCharacterType("knight");
        scene.add(knight);
        scene.addMouseListener(new MouseClickListener(knight));
    }

    private void initializeWizard(){
        wizard = new Player("wizard", 0, (int)(screenSize.height * 0.24));
        wizard.setpanel(scene);
        wizard.setVisible(false);
        wizard.setWorld(this);
        wizard.configureSkills();
        wizard.setWorld(this);
        wizard.setName(playerName);
        wizard.setCharacterType("wizard");
        scene.add(wizard);
        scene.addMouseListener(new MouseClickListener(wizard));
    }

    private void initializePriest(){
        priest = new Player("priest", 0, (int)(screenSize.height * 0.24));
        priest.setpanel(scene);
        priest.setVisible(false);
        priest.setWorld(this);
        priest.configureSkills();
        priest.setWorld(this);
        priest.setName(playerName);
        priest.setCharacterType("priest");
        scene.add(priest);
        scene.addMouseListener(new MouseClickListener(priest));
    }



    // OVERRIDDEN METHODS
    @Override
    public void initializeProtagonist(){
        // this constructor automatically imports sprites so we must be careful where to put these(obj and npcs too) -- jian
        initializePlayer();
        setMoney(player.getAttributes().getMoney());
        setMoneyLabel(player.getAttributes().getMoney() + "");
        player.setWorld(this);
        player.setVisible(true);
        scene.add(player);
        scene.setComponentZOrder(player, 0);
        scene.setPlayer(player);
        playerList.add(knight);
        playerList.add(priest);
        playerList.add(wizard);
        player.initializeInventory();
        configureShopAndInventory();
        System.out.println("Player type in world2: " + characterType);
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
        scene.npcList.add(new Npc("Yoo", "yoo", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.21), screenSize.width * 0.2, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Constance", "missC", (int)(screenSize.width * 0.6), (int)(screenSize.height * 0.21), screenSize.width * 0.6, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Faithful", "faithful", (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.21), screenSize.width * 0.2, screenSize.width * 0.4));
        scene.npcList.add(new Npc("Miggins", "miggins", (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.21), screenSize.width * 0.5, screenSize.width * 0.62));
        scene.npcList.add(new Npc("Natty", "natty", (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.21), screenSize.width * 0.4, screenSize.width * 0.8));
        for (Npc npc : scene.npcList) {
            scene.add(npc);
            scene.setComponentZOrder(npc, 0);
            npc.setWorld(this);
            if (npc.getName().equals("Yoo")) {
                npc.setIndex(0);
            }else if (npc.getName().equals("Constance")) {
                npc.setIndex(0);
                npc.setStatic(false);
            }else if (npc.getName().equals("Faithful")) {
                npc.setIndex(1);
            } else if (npc.getName().equals("Miggins")) {
                npc.setIndex(2);
            } else if (npc.getName().equals("Natty")) {
                npc.setIndex(1);
            } 
        }
    }
    @Override
    public void initializeEnemies(){
        scene.enemyList = new ArrayList<>();
        scene.enemyList.add(new Skeleton1("Broadaxe Skeleton",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.2), screenSize.width * 0.4, screenSize.width * 0.8, player));
        scene.enemyList.add(new Death("Death",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.005), screenSize.width * 0.4, screenSize.width * 0.8, player));
        for(Enemy enemy : scene.enemyList){
            enemy.setWorld(this);
            scene.add(enemy);
            scene.setComponentZOrder(enemy, 1);
            if (enemy.getCharacterType().equals("skeleton1")) {
                enemy.setIndex(3);
            }else if(enemy.getCharacterType().equals("death")) {
                enemy.setIndex(4);
            }
        }
    }

    @Override
    public void initializeAllyProfiles(){
        AllyProfiles allyProfiles = new AllyProfiles(this);
        allyProfiles.setPlayer(playerList);
        player.setAllyProfiles(allyProfiles);
    }

    @Override
    public void initializePlayerProfile(){
        player.setPlayerProfile(getLayeredPane());
    }

    @Override
    public void onWindowClosing(WindowEvent e){
        bgmPlayer.stopBGM();
    }

    // ABSTRACT METHODS - NONE
      
}