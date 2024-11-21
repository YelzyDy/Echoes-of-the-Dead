package EOD.objects.chests;

import EOD.characters.Player;
import EOD.objects.QuestableObjects;

public class Chest extends QuestableObjects{
    private int gold = 0;
    public Chest(String assetPackage, double x, double y, int width, int height, String type, boolean isAnimated, boolean isState, int numOfSprites){
        super(assetPackage, x, y, width, height, type, isAnimated, isState, numOfSprites);
    }

    public void setGold(int val){
        gold = val;
        setVisible(true);
    }

    @Override
    public void performQuest(){
        if(isClicked) return;
        if(!doneInteraction) doneInteraction = true;
        Player player = world.getPlayer();
        player.getAttributes().addMoney(gold);
        setVisible(false);
        isClicked = true;
    }
    
}
