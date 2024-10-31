package EOD.scenes;

import javax.swing.*;
import java.awt.*;

public class BattleBars {
    private int playerHealth;
    private int playerMana;
    private int maxPlayerHealth;
    private int maxPlayerMana;
    private Timer healthTimer;
    private Timer manaTimer;
    private int enemyHealth;
    private int maxEnemyHealth;
    private Timer enemyHealthTimer;
    private PlayerPanel playerPanel;
    private EnemyPanel enemyPanel;
    private double screenWidth;
    private double screenHeight;

    public BattleBars(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        playerPanel = new PlayerPanel();
        enemyPanel = new EnemyPanel();
    }

    public PlayerPanel getPlayerStats(){
        return playerPanel;
    }

    public EnemyPanel getEnemyStats(){
        return enemyPanel;
    }

    public void setPlayerHealth(int playerHealth) {
        animatePlayerHealth(playerHealth);
    }

    public void setPlayerMana(int playerMana) {
        animatePlayerMana(playerMana);
    }

    public void setEnemyHealth(int enemyHealth) {
        animateEnemyHealth(enemyHealth);
    }

    public void setPlayerStats(int playerHP, int playerMP){
        this.maxPlayerHealth = playerHP;
        animatePlayerHealth(playerHP);
        this.maxPlayerMana = playerMP;
        animatePlayerMana(playerMP);
    }

    public void setEnemyStats(int enemyHP) {
        this.maxEnemyHealth = enemyHP;
        this.enemyHealth = 0; // Reset enemy health to 0
        animateEnemyHealth(enemyHP);
    }
    
        public void animatePlayerHealth(int targetHealth) {
            if (healthTimer != null && healthTimer.isRunning()) {
                healthTimer.stop();
            }
            healthTimer = new Timer(20, e -> {
                if (playerHealth < targetHealth) {
                    playerHealth = Math.min(playerHealth + 1, targetHealth);
                } else if (playerHealth > targetHealth) {
                    playerHealth = Math.max(playerHealth - 1,  Math.max(targetHealth, 0));
                } else {
                    ((Timer) e.getSource()).stop();
                }
                playerPanel.repaint();
            });
            healthTimer.start();
        }

        public void animatePlayerMana(int targetMana) {
            if (manaTimer != null && manaTimer.isRunning()) {
                manaTimer.stop();
            }
            manaTimer = new Timer(20, e -> {
                if (playerMana < targetMana) {
                    playerMana = Math.min(playerMana + 1, targetMana);
                } else if (playerMana > targetMana) {
                    playerMana = Math.max(playerMana - 1, Math.max(targetMana, 0));
                } else {
                    ((Timer) e.getSource()).stop();
                }
                playerPanel.repaint();
            });
            manaTimer.start();
        }

        private class PlayerPanel extends JPanel {
            
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
    



        public void animateEnemyHealth(int targetHealth) {
            if (enemyHealthTimer != null && enemyHealthTimer.isRunning()) {
                enemyHealthTimer.stop();
            }
            enemyHealthTimer = new Timer(20, e -> {
                if (enemyHealth < targetHealth) {
                    enemyHealth = Math.min(enemyHealth + 1, targetHealth);
                } else if (enemyHealth > targetHealth) {
                    enemyHealth = Math.max(enemyHealth - 1, Math.max(targetHealth, 0));
                } else {
                    ((Timer) e.getSource()).stop();
                }
                enemyPanel.repaint();
            });
            enemyHealthTimer.start();
        }

        private class EnemyPanel extends JPanel {
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

            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension((int)(screenWidth * 0.05), (int)(screenHeight * 0.2));
            }
        }
    
}
