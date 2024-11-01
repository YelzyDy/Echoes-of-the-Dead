package EOD.objects.bars;

import javax.swing.*;

import EOD.gameInterfaces.BattleBarsBlueprint;
public class BattleBars implements BattleBarsBlueprint{
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
    public PlayerBar getPlayerStats(){
        return playerBar;
    }

    @Override
    public EnemyBar getEnemyStats(){
        return enemyBar;
    }

    @Override
    public void setPlayerHealth(int playerHealth) {
        animatePlayerHealth(playerHealth);
    }

    @Override
    public void setPlayerMana(int playerMana) {
        animatePlayerMana(playerMana);
    }

    @Override
    public void setEnemyHealth(int enemyHealth) {
        animateEnemyHealth(enemyHealth);
    }

    @Override
    public void setPlayerStats(int playerHP, int playerMP){
        playerBar.maxPlayerHealth = playerHP;
        animatePlayerHealth(playerHP);
        playerBar.maxPlayerMana = playerMP;
        animatePlayerMana(playerMP);
    }

    @Override
    public void setEnemyStats(int enemyHP) {
        enemyBar.maxEnemyHealth = enemyHP;
        enemyBar.enemyHealth = 0; // Reset enemy health to 0
        animateEnemyHealth(enemyHP);
    }
    
    @Override
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

    @Override
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

    @Override
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
