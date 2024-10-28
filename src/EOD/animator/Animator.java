package EOD.animator;

import EOD.characters.Character;
import EOD.utils.ImageList;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public abstract class Animator {
    protected int currentFrame;
    protected boolean isMoving;
    protected boolean isMovingRight;
    protected ImageList walkSprites;
    protected ImageList idleSprites;
    protected ImageList[] skillSprites;
    protected ImageList deadSprites;
    protected Character character;
    protected int targetX;
    protected int deltaX;
    protected boolean isInBattle;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected Runnable onAnimationComplete;

    protected double targetY;
    protected double currentY;
    protected boolean isDeathAnimating;
    protected Timer deathAnimationTimer;
    
    protected boolean isDead;
    protected boolean isUsingSkill;
    protected int currentSkill;
    protected int skillAnimationFrame;
    protected int originalPosX;
    protected boolean isReturning;
    protected int skillTargetX;
    protected boolean skillCompleted;
    protected boolean reachedTarget;

    protected double movementSpeedMultiplier;
    protected double skillAnimationSpeedMultiplier;
    protected double deathAnimationSpeedMultiplier;

    public Animator(Character character, int numberOfSkills) {
        this.currentFrame = 0;
        this.isMoving = false;
        this.isMovingRight = true;
        this.character = character;
        this.walkSprites = new ImageList();
        this.idleSprites = new ImageList();
        this.deadSprites = new ImageList();
        this.skillSprites = new ImageList[numberOfSkills];
        for (int i = 0; i < numberOfSkills; i++) {
            this.skillSprites[i] = new ImageList();
        }
        this.isInBattle = false;
        this.isDead = false;
        this.isUsingSkill = false;
        this.currentSkill = 0;
        this.skillAnimationFrame = 0;
        this.isReturning = false;
        this.skillCompleted = false;
        this.reachedTarget = false;
        this.isDeathAnimating = false;
    }

    public void setOnAnimationComplete(Runnable callback) {
        this.onAnimationComplete = callback;
    }

    public void setSpeedMultiplier(int speedMultiplier){
        movementSpeedMultiplier = speedMultiplier;
        skillAnimationSpeedMultiplier = speedMultiplier;
        deathAnimationSpeedMultiplier = speedMultiplier;
    }

    public void setDeathAnimationSpeedMultiplier(int deathAnimationSpeedMultiplier){
        this.deathAnimationSpeedMultiplier = deathAnimationSpeedMultiplier;
    }

    public void setSkillAnimationSpeedMultiplier(int skillAnimationSpeedMultiplier){
        this.skillAnimationSpeedMultiplier = skillAnimationSpeedMultiplier;
    }

    public void setMovementMultiplier(int movementSpeedMultiplier){
        this.movementSpeedMultiplier = movementSpeedMultiplier;
    }

    public void importSprites(String assetPackage, String type, double scale, int numOfSprites) {
        ImageList sprites = getSpriteList(type);
        sprites.clear();
        for (int i = 0; i < numOfSprites; i++) {
            String path = "/" + assetPackage + "/" + character.getCharacterType() + "/" + type + "/sprite_" + (i + 1) + ".png";
            System.out.println(path);
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                sprites.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sprites.scaleImageList(scale);
    }

    protected ImageList getSpriteList(String type) {
        switch (type) {
            case "walk": return walkSprites;
            case "idle": return idleSprites;
            case "dead": return deadSprites;
            default:
                if (type.startsWith("skill")) {
                    int skillNumber = Integer.parseInt(type.substring(5)) - 1;
                    return skillSprites[skillNumber];
                }
                throw new IllegalArgumentException("Invalid sprite type: " + type);
        }
    }


    public void updateAnimation() {
        if (isDead) {
            int frameIncrement = Math.max(1, (int)(1 * deathAnimationSpeedMultiplier));
            currentFrame = Math.min(currentFrame + frameIncrement, deadSprites.getSize() - 1);
        } else if (isUsingSkill) {
            updateSkillAnimation();
        } else {
            ImageList currentSprites = isMoving ? walkSprites : idleSprites;
            currentFrame = (currentFrame + 1) % currentSprites.getSize();
        }
    }

    protected void updateSkillAnimation() {
        character.getPanel().setComponentZOrder(character, 0);
        if (!reachedTarget) {
            updateMovement();
            currentFrame = (currentFrame + 1) % walkSprites.getSize();
            if (!isMoving) {
                reachedTarget = true;
            }
        } else if (!skillCompleted) {
            skillAnimationFrame += Math.max(1, (int)(1 * skillAnimationSpeedMultiplier));
            
            if (skillAnimationFrame >= skillSprites[currentSkill].getSize()) {
                skillCompleted = true;
                isReturning = true;
                int returnDeltaX = calculateDeltaX(originalPosX, true);
                returnDeltaX = (int)(returnDeltaX * movementSpeedMultiplier);
                moveTo(originalPosX, returnDeltaX);
            }
        } else if (isReturning) {
            updateMovement();
            currentFrame = (currentFrame + 1) % walkSprites.getSize();
            if (!isMoving) {
                isUsingSkill = false;
                isReturning = false;
                skillAnimationFrame = 0;
                setMovingRight(initialDirection);
                
                // Animation is complete, trigger callback if set
                if (onAnimationComplete != null) {
                    Runnable callback = onAnimationComplete;
                    onAnimationComplete = null;  // Clear callback
                    callback.run();
                }
            }
        }
    }

    public void cropSprites(){
        walkSprites.cropImageList();
        idleSprites.cropImageList();
        deadSprites.cropImageList();
          // Crop each skill sprite
        for (ImageList skillSprite : skillSprites) {
            skillSprite.cropImageList();
        }
    }

    public Image getCurrentSprite() {
        if (isDead) {
            return deadSprites.get(Math.min(currentFrame, deadSprites.getSize() - 1));
        } else if (isUsingSkill) {
            if (!reachedTarget || isReturning) {
                return walkSprites.get(currentFrame % walkSprites.getSize());  // Here's the problem!
            } else {
                return skillSprites[currentSkill].get(Math.min(skillAnimationFrame, skillSprites[currentSkill].getSize() - 1));
            }
        } else {
            ImageList sprites = isMoving ? walkSprites : idleSprites;
            return sprites.get(currentFrame % sprites.getSize());
        }
    }

    protected boolean initialDirection; // Add this as a new class field

    public void triggerSkillAnimation(int skillNumber, int targetX) {
        isUsingSkill = true;
        currentSkill = skillNumber - 1;
        skillAnimationFrame = 0;
        originalPosX = (int)character.getPosX();
        skillTargetX = targetX;
        isReturning = false;
        skillCompleted = false;
        reachedTarget = false;
        
        // Store initial direction before moving to target
        initialDirection = isMovingRight;
        
        int calculatedDeltaX = calculateDeltaX(targetX, false);
        calculatedDeltaX = (int)(calculatedDeltaX * movementSpeedMultiplier);
        moveTo(targetX, calculatedDeltaX);
    }

    protected int calculateDeltaX(int targetX, boolean returning) {
        int startX = returning ? skillTargetX : (int)character.getPosX();
        int distance = Math.abs(targetX - startX);
        ImageList currentSkillSprites = skillSprites[currentSkill];
        int framesToComplete = currentSkillSprites.getSize();
        int delta = distance / framesToComplete;
        return (targetX > startX) ? delta : -delta;
    }

    public void triggerDeathAnimation(double targetY) {
        isDead = true;
        isDeathAnimating = true;
        currentFrame = 0;
        this.targetY = targetY;
        this.currentY = character.getPosY();

        final double totalDistance = targetY - currentY;
        final int baseAnimationDuration = 1000; // Base duration of 1 second
        final int adjustedDuration = (int)(baseAnimationDuration / deathAnimationSpeedMultiplier);
        final int fps = 60;
        final int totalFrames = adjustedDuration / (1000 / fps);
        final double stepSize = totalDistance / totalFrames;

        deathAnimationTimer = new Timer(1000 / fps, new ActionListener() {
            int frame = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (frame < totalFrames) {
                    currentY += stepSize;
                    character.setPosY(currentY);
                    frame += Math.max(1, (int)(1 * deathAnimationSpeedMultiplier));
                    updateBounds();
                } else {
                    isDeathAnimating = false;
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        deathAnimationTimer.start();
    }

    public void scaleSprites(String spriteType, double scale) {
        getSpriteList(spriteType).scaleImageList(scale);
    }

    public void scaleDownSprites(String spriteType, double scale) {
        getSpriteList(spriteType).scaleImageListDown(scale);
    }

    // Getters and setters
    public boolean getIsMoving() { return isMoving; }
    public boolean getIsMovingRight() { return isMovingRight; }
    public boolean getIsInBattle() { return isInBattle; }
    public boolean getIsDead() { return isDead; }
    public boolean getIsUsingSkill() { return isUsingSkill; }
    public void setMoving(boolean isMoving) { this.isMoving = isMoving; }
    public void setMovingRight(boolean isMovingRight) { this.isMovingRight = isMovingRight; }
    public void setIsInBattle(boolean value) { this.isInBattle = value; }

    public void restartAnimation() { currentFrame = 0; }

    public void updateBounds() {
        Image currentSprite = getCurrentSprite();
        character.setBounds((int)character.getPosX(), (int)character.getPosY(), 
                            currentSprite.getWidth(null), currentSprite.getHeight(null));
    }

    
    public void moveTo(int targetX, int deltaX) {
        this.targetX = targetX;
        this.deltaX = deltaX;
        this.isMoving = true;
        this.isMovingRight = (targetX > character.getPosX());
    }

    // Abstract methods to be implemented by subclasses
    public abstract void startMovement();
    public abstract void stopMovement();
    public abstract void updateMovement();
}