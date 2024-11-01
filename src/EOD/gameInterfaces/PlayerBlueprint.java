package EOD.gameInterfaces;

import EOD.animator.ProtagonistAnimator;
import EOD.characters.PlayerAttributes;
import EOD.characters.enemies.Enemy;
import EOD.objects.SkillEffects;
import EOD.objects.inventory.Inventory;

public interface PlayerBlueprint {
    
    public Inventory getInventory();

    public boolean isWizard();

    public boolean isKnight();

    public boolean isPriest();

    public void configureSprites();

    public void configureSkills();

    public void configureSkillDimension();

    public void reset(boolean playerWon);

    public String getAction();

    public void setEnemy(Enemy enemy);

    public double getXFactor();

    public int getSkill2CD();

    public int getSkill3CD();

    public int getSkill4CD();

    public int getShieldBuffRemaining();

    public int getSkill2BuffRemaining();

    public ProtagonistAnimator getAnimator();

    public void takeDamage(int damage);

    public int getDamageDealt();

    public boolean useSkill(int skillNumber);

    public int getSkillEffectStopFrame();

    public boolean isDamageReducerActive();

    public void resetDamageReducer();

    public void attributeTurnChecker();

    public boolean canUseSkill(int manaCost, int cooldown);

    public void applySkillEffect(SkillEffects effect, Entity target, int stopFrame, double offsetX, double offsetY);

    public boolean skill1();

    public boolean skill2();

    public boolean skill3();

    public boolean skill4();
    
    public PlayerAttributes getAttributes();
}
