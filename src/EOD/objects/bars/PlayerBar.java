package EOD.objects.bars;

import javax.swing.*;
import java.awt.*;

public class PlayerBar extends JPanel {
    protected int playerHealth;
    protected int playerMana;
    protected int maxPlayerHealth;
    protected int maxPlayerMana;

    private double screenWidth;
    private double screenHeight;

    public PlayerBar(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arcWidth = 20; // Rounded corner radius
        int arcHeight = 20;
        
        // Draw player health bar background with rounded corners
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(2, 5, 296, 28, arcWidth, arcHeight);
        
        // Draw player health bar with capped width at max if health exceeds max
        g2d.setColor(new Color(124,140,156,255));
        int playerHealthWidth = (int) ((Math.min(playerHealth, maxPlayerHealth) / (double) maxPlayerHealth) * 296);
        g2d.fillRoundRect(2, 5, playerHealthWidth, 28, arcWidth, arcHeight);
        
        // Display actual health value even if it exceeds max
        g2d.setColor(Color.WHITE);
        g2d.drawString("Player HP: " + playerHealth + " / " + maxPlayerHealth, 90, 23);

        // Draw player mana bar background with rounded corners
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(2, 40, 296, 28, arcWidth, arcHeight);

        // Draw player mana bar with capped width at max if mana exceeds max
        g2d.setColor(new Color(200,47,60));
        int playerManaWidth = (int) ((Math.min(playerMana, maxPlayerMana) / (double) maxPlayerMana) * 296);
        g2d.fillRoundRect(2, 40, playerManaWidth, 28, arcWidth, arcHeight);

        // Display actual mana value even if it exceeds max
        g2d.setColor(Color.WHITE);
        g2d.drawString("Player MP: " + playerMana + " / " + maxPlayerMana, 90, 58);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(screenWidth * 0.05), (int)(screenHeight * 0.1));
    }
}
