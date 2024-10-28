package EOD.animator;


import EOD.characters.Character;
import EOD.scenes.SceneBuilder;

public class ProtagonistAnimator extends Animator {
    //private boolean isTransitioned;

    public ProtagonistAnimator(Character character) {
        super(character, 4);
    }


    public void importSkillSprites(int skillNumber, String assetPackage, double scale, int numOfSprites) {
        String type = "skill" + skillNumber;
        importSprites(assetPackage, type, scale, numOfSprites);
        //isTransitioned = false;
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
        
        SceneBuilder panel = character.getPanel();
        if(isUsingSkill || isDead) {
            panel.setComponentZOrder(character, 0);
            return;
        }
        
        if (!isMoving) return;

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
    }

    @Override
    public void startMovement() {
        isMoving = true;
        // Additional protagonist-specific start movement logic
    }

    @Override
    public void stopMovement() {
        isMoving = false;
        restartAnimation();
        // Additional protagonist-specific stop movement logic
    }

    @Override
    public void updateMovement() {
        if (!isMoving) return;

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
    }
}
