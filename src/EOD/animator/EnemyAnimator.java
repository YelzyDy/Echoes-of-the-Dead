package EOD.animator;

import EOD.characters.Enemy;
import java.util.Random;

public class EnemyAnimator extends Animator {
    private Enemy enemy;
    private Random random;
    private long lastMovementTime;
    private int moveDuration = 3000; // 3 seconds of movement
    private int pauseDuration = 1000; // 1 second pause
    private boolean isPaused;
    private int moveSpeed;

    public EnemyAnimator(Enemy enemy) {
        super(enemy);
        this.enemy = enemy;
        this.random = new Random();
        this.lastMovementTime = System.currentTimeMillis();
        this.isPaused = false;
        this.moveSpeed = enemy.getCharacterType().equals("miniboss") ? 1 : 2; // Slower for miniboss
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
        if (isInBattle || !isMoving) return;

        long currentTime = System.currentTimeMillis();

        if (isPaused) {
            if (currentTime - lastMovementTime >= pauseDuration) {
                isPaused = false;
                lastMovementTime = currentTime;
                chooseNewDirection();
            }
            return;
        }

        if (currentTime - lastMovementTime >= moveDuration) {
            isPaused = true;
            lastMovementTime = currentTime;
            isMoving = false;
            return;
        }

        if (isMovingRight) {
            enemy.setPosX(enemy.getPosX() + moveSpeed);
            if (enemy.getPosX() >= targetX || enemy.getPosX() >= enemy.getMaxRange()) {
                chooseNewDirection();
            }
        } else {
            enemy.setPosX(enemy.getPosX() - moveSpeed);
            if (enemy.getPosX() <= targetX || enemy.getPosX() <= enemy.getMinRange()) {
                chooseNewDirection();
            }
        }
        updateBounds();
    }

    public void chooseNewDirection() {
        int target = random.nextInt((int)enemy.getMaxRange() - (int)enemy.getMinRange()) + (int)enemy.getMinRange();
        boolean newDirection = random.nextBoolean();
        if (newDirection != isMovingRight) {
            setMovingRight(newDirection);
            restartAnimation();
        }
        moveTo(target, moveSpeed);
    }

    public void performSpecialAttack() {
        // Implement special attack animation logic
        // This can be overridden in subclasses if needed
    }
}