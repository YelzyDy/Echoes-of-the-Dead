package EOD.objects.chests;


public class MinionChest extends Chest{
    public MinionChest(){
        super("rewards", (int)(screenSize.width * 0.1), (int)(screenSize.width * 0), (int)(screenSize.width * 0.1), (int)(screenSize.width * 0.08), "minions", false, true, 2);
    
    }

}
