package EOD.characters;

import java.awt.event.MouseEvent;

import EOD.MouseInteractable;
import EOD.animator.NpcAnimator;
import EOD.listeners.MouseClickListener;
import EOD.scenes.BattleUI;
import EOD.scenes.SceneBuilder;
import EOD.dialogues.*;

// This class makes NPC move randomly
public class Minions extends Character implements MouseInteractable {
    Dialogues dialogues;
    BattleUI battleUI;
    private Protagonist character;
    private int health;

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
        dialogues = new Dialogues();
        battleUI = new BattleUI(character, this);

    }

    // private int playerHp;
    // public void Battle(Protagonist player){
    //     playerHp = player.getHp();
    //     while(playerHp > 0 || health > 0){
    //         health -= player.skill1();
    //     }
    // }

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
        animator.setIsInBattle(true);
        character.getAnimator().setIsInBattle(true);
        animator.setIsInBattle(true);
        
        positionForBattle();
    
        battleUI.displayDialogues();
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