package EOD.objects.bars;
import javax.swing.*;
import java.awt.*;

public class EnemyBar extends JPanel {
    protected int enemyHealth;
    protected int maxEnemyHealth;
    private int barWidth;
    private int barHeight;
    
    public EnemyBar() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        barWidth = (int) (screenSize.width * 0.16); // Width is 20% of screen width
        barHeight = (int) (screenSize.height * 0.03); // Height is 5% of screen height
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int arcWidth = 20; // Rounded corner radius
        int arcHeight = 20;
        
        // Draw enemy health bar background with rounded corners
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(2, 5, barWidth, barHeight, arcWidth, arcHeight);
        
        // Draw enemy health bar with capped width at max if health exceeds max
        g2d.setColor(new Color(124, 140, 156, 255));
        int enemyHealthWidth = (int) ((Math.min(enemyHealth, maxEnemyHealth) / (double) maxEnemyHealth) * barWidth);
        g2d.fillRoundRect(2, 5, enemyHealthWidth, barHeight, arcWidth, arcHeight);
        
        // Display actual health value even if it exceeds max
        g2d.setColor(Color.WHITE);
        g2d.drawString("Soul Energy: " + enemyHealth + " / " + maxEnemyHealth, barWidth / 3, barHeight - 8);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(barWidth, barHeight);
    }

    // Setter methods for updating health values
    public void setEnemyHealth(int enemyHealth) {
        this.enemyHealth = enemyHealth;
        repaint();
    }

    public void setMaxEnemyHealth(int maxEnemyHealth) {
        this.maxEnemyHealth = maxEnemyHealth;
        repaint();
    }
}
