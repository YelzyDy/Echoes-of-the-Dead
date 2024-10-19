/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Graphics;

public interface Entity {
    
    public int getPosX();

    public void setPosY(int posY);
    
    public void setPosX(int posX);
    
    public void draw(Graphics g);

}
