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
    private boolean isInBattle;
    public Protagonist(String name, String characterType, SceneBuilder panel, int posX, int posY){
        super(name, characterType, panel, posX, posY);
        System.out.println("test");
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.006), 8);
        animator.importSprites("character_asset", "idle",(int)(screenSize.height * 0.006), 6);
        animator.updateBounds();
        System.out.println("Protagonist: " + posX + " " + posY);
        isInBattle = false;
    }
// Created the 3 skills for the protagonists but function will be implemented later --jm
    public void skill1(){
        switch(getCharacterType()){
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

    public double skill2(){
        switch(getCharacterType()){
            case "knight":
                System.out.println(getName() + " used ETHEREAL SHIELD OF LOGIC");
                System.out.println("Absorbs 40% damage, if damage is greater than 20% Soul Energy left, increase Soul Shards by 10%");
                return 0.4;
            case "wizard":
                System.out.println(getName() + " used QUANTUM SHIFT");
                System.out.println("Has a 60% chance to dodge the attack. Deals 40 damage and regains 50 mana if successful");
                return 40.0;
            case "priest":
                System.out.println(getName() + " used LIFE LEECH");
                System.out.println("Steals 30% of self's Soul Energy from the opponent");
                return 0.3;
            default:
                return 0;
        }
    }

    public double skill3(){
        switch(getCharacterType()){
            case "knight":
                System.out.println(getName() + " used TRUTHBINDING");
                System.out.println("Deal 200% Attack + 40% Soul Shards damage and the opponent can’t attack this turn");
                return 0.4;
            case "wizard":
                System.out.println(getName() + " used CODE RAGE QUAKE");
                System.out.println("Induce a strong quake dealing 60 + 25% Mana damage");
                return 0.25;
            case "priest":
                System.out.println(getName() + " used VENGEFUL VITALITY");
                System.out.println("Deal 60% of Soul Energy lost to the opponent and gains 40% Soul Energy");
                return 0.6;
            default:
                return 0;
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

    //get mana for battle sequence
    public int getMana(){
        return mana;
    }

    //set mana after battle sequence
    public void setMana(int newMana){
        mana = newMana;
    }

    //get hp for battle sequence
    public int getMoney(){
        return money;
    }

    //set hp after battle sequence
    public void setMoney(int newMoney){
        money = newMoney;
    }

    //get atk for battle sequence
    public int getAttack(){
        return attack;
    }

    public void setIsInBattle (boolean isInBattle){
        this.isInBattle = isInBattle;
        if(this.isInBattle){
            System.out.println("Entering battle mode. Scaling sprite to size 2.");
            this.animator.stopMovement();
            this.setPosX(screenSize.width * 0.1);
            this.setPosY(0); // Adjust Y position as needed
            this.animator.scaleSprites("idle", 2);
        }else{
            System.out.println("Exiting battle mode. Resetting sprite size to 1.");
            this.animator.startMovement();
            this.setPosX(screenSize.width * 0.1);
            this.setPosY(0); // Adjust Y position as needed
            this.animator.scaleSprites("idle", 1);
        }
    }

    public boolean getInBattle(){
        return isInBattle;
    }

    @Override
    public void onClick(MouseEvent e) {
        if (isInBattle){
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
