package echoes.of.the.dead;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import java.awt.Image;
import javax.imageio.ImageIO;

// This class makes NPC move randomly
public class Npc extends Character implements MouseInteractable {
    private Random random;
    private long lastMovementTime;
    private long lastDirectionChangeTime;
    private int moveDuration = 5000; // Move for 5 seconds
    private int pauseDuration = 1000; // Pause for 1 second (reduced for more frequent movement)
    private int directionChangeCooldown = 5000; // 5 seconds cooldown for direction changes
    private boolean isPaused;
    private int moveSpeed = 2; // Pixels per frame

    public Npc(String name, String characterType, SceneBuilder panel, int posX, int posY) {
        super(name, characterType, panel, posX, posY);
        setVisible(true); // Make sure the NPC is visible
        random = new Random();
        lastMovementTime = System.currentTimeMillis();
        lastDirectionChangeTime = System.currentTimeMillis();
        isPaused = false; // Start in a moving state
        initializeSprites("character_asset", "walk", (int)(screenSize.height * 0.006));
        initializeSprites("character_asset", "idle",(int)(screenSize.height * 0.006));
        chooseNewDirection(); // Start with a direction
        updateBounds();
    }

    @Override
    public void initializeSprites(String assetPackage, String type, int scale){
        ((type.equals("walk"))? walkSprites : idleSprites).clear();
        int size = 4;
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
        long currentTime = System.currentTimeMillis();

        if (isPaused) {
            if (currentTime - lastMovementTime >= pauseDuration) {
                isPaused = false;
                lastMovementTime = currentTime;
                chooseNewDirection();
            }
            return;
        }

        if (currentTime - lastMovementTime >= moveDuration) {
            isPaused = true;
            lastMovementTime = currentTime;
            stopMovement();
            return;
        }

        // Move the NPC
        if (isMovingRight) {
            posX += moveSpeed;
            if (posX >= targetX || posX >= screenSize.width * 0.8) {
                chooseNewDirection();
            }
        } else {
            posX -= moveSpeed;
            if (posX <= targetX || posX <= 0) {
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
        int target = random.nextInt((int)(screenSize.width * 0.8));
        boolean newDirection = random.nextBoolean();
        if (newDirection != isMovingRight) {
            isMovingRight = newDirection;
            currentFrame = 0; // Reset animation frame when changing direction
        }
        moveTo(target, moveSpeed);
          }

    @Override
    public void onClick(MouseEvent e) {
        
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
      
    }
}