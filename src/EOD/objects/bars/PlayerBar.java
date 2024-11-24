package EOD.objects.bars;

import javax.swing.*;
import java.awt.*;

public class PlayerBar extends JPanel {
    protected int playerHealth;
    protected int playerMana;
    protected int maxPlayerHealth;
    protected int maxPlayerMana;

    private int barWidth;
    private int barHeight;
    private int barHeightSpacing;

    public PlayerBar() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        barWidth = (int) (screenSize.width * 0.16);   // Width is 20% of the screen width
        barHeight = (int) (screenSize.height * 0.03); // Height is 3% of the screen height
        barHeightSpacing = (int) (barHeight * 1.4);   // Spacing between health and mana bars
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
        g2d.fillRoundRect(2, 5, barWidth, barHeight, arcWidth, arcHeight);

        // Draw player health bar with capped width at max if health exceeds max
        g2d.setColor(new Color(124, 140, 156, 255));
        int playerHealthWidth = (int) ((Math.min(playerHealth, maxPlayerHealth) / (double) maxPlayerHealth) * barWidth);
        g2d.fillRoundRect(2, 5, playerHealthWidth, barHeight, arcWidth, arcHeight);

        // Display actual health value even if it exceeds max
        g2d.setColor(Color.WHITE);
        g2d.drawString("Soul Energy: " + playerHealth + " / " + maxPlayerHealth, barWidth / 3, barHeight - 8);

        // Draw player mana bar background with rounded corners
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(2, barHeightSpacing, barWidth, barHeight, arcWidth, arcHeight);

        // Draw player mana bar with capped width at max if mana exceeds max
        g2d.setColor(new Color(200, 47, 60));
        int playerManaWidth = (int) ((Math.min(playerMana, maxPlayerMana) / (double) maxPlayerMana) * barWidth);
        g2d.fillRoundRect(2, barHeightSpacing, playerManaWidth, barHeight, arcWidth, arcHeight);

        // Display actual mana value even if it exceeds max
        g2d.setColor(Color.WHITE);
        g2d.drawString("MP: " + playerMana + " / " + maxPlayerMana, barWidth / 3, barHeightSpacing + barHeight - 8);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(barWidth, barHeightSpacing + barHeight + 10); // Adjust height to fit both bars with spacing
    }

    // Setter methods for updating health and mana values
    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
        repaint();
    }

    public void setMaxPlayerHealth(int maxPlayerHealth) {
        this.maxPlayerHealth = maxPlayerHealth;
        repaint();
    }

    public void setPlayerMana(int playerMana) {
        this.playerMana = playerMana;
        repaint();
    }

    public void setMaxPlayerMana(int maxPlayerMana) {
        this.maxPlayerMana = maxPlayerMana;
        repaint();
    }
}
