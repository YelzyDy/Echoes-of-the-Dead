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
    
    public abstract int getPosX();
    
    public abstract Image getCurrentSprite();
    
    public abstract void draw(Graphics g);
}
