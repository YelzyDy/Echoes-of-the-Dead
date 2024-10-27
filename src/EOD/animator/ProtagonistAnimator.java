package EOD.animator;


import EOD.characters.Character;
import EOD.scenes.SceneBuilder;
import EOD.utils.SceneTransitionHandler;

public class ProtagonistAnimator extends Animator {
    //private boolean isTransitioned;
    private SceneTransitionHandler transitionHandler;

    public ProtagonistAnimator(Character character) {
        super(character, 4);
        this.transitionHandler = new SceneTransitionHandler(screenSize);
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
        
        // Check if we're at a transition point
        if (transitionHandler.isAtTransitionPoint(character.getPosX(), 
                                               panel.getCurrentSceneIndex(), 
                                               panel.getNumOfScenes() - 3)) {
            stopMovement(); // Stop movement when reaching transition point
        }
        
        if (transitionHandler.isAtNonTransitionPoint(character.getPosX())) {
            transitionHandler.setIsInTransition(false);
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
