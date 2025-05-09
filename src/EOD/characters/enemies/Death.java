package EOD.characters.enemies;

import EOD.characters.Player;
import EOD.objects.ClickableObjects;

public class Death extends Enemy{
    // private static final int BASE_ATTACK = 15;
    // private static final int BASE_HEALTH = 150;

    private static final int BASE_ATTACK = 15;
    private static final int BASE_HEALTH = 150;
    
    // Skill cooldowns
    private int skill2Cooldown = 0;
    private static final int SKILL2_MAX_COOLDOWN = 3;

    public Death(String name, int posX, int posY, 
            double minRange, double maxRange, Player protagonist) {
            super(name, "death", posX, posY, minRange, maxRange, protagonist);
            configureSprites();
            health = BASE_HEALTH;
            attack = BASE_ATTACK;
            baseHp = BASE_HEALTH;
            baseAttack = BASE_ATTACK;
            moneyDrop = 50;
            animator.setMovementMultiplier(2);
            animator.setDeathAnimationSpeedMultiplier(1);
            animator.setSkillAnimationSpeedMultiplier(1);
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.0055), 8);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.0055), 8);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.0055), 11);
        animator.importSprites("character_asset","hurt",(int)(screenSize.height * 0.0055), 4);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.0055), 10);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.0055), 9);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }
    

    @Override
    public void skill1() {
        //sfxPlayer.playSFX("src/audio_assets/sfx/miniboss/deathbasicatk.wav");
        damageDealt = attack + (int)(Math.random() * 3);
        actionString = getName() + " used a Slice, dealt " + damageDealt + " damage!";
        lastUsedSkill = 1;
        xFactor = screenSize.width * 0.3;
        yFactor = 0;
    }

    @Override 
    public void skill2() {
        //sfxPlayer.playSFX("src/audio_assets/sfx/miniboss/deathskill1.wav");
        int baseSkill2Damage = (int)(attack * 1.5);
        damageDealt = baseSkill2Damage + (int)(Math.random() * 4) - 2;
        
        actionString = getName() + " used Dark Invocation, dealt " + damageDealt + " damage!";
        lastUsedSkill = 2;
        skill2Cooldown = SKILL2_MAX_COOLDOWN;
        xFactor = screenSize.width * 0.6;
        yFactor = 0;
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
        //increasing this moves skill effects up
        if(skill == 1){
            if(player.isKnight()){
                return 0.001;
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
                return 0.001;
            }else if(player.isWizard()){
                return 0.2;
            }else{
                return 0.1;
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
    public void update() {
        if (skill2Cooldown > 0) {
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
    protected void onBattleStart() {
        ClickableObjects portal = getPanel().objList.get(2);
        getPanel().configureBattle(this, portal);
    }
}
