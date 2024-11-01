package EOD.gameInterfaces;

import EOD.objects.SkillEffects;

public interface PlayerAttributesBlueprint extends Attributes{
    public void configure(String characterType);

    public void setupAttributes(int atk, int hp, int mp, int moneyAmt, int s1, int s2, int s3, int s4);
    public SkillEffects createSkillEffect(String type, double xFactor, double yFactor, int numSprites, boolean looping);

    // Getters and Setters for attributes (health, mana, attack, etc.)
    public int getMana();
    public void setMana(int newMana);
    public int getBaseMana();
    public void setBaseMana(int addMana);

    public int getMoney();
    public void addMoney(int newMoney);
    public void setMoney(int newMoney);
}
