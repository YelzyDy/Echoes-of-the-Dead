package EOD.characters;

import EOD.gameInterfaces.PlayerAttributesBlueprint;
import EOD.objects.SkillEffects;
import java.awt.Dimension;

public class PlayerAttributes implements PlayerAttributesBlueprint{
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
    protected Player player;
    protected int skill2Cd;
    protected int skill3Cd;
    protected int skill4Cd;
    public SkillEffects skillEffectsRandom;
    public SkillEffects skillEffects1;
    public SkillEffects skillEffects2;
    public SkillEffects skillEffects3;
    public SkillEffects skillEffects4;

    private final Dimension screenSize;

    public PlayerAttributes(Player player) {
        this.player = player;
        this.screenSize = player.screenSize;
        configure(player.getCharacterType());
    }

    @Override
    public void configure(String characterType) {
        switch (characterType) {
            case "knight":
                setupAttributes(15, 100, 100, 100, 7, 4, 4, 11);
                break;
            case "wizard":
                setupAttributes(15, 100, 130, 50, 6, 6, 6, 6);
                break;
            case "priest":
                setupAttributes(10, 130, 100, 75, 9, 9, 10, 6);
                break;
        }
    }

    @Override
    public void setupAttributes(int atk, int hp, int mp, int moneyAmt, int s1, int s2, int s3, int s4) {
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
    }

    @Override
    public SkillEffects createSkillEffect(String type, double xFactor, double yFactor, int numSprites, boolean looping) {
        SkillEffects effect = new SkillEffects(
            "effects",
            (int) (screenSize.width * xFactor),
            (int) (screenSize.height * yFactor),
            (int) (screenSize.width * 1),
            (int) (screenSize.width * 1),
            type,
            numSprites,
            player.getPanel()
        );
        effect.setLooping(looping);
        return effect;
    }

    // Getters and Setters for attributes (health, mana, attack, etc.)
    @Override public int getHp() { return health; }
    @Override public void setHp(int newHealth) { health = newHealth; }
    @Override public int getBaseHp() { return baseHealth; }
    @Override public void setBaseHp(int newBaseHp) { baseHealth = newBaseHp; }

    @Override public int getMana() { return mana; }
    @Override public void setMana(int newMana) { mana = newMana; }
    @Override public int getBaseMana() { return baseMana; }
    @Override public void setBaseMana(int newBaseMana) { baseMana = newBaseMana; }

    @Override public int getMoney() { return money; }
    @Override public void addMoney(int newMoney) { money += newMoney; }
    @Override public void setMoney(int newMoney) { money = newMoney; }

    @Override public int getAttack() { return attack; }
    @Override public void setAttack(int newAttack) { attack = newAttack; }
    @Override public int getBaseAttack() { return baseAttack; }
    @Override public void setBaseAttack(int newBaseAttack) { baseAttack = newBaseAttack; }
}
