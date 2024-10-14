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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author Joana
 */
public class EchoesObjects extends TransparentPanel implements MouseInteractable, Entity{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int posX;
    private int posY;

    private Timer animationTimer;
    private int currentFrame = 0;
    private ImageList objSprites = new ImageList();
    private String type = null;
    private String assetPackage = null;
    private ImageList state  = new ImageList();
    private boolean isAnimated = false;
    private boolean isState = false;
    private int numOfSprites = 0;
    public EchoesObjects(String assetPackage, int x, int y, int width, int height, String type, boolean isAnimated, boolean isState, int numOfSprites){
        super(x, y, width, height);
        this.type = type;
        this.isAnimated = isAnimated;
        startAnimationTimer();
        this.isState = isState;
        this.numOfSprites = numOfSprites;
        this.assetPackage = assetPackage;
        if(isState){
            initializeObjState(assetPackage, width, height, 2);
        }else if(isAnimated){
            initializeObjSprites(assetPackage, width, height);
        }else if(!isState && !isAnimated){
            initializeObjState(assetPackage, width, height, 1);
            System.out.println(height + " " + width);
        }
        this.addMouseListener(new MouseClickListener(this));
    }   
    
    public void startAnimationTimer() {
        animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
            }
        });
        if(isAnimated){
            animationTimer.start();
        }
    }
    
    public void initializeObjSprites(String assetPackage, int width, int height){
        state.clear();
        int size = numOfSprites;
        String[] spritePaths = new String[size];
        for(int i = 0; i < size; i++){
            spritePaths[i] = "/" + assetPackage + "_assets/" + type + "/sprite"+ i +".png";
        }     
        for (String path : spritePaths) {
            try {
                System.out.println("Attempting to load image from path: " + path);
                Image image = ImageIO.read(getClass().getResource(path));
                objSprites.add(image); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        objSprites.resizeImageList(width, height);
    }
    
    public void initializeObjState(String assetPackage, int width, int height, int numOfState){
        state.clear();
        int size = numOfState;
        String[] spritePaths = new String[size];
        for(int i = 0; i < size; i++){
            spritePaths[i] = "/" + assetPackage + "_assets/" + type + i + ".png";
        }     
        for (String path : spritePaths) {
            try {
                System.out.println("Attempting to load image from path: " + path);
                Image image = ImageIO.read(getClass().getResource(path));
                state.add(image); 
            } catch (IOException e) {;
                e.printStackTrace();
            }
        }
        state.resizeImageList(width, height);
    }
    
   
    
    @Override
    public void onClick(MouseEvent e) {     
           animationTimer.stop();
    }
    @Override
    public void onHover(MouseEvent e) {
        if(!isAnimated){
           currentFrame = 1;
           repaint();
           return;
        }
    }

    @Override
    public void onExit(MouseEvent e) {
        if(!isAnimated){
            currentFrame = 0;  
            repaint();    
            return;  
        }
    }
    
    @Override
    public void stopMovement() {
        currentFrame = 0;
    }
    
    @Override
    public void updateAnimation(){
        if(isAnimated){
            currentFrame++;
            if(currentFrame >= objSprites.getSize()){
                currentFrame = 0;
            }      
        }
    }
    
    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public Image getCurrentSprite() {
        System.out.println(assetPackage + "  " + type);
        return ((isAnimated) ? objSprites : state).get(currentFrame);      
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
}
