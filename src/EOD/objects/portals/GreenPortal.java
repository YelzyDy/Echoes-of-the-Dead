package EOD.objects.portals;
import EOD.characters.Player;
import EOD.dialogues.Dialogues;
import EOD.objects.QuestableObjects;
import EOD.objects.Quests;
import EOD.scenes.BattleUI;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import EOD.utils.SFXPlayer;
import java.util.ArrayList;

public class GreenPortal extends QuestableObjects{
    public GreenPortal(){
        super("world1", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portal", true, false, 29);
        setIndex(1);

    }

    @Override
    public void performQuest(){
        if(isClicked) return;
        if(!doneInteraction) doneInteraction = true;
        SceneBuilder scene = world.getScene();
        BGMPlayer bgmPlayer = world.getBGMPlayer();
        SFXPlayer sfxPlayer = SFXPlayer.getInstance();
        BattleUI battle = world.getBattle();
        Quests quests = world.getQuests();
        ArrayList<Player> playerList = world.getPlayerList();

        if(scene.enemyList != null && !scene.enemyList.get(0).getIsDefeated()){
            sfxPlayer.playSFX("src/audio_assets/sfx/general/teleport.wav");
            bgmPlayer.playBGM("src/audio_assets/bgm/fightbgm.wav");
            scene.setCurrentSceneIndex(3);
            System.out.println(scene.getCurrentSceneIndex());
        }else{
            sfxPlayer.playSFX("src/audio_assets/sfx/general/teleport.wav");
            scene.setCurrentSceneIndex(1);
            bgmPlayer.stopBGM();
            bgmPlayer.playBGM("src/audio_assets/bgm/" + world.getTitle() + "bgm.wav");
        }

        if (scene.enemyList.get(0).getIsDefeated()){
                battle.getEnemyWrapper().setVisible(false);
                battle.toggleInventoryOff();
                quests.setVisible(true);
                for(Player player : playerList){
                    player.getAnimator().setIsInBattle(false);
                }
                Dialogues dialogues = battle.getBattleExperiment().getEnemy().getDialogues();
                    if(dialogues != null && dialogues.getStoryJDialog() != null) dialogues.getStoryJDialog().dispose();
        }
    }

}
