package EOD.objects.chests;
public class QuestsChest extends Chest{
    public QuestsChest(){
        super("rewards", (int)(screenSize.width * 0.1), (int)(screenSize.width * 0), (int)(screenSize.width * 0.1), (int)(screenSize.width * 0.08), "quests", false, true, 2);
    }
}
