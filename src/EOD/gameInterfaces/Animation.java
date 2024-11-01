package EOD.gameInterfaces;

import EOD.utils.ImageList;
import java.awt.Image;

public interface Animation {
    public void setOnAnimationComplete(Runnable callback);

    public void setSpeedMultiplier(int speedMultiplier);

    public void setDeathAnimationSpeedMultiplier(int deathAnimationSpeedMultiplier);

    public void setSkillAnimationSpeedMultiplier(int skillAnimationSpeedMultiplier);

    public void setMovementMultiplier(int movementSpeedMultiplier);

    public void importSprites(String assetPackage, String type, double scale, int numOfSprites);

    public ImageList getSpriteList(String type);

    public void updateAnimation();

    public void updateSkillAnimation();

    public void cropSprites();

    public Image getCurrentSprite();

    public void triggerSkillAnimation(int skillNumber, int targetX);

    public int calculateDeltaX(int targetX, boolean returning);

    public void triggerDeathAnimation(double targetY);

    public void scaleSprites(String spriteType, double scale);

    public void scaleDownSprites(String spriteType, double scale);

    public void importSkillSprites(int skillNumber, String assetPackage, double scale, int numOfSprites);

    // Getters and setters
    public boolean getIsMoving();
    public boolean getIsMovingRight();
    public boolean getIsInBattle();
    public boolean getIsDead();
    public boolean getIsUsingSkill();
    public void setMoving(boolean isMoving);
    public void setMovingRight(boolean isMovingRight);
    public void setIsInBattle(boolean value);

    public void restartAnimation();

    public void updateBounds();

    
    public void moveTo(int targetX, int deltaX);
}
