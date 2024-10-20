package EOD.animator;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

import EOD.characters.Character;
import EOD.utils.ImageList;

import java.awt.Dimension;
import java.awt.Toolkit;

public abstract class Animator {
    protected int currentFrame;
    protected boolean isMoving;
    protected boolean isMovingRight;
    protected ImageList walkSprites;
    protected ImageList idleSprites;
    protected Character character;
    protected int targetX;
    protected int deltaX;
    protected boolean isInBattle;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Animator(Character character) {
        this.currentFrame = 0;
        this.isMoving = false;
        this.isMovingRight = true;
        this.character = character;
        this.walkSprites = new ImageList();
        this.idleSprites = new ImageList();
        this.isInBattle = false;
    }

    public void importSprites(String assetPackage, String type, double scale, int numOfSprites) {
        ImageList sprites = (type.equals("walk")) ? walkSprites : idleSprites;
        sprites.clear();
        for (int i = 0; i < numOfSprites; i++) {
            String path = "/" + assetPackage + "/" + character.getCharacterType() + "/" + type + "/sprite_" + (i + 1) + ".png";
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                sprites.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sprites.scaleImageList(scale);
    }

    public void updateAnimation() {
        ImageList currentSprites = isMoving ? walkSprites : idleSprites;
        currentFrame = (currentFrame + 1) % currentSprites.getSize();
    }

    public Image getCurrentSprite(){
        ImageList sprites = isMoving ? walkSprites : idleSprites;
        currentFrame = Math.min(currentFrame, sprites.getSize() - 1);
        return sprites.get(currentFrame);
    }

    public void scaleSprites(String spriteType, double scale) {
        (spriteType.equals("walk") ? walkSprites : idleSprites).scaleImageList(scale);
    }

    // Getters and setters
    public boolean getIsMoving() { return isMoving; }
    public boolean getIsMovingRight() { return isMovingRight; }
    public boolean getIsInBattle() { return isInBattle; }
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