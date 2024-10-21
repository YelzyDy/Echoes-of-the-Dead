package EOD.characters;

import EOD.MouseInteractable;
import EOD.dialogues.Dialogues;
import EOD.listeners.MouseClickListener;
<<<<<<< HEAD
import EOD.MouseInteractable;
import EOD.animator.NpcAnimator;
=======
import EOD.scenes.Battle;
>>>>>>> e663e67cc8fc4cbab04ee391a31e17a8297fd49e
import EOD.scenes.SceneBuilder;
import java.awt.event.MouseEvent;


// This class makes NPC move randomly
public class MiniBoss extends Character implements MouseInteractable {
    Dialogues dialogues = new Dialogues();
    private int health = 200;
    private int attack = 20;
    //depends on the world
    private int moneyDrop = 100;
    private SceneBuilder panel;
    private Protagonist character;
<<<<<<< HEAD
    private NpcAnimator animator;
=======
    private boolean isItDefeated = false;
>>>>>>> e663e67cc8fc4cbab04ee391a31e17a8297fd49e

    public MiniBoss(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange, int numIdleSprites, int numWalkSprites,  Protagonist character) {
        super(name, characterType, panel, posX, posY);
        animator = new NpcAnimator(this);
        animator = new NpcAnimator(this);
        setAnimator(animator);
        this.panel = panel;
        setVisible(true); // Make sure the NPC is visible
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), numWalkSprites);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), numIdleSprites);
        this.addMouseListener(new MouseClickListener(this));
        animator.startMovement();
        animator.chooseNewDirection(); 
        animator.updateBounds();
        animator.setRange(minRange, maxRange);
        this.character = character;
    }

    //get hp for battle sequence
    public int getHp(){
        return health;
    }
 
    //get atk for battle sequence
    public int getAttack(){
        return attack;
    }

    //get moneydrop for after battle sequence
    public int getMoneyDrop(){
        return moneyDrop;
    }

    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
        animator.setIsInBattle(true);
       

<<<<<<< HEAD
        setPosX(screenSize.width * 0.7);
        setPosY(0);
        animator.scaleSprites("idle", 2);
   
=======
        setPosX(screenSize.width * 0.6);
        setPosY(screenSize.width * 0.04);
        animator.scaleSprites("idle", 1.1);
        animator.isEnlarged = true;
        animator.setCurrentFrame(1);
>>>>>>> e663e67cc8fc4cbab04ee391a31e17a8297fd49e
        animator.setMovingRight(false);
        
        //need ko help diri :(
        //start battle
        new Thread(() -> {
            //gibalhin nakos protagonist dapit sa setisinbattle ang katong modako siya - jm
          
            Battle battle = new Battle(character, this);
            battle.start();
            
            // Wait for the battle to end
            while (!battle.battleOver) {
                try {
                    Thread.sleep(100); // Check every 100ms
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

<<<<<<< HEAD
        
=======
            character.setIsInBattle(false);
            isItDefeated = true;
>>>>>>> e663e67cc8fc4cbab04ee391a31e17a8297fd49e
        }).start();
        
    }

    @Override
    public void onHover(MouseEvent e) {
        if (animator.getIsInBattle()){
            return;
        }
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
    }
    
    @Override
    public void onExit(MouseEvent e) {
        if (animator.getIsInBattle()){
            return;
        }
        animator.startMovement();
        animator.setPaused(false);
        animator.setInteracting(false);
    }

    public boolean isDefeated(){
        return isItDefeated;
    }
}