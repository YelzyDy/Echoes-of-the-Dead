/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.utils;

import javax.swing.JPanel;

/**
 *
 * @author Joana
 */
public class TransparentPanel extends JPanel {
    public TransparentPanel(double x, double y, double width, double height) {
        this.setBounds((int)x, (int)y, (int)width, (int)height);
        this.setVisible(false);
        this.setOpaque(false);
    }
   
}
  
