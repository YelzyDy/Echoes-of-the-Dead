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

    private static final int MANA_REGEN = 15;

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

    public void reset() {
        skillIsUseable = true;
        attributes.skill3Cd = attributes.skill4Cd = 0;
    }

    public String getAction() {
        return actionString;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public int getTurnDuration() {
        return attributes.turnDuration;
    }

    public double getXFactor() {
        return xFactor;
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
        // Logic to check if the skill is available (e.g., not on cooldown, sufficient mana, etc.)
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

    public boolean isDamageReducerActive() {
        return damageReducer;
    }

    // Reset the damage reducer flag
    public void resetDamageReducer() {
        damageReducer = false;
    }

    public void attributeTurnChecker() {
        if (attributes.skill3Cd > 0) attributes.skill3Cd--;
        if (attributes.skill4Cd > 0) attributes.skill4Cd--;

        attributes.mana = Math.min(attributes.mana + MANA_REGEN, attributes.baseMana);
        attributes.health = Math.min(attributes.health, attributes.baseHealth);
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
        damageDealt = attributes.attack;
        actionString = "Player dealt " + damageDealt + " damage to the enemy!";
        xFactor = switch (getCharacterType()) {
            case "knight" -> screenSize.width * 0.5;
            case "wizard" -> screenSize.width * 0.1;
            default -> screenSize.width * 0.3;
        };
        return true;
    }

    public boolean skill2() {
        xFactor = getPosX();
        int skillEffectStopFrame = switch (getCharacterType()) {
            case "knight" -> 15;
            case "wizard" -> 14;
            case "priest" -> 9;
            default -> 0;
        };

        if (!skillIsUseable) {
            actionString = "Can only be used once per battle!";
            return false;
        }

        if (getCharacterType().equals("knight") && attributes.money < 15 ||
            getCharacterType().equals("wizard") && attributes.mana < 15 ||
            getCharacterType().equals("priest") && attributes.health < 50) {
            actionString = "Not enough resources!";
            return false;
        }

        applySkillEffect(attributes.skillEffects2, this, skillEffectStopFrame, 0.35, 0.3);

        if (getCharacterType().equals("knight")) {
            attributes.money -= 15;
            attributes.attack += 15;
            actionString = "Player's attack increased by 15!";
        } else if (getCharacterType().equals("wizard")) {
            attributes.mana -= 15;
            attributes.attack += 15;
            actionString = "Player's attack increased by 15!";
        } else if (getCharacterType().equals("priest")) {
            attributes.health -= 15;
            attributes.attack += 30;
            actionString = "Player's attack increased by 30!";
        }
        
        skillIsUseable = false;
        return true;
    }

    public boolean skill3() {
        if (!canUseSkill(25, attributes.skill3Cd)) return false;

        switch (getCharacterType()) {
            case "knight" -> {
                damageReducer = true;
                attributes.skill3Cd = 3;
                applySkillEffect(attributes.skillEffects3, this, 14, enemy.getOffsetX(3), enemy.getOffsetY(3));
                actionString = "Enemy's damage reduced by 4%";
                xFactor = getPosX();
                return true;
            }
            case "wizard" -> {
                if (random.nextInt(100) < 60) {
                    damageDealt = 40;
                    attributes.skill3Cd = 3;
                    attributes.mana = Math.min(attributes.mana + 75, attributes.baseMana);
                    applySkillEffect(attributes.skillEffects3, enemy, 14, 0.25, 0.30);
                    actionString = "Shift Successful! 40 damage dealt to enemy!";
                    xFactor = screenSize.width * 0.1;
                    return true;
                } else {
                    actionString = "Shift Failed!";
                    return false;
                }
            }
            case "priest" -> {
                damageDealt = (int) (attributes.baseHealth * 0.3);
                attributes.health = Math.min(attributes.health + damageDealt, attributes.baseHealth);
                attributes.mana -= 40;
                attributes.skill3Cd = 3;
                actionString = "Player dealt " + damageDealt + " damage to the enemy!";
                xFactor = screenSize.width * 0.22;
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public boolean skill4() {
        if (!canUseSkill(50, attributes.skill4Cd)) return false;
        switch (getCharacterType()) {
            case "knight" -> {
                damageDealt = 2 * attributes.attack + (int) (attributes.money * 0.2);
                attributes.skill4Cd = 4;
                applySkillEffect(attributes.skillEffects4, enemy, 19, enemy.getOffsetX(4), enemy.getOffsetY(4));
                actionString = "Enemy missed a turn! Player dealt " + damageDealt + " damage to the enemy";
                xFactor = screenSize.width * 0.5;
                return true;
            }
            case "wizard" -> {
                damageDealt = 60 + (int) (attributes.baseMana * 0.25);
                attributes.skill4Cd = 4;
                applySkillEffect(attributes.skillEffects4, enemy, 12, enemy.getOffsetX(4), enemy.getOffsetY(4));
                actionString = "Player dealt " + damageDealt + " damage to the enemy";
                xFactor = screenSize.width * 0.1;
                return true;
            }
            case "priest" -> {
                damageDealt = (int) ((attributes.baseHealth - attributes.health) * 0.6);
                attributes.health = Math.min(attributes.health + (int) (attributes.baseHealth * 0.4), attributes.baseHealth);
                attributes.skill4Cd = 4;
                applySkillEffect(attributes.skillEffects4, enemy, 12, 0.35, 0.40);
                applySkillEffect(attributes.skillEffectsRandom, this, 14, 0.4, 0.30);
                actionString = "Player dealt " + damageDealt + " damage to the enemy";
                xFactor = screenSize.width * 0.3;
                return true;
            }
            default -> {
                return false;
            }
        }
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
