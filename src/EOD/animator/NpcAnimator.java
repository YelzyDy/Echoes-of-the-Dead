package EOD.animator;

import EOD.animator.NpcAnimator;
import EOD.characters.Character;

public class NpcAnimator extends RandomAnimator {

    public NpcAnimator(Character character) {
        super(character, 0);

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
}
