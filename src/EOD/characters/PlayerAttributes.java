package EOD.characters;

import EOD.gameInterfaces.Freeable;
import EOD.objects.SkillEffects;
import java.awt.Dimension;

public class PlayerAttributes implements Freeable{
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

    //Constructor
    public PlayerAttributes(Player player) {
        this.player = player;
        this.screenSize = player.screenSize;
        configure(player.getCharacterType());
    }

    //Setters
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

    public void setHp(int newHealth) { health = newHealth; }
    public void setBaseHp(int newBaseHp) { baseHealth = newBaseHp; }
    public void setMana(int newMana) { mana = newMana; }
    public void setBaseMana(int newBaseMana) { baseMana = newBaseMana; }
    public void setMoney(int newMoney) { money = newMoney; }
    public void addMoney(int newMoney) { money += newMoney; }
    public void setAttack(int newAttack) { attack = newAttack; }
    public void setBaseAttack(int newBaseAttack) { baseAttack = newBaseAttack; }

    //Getters
    public int getHp() { return health; }
    public int getBaseHp() { return baseHealth; }
    public int getMana() { return mana; }
    public int getBaseMana() { return baseMana; }
    public int getMoney() { return money; }
    public int getAttack() { return attack; }
    public int getBaseAttack() { return baseAttack; }

    //Local Methods
    @Override
    public void free(){
        if(skillEffects1 != null) skillEffects1.free();
        if(skillEffects2 != null) skillEffects2.free();
        if(skillEffects3 != null) skillEffects3.free();
        if(skillEffects4 != null) skillEffects4.free();
        if(skillEffectsRandom != null) skillEffectsRandom.free();
        skillEffects1 = null;
        skillEffects2 = null;
        skillEffects3 = null;
        skillEffects4 = null;
        skillEffectsRandom = null;
    }

    public void configure(String characterType) {
        switch (characterType) {
            case "knight":
                setupAttributes(15, 100, 100, 30, 7, 4, 4, 11);
                break;
            case "wizard":
                setupAttributes(15, 100, 130, 15, 6, 6, 6, 6);
                break;
            case "priest":
                setupAttributes(15, 130, 100, 15, 9, 9, 9, 6);
                break;
        }
    }       

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
}
