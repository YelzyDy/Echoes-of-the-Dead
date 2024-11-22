/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.gameInterfaces;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

public interface Entity extends Freeable{
    public final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public double getPosX();
    
    public double getPosY();
    
    public void setPosX(double posX);

    public void setPosY(double posY);

    public void setIndex(int index);

    public int getIndex();

    public void draw(Graphics g);
    
}
