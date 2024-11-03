package EOD.characters.enemies;

import EOD.characters.Player;
import EOD.objects.EchoesObjects;
public class Skeleton1 extends Enemy {
    // Constants for better maintainability
    private static final int BASE_ATTACK = 8;
    private static final int BASE_HEALTH = 100;
    private static final double SPRITE_SCALE = 0.3; // Keep the smaller scale for minion appearance
    
    // Skill cooldowns
    private int skill2Cooldown = 0;
    private static final int SKILL2_MAX_COOLDOWN = 3;

    public Skeleton1(String name, int posX, int posY, 
            double minRange, double maxRange, Player protagonist) {
            super(name, "skeleton1", posX, posY, minRange, maxRange, 
           protagonist);
        configureSprites();
        
        // Base stats - weaker than protagonist but not trivial
        attack = BASE_ATTACK;
        health = BASE_HEALTH;
        moneyDrop = 15;
        animator.setSpeedMultiplier(1);
    }

    public void configureSprites() {
        int baseSize = (int)(screenSize.height * 0.007);
        
        // Import all sprite sets
        animator.importSprites("character_asset", "walk", baseSize, 8);
        animator.importSprites("character_asset", "idle", baseSize, 7);
        animator.importSprites("character_asset", "dead", baseSize, 3);
        animator.importSkillSprites(1, "character_asset", baseSize, 7);
        animator.importSkillSprites(2, "character_asset", baseSize, 7);
        
        // Scale all sprites uniformly
        String[] spriteTypes = {"walk", "idle", "dead", "skill1", "skill2"};
        for (String type : spriteTypes) {
            animator.scaleDownSprites(type, SPRITE_SCALE);
        }
        
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }

    @Override 
    public void skill1() {
        // Basic attack - consistent but low damage
        damageDealt = attack + (int)(Math.random() * 3); // 8-10 damage
        actionString = getName() + " swings its rusty sword for " + damageDealt + " damage!";
        lastUsedSkill = 1;
    }

    @Override 
    public void skill2() {
        // Special attack - bone throw with damage variation
        if (skill2Cooldown > 0) {
            // Fallback to basic attack if on cooldown
            skill1();
            return;
        }

        // Slightly higher damage than basic attack but unreliable
        int baseSkill2Damage = (int)(attack * 1.5);
        damageDealt = baseSkill2Damage + (int)(Math.random() * 4) - 2; // 10-14 damage with variation
        
        actionString = getName() + " throws a bone for " + damageDealt + " damage!";
        lastUsedSkill = 2;
        skill2Cooldown = SKILL2_MAX_COOLDOWN;
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
    public double getXFactor() {
        return screenSize.width * 0.4;
    }

    // Keep the existing offset methods as they handle skill effect positioning
    @Override 
    public double getOffsetX(int skill) {
        if(skill == 1){
            if(player.isKnight()){
                return 0.2;
            }else{
                return 0.1;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.35;
            }else{
                return 0.15;
            }
        }else if(skill == 3) {
            if(player.isWizard()) {
                return 0.3;
            } else{
                return 0.25;
            }
        } else {
            if(player.isKnight()){
                return 0.35;
            }else if(player.isWizard()){
                return 0.35;
            }else{
                return 0.35;
            }
        }
    }

    @Override 
    public double getOffsetY(int skill) {
        if(skill == 1){
            if(player.isKnight()){
                return 0.6;
            }else{
                return 0.1;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.4;
            }else if(player.isWizard()){
                return 0.4;
            }else{
                return 0.3;
            }
        } else if(skill == 3){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.3;
            }  
        }else{
            if(player.isKnight()){
                return 0.4;
            }else if(player.isWizard()){
                return 0.4;
            }else{
                return 0.6;
            }
        }
    }

    @Override 
    public double getOffsetW(int skill) {
        if(skill == 1){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.2;
            }
        }else if(skill == 2) {
            if(player.isKnight()){
                return 0.2;
            }else if(player.isWizard()){
                return 0.15;
            } else {
                return 0.25;
            }
        } else if(skill == 3) {
            if(player.isKnight()) {
                return 0.15;
            } else if(player.isWizard()) {
                return 0.2;
            } else {
                return 0.17;
            }
        } else {
            if(player.isKnight()) {
                return 0.2;
            } else if(player.isWizard()){
                return 0.15;
            }else{
                return 0.2;
            }
        }
    }

    @Override 
    public double getOffsetH(int skill){
        if(skill == 1){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.2;
            }
        }else if(skill == 2) {
            if(player.isKnight()) {
                return 0.15;
            } else {
                return 0.15;
            }
        } else if(skill == 3) {
            if(player.isKnight()) {
                return 0.15;
            } else if(player.isWizard()) {
                return 0.2;
            } else {
                return 0.17;
            }
        }else {
            if(player.isKnight()) {
                return 0.2;
            } else if(player.isWizard()){
                return 0.15;
            }else{
                return 0.2;
            }
        }
        
    }

    @Override
    protected void onBattleStart() {
        EchoesObjects portal = getPanel().objList.get(1);
        getPanel().configureBattle(this, portal);
    }
}