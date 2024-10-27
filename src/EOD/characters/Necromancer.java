package EOD.characters;

import EOD.objects.EchoesObjects;

public class Necromancer extends Enemy {    
    private static final int BASE_ATTACK = 15;
    private static final int BASE_HEALTH = 200;
    private static final int TURN_DURATION = 4000;
    private static final double SPRITE_SCALE = 0.3;
    
    // Skill cooldowns
    private int skill2Cooldown = 0;
    private static final int SKILL2_MAX_COOLDOWN = 3;

    public Necromancer(String name, int posX, int posY, 
            double minRange, double maxRange, Protagonist protagonist) {
            super(name, "necromancer", posX, posY, minRange, maxRange, protagonist);
            configureSprites();
            health = BASE_HEALTH;
            attack = BASE_ATTACK;
            turnDuration = TURN_DURATION;
            animator.setMovementMultiplier(2);
            animator.setDeathAnimationSpeedMultiplier(5);
            animator.setSkillAnimationSpeedMultiplier(3);
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), 10);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), 50);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.007), 52);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.007), 47);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.007), 47);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }
    

    @Override 
    public void skill1() {
        damageDealt = attack + (int)(Math.random() * 3);
        actionString = getName() + " swings its rusty sword for " + damageDealt + " damage!";
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
        
        actionString = getName() + " throws a bone for " + damageDealt + " damage!";
        lastUsedSkill = 2;
        skill2Cooldown = SKILL2_MAX_COOLDOWN;
    }


    @Override 
    public double getOffsetX(int skill){
        if(skill == 1){
            if(protagonist.isKnight()){
                return 0.3;
            }else{
                return 0.3;
            }
        }else if(skill == 2){
            if(protagonist.isKnight()){
                return 0.35;
            }else{
                return 0.15;
            }
        }else if(skill == 3){
            if(protagonist.isWizard()){
                return 0.3;
            }else{
                return 0.22;
            }
        }else{
            if(protagonist.isWizard()){
                return 0.2;
            }else{
                return 0.3;
            }
        }
    }

    @Override 
    public double getOffsetY(int skill){
        if(skill == 1){
            if(protagonist.isKnight()){
                return 0.18;
            }else{
                return 0.2;
            }
        }else if(skill == 2){
            if(protagonist.isKnight()){
                return 0.4;
            }else if(protagonist.isWizard()){
                return 0.4;
            }else{
                return 0.3;
            }
        }else if(skill == 3){
            if(protagonist.isKnight()){
                return 0.3;
            }else{
                return 0.2;
            }
        }else{
            if(protagonist.isKnight()){
                return 0.1;
            }else if(protagonist.isWizard()){
                return 0.2;
            }else{
                return 0.1;
            }
        }
    }

    @Override 
    public double getOffsetW(int skill){
        if(skill == 2) {
            if(protagonist.isKnight()){
                return 0.2;
            }else if(protagonist.isWizard()){
                return 0.15;
            } else {
                return 0.25;
            }
        }else if(skill == 3){
            if(protagonist.isKnight()){
                return 0.15;
            }else if(protagonist.isWizard()){
                return 0.3;
            }else{
                return 0.17;
            }
        }else{
            if(protagonist.isWizard()){
                return 0.2;
            }else if(protagonist.isKnight()){
                return 0.28;
            }else{
                return 0.3;
            }
        }
    }

    @Override 
    public double getOffsetH(int skill){
        if(skill == 2) {
            if(protagonist.isKnight()) {
                return 0.15;
            } else if(protagonist.isWizard()){
                return 0.15;
            }else{
                return 0.07;
            }
        }else if(skill == 3){
            if(protagonist.isKnight()){
                return 0.15;
            }else if(protagonist.isWizard()){
                return 0.3;
            }else{
                return 0.17;
            }
        }else{
            if(protagonist.isKnight()){
                return 0.22;
            }
            if(protagonist.isWizard()){
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