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
public abstract class Character extends TransparentPanel implements Entity{
    protected String name;
    protected int posX;
    protected int posY;
    protected int currentFrame;
    protected Timer animationTimer;
    protected boolean isMoving = false;

    protected boolean isFacingRight = true;
    protected String characterType;
    protected SceneBuilder panel;
   
    public Character(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public abstract void moveTo(int targetX);
    
}
