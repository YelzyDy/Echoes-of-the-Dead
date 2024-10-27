package EOD.characters;

import EOD.objects.SkillEffects;
import java.awt.Dimension;

public class ProtagonistAttributes {
    protected int baseAttack;
    protected int attack;
    protected int health;
    protected int baseHealth;
    protected int mana;
    protected int baseMana;
    protected int baseMoney;
    protected int money;
    protected int s1num;
    protected int s2num;
    protected int s3num;
    protected int s4num;
    protected int turnDuration;
    protected Protagonist player;
    protected int skill2Cd;
    protected int skill3Cd;
    protected int skill4Cd;
    public SkillEffects skillEffectsRandom;
    public SkillEffects skillEffects1;
    public SkillEffects skillEffects2;
    public SkillEffects skillEffects3;
    public SkillEffects skillEffects4;

    private final Dimension screenSize;

    public ProtagonistAttributes(Protagonist player) {
        this.player = player;
        this.screenSize = player.screenSize;
        configure(player.getCharacterType());
    }
    
    private void configure(String characterType) {
        switch (characterType) {
            case "knight":
                setupAttributes(15, 100, 100, 100, 7, 4, 4, 11, 3000);
                break;
            case "wizard":
                setupAttributes(15, 100, 130, 50, 6, 6, 6, 6, 3000);
                break;
            case "priest":
                setupAttributes(10, 130, 100, 75, 9, 9, 10, 6, 3000);
                break;
        }
    }

    private void setupAttributes(int atk, int hp, int mp, int moneyAmt, int s1, int s2, int s3, int s4, int duration) {
        this.attack = atk;
        this.baseAttack = attack;
        this.health = hp;
        this.baseHealth = hp;
        this.mana = mp;
        this.baseMana = mp;
        this.money = moneyAmt;
        this.baseMoney = money;
        this.s1num = s1;
        this.s2num = s2;
        this.s3num = s3;
        this.s4num = s4;
        this.turnDuration = duration;
    }

    protected SkillEffects createSkillEffect(String type, double xFactor, double yFactor, double widthFactor, double heightFactor, int numSprites, boolean looping) {
        SkillEffects effect = new SkillEffects(
            "effects",
            (int) (screenSize.width * xFactor),
            (int) (screenSize.height * yFactor),
            (int) (screenSize.width * widthFactor),
            (int) (screenSize.width * heightFactor),
            type,
            numSprites,
            player.panel
        );
        effect.setLooping(looping);
        return effect;
    }

    // Getters and Setters for attributes (health, mana, attack, etc.)
    public int getHp() { return health; }
    public void setHp(int newHealth) { health = newHealth; }
    public int getBaseHp() { return baseHealth; }
    public void setBaseHp(int addHp) { baseHealth += addHp; }

    public int getMana() { return mana; }
    public void setMana(int newMana) { mana = newMana; }
    public int getBaseMana() { return baseMana; }
    public void setBaseMana(int addMana) { baseMana += addMana; }

    public int getMoney() { return money; }
    public void setMoney(int newMoney) { money += newMoney; }

    public int getAttack() { return attack; }
    public void setAttack(int newAttack) { attack = newAttack; }
    public int getBaseAttack() { return baseAttack; }
    public void setBaseAttack(int addAttack) { baseAttack += addAttack; }
}
