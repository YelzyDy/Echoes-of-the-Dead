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
    private SceneBuilder panel;
    private int currentFrame = 0;
    private ImageList objSprites = new ImageList();
    private String type = null;
    private ImageList state  = new ImageList();
    private boolean isAnimated = false;
    public EchoesObjects(int x, int y, int width, int height, SceneBuilder panel, String type, boolean isAnimated){
        super(x, y, width, height);
        this.type = type;
        this.panel = panel;
        startAnimationTimer(panel);
        this.isAnimated = isAnimated;
       
        initializeObjState((int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32));
        this.addMouseListener(new MouseClickListener(this));
    }   
    
    private void startAnimationTimer(SceneBuilder panel) {
        animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
                panel.repaint();
                System.out.println("animating?");
            }
        });
        if(isAnimated){
            initializeObjSprites((int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32));
            animationTimer.start();
        }
    }
    
    public void initializeObjSprites(int width, int height){
        state.clear();
        int size = 2;
        String[] spritePaths = new String[size];
        for(int i = 0; i < size; i++){
            spritePaths[i] = "/world1_assets/" + type + "/sprite/"+ ".png";
        }     
        for (String path : spritePaths) {
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                objSprites.add(image); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        objSprites.resizeImageList((int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32));
    }
    
    public void initializeObjState(int width, int height){
        state.clear();
        int size = 2;
        String[] spritePaths = new String[size];
        for(int i = 0; i < size; i++){
            spritePaths[i] = "/world1_assets/" + type + i + ".png";
        }     
        for (String path : spritePaths) {
            try {
                Image image = ImageIO.read(getClass().getResource(path));
                state.add(image); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        state.resizeImageList((int)(screenSize.width * 0.22),(int)(screenSize.height * 0.32));
    }
    
    @Override
    public void onClick(MouseEvent e) {
       if(type.equals("shop")){
           
           animationTimer.stop();
       }
    }
    @Override
    public void onHover(MouseEvent e) {
        if(type.equals("shop")){
           currentFrame = 1;
            if(isAnimated){
               animationTimer.start();
            }
        }
    }

    @Override
    public void onExit(MouseEvent e) {
       if(type.equals("shop")){
           currentFrame = 0;
           if(isAnimated){
               animationTimer.stop();
           }
        }
    }
    
    @Override
    public void stopMovement() {
        currentFrame = 0;
    }
    
    @Override
    public void updateAnimation(){
        currentFrame++;
        if(currentFrame >= objSprites.getSize()){
            currentFrame = 0;
        }      
    }
    
    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public Image getCurrentSprite() {
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
