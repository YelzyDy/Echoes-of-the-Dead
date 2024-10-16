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
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
/**


/**
 *
 * @author Joana
 */
public class Character extends TransparentPanel implements Entity{
    protected String name;
    protected int posX;
    protected int posY;
    protected int currentFrame;
    protected boolean isMoving = false;
    protected boolean isMovingRight = true;
    protected int targetX;
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
        initializeSprites("character_asset", "walk", (int)(screenSize.height * 0.006));
        initializeSprites("character_asset", "idle",(int)(screenSize.height * 0.006));
        this.panel = panel;
        this.setVisible(true);
        this.currentFrame = 0;
        updateBounds();
    }

    private int getSpriteSize(String type){
        if(characterType.equals("priest") || characterType.equals("knight")
        || characterType.equals("wizard")){
            return (type.equals("walk") ? 8 : 6);
        }
        return 4;
    }

    @Override
    public void initializeSprites(String assetPackage, String type, int scale){
        ((type.equals("walk"))? walkSprites : idleSprites).clear();
        int size = getSpriteSize(type);
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
    
    @Override
    public void initializeSprites(String assetPackage, int width, int height) {
        
    }

    @Override
    public void scaleSprites(String spriteType, int scale){
        if(spriteType.equals("walk")){
            walkSprites.scaleImageList(scale);
        }else if(spriteType.equals("idle")){
            idleSprites.scaleImageList(scale);
        }
    }
    @Override
    public void setPosY(int posY){
        this.posY = posY;
    }
    @Override
    public void setPosX(int posX){
        this.posX = posX;
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

        if (!isMovingRight) {
            drawX = getWidth() - currentSprite.getWidth(null);
            g2d.scale(-1, 1);
            g2d.translate(-getWidth(), 0);
        }

        g2d.drawImage(currentSprite, drawX, drawY, null);
    }
}
