package echoes.of.the.dead;

import java.awt.event.MouseEvent;


// This class makes NPC move randomly
public class MiniBoss extends Character implements MouseInteractable {
    private int health = 200;
    private int attack = 20;
    private SceneBuilder panel;
    Dialogues dialogues = new Dialogues();
    private Protagonist character;

    public MiniBoss(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange, int numIdleSprites, int numWalkSprites,  Protagonist character) {
        super(name, characterType, panel, posX, posY);
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

    public void endBattle() {
        // Remove the miniboss from the panel directly using the stored panel reference
        if (panel != null) {
            System.out.println("Removing miniboss using stored panel reference");
            panel.remove(this);
            panel.revalidate();
            panel.repaint();
        } else {
            System.out.println("Panel is null; cannot remove miniboss");
        }
    
        // Reset the player's state and move it to the center of the screen
        character.animator.scaleSprites("idle", 1); // Reset player scale
        character.setPosX(screenSize.width / 2); // Center the character horizontally
        character.setPosY(screenSize.height / 2); // Center the character vertically
        character.setIsInBattle(false);
        character.setVisible(true); // Ensure the character is visible
        character.animator.startMovement(); // Resume movement
        System.out.println("Character position after battle: (" + character.getPosX() + ", " + character.getPosY() + ")");
        System.out.println("Character visibility: " + character.isVisible());
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
        new Thread(() -> {
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
    
            // After the battle ends, reset player and miniboss
            endBattle();
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
}