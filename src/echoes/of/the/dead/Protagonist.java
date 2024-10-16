 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;


import java.awt.event.MouseEvent;

/**
 *
 * @author Joana
 */

public class Protagonist extends Character implements MouseInteractable {
    private int mana = 100;
    private int attack = 20;
    private int health = 150;
    private int money = 0;
    public Protagonist(String name, String characterType, SceneBuilder panel, int posX, int posY){
        super(name, characterType, panel, posX, posY);
    }
   
    public void updateMovement() {
        int maxPanel = panel.getNumOfScenes() - 1;
        if (!isMoving) return;
    
        // Assuming deltaX is the distance to move
        if (isMovingRight) {
            posX += (deltaX - 10);
            if (posX >= targetX) { // Check if it reached the target position
                stopMovement(); // Stop when the target is reached
                posX = targetX; // Ensure it doesn't overshoot
                if (posX >= (int)(screenSize.width * 0.9) && panel.currentSceneIndex < maxPanel - 1) {
                    panel.currentSceneIndex++;
                    posX = (int)(screenSize.width * 0.001);
                } else if (panel.currentSceneIndex > 0 && posX <= (int)(screenSize.width * 0.05)) {
                    panel.currentSceneIndex--;
                    posX = (int)(screenSize.width * 0.9);
                }   
            }
        } else {
            posX += deltaX;
            if (posX <= targetX) { // Check if it reached the target position
                stopMovement(); // Stop when the target is reached
                posX = targetX; // Ensure it doesn't overshoot
                if (posX >= (int)(screenSize.width * 0.9) && panel.currentSceneIndex < maxPanel - 1) {
                    panel.currentSceneIndex++;
                    posX = (int)(screenSize.width * 0.001);
                } else if (panel.currentSceneIndex > 0 && posX <= (int)(screenSize.width * 0.05)) {
                    panel.currentSceneIndex--;
                    posX = (int)(screenSize.width * 0.9);
                }   
            }
        }
    }
// Created the 3 skills for the protagonists but function will be implemented later --jm
    public void skill1(){
        switch(this.characterType){
            case "knight":
                System.out.println(name + " used OBJECTION SURGE");
                System.out.println("-15 Soul Shards, +15 Attack");
                break;
            case "wizard":
                System.out.println(name + " used OVERCLOCK");
                System.out.println("-15 Mana, +15 Attack");
                break;
            case "priest":
                System.out.println(name + " used VITAL RUSH");
                System.out.println("-15 Soul Energy, +15 Attack");
                break;
        }
    }

    public void skill2(){
        switch(this.characterType){
            case "knight":
                System.out.println(name + " used ETHEREAL SHIELD OF LOGIC");
                System.out.println("Absorbs 40% damage, if damage is greater than 20% Soul Energy left, increase Soul Shards by 10%");
                break;
            case "wizard":
                System.out.println(name + " used QUANTUM SHIFT");
                System.out.println("Has a 60% chance to dodge the attack. Deals 40 damage and regains 50 mana if successful");
                break;
            case "priest":
                System.out.println(name + " used LIFE LEECH");
                System.out.println("Steals 30% of self's Soul Energy from the opponent");
                break;
        }
    }

    public void skill3(){
        switch(this.characterType){
            case "knight":
                System.out.println(name + " used TRUTHBINDING");
                System.out.println("Deal 200% Attack + 40% Soul Shards damage and the opponent can’t attack this turn");
                break;
            case "wizard":
                System.out.println(name + " used CODE RAGE QUAKE");
                System.out.println("Induce a strong quake dealing 60 + 25% Mana damage");
                break;
            case "priest":
                System.out.println(name + " used VENGEFUL VITALITY");
                System.out.println("Deal 60% of Soul Energy lost to the opponent and gains 40% Soul Energy");
                break;
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        int deltaX = (e.getX() - posX) / 10;
        moveTo(e.getX(), deltaX);
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
