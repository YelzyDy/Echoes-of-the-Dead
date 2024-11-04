/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters.enemies;

import EOD.characters.Player;
import EOD.objects.EchoesObjects;

/**
 *
 * @author zendy
 */
public class Gorgon extends Enemy {
    private static final int BASE_ATTACK = 15;
    private static final int BASE_HEALTH = 2;
    // Skill cooldowns
    private int skill2Cooldown = 0;
    private static final int SKILL2_MAX_COOLDOWN = 3;

    public Gorgon(String name, int posX, int posY, 
            double minRange, double maxRange, Player protagonist) {
            super(name, "gorgon", posX, posY, minRange, maxRange, protagonist);
            configureSprites();
            health = BASE_HEALTH;
            attack = BASE_ATTACK;
            moneyDrop = 50;
            animator.setMovementMultiplier(3);
            animator.setDeathAnimationSpeedMultiplier(1);
            animator.setSkillAnimationSpeedMultiplier(3);
    }

    public void configureSprites(){
        double spriteScale = screenSize.height * 0.004;
        animator.importSprites("character_asset", "walk", spriteScale, 13);
        animator.importSprites("character_asset", "idle", spriteScale, 7);
        animator.importSprites("character_asset", "dead", spriteScale, 3);
        animator.importSkillSprites(1, "character_asset", spriteScale, 16);
        animator.importSkillSprites(2, "character_asset", spriteScale, 10);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }
    

    @Override 
    public void skill1() {
        damageDealt = attack + (int)(Math.random() * 3);
        actionString = getName() + " used a basic spell, dealt " + damageDealt + " damage!";
        lastUsedSkill = 1;
    }

    @Override 
    public void skill2() {
        if (skill2Cooldown > 0) {
            skill1();
            return;
        }

        int baseSkill2Damage = (int)(attack * 1.5);
        damageDealt = baseSkill2Damage + (int)(Math.random() * 4) - 2;
        
        actionString = getName() + " used Mutilate, dealt " + damageDealt + " damage!";
        lastUsedSkill = 2;
        skill2Cooldown = SKILL2_MAX_COOLDOWN;
    }

    @Override 
    public double getOffsetX(int skill){
        if(skill == 1){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.3;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.35;
            }else{
                return 0.15;
            }
        }else if(skill == 3){
            if(player.isWizard()){
                return 0.3;
            }else{
                return 0.22;
            }
        }else{
            if(player.isWizard()){
                return 0.2;
            }else{
                return 0.3;
            }
        }
    }

    @Override 
    public double getOffsetY(int skill){
        if(skill == 1){
            if(player.isKnight()){
                return 0.18;
            }else{
                return 0.2;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.4;
            }else if(player.isWizard()){
                return 0.4;
            }else{
                return 0.3;
            }
        }else if(skill == 3){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.2;
            }
        }else{
            if(player.isKnight()){
                return 0.1;
            }else if(player.isWizard()){
                return 0.2;
            }else{
                return 0.32;
            }
        }
    }

    @Override 
    public double getOffsetW(int skill){
        if(skill == 2) {
            if(player.isKnight()){
                return 0.2;
            }else if(player.isWizard()){
                return 0.15;
            } else {
                return 0.25;
            }
        }else if(skill == 3){
            if(player.isKnight()){
                return 0.15;
            }else if(player.isWizard()){
                return 0.3;
            }else{
                return 0.17;
            }
        }else{
            if(player.isWizard()){
                return 0.2;
            }else if(player.isKnight()){
                return 0.28;
            }else{
                return 0.3;
            }
        }
    }

    @Override 
    public double getOffsetH(int skill){
        if(skill == 2) {
            if(player.isKnight()) {
                return 0.15;
            } else if(player.isWizard()){
                return 0.15;
            }else{
                return 0.07;
            }
        }else if(skill == 3){
            if(player.isKnight()){
                return 0.15;
            }else if(player.isWizard()){
                return 0.3;
            }else{
                return 0.17;
            }
        }else{
            if(player.isKnight()){
                return 0.22;
            }
            if(player.isWizard()){
                return 0.25;
            }else{
                return 0.28;
            }
        }
    }

    @Override
    public double getXFactor(){
        return screenSize.width * 0.6;
    }


    @Override
    public void update() {
        if (skill2Cooldown > 0) {
            skill2Cooldown--;
        }
    }

    // Decision making for skill usage
    @Override
    public int decideSkill() {
        // 70% chance to use basic attack
        // 30% chance to try skill2 (will fall back to basic if on cooldown)
        return Math.random() < 0.7 ? 1 : 2;
    }
    
    @Override
    protected void onBattleStart() {
        EchoesObjects portal = getPanel().objList.get(2);
        getPanel().configureBattle(this, portal);
    }
}
