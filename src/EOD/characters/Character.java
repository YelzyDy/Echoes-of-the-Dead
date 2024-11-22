/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters;

import EOD.animator.*;
import EOD.gameInterfaces.Entity;
import EOD.scenes.SceneBuilder;
import EOD.utils.TransparentPanel;
import EOD.worlds.World;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
/**


/**
 *
 * @author Joana
 */
public class Character extends TransparentPanel implements Entity{
    private double posX;
    private double posY;
    private String characterType;
    protected World world;
    private SceneBuilder panel;
    private int index;
    private Animator animator;

    public Character(String name, String characterType, double  posX, double posY) {
        super(posX, posY, 0, 0);
        this.posY = posY;
        this.posX = posX;
        this.characterType = characterType;
        this.setVisible(true);
        this.setName(name);
    }   

    @Override
    public void free(){
        try{
            if(panel != null){
                panel.free();
                panel = null;
            }
            if(animator != null){
                animator.free();
                animator = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }   

    public void setWorld(World world){
        this.world = world;
    }

    public World getWorld(){
        return world;
    }

    public double getWidthE(){
        return getWidth();
    }

    public double getHeightE(){
        return getHeight();
    }

    public void setAnimator(Animator animator){
        this.animator = animator;
    }

    public Animator getAnimator(){
        return animator;
    }

    public String getCharacterType(){
        return characterType;
    }

    public void setpanel(SceneBuilder panel){
        this.panel = panel;
    }

    public SceneBuilder getPanel(){
        if(world != null){
            return world.getScene();
        }else{
            return panel;
        }
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

    public double getPosX(){
        return posX;
    }

    public double getPosY(){
        return posY;
    }

    public void setCharacterType(String characterType){
        this.characterType = characterType;
    }

    public void setPosY(double posY){
        this.posY = posY;
    }
    public void setPosX(double posX){
        this.posX = posX;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        draw(g2d);
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        Image currentSprite = animator.getCurrentSprite();
        int drawX = 0;
        int drawY = 0;

        if (!animator.getIsMovingRight()) {
            drawX = getWidth() - currentSprite.getWidth(null);
            g2d.scale(-1, 1);
            g2d.translate(-getWidth(), 0);
        }

        g2d.drawImage(currentSprite, drawX, drawY, null);
    }
}
