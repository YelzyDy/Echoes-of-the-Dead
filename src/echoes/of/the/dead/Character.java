/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import javax.swing.Timer;

/**
 *
 * @author Joana
 */
public abstract class Character implements Entity{
    protected String name;
    protected int posX;
    protected int posY;
    protected ImageList walkSprites = new ImageList();
    protected ImageList idleSprites = new ImageList();
    protected int currentFrame = 0;
    protected Timer animationTimer;
    protected boolean isMoving = false;
    protected boolean isFacingRight = true;
    protected String characterType;
    protected SceneBuilder panel;
    
    public abstract void moveTo(int targetX);

}
