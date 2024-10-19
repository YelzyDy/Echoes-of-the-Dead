 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;


import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Joana
 */

public class Protagonist extends Character implements MouseInteractable {
    private int mana = 100;
    private int attack = 20;
    private int health = 150;
    private int money = 0;
    private boolean isInBattle;
    public Protagonist(String name, String characterType, SceneBuilder panel, int posX, int posY){
        super(name, characterType, panel, posX, posY);
        initializeSprites("character_asset", "walk", screenSize.height * 0.006);
        initializeSprites("character_asset", "idle", screenSize.height * 0.006);
        updateBounds();
        isInBattle =false;
    }
    
    @Override
    public void initializeSprites(String assetPackage, String type, double scale){
        ((type.equals("walk"))? walkSprites : idleSprites).clear();
        int size = (type.equals("walk") ? 8 : 6);
        String[] spritePaths = new String[size];
        for(int i = 0; i < size; i++){
            spritePaths[i] = "/" + assetPackage + "/" + characterType + "/" + type + "/sprite" + (i + 1) + ".png";
            // System.out.println(spritePaths[i]);
        }     
        for (String path : spritePaths) {
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                ((type.equals("walk"))? walkSprites : idleSprites).add(image); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ((type.equals("walk"))? walkSprites : idleSprites).scaleImageList(scale);
    }

    public void updateMovement() {
        int maxPanel = panel.getNumOfScenes() - 1;
        int currentScene = panel.getCurrentSceneIndex();
        if (!getIsMoving()) return;
    
        // Assuming deltaX is the distance to move
        if (getIsMovingRight()) {
            setPosX(getPosX() + deltaX - 10);
            if (getPosX() >= getTargetX()) { // Check if it reached the target position
                stopMovement(); // Stop when the target is reached
                setPosX(getTargetX());; // Ensure it doesn't overshoot
                if (getPosX()  >= (int)(screenSize.width * 0.8) && currentScene< maxPanel - 1) {
                    panel.incCurrentScene();
                    setPosX((int)(screenSize.width * 0.001));
                } else if (currentScene> 0 && getPosX() <= (int)(screenSize.width * 0.05)) {
                    panel.decCurrentScene();;
                    setPosX((int)(screenSize.width * 0.09));
                }   
            }
        } else {
            setPosX(getPosX() + deltaX); 
            if (getPosX() <= getTargetX()) { // Check if it reached the target position
                stopMovement(); // Stop when the target is reached
                setPosX(getTargetX());
                if (getPosX()>= (int)(screenSize.width * 0.8) && currentScene<  maxPanel - 1) {
                    panel.incCurrentScene();
                    setPosX((int)(screenSize.width * 0.001));
                } else if (currentScene > 0 && getPosX() <= (int)(screenSize.width * 0.05)) {
                    panel.decCurrentScene();
                    setPosX((int)(screenSize.width * 0.9));
                }   
            }
        }
    }
// Created the 3 skills for the protagonists but function will be implemented later --jm
    public void skill1(){
        switch(this.characterType){
            case "knight": 
                System.out.println(getName() + " used OBJECTION SURGE");
                System.out.println("-15 Soul Shards, +15 Attack");
                break;
            case "wizard":
                System.out.println(getName() + " used OVERCLOCK");
                System.out.println("-15 Mana, +15 Attack");
                break;
            case "priest":
                System.out.println(getName() + " used VITAL RUSH");
                System.out.println("-15 Soul Energy, +15 Attack");  
                break;
        }
    }

    public void skill2(){
        switch(this.characterType){
            case "knight":
                System.out.println(getName() + " used ETHEREAL SHIELD OF LOGIC");
                System.out.println("Absorbs 40% damage, if damage is greater than 20% Soul Energy left, increase Soul Shards by 10%");
                break;
            case "wizard":
                System.out.println(getName() + " used QUANTUM SHIFT");
                System.out.println("Has a 60% chance to dodge the attack. Deals 40 damage and regains 50 mana if successful");
                break;
            case "priest":
                System.out.println(getName() + " used LIFE LEECH");
                System.out.println("Steals 30% of self's Soul Energy from the opponent");
                break;
        }
    }

    public void skill3(){
        switch(this.characterType){
            case "knight":
                System.out.println(getName() + " used TRUTHBINDING");
                System.out.println("Deal 200% Attack + 40% Soul Shards damage and the opponent can’t attack this turn");
                break;
            case "wizard":
                System.out.println(getName() + " used CODE RAGE QUAKE");
                System.out.println("Induce a strong quake dealing 60 + 25% Mana damage");
                break;
            case "priest":
                System.out.println(getName() + " used VENGEFUL VITALITY");
                System.out.println("Deal 60% of Soul Energy lost to the opponent and gains 40% Soul Energy");
                break;
        }
    }

    @Override
    public void onClick(MouseEvent e) {
        if (isInBattle){
            return;
        }
        int deltaX = (e.getX() - getPosX()) / 10;
        moveTo(e.getX(), deltaX);
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }

    public void setIsInBattle (boolean isInBattle){
        this.isInBattle = isInBattle;
    }
}
