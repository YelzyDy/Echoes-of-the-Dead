package EOD.objects.chests;

import EOD.characters.Player;
import EOD.objects.ClickableObjects;
import EOD.utils.SFXPlayer;

public class Chest extends ClickableObjects{
    private int gold = 0;
    private SFXPlayer sfxPlayer = SFXPlayer.getInstance();
    public Chest(String assetPackage, double x, double y, int width, int height, String type, boolean isAnimated, boolean isState, int numOfSprites){
        super(assetPackage, x, y, width, height, type, isAnimated, isState, numOfSprites);
    }

    public void setGold(int val){
        gold = val;
        setVisible(true);
    }

    @Override
    public void performClick(){
        if(isPerformQActive) return;
        if(!doneInteraction) doneInteraction = true;
        Player player = world.getPlayer();
        sfxPlayer.playSFX("src/audio_assets/sfx/general/reward.wav");
        player.getAttributes().addMoney(gold);
        setVisible(false);
        isPerformQActive = true;
    }
    
}
