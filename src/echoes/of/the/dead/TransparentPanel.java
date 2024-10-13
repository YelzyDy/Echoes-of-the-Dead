/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import javax.swing.JPanel;

/**
 *
 * @author Joana
 */
public class TransparentPanel extends JPanel {
    public TransparentPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setVisible(false);
        this.setOpaque(false);
    }
   
}
  
