package EOD.animator;

import java.util.Random;
import EOD.animator.NpcAnimator;
import EOD.characters.Character;

public class NpcAnimator extends Animator {
    private Random random;
    private long lastMovementTime;
    private long lastDirectionChangeTime;
    private int moveDuration = 5000;
    private int pauseDuration = 1000;
    private int directionChangeCooldown = 5000;
    private boolean isPaused;
    private int moveSpeed = 2;
    private boolean isInteracting;
    private double minRange;
    private double maxRange;

    public NpcAnimator(Character character) {
        super(character);
        this.random = new Random();
        this.lastMovementTime = System.currentTimeMillis();
        this.lastDirectionChangeTime = System.currentTimeMillis();
        this.isPaused = false;
    }

    public void setRange(double minRange, double maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;
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

    @Override
    public void updateMovement() {
        if (isInteracting || isInBattle) return;

        long currentTime = System.currentTimeMillis();

        if (isPaused) {
            if (currentTime - lastMovementTime >= pauseDuration) {
                isPaused = false;
                lastMovementTime = currentTime;
                chooseNewDirection();
                setMoving(true);
            }
            return;
        }

        if (currentTime - lastMovementTime >= moveDuration) {
            isPaused = true;
            lastMovementTime = currentTime;
            setMoving(false);
            return;
        }

        if (isMovingRight) {
            character.setPosX(character.getPosX() + moveSpeed);
            if (character.getPosX() >= targetX || character.getPosX() >= maxRange) {
                chooseNewDirection();
            }
        } else {
            character.setPosX(character.getPosX() - moveSpeed);
            if (character.getPosX() <= targetX || character.getPosX() <= minRange) {
                chooseNewDirection();
            }
        }
        updateBounds();
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
