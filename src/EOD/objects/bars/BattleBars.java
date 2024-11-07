package EOD.objects.bars;

import javax.swing.*;

import EOD.gameInterfaces.Freeable;

public class BattleBars implements Freeable{
    private Timer healthTimer;
    private Timer manaTimer;
    private PlayerBar playerBar;
    private EnemyBar enemyBar;
    private Timer enemyHealthTimer;
    public BattleBars(){
        playerBar = new PlayerBar();
        enemyBar = new EnemyBar();
    }

    @Override
    public void free(){
        healthTimer = null;
        manaTimer = null;
        playerBar = null;
        enemyBar = null;
        enemyHealthTimer = null;
    }
    public PlayerBar getPlayerStats(){
        return playerBar;
    }


    public EnemyBar getEnemyStats(){
        return enemyBar;
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
        playerBar.maxPlayerHealth = playerHP;
        animatePlayerHealth(playerHP);
        playerBar.maxPlayerMana = playerMP;
        animatePlayerMana(playerMP);
    }


    public void setEnemyStats(int enemyHP) {
        enemyBar.maxEnemyHealth = enemyHP;
        enemyBar.enemyHealth = 0; // Reset enemy health to 0
        animateEnemyHealth(enemyHP);
    }

    public void animatePlayerHealth(int targetHealth) {
        if (healthTimer != null && healthTimer.isRunning()) {
            healthTimer.stop();
        }
        healthTimer = new Timer(20, e -> {
            if ( playerBar.playerHealth < targetHealth) {
                playerBar.playerHealth = Math.min( playerBar.playerHealth + 1, targetHealth);
            } else if ( playerBar.playerHealth > targetHealth) {
                playerBar.playerHealth = Math.max( playerBar.playerHealth - 1,  Math.max(targetHealth, 0));
            } else {
                ((Timer) e.getSource()).stop();
            }
            playerBar.repaint();
        });
        healthTimer.start();
    }

    public void animatePlayerMana(int targetMana) {
        if (manaTimer != null && manaTimer.isRunning()) {
            manaTimer.stop();
        }
        manaTimer = new Timer(20, e -> {
            if ( playerBar.playerMana < targetMana) {
                playerBar.playerMana = Math.min( playerBar.playerMana + 1, targetMana);
            } else if ( playerBar.playerMana > targetMana) {
                playerBar.playerMana = Math.max( playerBar.playerMana - 1, Math.max(targetMana, 0));
            } else {
                ((Timer) e.getSource()).stop();
            }
            playerBar.repaint();
        });
        manaTimer.start();
    }

    public void animateEnemyHealth(int targetHealth) {
        if (this.enemyHealthTimer != null && this.enemyHealthTimer.isRunning()) {
            this.enemyHealthTimer.stop();
        }
        enemyHealthTimer = new Timer(20, e -> {
            if (enemyBar.enemyHealth < targetHealth) {
                enemyBar.enemyHealth = Math.min(enemyBar.enemyHealth + 1, targetHealth);
            } else if (enemyBar.enemyHealth > targetHealth) {
                enemyBar.enemyHealth = Math.max(enemyBar.enemyHealth - 1, Math.max(targetHealth, 0));
            } else {
                ((Timer) e.getSource()).stop();
            }
            enemyBar.repaint();
        });
        enemyHealthTimer.start();
    }
    
}
