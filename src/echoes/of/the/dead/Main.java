/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Joana
 */
public class Main extends javax.swing.JFrame implements MouseInteractable{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;
    public Main(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // Setup background label
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/title_screen_assets/titleScreen.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        
        JLabel lbl_background = new JLabel();
        lbl_background.setIcon(resizedIcon);
        lbl_background.setBounds(0, 0, width, height);
        this.add(lbl_background);
        
        
        EchoesButton btn_title_play = new EchoesButton();
        btn_title_play.setFont(createDynamicFont()); 
        btn_title_play.setForeground(new java.awt.Color(199,205,136,255));
        btn_title_play.setText("PLAY");
        btn_title_play.setOpaque(true);
        btn_title_play.setColor(Color.BLACK);
        btn_title_play.setColorOver(new Color(24,42,58,255));
        btn_title_play.setRadius(50);
        btn_title_play.setColorClick(new Color(7,13,26,255));
        btn_title_play.setBorderColor(new Color(205,192,94,255));
        btn_title_play.setBounds((int)(width * 0.80), (int)(height * 0.85), (int)(width * 0.15), (int) (height * 0.1)); 
        btn_title_play.setVisible(true);
        btn_title_play.addMouseListener(new MouseClickListener(this));
        
       this.add(btn_title_play);
       
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);  // Create and display the Main window
        });
    }
    
    private java.awt.Font createDynamicFont() {
        // Adjust the font size based on window height
        int baseFontSize = 60; // You can choose a base font size that looks good
        int dynamicFontSize = (int)(height * 0.05); // 5% of the window height
        return new java.awt.Font("SansSerif", java.awt.Font.PLAIN, Math.max(baseFontSize, dynamicFontSize));
    }
    
    @Override
    public void onClick(MouseEvent e) {
        ChooseChar window = new ChooseChar();
        window.setVisible(true);
        this.setVisible(false);
    }

    @Override
    public void onHover(MouseEvent e) {
            
    }

    @Override
    public void onExit(MouseEvent e) {
           
    }
}
