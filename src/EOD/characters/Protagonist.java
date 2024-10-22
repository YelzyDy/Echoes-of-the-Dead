 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters;

import java.awt.event.MouseEvent;

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
    private int s1num;
    private int s2num;
    private int s3num;
    private int s4num;
    private int turnDuration;
    private int damageDealt;
    private int damageReduction;

    public Protagonist(String name, String characterType, SceneBuilder panel, int posX, int posY){
        super(name, characterType, panel, posX, posY);
        animator = new ProtagonistAnimator(this);
        setAnimator(animator);
        configure();
        configureSprites();
        animator.updateBounds();
        damageDealt = 0;
        damageReduction = 0;
        System.out.println("Protagonist: " + posX + " " + posY);
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 8);
        animator.importSprites("character_asset", "idle",(int)(screenSize.height * 0.006), 6);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.006), s1num);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.006), s2num);
        animator.importSkillSprites(3, "character_asset", (int)(screenSize.height * 0.006), s3num);
        animator.importSkillSprites(4, "character_asset", (int)(screenSize.height * 0.006), s4num);
    }

    public int getTurnDuration(){
        return turnDuration;
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
                s2num = 10;
                s3num = 4;
                s4num = 11;
                turnDuration = 2000;
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
                turnDuration = 2000;
                break;
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
                turnDuration = 2000;
                break;
        }
    }

    public ProtagonistAnimator getAnimator(){
        return animator;
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    public int getDamageDealt(){
        return damageDealt;
    }

    public int getDamageReduction(){
        return damageReduction;
    }
    
    public boolean skill1(){ // buff type skills
        switch(getCharacterType()){
            case "knight": 
                if(money >= 15){
                    attack += 15;
                    money -= 15;
                    break;
                }else{
                    return false;
                }
            case "wizard":
                if(mana >= 10){
                    attack += 15;
                    mana -= 10;
                    break;
                }else{
                    return false;
                }
            case "priest":
                if(health >= 40){
                    attack += 15;
                    health -= 10;
                    break;
                }else{
                    return false;
                }
        }
        return true;
    }

    public boolean skill2(){ // damage type
        switch(getCharacterType()){
            case "knight": 
                if(mana >= 30){
                    damageDealt = 50;
                    mana-=30;
                    break;
                }else{
                    return false;
                }
            case "wizard":
                if(mana >= 30){
                    damageDealt = 50;
                    mana-=30;
                    break;
                }else{
                    return false;
                }
            case "priest":
                if(mana >= 30){
                    damageDealt = 50;
                    mana-=30;
                    break;
                }else{
                    return false;
                }
        }
        return true;
    }

    public boolean skill3(){
        switch(getCharacterType()){
            case "knight": 
                if(mana >= 30){
                    damageReduction = 20;
                    mana -= 30;
                    break;
                }else{
                    return false;
                }
            case "wizard":
                if(mana >= 30){
                    damageReduction = 20;
                    mana -= 30;
                    break;
                }else{
                    return false;
                }
            case "priest":
                if(mana >= 30){
                    damageReduction = 20;
                    mana -= 30;
                    break;
                }else{
                    return false;
                }
        }
        return true;
    }

    public boolean skill4(){
        switch(getCharacterType()){
            case "knight": 
                if(money >= 15){
                    attack += 15;
                    money -= 15;
                    break;
                }else{
                    return false;
                }
            case "wizard":
                if(mana >= 10){
                    attack += 15;
                    mana -= 10;
                    break;
                }else{
                    return false;
                }
            case "priest":
                if(health >= 40){
                    attack += 15;
                    health -= 10;
                    break;
                }else{
                    return false;
                }
        }
        return true;
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
