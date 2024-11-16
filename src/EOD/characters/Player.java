 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters;

import EOD.animator.*;
import EOD.characters.enemies.Enemy;
import EOD.gameInterfaces.Entity;
import EOD.gameInterfaces.MouseInteractable;
import EOD.objects.*;
import EOD.objects.inventory.Inventory;
import EOD.objects.profiles.AllyProfiles;
import EOD.objects.profiles.PlayerProfile;
import EOD.utils.SFXPlayer;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLayeredPane;

public class Player extends Character implements MouseInteractable{
    private PlayerAnimator animator;
    private Random random = new Random();
    private String characterType;
    public boolean damageReducer;
    private int damageDealt;
    private double xFactor;
    private Enemy enemy;
    private String actionString;
    private SFXPlayer sfxPlayer;
    public double clickX;
    public PlayerAttributes attributes;

    // private static final int MONEY_REGEN = 5; // Knights gain some money per turn
    private static final int MANA_REGEN = 10; // Reduced from 15
    // private static final int HEALTH_REGEN = 5; // Small health regen for Pr

    private static final int SKILL2_DURATION = 3; // Duration of buff in turns
    private static final int SHIELD_DURATION = 3; // Duration of shield in turns
    private int skill2BuffRemaining = 0; // Tracks remaining turns of skill 2 buff
    private int shieldBuffRemaining = 0; // Tracks remaining turns of skill 2 buff
    private int originalAttack; // Stores original attack value
    private Inventory inventory;
    private AllyProfiles allyProfiles;
    private PlayerProfile playerProfile;


    public Player(String characterType, int posX, int posY) {
        super("name", characterType, posX, posY);
        animator = new PlayerAnimator(this);
        animator.setSpeedMultiplier(1);
        attributes = new PlayerAttributes(this);
        setAnimator(animator);
        configureSprites();
        animator.updateBounds();
        xFactor = 0;
        clickX = 0;
        attributes.skill3Cd = attributes.skill4Cd = 0;
        actionString = null;
        this.damageReducer = false;
        this.characterType = characterType;
        originalAttack = attributes.attack;
        sfxPlayer = SFXPlayer.getInstance();
        clickX = -15;
    }

    public void initializeInventory(){
        inventory = new Inventory();
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    @Override
    public void free(){
        super.free();
        random = null;
        characterType = null;
        if(attributes != null){
            attributes.free();
            attributes = null;
        }
    }

    public void setAllyProfiles(AllyProfiles allyProfiles){
        this.allyProfiles = allyProfiles;
    }

    public void setPlayerProfile(JLayeredPane panel){
        playerProfile = new PlayerProfile(panel);
    }

    public AllyProfiles getAllyProfiles(){
        return allyProfiles;
    }

    public PlayerProfile getPlayerProfile(){
        return playerProfile;
    }


    public Inventory getInventory(){
        return inventory;
    }

    public boolean isWizard() {
        return "wizard".equals(characterType);
    }

    public boolean isKnight() {
        return "knight".equals(characterType);
    }

    public boolean isPriest() {
        return "priest".equals(characterType);
    }

    public void configureSprites() {
        int spriteSize = (int) (screenSize.height * 0.006);
        animator.importSprites("character_asset", "walk", spriteSize, 8);
        animator.importSprites("character_asset", "idle", spriteSize, 6);
        animator.importSkillSprites(1, "character_asset", spriteSize, attributes.s1num);
        animator.importSkillSprites(2, "character_asset", spriteSize, attributes.s2num);
        animator.importSkillSprites(3, "character_asset", spriteSize, attributes.s3num);
        animator.importSkillSprites(4, "character_asset", spriteSize, attributes.s4num);
    }

    public void configureSkills(){
        switch(getCharacterType()){
            case "knight":
            attributes.skillEffects1 = attributes.createSkillEffect("kskill1", 0.4, 0.2, 9, false);
            attributes.skillEffects2 = attributes.createSkillEffect("kbuff", 0.4, 0.2, 8, false);
            attributes.skillEffects3 = attributes.createSkillEffect("shield", getPosX() * 0.9, 0.08, 13, true);
            attributes.skillEffects4 = attributes.createSkillEffect("knightss", getPosX() * 0.9, 0.08,  25, false);
            break;
            case "wizard":
            attributes.skillEffects2 = attributes.createSkillEffect("wbuff", getPosX() * 0.9, 0.08, 14, false);
            attributes.skillEffects3 = attributes.createSkillEffect("distortedClock", 0, 0, 19, false);
            attributes.skillEffects4 = attributes.createSkillEffect("wizardss", getPosX() * 0.9, 0.08, 8, false);
            break;
            default:
            attributes.skillEffectsRandom = attributes.createSkillEffect("heal", getPosX() * 0.9, 0.08,14, false);
            attributes.skillEffects2 = attributes.createSkillEffect("pbuff", getPosX() * 0.9, 0.08, 9, false);
            attributes.skillEffects4 = attributes.createSkillEffect("lightning", getPosX() * 0.9, 0.08, 10, false);
            break;
        }
    }

    public void configureSkillDimension() {
        System.out.println("configure Skill dimension called: ");
        switch(getCharacterType()) {
            case "knight":
                attributes.skillEffects1.setDimensions((int)(screenSize.width * enemy.getOffsetW(1)), (int)(screenSize.width * enemy.getOffsetH(1)));
                attributes.skillEffects2.setDimensions((int)(screenSize.width * enemy.getOffsetW(2)), (int)(screenSize.width * enemy.getOffsetH(2)));
                attributes.skillEffects3.setDimensions((int)(screenSize.width * enemy.getOffsetW(3)), (int)(screenSize.width * enemy.getOffsetH(3)));
                attributes.skillEffects4.setDimensions((int)(screenSize.width * enemy.getOffsetW(4)), (int)(screenSize.width * enemy.getOffsetH(4)));
                break;
    
            case "wizard":
                attributes.skillEffects2.setDimensions((int)(screenSize.width * enemy.getOffsetW(2)), (int)(screenSize.width * enemy.getOffsetH(2)));
                attributes.skillEffects3.setDimensions((int)(screenSize.width * enemy.getOffsetW(3)), (int)(screenSize.width * enemy.getOffsetH(3)));
                attributes.skillEffects4.setDimensions((int)(screenSize.width * enemy.getOffsetW(4)), (int)(screenSize.width * enemy.getOffsetH(4)));
                break;
    
            default:
                attributes.skillEffectsRandom.setDimensions((int)(screenSize.width * 0.25), (int)(screenSize.width * 0.15));
                attributes.skillEffects2.setDimensions((int)(screenSize.width * enemy.getOffsetW(3)), (int)(screenSize.width * enemy.getOffsetH(3)));  
                attributes.skillEffects4.setDimensions((int)(screenSize.width * enemy.getOffsetW(4)), (int)(screenSize.width * enemy.getOffsetH(4)));
                break;
        }
    }

    public void reset(boolean playerWon) {
        attributes.skill3Cd = attributes.skill4Cd  = attributes.skill2Cd = 0;
        skill2BuffRemaining = shieldBuffRemaining = 0;
        attributes.setMana((int)(attributes.getBaseMana() * 0.75));
        // attributes.setHp((int)(attributes.getBaseHp() * 0.75));
        if(!playerWon){
            attributes.setHp((int)(attributes.getBaseHp() * 0.75));
        }
        System.out.println("Player won? " + playerWon);
    }

    public String getAction() {
        return actionString;
    }

    public Enemy getEnemy(){
        return enemy;
    }
    
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
        configureSkillDimension();
    }

    public double getXFactor() {
        return xFactor;
    }

    public int getSkill2CD() {
        return attributes.skill2Cd;
    }

    public int getSkill3CD() {
        return attributes.skill3Cd;
    }

    public int getSkill4CD() {
        return attributes.skill4Cd;
    }

    public int getShieldBuffRemaining() {
        return shieldBuffRemaining;
    }

    public int getSkill2BuffRemaining() {
        System.out.println(skill2BuffRemaining + "            asdfsadfasfsadf");
        return skill2BuffRemaining;
    }

    @Override
    public PlayerAnimator getAnimator() {
        return animator;
    }

    public void takeDamage(int damage) {
        attributes.health -= damage;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public boolean useSkill(int skillNumber) {
        switch (skillNumber) {
            case 1:
                return skill1();
            case 2:
                return skill2();
            case 3:
                return skill3();
            case 4:
                return skill4();
            default:
                return false;
        }
    }

    public int getSkillEffectStopFrame() {
        // Returns the appropriate stop frame for skill animations based on character type
        return switch (getCharacterType()) {
            case "knight" -> 8;  // Knight's buff animation frames
            case "wizard" -> 14;  // Wizard's buff animation frames
            case "priest" -> 9;   // Priest's buff animation frames
            default -> 0;
        };
    }

    public boolean isDamageReducerActive() {
        return damageReducer;
    }

    public void resetDamageReducer() {
        damageReducer = false;
    }

    public void attributeTurnChecker() {
        if (attributes.skill2Cd > 0) attributes.skill2Cd--;
        if (attributes.skill3Cd > 0) attributes.skill3Cd--;
        if (attributes.skill4Cd > 0) attributes.skill4Cd--;
        
        // Handle skill 2 buff duration
        if (skill2BuffRemaining > 0) {
            skill2BuffRemaining--;
            if (skill2BuffRemaining == 0) {
                // Reset attack to original value when buff expires
                attributes.attack = originalAttack;
            }
        }

        // Handle shield buff duration
        if (shieldBuffRemaining > 0) {
            shieldBuffRemaining--;
            damageReducer = true;
            if (shieldBuffRemaining == 0) {
                damageReducer = false;
            }
        } else {
            // Ensure damage reducer is false when no shield is active
            damageReducer = false;
        }    
        
        attributes.mana = Math.min(attributes.mana + MANA_REGEN, attributes.baseMana);

    }

    public boolean canUseSkill(int manaCost, int cooldown) {
        if (attributes.mana < manaCost) {
            actionString = "Not enough mana!";
            return false;
        }
        if (cooldown > 0) {
            actionString = "Skill on cooldown!";
            return false;
        }
        return true;
    }

    public void applySkillEffect(SkillEffects effect, Entity target, int stopFrame, double offsetX, double offsetY) {
        effect.bindToTarget(target, -effect.getWidth() * offsetX, -effect.getHeight() * offsetY);
        effect.play();
        effect.setStopFrame(stopFrame);
    }

    private void handleXFactorBasedOnEnemy(int skill){
        switch(getCharacterType()){
            case "knight" -> {
                xFactor = getKnightXFactor(skill);
            }
            case "wizard"  -> {
                xFactor = getWizardXFactor(skill);
            }
            case "priest" -> {
                xFactor = getPriestXFactor(skill);
            }
        }
    }

    private double getKnightXFactor(int skill){
        switch(skill){
            case 1 ->{
                return screenSize.width * 0.5;
            }
            case 2 ->{
                return getPosX();
            }
            case 3 ->{
                return getPosX();
            }
            case 4 ->{
                return screenSize.width * 0.5;
            }
        }
        return 0;
    }

    private double getWizardXFactor(int skill){
        switch(skill){
            case 1 ->{
                return screenSize.width * 0.1;
            }
            case 2 ->{
                return getPosX();
            }
            case 3 ->{
                return screenSize.width * 0.1;
            }
            case 4 ->{
                return screenSize.width * 0.1;
            }
        }
        return 0;
    }

    private double getPriestXFactor(int skill){
        switch(skill){
            case 1 ->{
                if(enemy.getCharacterType().equals("skeleton1")){
                    return screenSize.width * 0.3;
                }else if(enemy.getCharacterType().equals("skeleton2")){
                    return screenSize.width * 0.4;
                }else if(enemy.getCharacterType().equals("necromancer")){
                    return screenSize.width * 0.35;
                }
                return screenSize.width * 0.3;
            }
            case 2 ->{
                return getPosX();
            }
            case 3 ->{
                return screenSize.width * 0.22;
            }
            case 4 ->{
                return screenSize.width * 0.3;
            }
        }
        return 0;
    }

    public boolean skill1() {
        // Basic attack with class-specific mechanics
        handleXFactorBasedOnEnemy(1);
        switch(getCharacterType()) {
            case "knight":
                sfxPlayer.playSFX("src/audio_assets/sfx/knight/knightbasicatk.wav");
                damageDealt = (int)(attributes.attack * 1.2); // Knights deal more basic attack damage
                applySkillEffect(attributes.skillEffects1, enemy, 13, enemy.getOffsetX(1), enemy.getOffsetY(1));
                break;
            case "wizard":
                sfxPlayer.playSFX("src/audio_assets/sfx/wizard/wizardbasicatk.wav");
                damageDealt = attributes.attack;
                attributes.mana = Math.min(attributes.mana + 5, attributes.baseMana); // Mana return on basic attack
                break;
            case "priest":
                sfxPlayer.playSFX("src/audio_assets/sfx/priest/priestbasicatk.wav");
                damageDealt = attributes.attack;
                attributes.health = Math.min(attributes.health + 5, attributes.baseHealth); // Small heal on basic attack
                break;
        }
        actionString = getName() + " dealt " + damageDealt + " damage to the enemy!";
        return true;
    }

    public boolean skill2() {
        handleXFactorBasedOnEnemy(2);
        if (attributes.skill2Cd > 0) {
            actionString = "Skill on cooldown! " + attributes.skill2Cd + " turns remaining!";
            return false;
        }

        switch(getCharacterType()) {
            case "knight":
                if (attributes.money < 25) {
                    actionString = "Not enough Soul Shards!";
                    return false;
                }
                sfxPlayer.playSFX("src/audio_assets/sfx/knight/knightskill1.wav");
                attributes.money -= 10;
                originalAttack = attributes.attack; // Store current attack
                attributes.attack += 15;
                actionString = getName() + "\'s attack increased by 15 for " + SKILL2_DURATION + " turns!";
                applySkillEffect(attributes.skillEffects2, this, getSkillEffectStopFrame(), enemy.getOffsetX(2), enemy.getOffsetY(2));
                break;
                
            case "wizard":
                if (attributes.mana < 20) {
                    actionString = "Not enough mana!";
                    return false;
                }
                sfxPlayer.playSFX("src/audio_assets/sfx/wizard/wizardskill1.wav");
                attributes.mana -= 20;
                originalAttack = attributes.attack;
                attributes.attack += 20;
                actionString = getName() + "\'s attack increased by 20 for " + SKILL2_DURATION + " turns!";
                applySkillEffect(attributes.skillEffects2, this, getSkillEffectStopFrame(), 0.35, 0.3);
                break;
                
            case "priest":
                if (attributes.health < 25) {
                    actionString = "Not enough Soul Energy!";
                    return false;
                }
                sfxPlayer.playSFX("src/audio_assets/sfx/priest/priestskill1.wav");
                attributes.health -= 25;
                originalAttack = attributes.attack;
                attributes.attack += 30;
                actionString = getName() + "\'s attack increased by 30 for " + SKILL2_DURATION + " turns!";
                applySkillEffect(attributes.skillEffects2, this, getSkillEffectStopFrame(), 0.35, 0.3);
                break;
        }
        
        skill2BuffRemaining = SKILL2_DURATION;
        attributes.skill2Cd = 4; // Set cooldown to 4 turns
        return true;
    }

    public boolean skill3() {
        handleXFactorBasedOnEnemy(3);
        if (!canUseSkill(40, attributes.skill3Cd)) return false;

        switch(getCharacterType()) {
            case "knight":
                sfxPlayer.playSFX("src/audio_assets/sfx/knight/knightskill2.wav");
                damageReducer = true;
                attributes.skill3Cd = 3;
                attributes.mana -= 40;
                applySkillEffect(attributes.skillEffects3, this, 14, enemy.getOffsetX(3), enemy.getOffsetY(3));
                actionString = "Defense activated! Damage reduced by 50% for 3 turns!";
                shieldBuffRemaining = SHIELD_DURATION;
                return true;

            case "wizard":
                attributes.mana -= 30;
                if (random.nextInt(100) < 46) { // 45% success rate
                    sfxPlayer.playSFX("src/audio_assets/sfx/wizard/wizardskill2.wav");
                    damageDealt = 35;
                    attributes.skill3Cd = 3;
                    attributes.mana = Math.min(attributes.mana + 90, attributes.baseMana);
                    applySkillEffect(attributes.skillEffects3, enemy, 14, enemy.getOffsetX(3), enemy.getOffsetY(3));
                    actionString = "Shift Successful! 35 damage dealt to enemy!";
                    return true;
                } else {
                    actionString = "Shift Failed!";
                    return false;
                }

            case "priest":
                sfxPlayer.playSFX("src/audio_assets/sfx/priest/priestskill2.wav");
                int Damage = (int)(attributes.baseHealth * 0.4);
                damageDealt = Damage;
                attributes.mana -= 40;
                attributes.skill3Cd = 3;
                actionString = "Health converted to force! " + damageDealt + " damage dealt to enemy!";
                return true;
        }
        return false;
    }

    public boolean skill4() {
        handleXFactorBasedOnEnemy(4);
        if (!canUseSkill(50, attributes.skill4Cd)) return false;
        switch(getCharacterType()) {
            case "knight":
                sfxPlayer.playSFX("src/audio_assets/sfx/knight/knightskill3.wav");
                int moneyBonus = (int)Math.min(attributes.money * 0.15, attributes.attack);
                damageDealt = 2 * attributes.attack + moneyBonus;
                attributes.skill4Cd = 4;
                attributes.mana -= 50;
                applySkillEffect(attributes.skillEffects4, enemy, 25, enemy.getOffsetX(4), enemy.getOffsetY(4));
                actionString = "Truthbinding! Dealt " + damageDealt + " damage to the enemy";
                return true;

            case "wizard":
                sfxPlayer.playSFX("src/audio_assets/sfx/wizard/wizardskill3.wav");
                damageDealt = 30 + (int)(attributes.mana * 0.3);
                attributes.mana -= 50;
                attributes.skill4Cd = 4;
                applySkillEffect(attributes.skillEffects4, enemy, 12, enemy.getOffsetX(4), enemy.getOffsetY(4));
                actionString = "Azure Inferno! Dealt " + damageDealt + " damage to the enemy";
                return true;

            case "priest":
                sfxPlayer.playSFX("src/audio_assets/sfx/priest/priestskill3.wav");
                int missingHealth = attributes.baseHealth - attributes.health;
                damageDealt = (int)(missingHealth * 0.6);
                int ultimateHeal = (int)(attributes.baseHealth * 0.4);
                attributes.health = Math.min(attributes.health + ultimateHeal, attributes.baseHealth);
                attributes.mana -= 50;
                attributes.skill4Cd = 4;
                applySkillEffect(attributes.skillEffects4, enemy, 12, enemy.getOffsetX(4), enemy.getOffsetY(4));
                applySkillEffect(attributes.skillEffectsRandom, this, 14, 0.42, 0.15);
                actionString = "Vengeful Vitality! Healed for " + ultimateHeal + " and dealt " + damageDealt + " damage!";
                ArrayList<Player> playerList = this.getWorld().getPlayerList();

                for(Player player : playerList){
                    player.getAttributes().setHp(player.getAttributes().getHp() + (int)(player.getAttributes().getBaseHp()*0.3));
                    if(player.getAttributes().getHp()>player.getAttributes().getBaseHp()){
                        player.getAttributes().setHp(player.getAttributes().getBaseHp());
                    }
                }
                return true;
        }
        return false;
    }

    public void clickObjectAt(Component obj, double x) {
    
        // Create a fake MouseEvent targeting the desired component with specified coordinates
        MouseEvent fakeClickEvent = new MouseEvent(
            obj,                            // Target component
            MouseEvent.MOUSE_CLICKED,       // Event type
            System.currentTimeMillis(),     // Event time
            0,                              // Modifiers (no modifiers here)
            (int)x,                        // Specified X position
            obj.getY(),                        // Specified Y position
            1,                              // Click count
            false                           // Not a popup trigger
        );
    
        // Call the world's click handler with the created event
        onClick(fakeClickEvent);
    }

    public PlayerAttributes getAttributes() {
        return attributes;
    }

    @Override
    public void onClick(MouseEvent e) {
        if (animator.getIsInBattle() && (enemy != null && !enemy.getIsDefeated())){
            return;
        }
        clickX = e.getX();
        int deltaX = ((int)e.getX() - (int)getPosX()) / 10;
        animator.moveTo(e.getX(), deltaX);
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }

    public void playSfx(Player player, int skillNumber) {
        if (player.getCharacterType().equals("wizard")){
            switch(skillNumber){
                case 1: return; 
            }
        }
    }
}
