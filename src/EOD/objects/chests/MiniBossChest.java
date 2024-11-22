package EOD.objects.chests;

public class MiniBossChest extends Chest {
    public MiniBossChest(){
        super("rewards", (int)(screenSize.width * 0.1), (int)(screenSize.width * 0), (int)(screenSize.width * 0.1), (int)(screenSize.width * 0.08), "miniboss", false, true, 2);
    
    }
}
