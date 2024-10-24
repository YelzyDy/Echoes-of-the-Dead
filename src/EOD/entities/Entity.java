/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.entities;

import java.awt.Graphics;

public interface Entity {
    public double getPosX();
    
    public double getPosY();

    public double getWidthE();
    
    public double getHeightE();
    
    public void setPosX(double posX);

    public void setPosY(double posY);
    
    public void draw(Graphics g);

}
