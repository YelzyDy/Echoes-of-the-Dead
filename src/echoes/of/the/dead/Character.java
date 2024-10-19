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
/**


/**
 *
 * @author Joana
 */

 // This class basically draws/animates the sprite and sets its bounds
public class Character extends TransparentPanel implements Entity{
    private String name;
    private int posX;
    private int posY;
    private int currentFrame;
    private boolean isMoving = false;
    private boolean isMovingRight = true;
    private int targetX;
    protected int deltaX;

    protected String characterType;
    protected SceneBuilder panel;
   
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected ImageList walkSprites = new ImageList();
    protected ImageList idleSprites = new ImageList();

    public Character(String name, String characterType, SceneBuilder panel, int posX, int posY) {
        super(posX, posY, 0, 0);
        this.posY = posY;
        this.posX = posX;
        this.name = name;
        this.characterType = characterType;
        this.panel = panel;
        this.setVisible(true);
        this.currentFrame = 0;
    }   

    public String getName(){
        return name;
    }

    @Override
    public int getPosX(){
        return posX;
    }

    public boolean getMovement(){
        return isMoving;
    }
    
    public int getTragetX(){
        return targetX;
    }
    @Override
    public Image getCurrentSprite(){
        ImageList sprites = isMoving ? walkSprites : idleSprites;
        currentFrame = Math.min(currentFrame, sprites.getSize() - 1);
        return sprites.get(currentFrame);
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

    public void setCharacterType(String characterType){
        this.characterType = characterType;
    }

    @Override
    public void setPosY(int posY){
        this.posY = posY;
    }
    @Override
    public void setPosX(int posX){
        this.posX = posX;
    }

    public void startMovement(){
        isMoving = true;
    }
    
    public void setTargetX(int targetX){
        this.targetX = targetX;
    }
    public void setCurrentFrame(int value){
        this.currentFrame = value;
    }
    public void setIsMoving(boolean value){
        this.isMoving = value;
    }

    public void setIsMovingRight(boolean value){
        this.isMovingRight = value;
    }

    @Override
    public void restartAnimation(){
        this.currentFrame = 0;
    }

    public void stopMovement(){
        isMoving = false;
        restartAnimation();
    }

    @Override
    public void initializeSprites(String assetPackage, String type, double scale){
      
    }
    
    @Override
    public void initializeSprites(String assetPackage, double width, double height) {
        
    }

    @Override
    public void scaleSprites(String spriteType, double scale){
        (spriteType.equals("walk") ? walkSprites : idleSprites).scaleImageList(scale);
    }
    
    public void moveTo(int targetX, int deltaX) {
        this.targetX = targetX;
        this.deltaX = deltaX;
        this.isMoving = true;
        this.isMovingRight = (targetX > posX);
    }


    public void updateBounds() {
        Image currentSprite = getCurrentSprite();
        setBounds(posX, posY, currentSprite.getWidth(null), currentSprite.getHeight(null));
    }
    
    
    @Override
    public void updateAnimation(){
        if (isMoving) {
            currentFrame++;
            if (currentFrame >= walkSprites.getSize()) {
                currentFrame = 0;
            }
        } else {
            currentFrame++;
            if (currentFrame >= idleSprites.getSize()) {
                currentFrame = 0;
            }
        }  
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

        if (!isMovingRight) {
            drawX = getWidth() - currentSprite.getWidth(null);
            g2d.scale(-1, 1);
            g2d.translate(-getWidth(), 0);
        }

        g2d.drawImage(currentSprite, drawX, drawY, null);
    }
}
