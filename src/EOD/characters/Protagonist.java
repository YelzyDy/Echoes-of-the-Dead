 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters;

import java.awt.event.MouseEvent;
import java.util.Random;
import EOD.MouseInteractable;
import EOD.scenes.SceneBuilder;
import EOD.animator.*;
/**
 *
 * @author Joana
 */

public class Protagonist extends Character implements MouseInteractable {
    private ProtagonistAnimator animator;
    private boolean skillIsUseable = true;
    private Random random = new Random();

    public boolean damageReducer;

    private int damageDealt;
    private double effectX;
    private double effectY;

    private Enemy enemy;

    private double xFactor;

    private String actionString;

    public ProtagonistAttributes attributes;

    public Protagonist(String name, String characterType, SceneBuilder panel, int posX, int posY){
        super(name, characterType, panel, posX, posY);
        animator = new ProtagonistAnimator(this);
        animator.setSpeedMultiplier(1);
        attributes = new ProtagonistAttributes(this);
        setAnimator(animator);
        configureSprites();
        animator.updateBounds();
        damageDealt = 0;
        System.out.println("Protagonist: " + posX + " " + posY);
        xFactor = 0;
        attributes.skill3Cd = 0;
        attributes.skill4Cd = 0;
        actionString = null;
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 8);
        animator.importSprites("character_asset", "idle",(int)(screenSize.height * 0.006), 6);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.006), attributes.s1num);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.006), attributes.s2num);
        animator.importSkillSprites(3, "character_asset", (int)(screenSize.height * 0.006), attributes.s3num);
        animator.importSkillSprites(4, "character_asset", (int)(screenSize.height * 0.006), attributes.s4num);
    }

    public void reset(){
        skillIsUseable = true;
        attributes.skill3Cd = 0;
        attributes.skill4Cd = 0;
    }
    
    public String getAction(){
        return actionString;
    }

    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }

    public double getEffectX(){
        return effectX;
    }

    public double getEffectY(){
        return effectY;
    }
    
    public int getTurnDuration(){
        return attributes.turnDuration;
    }

    public double getXFactor(){
        return xFactor;
    }

    public int getSkill3CD(){
        return attributes.skill3Cd;
    }

    public int getSkill4CD(){
        return attributes.skill4Cd;
    }

    public double getEffectY(String effectType){
        if(effectType.equals("shield")){
            return getPosX() * 0.8;
        }else if(effectType.equals("explosion")){
            return screenSize.width * 0.08;
        }
        return 0;
    }

    public double getEffectX(String effectType){
        if(effectType.equals("shield")){
            return getPosX() * 0.9;
        }else if(effectType.equals("explosion")){
            return screenSize.width * 0.4;
        }
        return 0;
    }

    public ProtagonistAnimator getAnimator(){
        return animator;
    }

    public void takeDamage(int damage){ // this method is for taking damage you can call this in battleExperiment
        attributes.health -= damage;
    }

    public int getDamageDealt(){ // this method is for acessing damage dealt, call this in battleExperiment
        return damageDealt;
    }

    // getDamageDealt and takeDamage are both methods implemented in this class and in enemy class
    
    public void attributeTurnChecker(){
        if(attributes.skill3Cd!=0){
            attributes.skill3Cd--;
        }
        if(attributes.skill4Cd!=0){
            attributes.skill4Cd--;
        }
        
        attributes.mana += 15;
        if(attributes.mana > attributes.baseMana){
            attributes.mana = attributes.baseMana;
        }
        if(attributes.health > attributes.baseHealth){
            attributes.health = attributes.baseHealth;
        }
    }

    public boolean skill1(){ // basic attack type skills
        damageDealt = attributes.attack;
        actionString = "Player dealt " + damageDealt + " damage to the enemy!";
        switch(getCharacterType()) {
            case "knight":
                xFactor =  screenSize.width * 0.5;
                break;
            case "wizard":
                xFactor =  screenSize.width * 0.1;
                break;
            default:
                xFactor =  screenSize.width * 0.3;
        }
        return true;
    }

    public boolean skill2(){ // buff type
        xFactor =  getPosX();
        switch(getCharacterType()){
            case "knight":
                if(skillIsUseable){
                    if(attributes.money >= 15){
                        attributes.attack += 15;
                        attributes.money -= 15;
                        skillIsUseable = false;
                        actionString = "Player's attack is increased to " + 15;
                        attributes.skillEffects2.bindToTarget(this, 
                        -attributes.skillEffects2.getWidth() * 0.25,  // offset X
                        -attributes.skillEffects2.getHeight() * 0.30   // offset Y
                        );
                        attributes.skillEffects2.play();
                        attributes.skillEffects2.setStopFrame(15);
                        return true;
                    }else{
                        actionString = "Not enough money!";
                        return false;
                    }
                }else{
                    actionString = "Can only be used once per battle!";
                    return false;
                }
            case "wizard":
                if(skillIsUseable){
                    if(attributes.mana >= 15){
                        attributes.attack += 15;
                        attributes.mana -= 15;
                        skillIsUseable = false;
                        actionString = "Player's attack is increased to " + 15;
                        attributes.skillEffects2.bindToTarget(this, 
                        -attributes.skillEffects2.getWidth() * 0.25,  // offset X
                        -attributes.skillEffects2.getHeight() * 0.30   // offset Y
                        );
                        attributes.skillEffects2.play();
                        attributes.skillEffects2.setStopFrame(14);
                        return true;
                    }else{
                        actionString = "Not enough mana!";
                        return false;
                    }
                }else{
                     actionString = "Can only be used once per battle!";
                    return false;
                }
            case "priest":
                if(skillIsUseable){
                    if(attributes.health >= 50){
                        attributes.attack += 30;
                        attributes.health -= 15;
                        skillIsUseable = false;
                        actionString = "Player's attack is increased to " + 30;
                        attributes.skillEffects2.bindToTarget(this, 
                        -attributes.skillEffects2.getWidth() * 0.3,  // offset X
                        -attributes.skillEffects2.getHeight() * 0.25   // offset Y
                        );
                        attributes.skillEffects2.play();
                        attributes.skillEffects2.setStopFrame(9);
                        return true;
                    }else{
                        actionString = "Soul Energy too low!";
                        return false;
                    }
                }else{
                   actionString = "Can only be used once per battle!";
                    return false;
                }
            default:
                return true;
        }
    }

    public boolean skill3(){
        switch(getCharacterType()){
            case "knight": 
                if(attributes.skill3Cd==0){
                    if(attributes.mana >= 25){
                        damageReducer = true; 
                        attributes.mana -= 25;
                        attributes.skill3Cd = 3;
                        xFactor =  getPosX();
                        actionString = "Enemy's damage is reduced to " + "4%";
                        // Bind the effect to follow the character
                        attributes.skillEffects3.bindToTarget(this, 
                            -attributes.skillEffects3.getWidth() * 0.25,  // offset X
                            -attributes.skillEffects3.getHeight() * 0.30   // offset Y
                        );
                        // Play the effect
                        attributes.skillEffects3.play();
                        return true;
                    }else{
                        actionString = "Not enough mana!";
                        return false;
                    }
                }else{
                    return false;
                }
                // In Protagonist.java, modify the wizard case in skill3():

        case "wizard":
        if(attributes.skill3Cd==0){
            if(attributes.mana >= 25){
                if(random.nextInt(100) < 60){
                    damageDealt = 40;
                    attributes.mana += 75;
                    if(attributes.mana > attributes.baseMana){
                        attributes.mana = attributes.baseMana;
                    }

                    // Use skillEffects2 instead of skillEffects3
                    attributes.skillEffects3.bindToTarget(enemy, 
                        -attributes.skillEffects3.getWidth() * 0.25,  
                        -attributes.skillEffects3.getHeight() * 0.30   
                    );
                    System.out.println(attributes.skillEffects3.getName());
                    attributes.skillEffects3.play();
                    attributes.skillEffects3.setStopFrame(14);

                    xFactor = screenSize.width * 0.1;
                    actionString = "Shift Successful! " + damageDealt + " damage dealt to enemy!";
                    return true;
                } else {
                    actionString = "Shift Failed!";
                    return false;
                }
            } else {
                actionString = "Not enough mana!";
                return false;
            }
        } else {
            return false;
        }
            case "priest":
                if(attributes.skill3Cd==0){
                    if(attributes.mana >= 40){
                        damageDealt = (int)(attributes.baseHealth*0.3);
                        attributes.health += damageDealt;
                        if(attributes.health > attributes.baseHealth){
                            attributes.health = attributes.baseHealth;
                        }
                        attributes.mana -= 40;

                        attributes.skill3Cd = 3;
                        xFactor =  screenSize.width * 0.22;
                        actionString = "Player dealt " + damageDealt + "damage to the enemy!";
                        return true;
                    }else{
                        actionString = "Not enough mana!";
                        return false;
                    }
                }else{
                    return false;
                }
            default:
                return false;
        }
    }

    public boolean skill4(){
        switch(getCharacterType()){
            case "knight": 
                if(attributes.skill4Cd==0){
                    if(attributes.mana >= 40){
                        damageDealt = 2*attributes.attack + (int)(attributes.money * 0.2);
                        attributes.mana -= 40;
                        attributes.skill4Cd = 4;
                        xFactor =  screenSize.width * 0.5;
                        actionString = "Enemy missed a turn! Player dealt " + damageDealt + " damage to the enemy";
                        return true;
                    }else{
                        actionString = "Not enough mana!";
                        return false;
                    }
                }else{
                    return false;
                }
            case "wizard":
                if(attributes.skill4Cd==0){
                    if(attributes.mana >= 50){
                        damageDealt = 60 + (int)(attributes.baseMana*0.25);
                        attributes.mana -= 50;
                        attributes.skill4Cd = 4;
                        
                        actionString = "Player dealt " + damageDealt + " damage to the enemy";
                        xFactor =  screenSize.width * 0.18;
                        
                        // Bind the effect to follow the character
                        attributes.skillEffects4.bindToTarget(enemy, 
                            -attributes.skillEffects4.getWidth() * 0.35,  // offset X
                            -attributes.skillEffects4.getHeight() * 0.4   // offset Y
                        );

                        // Play the effect
                        attributes.skillEffects4.play();
                        attributes.skillEffects4.setStopFrame(12);
                        return true;
                    }else{
                       actionString = "Not enough mana!";
                        return false;
                    }
                }else{
                    return false;
                }
            case "priest":
                if(attributes.skill4Cd==0){
                    if(attributes.mana >= 50){
                        damageDealt = (int)((attributes.baseHealth - attributes.health) * 0.6);
                        attributes.health += (int)(attributes.baseHealth * 0.4);
                        if(attributes.health > attributes.baseHealth){
                            attributes.health = attributes.baseHealth;
                        }
                        actionString = "Player dealt " + damageDealt + " damage to the enemy";
                        attributes.skillEffectsRandom.bindToTarget(this, 
                        -attributes.skillEffectsRandom.getWidth() * 0.4,  
                        -attributes.skillEffectsRandom.getHeight() * 0.30   
                        );
                        attributes.skillEffectsRandom.play();

                        attributes.skillEffects4.bindToTarget(enemy, 
                        -attributes.skillEffects4.getWidth() * 0.35,  // offset X
                        -attributes.skillEffects4.getHeight() * 0.4   // offset Y
                        );
                        attributes.skillEffects4.play();

                        attributes.mana -= 50;
                        attributes.skill4Cd = 4;
                        xFactor =  screenSize.width * 0.3;
                        actionString = "";
                        return true;
                    }else{
                        actionString = "Not enough mana!";
                        return false;
                    }
                }else{
                    actionString = "Can't use it yet!";
                    return false;
                }
            default:
                return false;
        }
    }

    public ProtagonistAttributes getAttributes(){
        return attributes;
    }


    @Override
    public void onClick(MouseEvent e) {
        if (animator.getIsInBattle()){
            return;
        }
        int deltaX = ((int)e.getX() - (int)getPosX()) / 10;
        animator.moveTo(e.getX(), deltaX);
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
