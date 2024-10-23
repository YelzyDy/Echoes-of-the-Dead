 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
    private int mana;
    private int baseMana;
    private int attack;
    private int health;
    private int baseHealth;
    private int money;
    private int skill3Cd = 0;
    private int skill4Cd = 0;
    private boolean skillIsUseable = true;
    private Random random = new Random();

    public boolean damageReducer;

    private int s1num;
    private int s2num;
    private int s3num;
    private int s4num;
    private int turnDuration;
    private int damageDealt;
    private double effectX;
    private double effectY;

    private double xFactor = 0;
    public Protagonist(String name, String characterType, SceneBuilder panel, int posX, int posY){
        super(name, characterType, panel, posX, posY);
        animator = new ProtagonistAnimator(this);
        setAnimator(animator);
        configure();
        configureSprites();
        animator.updateBounds();
        damageDealt = 0;
        System.out.println("Protagonist: " + posX + " " + posY);
        xFactor = 0;
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 8);
        animator.importSprites("character_asset", "idle",(int)(screenSize.height * 0.006), 6);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.006), s1num);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.006), s2num);
        animator.importSkillSprites(3, "character_asset", (int)(screenSize.height * 0.006), s3num);
        animator.importSkillSprites(4, "character_asset", (int)(screenSize.height * 0.006), s4num);
    }

    public double getEffectX(){
        return effectX;
    }

    public double getEffectY(){
        return effectY;
    }
    
    public int getTurnDuration(){
        return turnDuration;
    }

    public double getXFactor(){
        return xFactor;
    }
    public void configure(){
        //buffs depending on characterType
        switch(getCharacterType()){
            case "knight": 
                attack = 20;
                health = 150;
                baseHealth = health;
                mana = 100;
                baseMana = mana;
                money = 40;
                s1num = 7;
                s2num = 4;
                s3num = 4;
                s4num = 11;
                turnDuration = 3000;
                break;
            case "wizard":
                attack = 20;
                health = 150;
                baseHealth = health;
                mana = 130;
                baseMana = mana;
                money = 0;
                s1num = 6;
                s2num = 6;
                s3num = 6;
                s4num = 6;
                turnDuration = 3000;
            case "priest":
                attack = 20;
                health = 180; 
                baseHealth = health;
                mana = 100;
                baseMana = mana;
                attack = 20;
                money = 0;
                s1num = 9;
                s2num = 9;
                s3num = 6;
                s4num = 9;
                turnDuration = 3000;
                break;
        }
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
        health -= damage;
    }

    public int getDamageDealt(){ // this method is for acessing damage dealt, call this in battleExperiment
        return damageDealt;
    }

    // getDamageDealt and takeDamage are both methods implemented in this class and in enemy class
    
    public void attributeTurnChecker(){
        if(skill3Cd!=0){
            skill3Cd--;
        }
        if(skill4Cd!=0){
            skill4Cd--;
        }
        
        mana += 15;
        if(mana > baseMana){
            mana = baseMana;
        }
        if(health > baseHealth){
            health = baseHealth;
        }
    }

    public boolean skill1(){ // basic attack type skills
        damageDealt = attack;
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
                    if(money >= 15){
                        attack += 15;
                        money -= 15;
                        skillIsUseable = false;
                    }else{
                        System.out.println("Not enough money!");
                        return false;
                    }
                }else{
                    System.out.println("Can only be used once per battle!");
                    return false;
                }
                return true;
            case "wizard":
                if(skillIsUseable){
                    if(mana >= 15){
                        attack += 15;
                        mana -= 15;
                        skillIsUseable = false;
                    }else{
                        System.out.println("Not enough mana!");
                        return false;
                    }
                }else{
                    System.out.println("Can only be used once per battle!");
                    return false;
                }
                return true;
            case "priest":
                if(skillIsUseable){
                    if(health >= 50){
                       attack += 30;
                       health -= 15;
                        skillIsUseable = false;
                    }else{
                        System.out.println("Soul Energy too low!");
                        return false;
                    }
                }else{
                    System.out.println("Can only be used once per battle!");
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    public boolean skill3(){
        switch(getCharacterType()){
            case "knight": 
                if(skill3Cd==0){
                    if(mana >= 25){
                        damageReducer = true; 
                        mana -= 25;
                        skill3Cd = 3;
                        xFactor =  getPosX();
                        return true;
                    }else{
                        System.out.println("Not enough mana!");
                        return false;
                    }
                }else{
                    System.out.println("Can't use it yet!");
                    return false;
                }
            case "wizard":
                if(skill3Cd==0){
                    if(mana >= 25){
                        if(random.nextInt(100) < 60){
                            damageDealt = 40;
                            mana += 75;
                            System.out.println("Shift Successful!");
                            xFactor =  screenSize.width * 0.2;
                            return true;
                        }else{
                            System.out.println("Shift Failed!");
                            return false;
                        }
                    }else{
                        System.out.println("Not enough mana!");
                        return false;
                    }
                }else{
                    System.out.println("Can't use it yet!");
                    return false;
                }
            case "priest":
                if(skill3Cd==0){
                    if(mana >= 25){
                        damageReducer = true;
                        mana -= 25;
                        skill3Cd = 3;
                        xFactor =  screenSize.width * 0.3;
                        return true;
                    }else{
                        System.out.println("Not enough mana!");
                        return false;
                    }
                }else{
                    System.out.println("Can't use it yet!");
                    return false;
                }
            default:
                return false;
        }
    }

    public boolean skill4(){
        switch(getCharacterType()){
            case "knight": 
                if(skill4Cd==0){
                    if(mana >= 40){
                        damageDealt = 2*attack + (int)(money * 0.2);
                        mana -= 40;
                        skill4Cd = 4;
                        xFactor =  screenSize.width * 0.5;
                    }else{
                        System.out.println("Not enough mana!");
                        return false;
                    }
                }else{
                    System.out.println("Can't use it yet!");
                    return false;
                }
                return true;
            case "wizard":
                if(skill4Cd==0){
                    if(mana >= 50){
                        damageDealt = 60 + (int)(baseMana*0.25);
                        mana -= 50;
                        skill4Cd = 4;
                        xFactor =  screenSize.width * 0.2;
                    }else{
                        System.out.println("Not enough mana!");
                        return false;
                    }
                }else{
                    System.out.println("Can't use it yet!");
                    return false;
                }
                return true;
            case "priest":
                if(skill4Cd==0){
                    if(mana >= 25){
                        damageReducer = true;
                        mana -= 25;
                        skill4Cd = 4;
                        xFactor =  screenSize.width * 0.3;
                    }else{
                        System.out.println("Not enough mana!");
                        return false;
                    }
                }else{
                    System.out.println("Can't use it yet!");
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    //get hp for battle sequence
    public int getHp(){
        return health;
    }

    //set hp after battle sequence
    public void setHp(int newHealth){
        health = newHealth;
    }

    //get base hp for battle sequence
    public int getBaseHp(){
        return baseHealth;
    }

    //set basehp after getting potion or item
    public void setBaseHp(int addHp){
        baseHealth += addHp;
    }

    //get mana for battle sequence
    public int getMana(){
        return mana;
    }

    //set mana after battle sequence
    public void setMana(int newMana){
        mana = newMana;
    }

    //get basemana for battle sequence
    public int getBaseMana(){
        return baseMana;
    }

    //set basemana after getting potion or item
    public void setBaseMana(int addMana){
        baseMana += addMana;
    }

    //get money for battle sequence
    public int getMoney(){
        return money;
    }

    //set hp after battle sequence
    public void setMoney(int newMoney){
        money += newMoney;
    }

    //get atk for battle sequence
    public int getAttack(){
        return attack;
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
