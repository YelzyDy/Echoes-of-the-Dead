 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters;

import java.awt.event.MouseEvent;
import java.util.Random;
import EOD.MouseInteractable;
import EOD.scenes.SceneBuilder;
import EOD.animator.*;
import EOD.objects.*;
import EOD.entities.*;

public class Protagonist extends Character implements MouseInteractable {
    private ProtagonistAnimator animator;
    private boolean skillIsUseable = true;
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
    private int skill2BuffRemaining = 0; // Tracks remaining turns of skill 2 buff
    private int originalAttack; // Stores original attack value

    public Protagonist(String name, String characterType, SceneBuilder panel, int posX, int posY) {
        super(name, characterType, panel, posX, posY);
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
            attributes.skillEffects2 = attributes.createSkillEffect("kbuff", 0.4, 0.2, enemy.getOffsetW(2),  enemy.getOffsetH(2), 15, false);
            attributes.skillEffects3 = attributes.createSkillEffect("shield", getPosX() * 0.9, 0.08, enemy.getOffsetW(3),  enemy.getOffsetH(3), 13, true);
            attributes.skillEffects4 = attributes.createSkillEffect("distortedClock", getPosX() * 0.9, 0.08, enemy.getOffsetW(4),  enemy.getOffsetH(4), 19, false);
            break;
            case "wizard":
            attributes.skillEffects2 = attributes.createSkillEffect("wbuff", getPosX() * 0.9, 0.08, enemy.getOffsetW(2),  enemy.getOffsetH(2), 14, false);
            attributes.skillEffects3 = attributes.createSkillEffect("zawardo", 0, 0, enemy.getOffsetW(3),  enemy.getOffsetH(3), 14, false);
            attributes.skillEffects4 = attributes.createSkillEffect("explosion", getPosX() * 0.9, 0.08,enemy.getOffsetW(4),  enemy.getOffsetH(4), 12, false);
            break;
            default:
            attributes.skillEffectsRandom = attributes.createSkillEffect("heal", getPosX() * 0.9, 0.08, enemy.getOffsetW(2),  enemy.getOffsetH(2), 4, true);
            attributes.skillEffects2 = attributes.createSkillEffect("pbuff", getPosX() * 0.9, 0.08, enemy.getOffsetW(3),  enemy.getOffsetH(3), 9, false);
            attributes.skillEffects4 = attributes.createSkillEffect("lightning", getPosX() * 0.9, 0.08, enemy.getOffsetW(4),  enemy.getOffsetH(4), 10, false);
            break;
        }
    }

    public void reset() {
        skillIsUseable = true;
        attributes.skill3Cd = attributes.skill4Cd = 0;
    }

    public String getAction() {
        return actionString;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
        configureSkills();
    }

    public int getTurnDuration() {
        return attributes.turnDuration;
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
            case "knight" -> 15;  // Knight's buff animation frames
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
        
        if (isKnight()) {
            attributes.money = Math.min(attributes.money + MONEY_REGEN, attributes.baseMoney);
        }
        if (isWizard()) {
            attributes.mana = Math.min(attributes.mana + MANA_REGEN, attributes.baseMana);
        }
        if (isPriest()) {
            attributes.health = Math.min(attributes.health + HEALTH_REGEN, attributes.baseHealth);
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
                if (attributes.money < 10) {
                    actionString = "Not enough money!";
                    return false;
                }
                attributes.money -= 10;
                originalAttack = attributes.attack; // Store current attack
                attributes.attack += 15;
                actionString = "Player's attack increased by 15 for " + SKILL2_DURATION + " turns!";
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
                break;
                
            case "priest":
                if (attributes.health < 25) {
                    actionString = "Not enough health!";
                    return false;
                }
                attributes.health -= 25;
                originalAttack = attributes.attack;
                attributes.attack += 30;
                actionString = "Player's attack increased by 30 for " + SKILL2_DURATION + " turns!";
                break;
        }
        
        applySkillEffect(attributes.skillEffects2, this, getSkillEffectStopFrame(), 0.35, 0.3);
        skill2BuffRemaining = SKILL2_DURATION;
        attributes.skill2Cd = 4; // Set cooldown to 4 turns
        return true;
    }


     public boolean skill3() {
        if (!canUseSkill(25, attributes.skill3Cd)) return false;

        switch(getCharacterType()) {
            case "knight":
                damageReducer = true;
                attributes.skill3Cd = 3;
                applySkillEffect(attributes.skillEffects3, this, 14, enemy.getOffsetX(3), enemy.getOffsetY(3));
                actionString = "Defense activated! Damage reduced by 60%";
                xFactor = getPosX();
                return true;

            case "wizard":
                if (random.nextInt(100) < 75) { // 75% success rate
                    damageDealt = 35;
                    attributes.skill3Cd = 3;
                    attributes.mana = Math.min(attributes.mana + 50, attributes.baseMana);
                    applySkillEffect(attributes.skillEffects3, enemy, 14, 0.25, 0.30);
                    actionString = "Shift Successful! 35 damage dealt to enemy!";
                    xFactor = screenSize.width * 0.1;
                    return true;
                } else {
                    actionString = "Shift Failed!";
                    return false;
                }

            case "priest":
                int healing = (int)(attributes.baseHealth * 0.3);
                damageDealt = (int)(attributes.baseHealth * 0.25);
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
                applySkillEffect(attributes.skillEffects4, enemy, 19, enemy.getOffsetX(4), enemy.getOffsetY(4));
                actionString = "Time Stop! Dealt " + damageDealt + " damage to the enemy";
                xFactor = screenSize.width * 0.5;
                return true;

            case "wizard":
                damageDealt = 50 + (int)(attributes.mana * 0.3);
                attributes.skill4Cd = 4;
                applySkillEffect(attributes.skillEffects4, enemy, 12, enemy.getOffsetX(4), enemy.getOffsetY(4));
                actionString = "Explosion! Dealt " + damageDealt + " damage to the enemy";
                xFactor = screenSize.width * 0.1;
                return true;

            case "priest":
                int missingHealth = attributes.baseHealth - attributes.health;
                damageDealt = (int)(missingHealth * 0.4);
                int ultimateHeal = (int)(attributes.baseHealth * 0.4);
                attributes.health = Math.min(attributes.health + ultimateHeal, attributes.baseHealth);
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
