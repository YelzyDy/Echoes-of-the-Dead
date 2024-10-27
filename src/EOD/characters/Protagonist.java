 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters;

import java.awt.event.MouseEvent;
import java.util.Random;
import EOD.MouseInteractable;
import EOD.animator.*;
import EOD.objects.*;
import EOD.entities.*;

public class Protagonist extends Character implements MouseInteractable {
    private ProtagonistAnimator animator;
    private Random random = new Random();
    private String characterType;
    public boolean damageReducer;
    private int damageDealt;
    private double xFactor;
    private Enemy enemy;
    private String actionString;
    public ProtagonistAttributes attributes;

    private static final int MONEY_REGEN = 5; // Knights gain some money per turn
    private static final int MANA_REGEN = 10; // Reduced from 15
    private static final int HEALTH_REGEN = 5; // Small health regen for Pr

    private static final int SKILL2_DURATION = 3; // Duration of buff in turns
    private static final int SHIELD_DURATION = 1; // Duration of shield in turns
    private int skill2BuffRemaining = 0; // Tracks remaining turns of skill 2 buff
    private int shieldBuffRemaining = 0; // Tracks remaining turns of skill 2 buff
    private int originalAttack; // Stores original attack value

    public Protagonist(String name, String characterType, int posX, int posY) {
        super(name, characterType, posX, posY);
        animator = new ProtagonistAnimator(this);
        animator.setSpeedMultiplier(1);
        attributes = new ProtagonistAttributes(this);
        setAnimator(animator);
        configureSprites();
        animator.updateBounds();
        xFactor = 0;
        attributes.skill3Cd = attributes.skill4Cd = 0;
        actionString = null;
        this.damageReducer = false;
        this.characterType = characterType;
        originalAttack = attributes.attack;
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

    private void configureSprites() {
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
            attributes.skillEffectsRandom = attributes.createSkillEffect("heal", getPosX() * 0.9, 0.08, 4, true);
            attributes.skillEffects2 = attributes.createSkillEffect("pbuff", getPosX() * 0.9, 0.08, 9, false);
            attributes.skillEffects4 = attributes.createSkillEffect("lightning", getPosX() * 0.9, 0.08, 10, false);
            break;
        }
    }

    public void configureSkillDimension() {
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
                attributes.skillEffectsRandom.setDimensions((int)(screenSize.width * enemy.getOffsetW(2)), (int)(screenSize.width * enemy.getOffsetH(2)));
                attributes.skillEffects2.setDimensions((int)(screenSize.width * enemy.getOffsetW(3)), (int)(screenSize.width * enemy.getOffsetH(3)));  
                attributes.skillEffects4.setDimensions((int)(screenSize.width * enemy.getOffsetW(4)), (int)(screenSize.width * enemy.getOffsetH(4)));
                break;
        }
    }

    public void reset() {
        attributes.skill3Cd = attributes.skill4Cd  = attributes.skill2Cd = 0;
    }

    public String getAction() {
        return actionString;
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

    public ProtagonistAnimator getAnimator() {
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

    private int getSkillEffectStopFrame() {
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

    // Reset the damage reducer flag
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
        }
        
        if (isKnight()) {
            attributes.money = Math.min(attributes.money + MONEY_REGEN, attributes.baseMoney);
            attributes.mana = Math.min(attributes.mana + MANA_REGEN, attributes.baseMana);
        }
        if (isWizard()) {
            attributes.mana = Math.min(attributes.mana + MANA_REGEN, attributes.baseMana);
        }
        if (isPriest()) {
            attributes.health = Math.min(attributes.health + HEALTH_REGEN, attributes.baseHealth);
            attributes.mana = Math.min(attributes.mana + MANA_REGEN, attributes.baseMana);
        }
    }

    private boolean canUseSkill(int manaCost, int cooldown) {
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

    private void applySkillEffect(SkillEffects effect, Entity target, int stopFrame, double offsetX, double offsetY) {
        effect.bindToTarget(target, -effect.getWidth() * offsetX, -effect.getHeight() * offsetY);
        effect.play();
        effect.setStopFrame(stopFrame);
    }

    public boolean skill1() {
        // Basic attack with class-specific mechanics
        switch(getCharacterType()) {
            case "knight":
                damageDealt = (int)(attributes.attack * 1.2); // Knights deal more basic attack damage
                xFactor = screenSize.width * 0.5;
                applySkillEffect(attributes.skillEffects1, enemy, 13, enemy.getOffsetX(1), enemy.getOffsetY(1));
                attributes.skillEffects1.setOpaque(true);
                break;
            case "wizard":
                damageDealt = attributes.attack;
                attributes.mana = Math.min(attributes.mana + 5, attributes.baseMana); // Mana return on basic attack
                xFactor = screenSize.width * 0.1;
                break;
            case "priest":
                damageDealt = attributes.attack;
                attributes.health = Math.min(attributes.health + 5, attributes.baseHealth); // Small heal on basic attack
                xFactor = screenSize.width * 0.3;
                break;
        }
        
        actionString = "Player dealt " + damageDealt + " damage to the enemy!";
        return true;
    }

    public boolean skill2() {
        xFactor = getPosX();
        
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
                attributes.money -= 10;
                originalAttack = attributes.attack; // Store current attack
                attributes.attack += 25;
                actionString = "Player's attack increased by 15 for " + SKILL2_DURATION + " turns!";
                applySkillEffect(attributes.skillEffects2, this, getSkillEffectStopFrame(), enemy.getOffsetX(2), enemy.getOffsetY(2));
                break;
                
            case "wizard":
                if (attributes.mana < 20) {
                    actionString = "Not enough mana!";
                    return false;
                }
                attributes.mana -= 20;
                originalAttack = attributes.attack;
                attributes.attack += 20;
                actionString = "Player's attack increased by 20 for " + SKILL2_DURATION + " turns!";
                applySkillEffect(attributes.skillEffects2, this, getSkillEffectStopFrame(), 0.35, 0.3);
                break;
                
            case "priest":
                if (attributes.health < 25) {
                    actionString = "Not enough Soul Energy!";
                    return false;
                }
                attributes.health -= 25;
                originalAttack = attributes.attack;
                attributes.attack += 30;
                actionString = "Player's attack increased by 30 for " + SKILL2_DURATION + " turns!";
                applySkillEffect(attributes.skillEffects2, this, getSkillEffectStopFrame(), 0.35, 0.3);
                break;
        }
        
        skill2BuffRemaining = SKILL2_DURATION;
        attributes.skill2Cd = 4; // Set cooldown to 4 turns
        return true;
    }


     public boolean skill3() {
        if (!canUseSkill(40, attributes.skill3Cd)) return false;

        switch(getCharacterType()) {
            case "knight":
                damageReducer = true;
                attributes.skill3Cd = 3;
                attributes.mana -= 40;
                applySkillEffect(attributes.skillEffects3, this, 14, enemy.getOffsetX(3), enemy.getOffsetY(3));
                actionString = "Defense activated! Damage reduced by 60%";
                xFactor = getPosX();
                shieldBuffRemaining = SHIELD_DURATION;
                return true;

            case "wizard":
                attributes.mana -= 30;
                if (random.nextInt(100) < 55) { // 55% success rate
                    damageDealt = 35;
                    attributes.skill3Cd = 3;
                    attributes.mana = Math.min(attributes.mana + 90, attributes.baseMana);
                    applySkillEffect(attributes.skillEffects3, enemy, 14, enemy.getOffsetX(3), enemy.getOffsetY(3));
                    actionString = "Shift Successful! 35 damage dealt to enemy!";
                    xFactor = screenSize.width * 0.1;
                    return true;
                } else {
                    actionString = "Shift Failed!";
                    return false;
                }

            case "priest":
                int healing = (int)(attributes.baseHealth * 0.3);
                damageDealt = (int)(attributes.baseHealth * 0.4);
                attributes.health = Math.min(attributes.health + healing, attributes.baseHealth);
                attributes.mana -= 40;
                attributes.skill3Cd = 3;
                actionString = "Healed for " + healing + " and dealt " + damageDealt + " damage!";
                xFactor = screenSize.width * 0.22;
                return true;
        }
        return false;
    }

    public boolean skill4() {
        if (!canUseSkill(50, attributes.skill4Cd)) return false;

        switch(getCharacterType()) {
            case "knight":
                int moneyBonus = (int)Math.min(attributes.money * 0.15, attributes.attack);
                damageDealt = 2 * attributes.attack + moneyBonus;
                attributes.skill4Cd = 4;
                attributes.mana -= 50;
                applySkillEffect(attributes.skillEffects4, enemy, 25, enemy.getOffsetX(4), enemy.getOffsetY(4));
                actionString = "Time Stop! Dealt " + damageDealt + " damage to the enemy";
                xFactor = screenSize.width * 0.5;
                return true;

            case "wizard":
                damageDealt = 50 + (int)(attributes.mana * 0.3);
                attributes.mana -= 50;
                attributes.skill4Cd = 4;
                applySkillEffect(attributes.skillEffects4, enemy, 12, enemy.getOffsetX(4), enemy.getOffsetY(4));
                actionString = "Explosion! Dealt " + damageDealt + " damage to the enemy";
                xFactor = screenSize.width * 0.1;
                return true;

            case "priest":
                int missingHealth = attributes.baseHealth - attributes.health;
                damageDealt = (int)(missingHealth * 0.6);
                int ultimateHeal = (int)(attributes.baseHealth * 0.4);
                attributes.health = Math.min(attributes.health + ultimateHeal, attributes.baseHealth);
                attributes.mana -= 50;
                attributes.skill4Cd = 4;
                applySkillEffect(attributes.skillEffects4, enemy, 12, 0.35, 0.40);
                applySkillEffect(attributes.skillEffectsRandom, this, 14, 0.4, 0.30);
                actionString = "Divine Retribution! Healed for " + ultimateHeal + " and dealt " + damageDealt + " damage!";
                xFactor = screenSize.width * 0.3;
                return true;
        }
        return false;
    }

    public ProtagonistAttributes getAttributes() {
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
