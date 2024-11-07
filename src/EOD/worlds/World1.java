package EOD.worlds;

import EOD.characters.*;
import EOD.characters.enemies.Enemy;
import EOD.characters.enemies.Necromancer;
import EOD.characters.enemies.Skeleton1;
import EOD.dialogues.*;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.objects.profiles.AllyProfiles;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/**
 *
 * @author Joana
 */

public class World1 extends World{
    private String characterType;
    public boolean sideQuest1 = false;

    public World1(String characterType, String playerName){
        super("world1");
        bgmPlayer = BGMPlayer.getInstance();
        scene = new SceneBuilder(this);
        bgmPlayer.playBGM("src/old/audio_assets/world1.wav");
        this.playerName = playerName;
        this.characterType = characterType;
        Welcome();
    }
    
    @Override
    public void initializeProtagonist(){
        // this constructor automatically imports sprites so we must be careful where to put these(obj and npcs too) -- jian
        initializePlayer();
        configureShopAndInventory();
        setMoney(player.getAttributes().getMoney());
        setMoneyLabel(player.getAttributes().getMoney() + "");
        
        player.setVisible(true);
        scene.add(player);
        scene.setComponentZOrder(player, 0);
        scene.setPlayer(player);
        playerList.add(knight);
        playerList.add(priest);
        playerList.add(wizard);
        System.out.println("Player type in world2: " + characterType);
    }

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

            
            scene.objList.add(new EchoesObjects("world1", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portal", true, false, 29));
            scene.objList.add(new EchoesObjects("world1", (int)(screenSize.width * 0.3), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portalMiniBoss", true, false, 47));
            scene.objList.add(new EchoesObjects("world1", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portalNextWorld", true, false, 27));
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
        scene.npcList.add(new Npc("Yoo", "yoo", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Faithful", "faithful", (int)(screenSize.width * 0.2), (int)(screenSize.height * 0.25), screenSize.width * 0.2, screenSize.width * 0.4));
        scene.npcList.add(new Npc("Miggins", "miggins", (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.25), screenSize.width * 0.5, screenSize.width * 0.62));
        scene.npcList.add(new Npc("Natty", "natty", (int)(screenSize.width * 0.65), (int)(screenSize.height * 0.4), screenSize.width * 0.4, screenSize.width * 0.8));
        scene.npcList.add(new Npc("Constance", "missC", (int)(screenSize.width * 0.6), (int)(screenSize.height * 0.25), screenSize.width * 0.6, screenSize.width * 0.8));
        
        for (Npc npc : scene.npcList) {
            npc.setPosY((int)(screenSize.height * 0.21));
            scene.add(npc);
            scene.setComponentZOrder(npc, 0);
            npc.setWorld(this);
            if (npc.getName().equals("Yoo")) {
                npc.setIndex(0);
            }else if (npc.getName().equals("Constance")) {
                npc.setIndex(0);
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
        scene.enemyList.add(new Skeleton1("Skeleton1",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.22), screenSize.width * 0.4, screenSize.width * 0.8, player));
        scene.enemyList.add(new Necromancer("Necromancer",  (int) (screenSize.width * 0.65), (int)(screenSize.height * 0.05), screenSize.width * 0.4, screenSize.width * 0.8, player));
        for(Enemy enemy : scene.enemyList){
            enemy.setWorld(this);
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
    public void onClick(MouseEvent e) {
        super.onClick(e);
        Object source = e.getSource();
        if(scene.objList == null) return;
        for (EchoesObjects obj : scene.objList) {
            if (source == obj && obj.getName().equals("portal")){
                System.out.println("Enemy: " + scene.enemyList.get(0).getName() + scene.enemyList.get(0).getIsDefeated());
                if(scene.enemyList != null && !scene.enemyList.get(0).getIsDefeated()){
                    bgmPlayer.playBGM("src/old/audio_assets/fightscene.wav");
                    scene.setCurrentSceneIndex(3);
                    System.out.println(scene.getCurrentSceneIndex());
                }else{
                    scene.setCurrentSceneIndex(1);
                    bgmPlayer.stopBGM();
                    bgmPlayer.playBGM("src/old/audio_assets/world1.wav");
                    battle.getEnemyWrapper().setVisible(false);
                    battle.toggleTextListOff();
                    Dialogues dialogues = battle.getBattleExperiment().getEnemy().getDialogues();
                    if(dialogues != null && dialogues.getStoryJDialog() != null) dialogues.getStoryJDialog().dispose();
                }
            }else if (source == obj && obj.getName().equals("portalMiniBoss")) {
                if (scene.enemyList != null && !scene.enemyList.get(1).getIsDefeated()) {
                    scene.setCurrentSceneIndex(4);
                    bgmPlayer.stopBGM();
                    bgmPlayer.playBGM("src/old/audio_assets/fightscene.wav");
                } else {
                    scene.setCurrentSceneIndex(2);
                    bgmPlayer.stopBGM();
                    bgmPlayer.playBGM("src/old/audio_assets/world1.wav");
                    battle.getEnemyWrapper().setVisible(false);
                    battle.toggleTextListOff();
                    Dialogues dialogues = battle.getBattleExperiment().getEnemy().getDialogues();
                    if(dialogues != null && dialogues.getStoryJDialog() != null) dialogues.getStoryJDialog().dispose();
                    // isBattleStopped = false;
                    // isMiniBossDefeated = true;
                    // setIsMiniBossDefeated(true);
                    /*for (EchoesObjects object : scene.objList) {
                        if (object.getName().equals("portalNextWorld")) {
                            object.setVisible(true);
                            object.setIndex(2);  // Ensure it's at index 2
                        }
                    }*/

                    /*World window = new World2(getPlayerType(), getPlayerName(), player);
                    window.setVisible(true);
                    this.setVisible(false);*/
                }
            }else if(source == obj && obj.getName().equals("shop")){
                shop.makeElementsVisible();
            }else if (source == obj && obj.getName().equals("portalNextWorld")){
                scene.gameLoopTimer.stop();
                World window = new World2(player, playerList);
                window.setVisible(true);
                this.setVisible(false);
                this.free();
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