package echoes.of.the.dead;

import java.awt.event.MouseEvent;

// This class makes NPC move randomly
public class Npc extends Character implements MouseInteractable {
    Dialogues dialogues = new Dialogues();

    private int natty;
    private int missC;
    private int yoo;
    private int faithful;
    private int miggins;

    public Npc(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange) {
        super(name, characterType, panel, posX, posY);
        setVisible(true); // Make sure the NPC is visible
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 4);
        animator.importSprites("character_asset", "idle",(int)(screenSize.height * 0.006), 4);
        this.addMouseListener(new MouseClickListener(this));
        animator.startMovement();
        animator.chooseNewDirection(); 
        
        System.out.println();
        animator.updateBounds();
        animator.setRange(minRange, maxRange);
    }

    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
        if (getCharacterType().equals("natty")){
            if (natty != 0) {
                dialogues.displayDialogues(70, 80, 1, 1);
            } else {
                dialogues.displayDialogues(60, 70, 0, 1);
                this.natty = 1;
            }
        } 
        if (getCharacterType().equals("missC")){
            if (missC != 0) {
                dialogues.displayDialogues(50, 60, 1, 1);
            } else {
                dialogues.displayDialogues(40, 50, 0, 1);
                this.missC = 1;
            }
        } 
        if (getCharacterType().equals("yoo")){
            if (yoo != 0) {
                dialogues.displayDialogues(120, 130, 1, 2);
            } else {
                dialogues.displayDialogues(100, 116, 0, 2);
                this.yoo = 1;
            }
        } 
        if (getCharacterType().equals("faithful")){
            if (faithful != 0) {
                dialogues.displayDialogues(0, 1, 1, 1);
            } else {
                dialogues.displayDialogues(0, 0, 0, 1);
                this.faithful = 1;
            }
        } 
        if (getCharacterType().equals("miggins")){
            if (miggins != 0) {
                dialogues.displayDialogues(160, 170, 1, 1);
            } else {
                dialogues.displayDialogues(140, 155, 0, 1);
                this.miggins = 1;
            }
        } 
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