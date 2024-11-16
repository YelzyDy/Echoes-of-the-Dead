package EOD.characters;

import EOD.animator.NpcAnimator;
import EOD.dialogues.*;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.*;
import java.awt.event.MouseEvent;
import EOD.gameInterfaces.Questable;
import java.awt.Component;
// This class makes NPC move randomly
public class Npc extends Character implements MouseInteractable, Questable{
    public Dialogues dialogues = new Dialogues();
    protected NpcAnimator animator;
    private boolean isStatic;
    public boolean doneQuest;
    public double targetX;
    public boolean isClicked;
    
    public Npc(String name, String characterType, int posX, int posY, double minRange, double maxRange) {
        super(name, characterType, posX, posY);
        animator = new NpcAnimator(this);
        setAnimator(animator);
        animator.setSpeedMultiplier(1);
        setVisible(true); // Make sure the NPC is visible
        this.addMouseListener(new MouseClickListener(this));
        initializeNpcSprites();
        isStatic = true;
        doneQuest = false;
        animator.setRange(minRange, maxRange);
        dialogues.setNpc(this);
        targetX = -15;
        isClicked = false;
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

    private int getDialogueId(){
        if(isStatic && !(getCharacterType().equals("knight") || getCharacterType().equals("priest") || getCharacterType().equals("wizard"))) return 0;
        if (getCharacterType().equals("natty")){
            return 3;
        }
        if (getCharacterType().equals("missC")){
            return 1;
        } 
        if (getCharacterType().equals("yoo")){
           return 5;
        }  
        if (getCharacterType().equals("miggins")){
           return 7;
        }
        if (getCharacterType().equals("faithful")){
           return 9;
        }
        if(getCharacterType().equals("knight")){
            return 11;
        }
        if(getCharacterType().equals("wizard")){
            return 13;
        }
        if(getCharacterType().equals("priest")){
            return 15;
        }
        if(getCharacterType().equals("ruby")){
            return 25;
        }
        if(getCharacterType().equals("renegald")){
            return 27;
        }
        return 0;
    }

    @Override
    public void performQuest(){
        if(isClicked) return;
        dialogues.setPlayerType(world.getPlayerType());
        System.out.println("JDialog not null? " + (dialogues.getStoryJDialog() != null));
        System.out.println("JDialog displayable? " + (dialogues.getStoryJDialog().isDisplayable()));

        if(dialogues.getStoryJDialog() != null && dialogues.getStoryJDialog().isDisplayable()) return;

        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);

        dialogues.displayDialogues(getDialogueId(), world);
        isClicked = true;
    }
    
    @Override
    public void onClick(MouseEvent e) {
        System.out.println("NPC clicked: " + getCharacterType());
        Player player = this.getPanel().getPlayer();
        int currentScene = this.getPanel().getCurrentSceneIndex();
        targetX = 0;
        Component targetComponent = null;
        if(currentScene > getIndex()){
            targetX = screenSize.width * 0.05;
            targetComponent = getPanel();
            System.out.println("should be here");
        }else if(currentScene < getIndex()){
            targetX = screenSize.width * 0.9;
            targetComponent = getPanel();
        }else{
            if(player.getX() > getX()) targetX = getX() * 1.1;
            else targetX = getX() * 0.9;
            targetComponent = this;
        }
        // System.out.println("current scene: " + currentScene + "npc index: " + getIndex());

        MouseEvent fakeClickEvent = new MouseEvent(
            targetComponent,                            // Target component
            MouseEvent.MOUSE_CLICKED,       // Event type
            System.currentTimeMillis(),     // Event time
            0,                              // Modifiers (no modifiers here)
            (int) targetX,                        // Specified X position
            targetComponent.getY(),                        // Specified Y position
            1,                              // Click count
            false                           // Not a popup trigger
        );
    
        // Call the world's click handler with the created event
        player.onClick(fakeClickEvent);

        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
        isClicked = false;
    }

    @Override
    public void onHover(MouseEvent e) {
        if(dialogues != null && dialogues.getStoryJDialog() != null && dialogues.getStoryJDialog().isDisplayable()) return;
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
    }
    
    @Override
    public void onExit(MouseEvent e) {
        if(dialogues != null && dialogues.getStoryJDialog() != null && dialogues.getStoryJDialog().isDisplayable()) return;
        if(!isStatic) animator.startMovement();
        animator.setPaused(false);
        animator.setInteracting(false);
    }
}