/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.characters;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import EOD.animator.*;
import EOD.entities.Entity;
import EOD.utils.TransparentPanel;
import EOD.worlds.World;
import EOD.scenes.SceneBuilder;
/**


/**
 *
 * @author Joana
 */
public class Character extends TransparentPanel implements Entity{
    private String name;
    private double posX;
    private double posY;
    private String characterType;
    protected World world;
    private SceneBuilder panel;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    private Animator animator;

    public Character(String name, String characterType, double  posX, double posY) {
        super(posX, posY, 0, 0);
        this.posY = posY;
        this.posX = posX;
        this.name = name;
        this.characterType = characterType;
        this.setVisible(true);
    }   

    public void setWorld(World world){
        this.world = world;
    }

    @Override
    public double getWidthE(){
        return getWidth();
    }

    @Override
    public double getHeightE(){
        return getHeight();
    }
    public void setAnimator(Animator animator){
        this.animator = animator;
    }

    public Animator getAnimator(){
        return animator;
    }
    
    public String getName(){
        return name;
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


    @Override
    public double getPosX(){
        return posX;
    }

    @Override
    public double getPosY(){
        return posY;
    }

    public void setCharacterType(String characterType){
        this.characterType = characterType;
    }

    @Override
    public void setPosY(double posY){
        this.posY = posY;
    }
    @Override
    public void setPosX(double posX){
        this.posX = posX;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        draw(g2d);
    }
    
    @Override
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
