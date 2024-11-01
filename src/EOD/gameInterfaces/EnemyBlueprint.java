package EOD.gameInterfaces;

import EOD.animator.EnemyAnimator;

public interface EnemyBlueprint extends Attributes, Skills{
    public double getXFactor();
    public double getYFactor();

    
    public abstract double getOffsetX(int skill);
    public abstract double getOffsetY(int skill);
    public abstract double getOffsetW(int skill);
    public abstract double getOffsetH(int skill);

    public void setIsDefeated(boolean isDefeated);

    public boolean getIsDefeated();

    public void configureSprites();

    public int getDamageDealt();

    public int getLastUsedSkill();

    public void takeDamage(int damage);

    public void setIndex(int index);

    public int getIndex();

    public int getMoneyDrop();

    public EnemyAnimator getAnimator();

    public String getAction();

    public void positionForBattle();
}
