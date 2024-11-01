package EOD.animator;

import EOD.characters.Enemy;

public class EnemyAnimator extends RandomAnimator {

    public EnemyAnimator(Enemy enemy, int numberOfSkills) {
        super(enemy, numberOfSkills);
    }

    @Override
    public void updateMovement() {
        if (isUsingSkill) {
            double newPosX = character.getPosX() + deltaX;
            if (isMovingRight) {
                if (newPosX >= targetX) {
                    character.setPosX(targetX);
                    stopMovement();
                } else {
                    character.setPosX(newPosX);
                }
            } else {
                if (newPosX <= targetX) {
                    character.setPosX(targetX);
                    stopMovement();
                } else {
                    character.setPosX(newPosX);
                }
            }
            updateBounds();
            return;
        }
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
}