// file initially made by zendy

package echoes.of.the.dead;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import javax.imageio.ImageIO;
//import javax.swing.JPanel;
import javax.swing.Timer;

public class MinionsWorld1 implements MouseInteractable, Entity {
    //private int attack = 10;
    //private int health = 100;
    protected String name;
    protected int posX;
    protected int posY;
    protected ImageList idleSprites = new ImageList();
    protected int currentFrame = 3;
    protected Timer animationTimer;
    protected boolean isMoving = false;
    protected String characterType;
    protected SceneBuilder panel;
    protected boolean isFacingRight = true; 
    private int screenWidth;
    private int screenHeight;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MinionsWorld1(String characterType, SceneBuilder panel, int posX, int posY) {
        this.posY = posY;
        this.posX = posX;
        this.characterType = characterType;
        initializeIdleSprites("world1", (int)(screenSize.width *0.1), (int) (screenSize.height * 0.12));  
        this.panel = panel;
        startAnimationTimer(panel);
        this.screenWidth = (int)screenSize.getWidth();
        this.screenHeight = (int)screenSize.getHeight();
    }

    private void startAnimationTimer(SceneBuilder panel) {
        animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
                panel.repaint();
            }
        });
        animationTimer.start();
    }

    // Initialize idle sprites (placeholder for now)
    public void initializeIdleSprites(String assetPackage, int width, int height) {
        int size = 8;
        idleSprites.clear();
        String[] spritePaths = new String[size];
        for (int i = 0; i < size; i++) {
            spritePaths[i] = "/" + assetPackage + "_assets/minionsWorld1/sprite" + i + ".png";
        }
        for (String path : spritePaths) {
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                idleSprites.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        idleSprites.resizeImageList(width, height);
    }

    @Override
    public void stopMovement() {
        isMoving = false;
        currentFrame = 0;
    }

    @Override
    public void updateAnimation() {
        currentFrame++;
        if (currentFrame >= idleSprites.getSize()) {
            currentFrame = 0;
        }
    }

    @Override
    public int getPosX() {
        return posX;
    }

    public boolean getIsFacingRight() {
        return isFacingRight;
    }

    @Override
    public Image getCurrentSprite() {
        // Always use idle sprites until walking sprites are available
        return idleSprites.get(currentFrame);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        AffineTransform transform = new AffineTransform();

        if (getIsFacingRight()) {
            transform.translate(getPosX(), posY);
        } else {
            transform.translate(getPosX() + getCurrentSprite().getWidth(null), posY); // Move origin right
            transform.scale(-1, 1);
        }
        g2d.drawImage(getCurrentSprite(), transform, null);
    }

    // Move the minion to a new X position (linear movement)
    public void moveTo(int targetX) {
        if (targetX > posX) {
            isFacingRight = true;
        } else {
            isFacingRight = false;
        }

        isMoving = true;
        Timer moveTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isFacingRight) {
                    posX += 2;
                    if (posX >= targetX || posX >= screenWidth - getCurrentSprite().getWidth(null)) {
                        posX = Math.min(targetX, screenWidth - getCurrentSprite().getWidth(null));
                        ((Timer) e.getSource()).stop();
                        stopMovement();
                    }
                } else {
                    posX -= 2;
                    if (posX <= targetX || posX <= 0) {
                        posX = Math.max(targetX, 0);
                        ((Timer) e.getSource()).stop();
                        stopMovement();
                    }
                }
                panel.repaint();
            }
        });
        moveTimer.start();
    }

    @Override
    public void onClick(MouseEvent e) {
        int targetX = Math.max(0, Math.min(e.getX(), screenWidth - getCurrentSprite().getWidth(null)));
        moveTo(targetX);
    }

    @Override
    public void onHover(MouseEvent e) {
        // Optional hover behavior
    }

    @Override
    public void onExit(MouseEvent e) {
        // Optional exit behavior
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
}


