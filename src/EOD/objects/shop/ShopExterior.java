package EOD.objects.shop;
import EOD.objects.QuestableObjects;
public class ShopExterior  extends QuestableObjects{
    public ShopExterior(){
        super("shop",
        (int)(screenSize.width * 0.78),
        (int)(screenSize.height * 0.037),
        (int)(screenSize.width * 0.22),
        (int)(screenSize.height * 0.32),
        "shop", false, true, 2);
        setIndex(2);
    }

    @Override
    public void performQuest() {
        if(isPerformQActive) return;
        if(!doneInteraction) doneInteraction = true;
        Shop shop = world.getShop();
        shop.makeElementsVisible();
        isPerformQActive = true;
    }
}
