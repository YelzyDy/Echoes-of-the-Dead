package EOD.animator;


import EOD.characters.Character;
import EOD.scenes.SceneBuilder;
public class ProtagonistAnimator extends Animator {
    private boolean isTransitioned;
    
    public ProtagonistAnimator(Character character) {
        super(character, 4);
    }

    public void importSkillSprites(int skillNumber, String assetPackage, double scale, int numOfSprites) {
        String type = "skill" + skillNumber;
        importSprites(assetPackage, type, scale, numOfSprites);
        isTransitioned = false;
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation(); // Call the base class method

        // Add any protagonist-specific animation logic here
        // For example, handling scene transitions
        if (!isUsingSkill && !isDead) {
            SceneBuilder panel = character.getPanel();
            int maxPanel = panel.getNumOfScenes() - 3;
            int currentScene = panel.getCurrentSceneIndex();

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

            if (!isMoving) {
                if (character.getPosX() >= (screenSize.width * 0.8) && currentScene < maxPanel - 1 && !isTransitioned) {
                    character.setPosX(screenSize.width * 0.001);
                    panel.incCurrentScene();
                    isTransitioned = true;
                } else if (currentScene > 0 && character.getPosX() <= (screenSize.width * 0.05) && !isTransitioned) {
                    character.setPosX(screenSize.width * 0.9);
                    panel.decCurrentScene();
                    isTransitioned = true;
                } else if (character.getPosX() > (screenSize.width * 0.08) && character.getPosX() < (screenSize.width * 0.8)) {
                    isTransitioned = false;
                }
            }
        }
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

        // Scene transition logic is now in updateAnimation()
    }

}
