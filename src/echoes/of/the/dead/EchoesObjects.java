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

/**
 *
 * @author Joana
 */
public class EchoesObjects extends TransparentPanel implements MouseInteractable, Entity{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int posX;
    private int posY;
    private int currentFrame = 0;
    private ImageList objSprites = new ImageList();
    private String type = null;
    private String assetPackage = null;
    private boolean isAnimated = false;
    private boolean isState = false;
    private int numOfSprites = 0;
    public EchoesObjects(String assetPackage, int x, int y, int width, int height, String type, boolean isAnimated, boolean isState, int numOfSprites){
        super(x, y, width, height);
        this.type = type;
        this.isAnimated = isAnimated;
        this.isState = isState;
        this.numOfSprites = numOfSprites;
        this.assetPackage = assetPackage;
        // System.out.println("isState: " + isState);
        if(isState){
            // System.out.println(type);
            initializeSprites(assetPackage, width, height);
        }else if(isAnimated){
            initializeSprites(assetPackage, width, height);
            // System.out.println(type);
        }else if(!isState && !isAnimated){
            initializeSprites(assetPackage, width, height);
            // System.out.println(type);
        }
        // System.out.println(numOfSprites);
        this.addMouseListener(new MouseClickListener(this));
    }   
    
    public boolean isAnimated(){
        return isAnimated;
    }

    @Override
    public void initializeSprites(String assetPackage, double width, double height) {
        objSprites.clear();
        int size = numOfSprites;
        String[] spritePaths = new String[size];
        // System.out.println(isAnimated ? "anim" : "not anim");
        // System.out.println("Size: " + size);
        for (int i = 0; i < size; i++) {
            if (isAnimated) {
                spritePaths[i] = "/" + assetPackage + "_assets/" + type + "/sprite" + i + ".png";
            } else {
                spritePaths[i] = "/" + assetPackage + "_assets/" + type + i + ".png";
            }
            // System.out.println("path: " + spritePaths);
        }
        for (String path : spritePaths) {
            try {
                // System.out.println("Attempting to load image from path: " + path);
                Image image = ImageIO.read(getClass().getResource(path));
                if (image != null) {
                    objSprites.add(image);
                } else {
                    // System.out.println("Error: Failed to load image from " + path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        objSprites.resizeImageList(width, height);
        // System.out.println("Number of sprites loaded: " + objSprites.getSize());
    }


    @Override
    public void initializeSprites(String assetPackage, String type, double scale){
        
    }
   
    public void setCurrentFrame(int value){
        currentFrame = value;
    }

    @Override
    public void onClick(MouseEvent e) {     
          
    }

    @Override
    public void onHover(MouseEvent e) {
        if(!isAnimated && isState){
           currentFrame = 1;
           repaint();
           return;
        }
    }

    @Override
    public void onExit(MouseEvent e) {
        if(!isAnimated && isState){
            restartAnimation();
            repaint();    
            return;  
        }
    }
    
    @Override
    public void restartAnimation(){
        currentFrame = 0;
    }
    
    @Override
    public void updateAnimation(){
        if(isAnimated){
            currentFrame++;
            if(currentFrame >= objSprites.getSize()){
                restartAnimation();
            }      
        }
    }
    
    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(int posY) {
        this.posY = posY;
    }

    @Override
    public Image getCurrentSprite() {
        return objSprites.get(currentFrame);      
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(getCurrentSprite(), posX, posY, null);
        
    }


    @Override
    public void scaleSprites(String spriteType, double scale) {
       
    }
}
