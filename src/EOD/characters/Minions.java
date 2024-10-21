package EOD.characters;

import EOD.MouseInteractable;
<<<<<<< HEAD
import EOD.animator.NpcAnimator;
import EOD.listeners.MouseClickListener;
=======
import EOD.dialogues.*;
import EOD.listeners.MouseClickListener;
import EOD.scenes.Battle;
import EOD.scenes.BattleUI;
>>>>>>> e663e67cc8fc4cbab04ee391a31e17a8297fd49e
import EOD.scenes.SceneBuilder;
import java.awt.event.MouseEvent;

// This class makes NPC move randomly
public class Minions extends Character implements MouseInteractable {
<<<<<<< HEAD
    private Protagonist character;
    private int health;
=======

    Dialogues dialogues = new Dialogues();
    BattleUI battleUI = new BattleUI();
    private int health = 100;
    private int attack = 10;
    //depends on the world
    private int moneyDrop = 50;
    private Protagonist character;
    private boolean isItDefeated = false;
>>>>>>> e663e67cc8fc4cbab04ee391a31e17a8297fd49e

    private NpcAnimator animator;
    public Minions(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange, int numIdleSprites, int numWalkSprites, Protagonist character) {
        super(name, characterType, panel, posX, posY);
        animator = new NpcAnimator(this);
        setAnimator(animator);
        setVisible(true); // Make sure the NPC is visible
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.008), numWalkSprites);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.008), numIdleSprites);
        this.addMouseListener(new MouseClickListener(this));
        animator.startMovement();
        animator.chooseNewDirection(); 
        animator.updateBounds();
        animator.setRange(minRange, maxRange);
        this.character = character;
        health = 100;
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

    public int getHp(){
        return health;
    }

    public void setHp(int health){
        this.health = health;
    }

    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
<<<<<<< HEAD
        animator.setIsInBattle(true);
        character.getAnimator().setIsInBattle(true);
        animator.setIsInBattle(true);
        positionForBattle();
        panel.configureBattle(this);
        // Battle battle = new Battle(character, this);
        // battle.start();
    }

    private void positionForBattle() {
        if(!character.getAnimator().getIsInBattle())
            return;
        character.getAnimator().stopMovement();
        character.setPosX(screenSize.width * 0.35);
        // character.setPosY(0.); 
        // character.animator.scaleSprites("idle", 4);
        
        setPosX(screenSize.width * 0.55);
        // setPosY(screenSize.width * 0.04);
        // animator.scaleSprites("idle", 2.9);
        character.getAnimator().setMovingRight(true);
        animator.setMovingRight(false);
=======
        animator.isInBattle = true;
        if (animator.isEnlarged){
            return;
        }

        setPosX(screenSize.width * 0.6);
        setPosY(screenSize.width * 0.04);
        animator.scaleSprites("idle", 2.9);
        animator.isEnlarged = true;
        animator.setCurrentFrame(1);
        animator.setMovingRight(false);

        // Enlarge the player
        battleUI.displayDialogues();
        new Thread(() -> {
            //gibalhin nakos protagonist dapit sa setisinbattle ang katong modako siya - jm
            character.setIsInBattle(true);
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

            character.setIsInBattle(false);
            isItDefeated = true;
        }).start();
>>>>>>> e663e67cc8fc4cbab04ee391a31e17a8297fd49e
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