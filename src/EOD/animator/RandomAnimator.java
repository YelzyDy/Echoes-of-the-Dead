package EOD.animator;

import java.util.Random;
import EOD.characters.Character;

public abstract class RandomAnimator extends Animator{
    protected Random random;
    protected long lastMovementTime;
    protected long lastDirectionChangeTime;
    protected int moveDuration = 5000;
    protected int pauseDuration = 1000;
    protected int directionChangeCooldown = 5000;
    protected boolean isPaused;
    protected int moveSpeed;
    protected boolean isInteracting;
    protected double minRange;
    protected double maxRange;

    public RandomAnimator(Character character, int numberOfSkills) {
        super(character, numberOfSkills);
        this.random = new Random();
        this.lastMovementTime = System.currentTimeMillis();
        this.lastDirectionChangeTime = System.currentTimeMillis();
        this.isPaused = false;
        moveSpeed = 2;
    }

    @Override
    public void startMovement() {
        isMoving = true;
        isPaused = false;
        lastMovementTime = System.currentTimeMillis();
    }

    @Override
    public void stopMovement() {
        isMoving = false;
        restartAnimation();
    }

    public void setRange(double minRange, double maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public void chooseNewDirection() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDirectionChangeTime < directionChangeCooldown) {
            return;
        }

        lastDirectionChangeTime = currentTime;
        int target = random.nextInt((int)maxRange - (int)minRange) + (int)minRange;
        boolean newDirection = random.nextBoolean();
        if (newDirection != isMovingRight) {
            setMovingRight(newDirection);
            restartAnimation();
        }
        moveTo(target, moveSpeed);
    }

    public void setInteracting(boolean value) {
        this.isInteracting = value;
    }

    public int getTargetX(){
        return targetX;
    }

    public int getDeltaX(){
        return deltaX;
    }

    public void setTargetX(int targetX){
        this.targetX = targetX;
    }

    public void setDeltaX(int deltaX){
        this.deltaX = deltaX;
    }

    public void setPaused(boolean value){
        isPaused = value;
    }

}
