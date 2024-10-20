package EOD.animator;


import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Image;
import EOD.characters.Character;
import EOD.scenes.SceneBuilder;
import EOD.utils.*;
public class ProtagonistAnimator extends Animator {
    private ImageList skill1Sprites;
    private ImageList skill2Sprites;
    private ImageList skill3Sprites;
    private ImageList skill4Sprites;
    private boolean isUsingSkill;
    private int currentSkill;
    private int skillAnimationFrame;
    private int originalPosX;
    private boolean isReturning;
    private int skillTargetX;
    private boolean skillCompleted;
    private boolean reachedTarget;

    public ProtagonistAnimator(Character character) {
        super(character);
        skill1Sprites = new ImageList();
        skill2Sprites = new ImageList();
        skill3Sprites = new ImageList();
        skill4Sprites = new ImageList();
        isUsingSkill = false;
        currentSkill = 0;
        skillAnimationFrame = 0;
        isReturning = false;
        skillCompleted = false;
        reachedTarget = false;
    }

    public void importSkillSprites(int skillNumber, String assetPackage, double scale, int numOfSprites) {
        ImageList skillSprites = getSkillSpritesList(skillNumber);
        skillSprites.clear();
        for (int i = 0; i < numOfSprites; i++) {
            String path = "/" + assetPackage + "/" + character.getCharacterType() + "/skill" + skillNumber + "/sprite_" + (i + 1) + ".png";
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                skillSprites.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        skillSprites.scaleImageList(scale);
    }

    public void triggerSkillAnimation(int skillNumber, int targetX) {
        isUsingSkill = true;
        currentSkill = skillNumber;
        skillAnimationFrame = 0;
        originalPosX = (int)character.getPosX();
        skillTargetX = targetX;
        isReturning = false;
        skillCompleted = false;
        reachedTarget = false;
        moveTo(targetX, calculateDeltaX(targetX, false));
    }

    private int calculateDeltaX(int targetX, boolean returning) {
        int startX = returning ? skillTargetX : (int)character.getPosX();
        int distance = Math.abs(targetX - startX);
        ImageList currentSkillSprites = getSkillSpritesList(currentSkill);
        int framesToComplete = currentSkillSprites.getSize();
        int delta = distance / framesToComplete;
        return (targetX > startX) ? delta : -delta;
    }

    @Override
    public void updateAnimation() {
        if (isUsingSkill) {
            if (!reachedTarget) {
                updateMovement();
                if (!isMoving) {
                    reachedTarget = true;
                }
            } else if (!skillCompleted) {
                ImageList currentSkillSprites = getSkillSpritesList(currentSkill);
                skillAnimationFrame++;
                if (skillAnimationFrame >= currentSkillSprites.getSize()) {
                    skillCompleted = true;
                    isReturning = true;
                    moveTo(originalPosX, calculateDeltaX(originalPosX, true));
                }
            } else if (isReturning) {
                updateMovement();
                if (!isMoving) {
                    isUsingSkill = false;
                    isReturning = false;
                    skillAnimationFrame = 0;
                    setMovingRight(true);
                }
            }
        } else {
            super.updateAnimation();
        }
    }

    @Override
    public Image getCurrentSprite() {
        if (isUsingSkill) {
            if (!reachedTarget || isReturning) {
                return super.getCurrentSprite(); // Use walking animation
            } else {
                ImageList currentSkillSprites = getSkillSpritesList(currentSkill);
                int totalFrames = currentSkillSprites.getSize();
                return currentSkillSprites.get(Math.min(skillAnimationFrame, totalFrames - 1));
            }
        }
        return super.getCurrentSprite();
    }

    public boolean isUsingSkill() {
        return isUsingSkill;
    }

    public int getCurrentSkill() {
        return currentSkill;
    }

    public boolean isReturning() {
        return isReturning;
    }

    private ImageList getSkillSpritesList(int skillNumber) {
        switch (skillNumber) {
            case 1: return skill1Sprites;
            case 2: return skill2Sprites;
            case 3: return skill3Sprites;
            case 4: return skill4Sprites;
            default: throw new IllegalArgumentException("Invalid skill number");
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

        // Handle scene transitions if not using a skill
        if (!isUsingSkill) {
            SceneBuilder panel = character.getPanel();
            int maxPanel = panel.getNumOfScenes() - 3;
            int currentScene = panel.getCurrentSceneIndex();

            if (!isMoving) {
                if (character.getPosX() >= (screenSize.width * 0.8) && currentScene < maxPanel - 1) {
                    panel.incCurrentScene();
                    character.setPosX(screenSize.width * 0.001);
                } else if (currentScene > 0 && character.getPosX() <= (screenSize.width * 0.05)) {
                    panel.decCurrentScene();
                    character.setPosX(screenSize.width * 0.9);
                }
            }
        }
    }

}
