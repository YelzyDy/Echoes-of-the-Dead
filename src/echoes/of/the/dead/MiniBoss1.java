package echoes.of.the.dead;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

// This class makes NPC move randomly
public class MiniBoss1 extends Character implements MouseInteractable {
    Dialogues dialogues = new Dialogues();
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
    private boolean isInBattle;
    private boolean isEnlarged;

    public MiniBoss1(String name, String characterType, SceneBuilder panel, int posX, int posY, double minRange, double maxRange) {
        super(name, characterType, panel, posX, posY);
        setVisible(true); // Make sure the NPC is visible
        random = new Random();
        lastMovementTime = System.currentTimeMillis();
        lastDirectionChangeTime = System.currentTimeMillis();
        isPaused = false; // Start in a moving state
        initializeSprites("character_asset", "walk", (int)(screenSize.height * 0.006));
        initializeSprites("character_asset", "idle",(int)(screenSize.height * 0.006));
        idleSprites.scaleImageListDown(0.58);
        walkSprites.scaleImageListDown(0.58);
        chooseNewDirection(); // Start with a direction
        updateBounds();
        this.addMouseListener(new MouseClickListener(this));
        this.minRange = minRange;
        this.maxRange = maxRange;

        startMovement();
    }

    @Override
    public void initializeSprites(String assetPackage, String type, int scale){
        ((type.equals("walk"))? walkSprites : idleSprites).clear();
        int size = ((type.equals("walk") ? 13 : 7));
        String[] spritePaths = new String[size];
        for(int i = 0; i < size; i++){
            spritePaths[i] = "/" + assetPackage + "/" + characterType + "/" + type + "/sprite" + (i + 1) + ".png";
            System.out.println(spritePaths[i]);
        }     
        for (String path : spritePaths) {
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                ((type.equals("walk"))? walkSprites : idleSprites).add(image); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ((type.equals("walk"))? walkSprites : idleSprites).scaleImageList(scale);
    }
     public void updateMovement() {
        if (isInteracting) {
            return; // Don't update movement if interacting with user
        }

        long currentTime = System.currentTimeMillis();

        if (isPaused) {
            if (currentTime - lastMovementTime >= pauseDuration) {
                isPaused = false;
                lastMovementTime = currentTime;
                chooseNewDirection();
                isMoving = true; // Start moving after pause
            }
            return;
        }

        if (currentTime - lastMovementTime >= moveDuration) {
            isPaused = true;
            lastMovementTime = currentTime;
            isMoving = false; // Stop moving when paused
            return;
        }

        // Move the NPC
        if (isMovingRight) {
            setPosX(getPosX() + moveSpeed); 
            if (getPosX() >= targetX || getPosX() >= maxRange) {
                chooseNewDirection();
            }
        } else {
            setPosX(getPosX() - moveSpeed); 
            if (getPosX() <= targetX || getPosX() <= minRange) {
                chooseNewDirection();
            }
        }

        updateBounds();
    }

    private void chooseNewDirection() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDirectionChangeTime < directionChangeCooldown) {
            return;
        }

        lastDirectionChangeTime = currentTime;
        int target = random.nextInt((int)maxRange - (int)minRange) + (int)minRange;
        boolean newDirection = random.nextBoolean();
        if (newDirection != isMovingRight) {
            isMovingRight = newDirection;
            currentFrame = 0; // Reset animation frame when changing direction
        }
        moveTo(target, moveSpeed);
    }

    @Override
    public void onClick(MouseEvent e) {
        stopMovement();
        isPaused = true;
        isInteracting = true;
        isInBattle = true;
        if (isEnlarged){
            return;
        }
        // set pos x and
        scaleSprites("idle", (int)0.5);
        isEnlarged = true;
        if (characterType.equals("natty")){
            dialogues.displayDialogues(60, 70, 0, 1);
        }
        if (characterType.equals("missC")) {
            dialogues.displayDialogues(40, 50, 0, 1);
        } 

    }

      
    @Override
    public void onHover(MouseEvent e) {
        if (isInBattle){
            return;
        }
        stopMovement();
        isPaused = true;
        isInteracting = true;
    }
    
    @Override
    public void onExit(MouseEvent e) {
        if (isInBattle){
            return;
        }
        isInteracting = false;
        startMovement();
        isPaused = false;
    }
    
      // Modify the stopMovement method
    @Override
    public void stopMovement() {
        super.stopMovement();
        isMoving = false;
    }
    @Override
    public void startMovement() {
        super.startMovement();
        isMoving = true;
        isPaused = false;
        lastMovementTime = System.currentTimeMillis();
    }
}