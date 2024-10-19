package echoes.of.the.dead;

import java.awt.event.MouseEvent;


// This class makes NPC move randomly
public class MiniBoss1 extends Character implements MouseInteractable {
    Dialogues dialogues = new Dialogues();

    public MiniBoss1(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange) {
        super(name, characterType, panel, posX, posY);
        setVisible(true); // Make sure the NPC is visible
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 13);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.006), 7);
        this.addMouseListener(new MouseClickListener(this));
        animator.startMovement();
        animator.chooseNewDirection(); 
        animator.updateBounds();
        animator.setRange(minRange, maxRange);
    }


    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
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