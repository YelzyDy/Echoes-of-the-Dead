/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Joana
 */
public interface Entity {
    public void updateAnimation();
    
    public void stopMovement();

    public void startMovement();
    
    public abstract int getPosX();

    public void setPosY(int posY);
    
    public void setPosX(int posX);
    
    public abstract Image getCurrentSprite();

    public abstract void initializeSprites(String assetPackage, String type, int scale);

    public abstract void initializeSprites(String assetPackage, int width, int height);
    
    public abstract void draw(Graphics g);

    public abstract void scaleSprites(String spriteType, int scale);

}
