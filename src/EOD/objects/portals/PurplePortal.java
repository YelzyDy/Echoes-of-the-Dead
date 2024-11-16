package EOD.objects.portals;

import EOD.characters.Player;
import EOD.objects.QuestableObjects;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import EOD.worlds.World;
import EOD.worlds.World2;
import EOD.worlds.World3;
import java.util.ArrayList;

public class PurplePortal extends QuestableObjects{
    public PurplePortal(){
        super("world1", (int)(screenSize.width * 0.4), (int)(screenSize.height * 0.165), (int)(screenSize.width * 0.1), (int)(screenSize.height * 0.25), "portalNextWorld", true, false, 27);
        setIndex(2);
    }

    @Override
    public void performQuest(){
        if(isClicked) return;
        if(!doneQuest) doneQuest = true;

        SceneBuilder scene = world.getScene();
        BGMPlayer bgmPlayer = world.getBGMPlayer();
        ArrayList<Player> playerList = world.getPlayerList();
        Player player = world.getPlayer();

        scene.gameLoopTimer.stop();
        World window;
        if(world.getTitle().equals("world1")){
            window = new World2(player, playerList);
        }else{
            window = new World3(player, playerList);
        }
        window.setVisible(true);
        window.setBGMPlayer(bgmPlayer);
        scene.setVisible(false);
        scene.free();
    }

}
