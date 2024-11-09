package EOD.characters;

import EOD.animator.NpcAnimator;
import EOD.dialogues.*;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.*;
import java.awt.event.MouseEvent;
// This class makes NPC move randomly
public class Npc extends Character implements MouseInteractable{
    public Dialogues dialogues = new Dialogues();
    protected NpcAnimator animator;
    private boolean isStatic;
    public boolean doneQuest;
    
    public Npc(String name, String characterType, int posX, int posY, double minRange, double maxRange) {
        super(name, characterType, posX, posY);
        animator = new NpcAnimator(this);
        setAnimator(animator);
        animator.setSpeedMultiplier(1);
        setVisible(true); // Make sure the NPC is visible
        this.addMouseListener(new MouseClickListener(this));
        initializeNpcSprites();
        isStatic = false;
        doneQuest = false;
        animator.setRange(minRange, maxRange);
    }

    public void initializeNpcSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 4);
        animator.importSprites("character_asset", "idle",(int)(screenSize.height * 0.006), 4);
        animator.startMovement();
        animator.chooseNewDirection(); 
        animator.updateBounds();
    }

    @Override
    public void free(){
        try{
            super.free();
            if(animator != null){
                animator.free();
                animator = null;
            }
            if(dialogues != null){
                dialogues.free();
                dialogues = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setStatic(boolean isStatic){
        this.isStatic = isStatic;
    }
    
    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
        dialogues.setIsDialogueFinished(false);
        if(dialogues.getStoryJDialog() != null && dialogues.getStoryJDialog().isDisplayable()) return;
        if (getCharacterType().equals("natty")){
            dialogues.displayDialogues(3, world);
        }
        if (getCharacterType().equals("missC")){
            dialogues.displayDialogues(1, world);
        } 
        if (getCharacterType().equals("yoo")){
            dialogues.displayDialogues(5, world);
        }  
        if (getCharacterType().equals("miggins")){
            dialogues.displayDialogues(7, world);
        }
        if (getCharacterType().equals("faithful")){
            dialogues.displayDialogues(9, world);
        }
        if(getCharacterType().equals("knight")){
            dialogues.setPlayerType(getCharacterType());
            dialogues.displayDialogues(11, world);
        }
        if(getCharacterType().equals("wizard")){
            dialogues.setPlayerType(getCharacterType());
            dialogues.displayDialogues(13, world);
        }
        if(getCharacterType().equals("priest")){
            dialogues.setPlayerType(getCharacterType());
            dialogues.displayDialogues(15, world);
        }
        if(getCharacterType().equals("ruby")){
            dialogues.setPlayerType(getCharacterType());
            dialogues.displayDialogues(25, world);
        }
        if(getCharacterType().equals("renegald")){
            dialogues.setPlayerType(getCharacterType());
            dialogues.displayDialogues(27, world);
        }
    }

    @Override
    public void onHover(MouseEvent e) {
        System.out.println("hoverhover");
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
    }
    
    @Override
    public void onExit(MouseEvent e) {
        if(!isStatic) animator.startMovement();
        animator.setPaused(false);
        animator.setInteracting(false);
    }
}