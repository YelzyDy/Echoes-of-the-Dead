/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

import EOD.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.utils.*;
import EOD.entities.*;

/**
 *
 * @author Joana
 */
public class EchoesObjects extends TransparentPanel implements MouseInteractable, Entity{
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double posX;
    private double posY;
    private int currentFrame;
    private ImageList objSprites = new ImageList();
    private String assetPackage = null;
    private boolean isAnimated = false;
    private boolean isState = false;
    private int numOfSprites;
    private int index;

    public EchoesObjects(String assetPackage, int x, int y, int width, int height, String type, boolean isAnimated, boolean isState, int numOfSprites){
        super(x, y, width, height);
        setName(type);
        this.isAnimated = isAnimated;
        this.isState = isState;
        this.numOfSprites = numOfSprites;
        this.assetPackage = assetPackage;
        currentFrame = 0;
        numOfSprites = 0;
        index = 0;
        initializeSprites(assetPackage, width, height);
        this.addMouseListener(new MouseClickListener(this));
    }   
    
    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }
    public boolean isAnimated(){
        return isAnimated;
    }

    public void initializeSprites(String assetPackage, double width, double height) {
        objSprites.clear();
        int size = numOfSprites;
        String[] spritePaths = new String[size];
        // System.out.println(isAnimated ? "anim" : "not anim");
        // System.out.println("Size: " + size);
        for (int i = 0; i < size; i++) {
            if (isAnimated) {
                spritePaths[i] = "/" + assetPackage + "_assets/" + getName() + "/sprite" + i + ".png";
            } else {
                spritePaths[i] = "/" + assetPackage + "_assets/" + getName() + i + ".png";
            }
            System.out.println("path: " + spritePaths[i]);
        }
        for (String path : spritePaths) {
            try {
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
    
    public void restartAnimation(){
        currentFrame = 0;
    }
    

    public void updateAnimation(){
        if(isAnimated){
            currentFrame++;
            if(currentFrame >= objSprites.getSize()){
                restartAnimation();
            }      
        }
    }
    
    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }

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
        g2d.drawImage(getCurrentSprite(), (int)posX, (int)posY, null);
    }

}
