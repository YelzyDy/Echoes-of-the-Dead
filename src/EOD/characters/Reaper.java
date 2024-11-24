package EOD.characters;

import EOD.dialogues.Dialogues;
import EOD.scenes.ChoiceUI;
import javax.swing.JLayeredPane;


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
        if(isPerformQActive) return;
        dialogues.setPlayerType(world.getPlayerType());
        dialogues.setPlayerName(world.getPlayer().getName());
        
        if(dialogues.getStoryJDialog().isDisplayable()){
            dialogues.getStoryJDialog().dispose();
            return;
        }

        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
        
        // Display preEnding dialogues (ID 18)
        dialogues.displayDialogues(18, world);
        isPerformQActive = true;

        ChoiceUI choiceUI = new ChoiceUI(world);
        JLayeredPane layeredPane = world.getLayeredPane();
        layeredPane.add(choiceUI, JLayeredPane.POPUP_LAYER);

        choiceUI.setBounds(
            0, (int)(screenSize.height * 0.4), 
                 (int)(screenSize.width), (int)(screenSize.height * 0.6)
        );
        
        choiceUI.setVisible(true);
    }
}
