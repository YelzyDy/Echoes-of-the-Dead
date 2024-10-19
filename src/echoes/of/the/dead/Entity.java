/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Graphics;

public interface Entity {
    public double getPosX();
    
    public double getPosY();
    
    public void setPosX(double posX);

    public void setPosY(double posY);
    
    public void draw(Graphics g);

}
