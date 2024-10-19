package echoes.of.the.dead;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class CharacterAnimator {
    private int currentFrame;
    private boolean isMoving;
    private boolean isMovingRight;
    private ImageList walkSprites;
    private ImageList idleSprites;
    private Character character;
    private int targetX;
    private int deltaX;

    private Random random;
    private long lastMovementTime;
    private long lastDirectionChangeTime;
    private int moveDuration = 5000; // Move for 5 seconds
    private int pauseDuration = 1000; // Pause for 1 second (reduced for more frequent movement)
    private int directionChangeCooldown = 5000; // 5 seconds cooldown for direction changes
    private boolean isPaused;
    private int moveSpeed = 2; // Pixels per frame
    private boolean isInteracting;
    private double minRange;
    private double maxRange;

    public CharacterAnimator(Character character) {
        this.currentFrame = 0;
        this.isMoving = false;
        this.isMovingRight = true;
        this.character = character;
        random = new Random();
        lastMovementTime = System.currentTimeMillis();
        lastDirectionChangeTime = System.currentTimeMillis();
        isPaused = false; // Start in a moving state
        walkSprites = new ImageList();
        idleSprites = new ImageList();
    }

    public void setRange(double minRange, double maxRange){
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public ImageList getSprite(String type){
        if(type.equals("walk")){
            return walkSprites;
        }else{
            return idleSprites;
        }
    }

    public void importSprites(String assetPackage, String type, double scale, int numOfSprites){
        ((type.equals("walk"))? walkSprites : idleSprites).clear();
        int size = numOfSprites;
        String[] spritePaths = new String[size];

        for(int i = 0; i < size; i++){
            spritePaths[i] = "/" + assetPackage + "/" + character.getCharacterType() + "/" + type + "/sprite" + (i + 1) + ".png";
            System.out.println(spritePaths[i]);
        }     
        for (String path : spritePaths) {
            try {
                System.out.println("Attempting to import " + path);
                Image image = ImageIO.read(getClass().getResource(path));
                ((type.equals("walk"))? walkSprites : idleSprites).add(image); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ((type.equals("walk"))? walkSprites : idleSprites).scaleImageList(scale);
    }

    public void updateAnimation() {
        ImageList currentSprites = isMoving ? walkSprites : idleSprites;
        currentFrame++;
        if (currentFrame >= currentSprites.getSize()) {
            currentFrame = 0;
        }
    }

    public Image getCurrentSprite(){
        ImageList sprites = isMoving ? walkSprites : idleSprites;
        currentFrame = Math.min(currentFrame, sprites.getSize() - 1);
        return sprites.get(currentFrame);
    }

    public void scaleSprites(String spriteType, double scale){
        (spriteType.equals("walk") ? walkSprites : idleSprites).scaleImageList(scale);
    }

    public int getTargetX(){
        return targetX;
    }

    public int getDeltaX(){
        return deltaX;
    }

    public boolean getMovement(){
        return isMoving;
    }

    public int getCurrentFrame(){
        return currentFrame;
    }

    public boolean getIsMoving(){
        return isMoving;
    }

    public boolean getIsMovingRight(){
        return isMovingRight;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public void setMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
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

    public void setInteracting(boolean value){
        isInteracting = value;
    }

    public void restartAnimation() {
        currentFrame = 0;
    }

    public void startMovement(){
        isMoving = true;
        setMoving(true); // find this if there is error
        isPaused = false;
        lastMovementTime = System.currentTimeMillis();
    }

    public void setCurrentFrame(int value){
        this.currentFrame = value;
    }

    public void stopMovement(){
        isMoving = false;
        restartAnimation();
        setMoving(false);
    }

    public void moveTo(int targetX, int deltaX) {
        this.targetX = targetX;
        this.deltaX = deltaX;
        this.isMoving = true;
        this.isMovingRight = (targetX > character.getPosX());
    }

    public void updateBounds() {
        Image currentSprite = getCurrentSprite();
        character.setBounds(character.getPosX(), character.getPosY(), currentSprite.getWidth(null), currentSprite.getHeight(null));
        // System.out.println("Character Animator: " + character.getCharacterType() + " " + "X = " + character.getPosX() + "Y = " + character.getPosX());
    }

    public void updateProtagMovement() {
        int maxPanel = character.panel.getNumOfScenes() - 1;
        int currentScene = character.panel.getCurrentSceneIndex();
        if (!getIsMoving()) return;
    
        // Assuming deltaX is the distance to move
        if (getIsMovingRight()) {
            character.setPosX(character.getPosX() + getDeltaX() - 10);
            if (character.getPosX() >= getTargetX()) { // Check if it reached the target position
                stopMovement(); // Stop when the target is reached
                character.setPosX(getTargetX());; // Ensure it doesn't overshoot
                if (character.getPosX()  >= (int)(character.screenSize.width * 0.8) && currentScene< maxPanel - 1) {
                    character.panel.incCurrentScene();
                    character.setPosX((int)(character.screenSize.width * 0.001));
                } else if (currentScene> 0 && character.getPosX() <= (int)(character.screenSize.width * 0.05)) {
                    character.panel.decCurrentScene();;
                    character.setPosX((int)(character.screenSize.width * 0.09));
                }   
            }
        } else {
            character.setPosX(character.getPosX() + getDeltaX()); 
            if (character.getPosX() <= getTargetX()) { // Check if it reached the target position
                stopMovement(); // Stop when the target is reached
                character.setPosX(getTargetX());
                if (character.getPosX()>= (int)(character.screenSize.width * 0.8) && currentScene<  maxPanel - 1) {
                    character.panel.incCurrentScene();
                    character.setPosX((int)(character.screenSize.width * 0.001));
                } else if (currentScene > 0 && character.getPosX() <= (int)(character.screenSize.width * 0.05)) {
                    character.panel.decCurrentScene();
                    character.setPosX((int)(character.screenSize.width * 0.9));
                }   
            }
        }
    }

    public void updateNPCMovement() {
        if (isInteracting) {
            return; // Don't update movement if interacting with user
        }

        long currentTime = System.currentTimeMillis();

        if (isPaused) {
            if (currentTime - lastMovementTime >= pauseDuration) {
                isPaused = false;
                lastMovementTime = currentTime;
                chooseNewDirection();
                setMoving(true); // Start moving after pause
            }
            return;
        }

        if (currentTime - lastMovementTime >= moveDuration) {
            isPaused = true;
            lastMovementTime = currentTime;
            setMoving(false); // Stop moving when paused
            return;
        }

        // Move the NPC
        if (getIsMovingRight()) {
            character.setPosX(character.getPosX() + moveSpeed); 
            if (character.getPosX() >= getTargetX() || character.getPosX() >= maxRange) {
                chooseNewDirection();
            }
        } else {
            character. setPosX(character.getPosX() - moveSpeed); 
            if (character.getPosX() <= getTargetX() || character.getPosX() <= minRange) {
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
        if (newDirection != getIsMovingRight()) {
            setMovingRight(newDirection);
            restartAnimation();
        }
        moveTo(target, moveSpeed);
    }

}