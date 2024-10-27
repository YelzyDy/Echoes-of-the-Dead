package EOD.characters;

import java.awt.event.MouseEvent;

import EOD.MouseInteractable;
import EOD.animator.NpcAnimator;
import EOD.dialogues.*;
import EOD.listeners.*;
// This class makes NPC move randomly
public class Npc extends Character implements MouseInteractable {
    Dialogues dialogues = new Dialogues();
    private int index;
    private NpcAnimator animator;
    public Npc(String name, String characterType, int posX, int posY, double minRange, double maxRange) {
        super(name, characterType, posX, posY);
        animator = new NpcAnimator(this);
        setAnimator(animator);
        animator.setSpeedMultiplier(1);
        setVisible(true); // Make sure the NPC is visible
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 4);
        animator.importSprites("character_asset", "idle",(int)(screenSize.height * 0.006), 4);
        this.addMouseListener(new MouseClickListener(this));
        animator.startMovement();
        animator.chooseNewDirection(); 
        setName(name);
        System.out.println();
        animator.updateBounds();
        animator.setRange(minRange, maxRange);
    }
    
    public Npc getNpc(String name){
        if(this.getName().equals(name)){
            return this;
        }else{
            return null;
        }
    }
    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
    
    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
        
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
    }

    @Override
    public void onHover(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
    }
    
    @Override
    public void onExit(MouseEvent e) {
        animator.startMovement();
        animator.setPaused(false);
        animator.setInteracting(false);
    }
}