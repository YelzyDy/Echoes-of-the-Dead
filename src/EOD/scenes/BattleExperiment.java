package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Timer;


public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private BattleUI battleUI; // Reference to BattleUI for updating button states
    private Timer enemyTurnTimer;
    private Timer playerTurnTimer;
    private int enemyTurnDuration; 
    private int playerTurnDuration; // 2 seconds for enemy turn

    public BattleExperiment(Protagonist player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;

        this.enemyTurnDuration = enemy.getTurnDuration();
        this.playerTurnDuration = player.getTurnDuration();
        // Initialize enemy turn timer
        enemyTurnTimer = new Timer(enemyTurnDuration, e -> performEnemyTurn());
        enemyTurnTimer.setRepeats(false);

        // Initialize player turn timer
        playerTurnTimer = new Timer(playerTurnDuration, e -> startEnemyTurn());
        playerTurnTimer.setRepeats(false);
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


    public void skill1() {
        if (player.skill1()) {
            // Disable skill buttons
            battleUI.setSkillButtonsEnabled(false);

            // Trigger skill animation
            player.getAnimator().triggerSkillAnimation(1, (int)(getXFactor()));
            player.getAnimator().setMovingRight(true);

            // Start player turn timer
            battleUI.updateTurnIndicator("Your Turn");
            playerTurnTimer.start();  // Start timer for player's turn

            // Once the timer ends, enemy's turn will start automatically
        }
    }

    public void skill2() {

        
        
    }

    public void skill3() {
       
    }

    public void skill4() {
        
    }

    private void startEnemyTurn() {
        battleUI.updateTurnIndicator("Enemy's Turn");
        enemyTurnTimer.start();  // Start enemy turn after player's turn ends
    }

    // Perform enemy's attack and return to player's turn
    private void performEnemyTurn() {
        double damage = enemy.skill1();
        player.takeDamage((int) damage);
        battleUI.showEnemyAction("Enemy attacks for " + damage + " damage!");
        
        // After enemy's turn, enable skill buttons for the player
        battleUI.setSkillButtonsEnabled(true);
        battleUI.updateTurnIndicator("Your Turn");
    }
}