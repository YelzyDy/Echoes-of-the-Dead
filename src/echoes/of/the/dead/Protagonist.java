 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
/**
 *
 * @author Joana
 */
public class Protagonist extends TransparentPanel implements MouseInteractable, Entity {
    private int mana;
    private int attack;
    private int health;
    protected String name;
    protected int posX;
    protected int posY;
    protected ImageList walkSprites = new ImageList();
    protected ImageList idleSprites = new ImageList();
    protected int currentFrame;
    protected Timer animationTimer;
    protected boolean isMoving = false;

    protected boolean isFacingRight = true;
    protected String characterType;
    protected SceneBuilder panel;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public Protagonist(String name, String characterType, SceneBuilder panel, int posX, int posY){
        super(posX, posY, 0, 0);
        this.posY = posY;
        this.posX = posX;
        this.name = name;
        this.characterType = characterType;
        initializeWalkSprites((int)(screenSize.height * 0.006));
        initializeIdleSprites((int)(screenSize.height * 0.006));
        this.panel = panel;
        this.setVisible(true);
        this.setOpaque(false);
        this.currentFrame = 0;
        updateBounds();
        this.addMouseListener(new MouseClickListener(this));
    }
    
    public void initializeWalkSprites(int scale){
        walkSprites.clear();
        int size = 8;
        String[] spritePaths = new String[size];
        for(int i = 0; i < size; i++){
            spritePaths[i] = "/character_asset/" + characterType + "/walk/sprite" + (i + 1) + ".png";
        }     
        for (String path : spritePaths) {
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                walkSprites.add(image); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        walkSprites.scaleImageList(scale);
    }
    
    public void initializeIdleSprites(int scale){
        int size = 6;
        idleSprites.clear();
        String[] spritePaths = new String[size];
        for(int i = 0; i < size; i++){
            spritePaths[i] = "/character_asset/" + characterType + "/idle/sprite" + (i + 1) + ".png";
        }     
        for (String path : spritePaths) {
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                idleSprites.add(image); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        idleSprites.scaleImageList(scale);
    }
    
    public void scaleSprites(String spriteType, int scale){
        if(spriteType.equals("walk")){
            walkSprites.scaleImageList(scale);
        }else if(spriteType.equals("idle")){
            idleSprites.scaleImageList(scale);
        }
    }
    
    public void setPosY(int posY){
        this.posY = posY;
    }
    
    public void setPosX(int posX){
        this.posX = posX;
    }
    
   
    public void moveTo(int targetX) {
        int maxPanel = panel.getNumOfScenes() - 1;
        if (isMoving) {
            return;
        }

        if (targetX < posX && isFacingRight) {
            isFacingRight = false;
        } else if (targetX > posX && !isFacingRight) {
            isFacingRight = true;
        }
        isMoving = true;
        new Thread(() -> {
            int deltaX = (targetX - posX) / 10;

            for (int i = 0; i < 10; i++) {
                if (isFacingRight) {
                    posX += (deltaX - 10);
                } else {
                    posX += deltaX;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (posX >= (int)(screenSize.width * 0.9) && panel.currentSceneIndex < maxPanel - 1) {
                panel.currentSceneIndex++;
                posX = (int)(screenSize.width * 0.001);
            } else if (panel.currentSceneIndex > 0 && posX <= (int)(screenSize.width * 0.05)) {
                panel.currentSceneIndex--;
                posX = (int)(screenSize.width * 0.9);
            }   
            stopMovement();
        }).start();
    }

    public void updateBounds() {
        Image currentSprite = getCurrentSprite();
        setBounds(posX, posY, currentSprite.getWidth(null), currentSprite.getHeight(null));
    }

    
    public void setCharacterType(String characterType){
        this.characterType = characterType;
    }
    
    
    @Override
    public void stopMovement(){
        isMoving = false;
        currentFrame = 0;
    }
    @Override
    public void updateAnimation(){
        currentFrame++;
        if(currentFrame >= (((!isMoving) ? idleSprites : walkSprites).getSize())){
            currentFrame = 0;
        }      
    }
    @Override
    public int getPosX(){
        return posX;
    }
    
    public boolean getIsFacingRight(){
        return isFacingRight;
    }
    
    @Override
    public Image getCurrentSprite(){
        return ((!isMoving) ? idleSprites : walkSprites).get(currentFrame);      
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        draw(g2d);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        Image currentSprite = getCurrentSprite();
        int drawX = 0;
        int drawY = 0;

        if (!isFacingRight) {
            drawX = getWidth() - currentSprite.getWidth(null);
            g2d.scale(-1, 1);
            g2d.translate(-getWidth(), 0);
        }

        g2d.drawImage(currentSprite, drawX, drawY, null);
    }
    

    @Override
    public void onClick(MouseEvent e) {
        moveTo(e.getX());
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
