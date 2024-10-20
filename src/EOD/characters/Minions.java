package EOD.characters;

import EOD.MouseInteractable;
import EOD.dialogues.*;
import EOD.listeners.MouseClickListener;
import EOD.scenes.Battle;
import EOD.scenes.BattleUI;
import EOD.scenes.SceneBuilder;
import java.awt.event.MouseEvent;

// This class makes NPC move randomly
public class Minions extends Character implements MouseInteractable {
    Dialogues dialogues = new Dialogues();
    BattleUI battleUI = new BattleUI();
    private Protagonist character;
    private boolean isItDefeated = false;

    public Minions(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange, int numIdleSprites, int numWalkSprites, Protagonist character) {
        super(name, characterType, panel, posX, posY);
        setVisible(true); // Make sure the NPC is visible
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.008), numWalkSprites);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.008), numIdleSprites);
        this.addMouseListener(new MouseClickListener(this));
        animator.startMovement();
        animator.chooseNewDirection(); 
        animator.updateBounds();
        animator.setRange(minRange, maxRange);
        this.character = character;
    }

    // private int playerHp;
    // public void Battle(Protagonist player){
    //     playerHp = player.getHp();
    //     while(playerHp > 0 || health > 0){
    //         health -= player.skill1();
    //     }
    // }

    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
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
    }

    @Override
    public void onHover(MouseEvent e) {
        if (animator.isInBattle){
            return;
        }
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
    }
    
    @Override
    public void onExit(MouseEvent e) {
        if (animator.isInBattle){
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