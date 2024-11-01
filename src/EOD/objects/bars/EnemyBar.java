package EOD.objects.bars;
import javax.swing.*;
import java.awt.*;

public class EnemyBar extends JPanel{
    protected int enemyHealth;
    protected int maxEnemyHealth;
    private double screenWidth;
    private double screenHeight;
    
    public EnemyBar(){
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
    int enemyHealthWidth = (int) ((Math.min(enemyHealth, maxEnemyHealth) / (double) maxEnemyHealth) * 296);
    g2d.fillRoundRect(2, 5, enemyHealthWidth, 28, arcWidth, arcHeight);
        
    // Display actual health value even if it exceeds max
    g2d.setColor(Color.WHITE);
    g2d.drawString("Player HP: " + enemyHealth + " / " + maxEnemyHealth, 90, 23);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((int)(screenWidth * 0.05), (int)(screenHeight * 0.2));
    }
    
}
