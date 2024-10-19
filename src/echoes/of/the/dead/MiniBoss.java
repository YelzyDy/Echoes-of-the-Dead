package echoes.of.the.dead;

import java.awt.event.MouseEvent;


// This class makes NPC move randomly
public class MiniBoss extends Character implements MouseInteractable {
    private int health = 200;
    private int attack = 20;
    Dialogues dialogues = new Dialogues();
    private Protagonist character;

    public MiniBoss(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange, Protagonist character, int numIdleSprites, int numWalkSprites) {
        super(name, characterType, panel, posX, posY);
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
        animator.isInBattle = true;
        if (animator.isEnlarged){
            return;
        }

        setPosX(screenSize.width * 0.7);
        setPosY(0);
        animator.scaleSprites("idle", 2);
        animator.isEnlarged = true;
        animator.setCurrentFrame(1);
        animator.setMovingRight(false);
        // Enlarge the player
        character.animator.stopMovement();
        character.setPosX(screenSize.width * 0.1);
        character.setPosY(0); // Adjust Y position as needed
        character.animator.scaleSprites("idle", 4);
        character.setIsInBattle(true);

        //start battle
        Battle battle = new Battle(character, this);
        battle.start();
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