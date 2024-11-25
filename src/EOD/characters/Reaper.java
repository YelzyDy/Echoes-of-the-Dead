package EOD.characters;

import EOD.dialogues.Dialogues;


public class Reaper extends Npc {
    public Dialogues dialogues = new Dialogues();
    
    public Reaper(){
        super("Reaper", "reaper", (int)(screenSize.width * 0.45), (int)(screenSize.height * 0.01), 
              screenSize.width * 0.1, screenSize.width * 0.55);
        setStatic(false);
        dialogues.setNpc(this);
    }

    @Override
    public void initializeNpcSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 8);
        animator.importSprites("character_asset", "idle",(int)(screenSize.height * 0.006), 8);
        animator.startMovement();
        animator.chooseNewDirection(); 
        animator.updateBounds();
    }

    @Override
    public void performClick(){
        super.performClick();
    }
}
