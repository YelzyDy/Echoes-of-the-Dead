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
    private double posX;
    private double posY;
    private String characterType;
   
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected SceneBuilder panel;
    protected CharacterAnimator animator;

    public Character(String name, String characterType, SceneBuilder panel, int posX, int posY) {
        super(posX, posY, 0, 0);
        this.posY = posY;
        this.posX = posX;
        this.name = name;
        this.characterType = characterType;
        this.panel = panel;
        this.setVisible(true);
        animator = new CharacterAnimator(this);
    }   

    public String getName(){
        return name;
    }

    public String getCharacterType(){
        return characterType;
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
