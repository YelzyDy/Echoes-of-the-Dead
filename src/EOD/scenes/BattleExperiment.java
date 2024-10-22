package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;


public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private boolean isPlayerTurn;
    private BattleUI battleUI; // Reference to BattleUI for updating button states
    private Timer enemyTurnTimer;
    private static final int ENEMY_TURN_DURATION = 2000; // 2 seconds for enemy turn


    public BattleExperiment(Protagonist player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.isPlayerTurn = true;
        
        enemyTurnTimer = new Timer(ENEMY_TURN_DURATION, e -> {
            performEnemyTurn();
        });
        enemyTurnTimer.setRepeats(false);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setBattleUI(BattleUI battleUI){
        this.battleUI = battleUI;
    }

    public double getXFactor() {
        switch(player.getCharacterType()) {
            case "knight":
                return screenSize.width * 0.5;
            case "wizard":
                return screenSize.width * 0.1;
            default:
                return 0;
        }
    }
    
    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    private void startEnemyTurn() {
        isPlayerTurn = false;
        battleUI.setSkillButtonsEnabled(false);
        battleUI.updateTurnIndicator("Enemy's Turn");
        enemyTurnTimer.start();
    }

    private void performEnemyTurn() {
        // // Implement enemy attack logic here
        // int damage = enemy.calculateDamage(); // You'll need to implement this in Enemy class
        // player.takeDamage(damage); // You'll need to implement this in Protagonist class
        
        // Update UI with enemy action results
        battleUI.showEnemyAction("Enemy attacks for " + " damage!");
        
        // End enemy turn and start player turn
        isPlayerTurn = true;
        battleUI.setSkillButtonsEnabled(true);
        battleUI.updateTurnIndicator("Your Turn");
    }


    public void skill1() {
        if (isPlayerTurn) {
            if(player.skill1()) {
                player.getAnimator().triggerSkillAnimation(1, (int)(getXFactor()));
                player.getAnimator().setMovingRight(true);
                startEnemyTurn();
            }
        }
    }

    public void skill2() {
        if (isPlayerTurn) {
            int enemyHp = enemy.getHp();
            double xFactor = 0;
            
            switch(player.getCharacterType()) {
                case "knight":
                    enemy.setHp(enemyHp - 10);
                    xFactor = screenSize.width * 0.5;
                    break;
                case "wizard":
                    enemy.setHp(enemyHp - 10);
                    xFactor = screenSize.width * 0.1;
                    break;
            }
            
            player.getAnimator().triggerSkillAnimation(2, (int)(xFactor));
            player.getAnimator().setMovingRight(true);
            startEnemyTurn();
        }
    }

    public void skill3() {
        if (isPlayerTurn) {
            int enemyHp = enemy.getHp();
            double xFactor = 0;
            
            switch(player.getCharacterType()) {
                case "knight":
                    enemy.setHp(enemyHp - 10);
                    xFactor = screenSize.width * 0.5;
                    break;
                case "wizard":
                    enemy.setHp(enemyHp - 10);
                    xFactor = screenSize.width * 0.1;
                    break;
            }
            
            player.getAnimator().triggerSkillAnimation(3, (int)(xFactor));
            player.getAnimator().setMovingRight(true);
            startEnemyTurn();
        }
    }

    public void skill4() {
        if (isPlayerTurn) {
            int enemyHp = enemy.getHp();
            double xFactor = 0;
            
            switch(player.getCharacterType()) {
                case "knight":
                    enemy.setHp(enemyHp - 10);
                    xFactor = screenSize.width * 0.5;
                    break;
                case "wizard":
                    enemy.setHp(enemyHp - 10);
                    xFactor = screenSize.width * 0.1;
                    break;
            }
            
            player.getAnimator().triggerSkillAnimation(4, (int)(xFactor));
            player.getAnimator().setMovingRight(true);
            startEnemyTurn();
        }
    }
}