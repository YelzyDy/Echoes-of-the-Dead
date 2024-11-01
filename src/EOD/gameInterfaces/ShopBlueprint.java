package EOD.gameInterfaces;
import EOD.objects.inventory.Inventory;

public interface ShopBlueprint {

    public void setInventory(Inventory inventory);

    public void makeElementsVisible();
    public void showElementsInShop();

    public void buy();
}
