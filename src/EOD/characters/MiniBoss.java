package EOD.characters;

import java.awt.event.MouseEvent;

import EOD.scenes.Battle;
import EOD.dialogues.Dialogues;
import EOD.listeners.MouseClickListener;
import EOD.MouseInteractable;
import EOD.animator.NpcAnimator;
import EOD.scenes.SceneBuilder;


// This class makes NPC move randomly
public class MiniBoss extends Character implements MouseInteractable {
    private int health = 200;
    private int attack = 20;
    private SceneBuilder panel;
    Dialogues dialogues = new Dialogues();
    private Protagonist character;
    private NpcAnimator animator;

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

    public int getHp(){
        return health;
    }
 
    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
        animator.setIsInBattle(true);
       

        setPosX(screenSize.width * 0.7);
        setPosY(0);
        animator.scaleSprites("idle", 2);
   
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
}