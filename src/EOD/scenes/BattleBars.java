package EOD.scenes;

import javax.swing.*;
import java.awt.*;

public class BattleBars {
    private PlayerBarFrame playerBarFrame;
    private EnemyBarFrame enemyBarFrame;
    private int playerHealth;
    private int playerMana;
    private int maxPlayerHealth;
    private int maxPlayerMana;
    private Timer healthTimer;
    private Timer manaTimer;
    private int enemyHealth;
    private int maxEnemyHealth;
    private Timer enemyHealthTimer;

    public BattleBars() {
        playerBarFrame = new PlayerBarFrame();
        enemyBarFrame = new EnemyBarFrame();

        playerBarFrame.setLocation(50, 50);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int frameWidth = enemyBarFrame.getWidth();
        enemyBarFrame.setLocation(screenWidth - frameWidth - 50, 50);

        playerBarFrame.setVisible(true);
        enemyBarFrame.setVisible(true);
    }

    public void setPlayerHealth(int playerHealth) {
        playerBarFrame.animatePlayerHealth(playerHealth);
    }

    public void setPlayerMana(int playerMana) {
        playerBarFrame.animatePlayerMana(playerMana);
    }

    public void setEnemyHealth(int enemyHealth) {
        enemyBarFrame.animateEnemyHealth(enemyHealth);
    }

    public void setStats(int playerHP, int playerMP, int enemyHP) {
        this.maxPlayerHealth = playerHP;
        playerBarFrame.animatePlayerHealth(playerHP);
        this.maxPlayerMana = playerMP;
        playerBarFrame.animatePlayerMana(playerMP);
        this.maxEnemyHealth = enemyHP;
        enemyBarFrame.animateEnemyHealth(enemyHP);
    }

    private class PlayerBarFrame extends JFrame {
        private PlayerPanel playerPanel;

        public PlayerBarFrame() {
            setTitle("Player HP/MP Bars");
            setUndecorated(true);
            setSize(300, 75);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBackground(new Color(0, 0, 0, 0));
            playerPanel = new PlayerPanel();
            add(playerPanel);
            setOpacity(0.9f);
        }

        public void animatePlayerHealth(int targetHealth) {
            if (healthTimer != null && healthTimer.isRunning()) {
                healthTimer.stop();
            }
            healthTimer = new Timer(20, e -> {
                if (playerHealth < targetHealth) {
                    playerHealth = Math.min(playerHealth + 1, targetHealth);
                } else if (playerHealth > targetHealth) {
                    playerHealth = Math.max(playerHealth - 1, targetHealth);
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
                    playerMana = Math.max(playerMana - 1, targetMana);
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

                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(2, 5, 296, 28);
                g2d.setColor(Color.GREEN);
                int playerHealthWidth = (int) ((playerHealth / (double) maxPlayerHealth) * 296);
                g2d.fillRect(2, 5, playerHealthWidth, 28);
                g2d.setColor(Color.WHITE);
                g2d.drawString("Player HP: " + playerHealth + " / " + maxPlayerHealth, 90, 23);

                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(2, 40, 296, 28);
                g2d.setColor(Color.BLUE);
                int playerManaWidth = (int) ((playerMana / (double) maxPlayerMana) * 296);
                g2d.fillRect(2, 40, playerManaWidth, 28);
                g2d.setColor(Color.WHITE);
                g2d.drawString("Player MP: " + playerMana + " / " + maxPlayerMana, 90, 58);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 70);
            }
        }
    }

    private class EnemyBarFrame extends JFrame {
        private EnemyPanel enemyPanel;

        public EnemyBarFrame() {
            setTitle("Enemy HP Bar");
            setUndecorated(true);
            setSize(300, 40);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBackground(new Color(0, 0, 0, 0));
            enemyPanel = new EnemyPanel();
            add(enemyPanel);
            setOpacity(0.9f);
        }

        public void animateEnemyHealth(int targetHealth) {
            if (enemyHealthTimer != null && enemyHealthTimer.isRunning()) {
                enemyHealthTimer.stop();
            }
            enemyHealthTimer = new Timer(20, e -> {
                if (enemyHealth < targetHealth) {
                    enemyHealth = Math.min(enemyHealth + 1, targetHealth);
                } else if (enemyHealth > targetHealth) {
                    enemyHealth = Math.max(enemyHealth - 1, targetHealth);
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

                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(2, 5, 296, 28);
                g2d.setColor(Color.RED);
                int enemyHealthWidth = (int) ((enemyHealth / (double) maxEnemyHealth) * 296);
                g2d.fillRect(2, 5, enemyHealthWidth, 28);
                g2d.setColor(Color.WHITE);
                g2d.drawString("Enemy HP: " + enemyHealth + " / " + maxEnemyHealth, 90, 23);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 50);
            }
        }
    }
}
