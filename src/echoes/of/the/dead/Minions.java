package echoes.of.the.dead;

import java.awt.event.MouseEvent;


// This class makes NPC move randomly
public class Minions extends Character implements MouseInteractable {
    Dialogues dialogues = new Dialogues();
    private Protagonist character;

    public Minions(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange, Protagonist character) {
        super(name, characterType, panel, posX, posY);
        setVisible(true); // Make sure the NPC is visible
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.0045), 8);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.0045), 8);
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
        setPosY(0);
        animator.scaleSprites("idle", 2);
        animator.isEnlarged = true;
        animator.setCurrentFrame(1);

        // Enlarge the player
        character.animator.stopMovement();
        character.setPosX(screenSize.width * 0.1);
        character.setPosY(0); // Adjust Y position as needed
        character.animator.scaleSprites("idle", 4);
        character.setIsInBattle(true);
        // Battle battle = new Battle(character, this);
        // battle.start();
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
}