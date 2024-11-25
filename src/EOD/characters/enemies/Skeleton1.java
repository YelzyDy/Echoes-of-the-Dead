package EOD.characters.enemies;
import EOD.characters.Player;
import EOD.objects.ClickableObjects;

public class Skeleton1 extends Enemy {
    // Constants for better maintainability
    private static final int BASE_ATTACK = 10;
    private static final int BASE_HEALTH = 100;
    
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
        baseHp = BASE_HEALTH;
        baseAttack = BASE_ATTACK;
        moneyDrop = 30;
        animator.setSpeedMultiplier(1);
    }

    public void configureSprites() {
        int baseSize = (int)(screenSize.height * 0.007);
        
        // Import all sprite sets
        animator.importSprites("character_asset", "walk", baseSize, 8);
        animator.importSprites("character_asset", "idle", baseSize, 6);
        animator.importSprites("character_asset", "dead", baseSize, 4);
        animator.importSprites("character_asset","hurt", baseSize, 4);
        animator.importSkillSprites(1, "character_asset", baseSize, 8);
        animator.importSkillSprites(2, "character_asset", baseSize, 9);
    
        
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }

    @Override 
    public void skill1() {
        // Basic attack - consistent but low damage
        damageDealt = attack + (int)(Math.random() * 3); // 10-13 damage
        actionString = getName() + " thrusted its broadaxe dealing " + damageDealt + " damage!";
        lastUsedSkill = 1;
    }

    @Override 
    public void skill2(){
        // Slightly higher damage than basic attack but unreliable
        int baseSkill2Damage = (int)(attack * 1.5);
        damageDealt = baseSkill2Damage + (int)(Math.random() * 4); // 10-14 damage with variation
        
        actionString = getName() + " spins its broadaxe dealing " + damageDealt + " damage!";
        lastUsedSkill = 2;
        skill2Cooldown = SKILL2_MAX_COOLDOWN;
    }

    @Override
    public void update() {
        if (skill2Cooldown > 0) {
            System.out.println("cooldown: " + skill2Cooldown);
            skill2Cooldown--;
        }
    }

    // Decision making for skill usage
    @Override
    public int decideSkill() {
        if (skill2Cooldown > 0) {
            return 1;
        }
        // Otherwise, 70% chance for skill1, 30% chance for skill2
        return (Math.random() < 0.7) ? 1 : 2;
    }

    @Override
    public double getXFactor() {
        return screenSize.width * 0.35;
    }

    @Override 
    public double getOffsetX(int skill) {
        // increasing this moves skill effects to the left
        if(skill == 1){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.1;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.35;
            }else if(player.isWizard()){
                return 0.15;
            }else{
                return 0.25;
            }
        }else if(skill == 3) {
            if(player.isWizard()) {
                return 0.3;
            } else{
                return 0.25;
            }
        } else {
            if(player.isKnight()){
                return 0.3;
            }else if(player.isWizard()){
                return 0.25;
            }else{
                return 0.4;
            }
        }
    }

    @Override 
    public double getOffsetY(int skill) {
        //increasing this moves skill effects up
        if(skill == 1){
            if(player.isKnight()){
                return 0.4;
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
            }else if(player.isWizard()){
                return 0.3;
            }  else{
                return -0.7;
            }
        }else{
            if(player.isKnight()){
                return 0.4;
            }else if(player.isWizard()){
                return 0.35;
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
                return 0.17;
            }
        } else if(skill == 3) {
            if(player.isKnight()) {
                return 0.15;
            } else if(player.isWizard()) {
                return 0.2;
            } else {
                return 0.12;
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
                return 0.05;
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
        ClickableObjects portal = getPanel().objList.get(1);
        getPanel().configureBattle(this, portal);
    }
}