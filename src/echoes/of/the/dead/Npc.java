package echoes.of.the.dead;

import java.awt.event.MouseEvent;
import java.util.Random;

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
        chooseNewDirection(); // Start with a direction
        System.out.println("NPC created at position: " + posX + ", " + posY);
        addMouseListener(new MouseClickListener(this));
        isMoving = true;
    }

    public void updateMovement() {
        long currentTime = System.currentTimeMillis();

        if(!isMoving){
            return;
        }

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
        System.out.println("Mouse paused NPC at position: " + posX + ", " + posY);
        isPaused = true;
        stopMovement();
        // handle the dialogues here use conditions for example. if name is "miggins", implement dialogues for miggins
    }

    @Override
    public void onHover(MouseEvent e) {
        isPaused = true;
        stopMovement();
    }

    @Override
    public void onExit(MouseEvent e) {
        isPaused = false;
        isMoving = true;
    }

}
