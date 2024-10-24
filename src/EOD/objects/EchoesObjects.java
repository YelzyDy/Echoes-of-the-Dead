/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.objects;

import EOD.MouseInteractable;
import EOD.entities.*;
import EOD.listeners.MouseClickListener;
import EOD.utils.*;
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
    private boolean allowHover;
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
        allowHover = true;
        initializeSprites(assetPackage, width, height);
        this.addMouseListener(new MouseClickListener(this));
    }   

    @Override
    public double getWidthE(){
        return getWidth();
    }

    @Override
    public double getHeightE(){
        return getHeight();
    }
    
    public void setAllowHover(boolean allowHover){
        this.allowHover = allowHover;
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

    public void setBounds(int posX, int posY){
        this.setBounds(posX, posY, getWidth(), getHeight());
    }

    public void initializeSprites(String assetPackage, double width, double height) {
        objSprites.clear();
        int size = numOfSprites;
        String[] spritePaths = new String[size];
        for (int i = 0; i < size; i++) {
            if (isAnimated) {
                spritePaths[i] = "/" + assetPackage + "_assets/" + getName() + "/sprite_" + i + ".png";
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
                    System.out.println("Error: Failed to load image from " + path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        objSprites.resizeImageList(width, height);
        // System.out.println("Number of sprites loaded: " + objSprites.getSize());
    }

    public void scaleSprites(double scale) {
        objSprites.scaleImageList(scale);
    }

    

    public void scaleDownSprites(double scale) {
        objSprites.scaleImageListDown(scale);
    }
   
    public void setCurrentFrame(int value){
        currentFrame = value;
    }

    @Override
    public void onClick(MouseEvent e) {     
          
    }

    @Override
    public void onHover(MouseEvent e) {
        if(!allowHover) return;
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

    public String getAssetPackage(){
        return assetPackage;
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

    public int getCurrentFrame(){
        return currentFrame;
    }

    public int getNumOfSprites(){
        return numOfSprites;
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