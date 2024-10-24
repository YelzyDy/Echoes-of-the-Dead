package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;
import java.util.Random;
import javax.swing.Timer;

public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private BattleUI battleUI;
    private Timer enemyTurnTimer;
    private Timer playerTurnTimer;
    private Random random = new Random();
    private int turnCount = 0;

    public BattleExperiment(Protagonist player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        
        enemyTurnTimer = new Timer(enemy.getTurnDuration(), e -> performEnemyTurn());
        enemyTurnTimer.setRepeats(false);
        
        playerTurnTimer = new Timer(player.getTurnDuration(), e -> startEnemyTurn());
        playerTurnTimer.setRepeats(false);
        
        player.setEnemy(enemy);
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public void setBattleUI(BattleUI battle){
        this.battleUI = battle;
    }

    private void handleSkill(int skillNumber, boolean damageEnemy) {
        if (player.useSkill(skillNumber)) {
            turnCount++;
            
            if (damageEnemy) {
                int damage = player.getDamageDealt();
                enemy.takeDamage(damage);
                
                // Special effects for wizard and knight
                if ((player.isWizard() && skillNumber == 3) || 
                    (player.isKnight() && skillNumber == 4)) {
                    enemy.missedTurn = true;
                }
            }

            battleUI.showAction(player.getAction());
            battleUI.setSkillButtonsEnabled(false);
            player.getAnimator().triggerSkillAnimation(skillNumber, (int)player.getXFactor());
            
            battleUI.updateTurnIndicator("Your Turn");
            player.getAnimator().setMovingRight(true);
            battleUI.updateCooldowns();
            
            playerTurnTimer.start();
        }
    }

    // Skill methods remain unchanged
    public void skill1() { handleSkill(1, true); }
    public void skill2() { handleSkill(2, false); }
    public void skill3() { handleSkill(3, !player.isKnight()); }
    public void skill4() { handleSkill(4, true); }

    private void startEnemyTurn() {
        if (enemy.getHp() <= 0) {
            handleBattleEnd(true);
            return;
        }

        if (!enemy.missedTurn) {
            determineAndExecuteEnemyAction();
        } else {
            enemy.missedTurn = false;
            battleUI.showAction("Enemy's turn was skipped!");
        }

        enemyTurnTimer.start();
    }

    private void determineAndExecuteEnemyAction() {
        // Use the enemy's built-in decision making
        int chosenSkill = enemy.decideSkill();
        
        // Execute the chosen skill
        switch (chosenSkill) {
            case 1:
                enemy.skill1();
                break;
            case 2:
                enemy.skill2();
                break;
        }

        // Calculate and apply damage
        int damage = enemy.getDamageDealt();
        
        // Handle damage reduction if player has active defense
        if (player.isDamageReducerActive()) {
            damage = (int)(damage * 0.4);
            // Bonus money for successful defensive play against strong attacks
            if (damage > player.getAttributes().getHp() * 0.2) {
                player.getAttributes().setMoney(
                    player.getAttributes().getMoney() + 30
                );
            }
            player.resetDamageReducer();
        }
        
        // Apply the damage and update UI
        player.takeDamage(damage);
        battleUI.showAction(enemy.getAction());
        
        // Trigger animation with appropriate range
        enemy.getAnimator().triggerSkillAnimation(
            enemy.getLastUsedSkill(), 
            (int)enemy.getXFactor()
        );
    }

    private void performEnemyTurn() {
        // Update player status
        player.attributeTurnChecker();
        
        // Update UI elements
        battleUI.setSkillButtonsEnabled(true);
        battleUI.updateTurnIndicator("Your Turn");
        battleUI.updateCooldowns();
        
        // Check for player defeat
        if (player.attributes.getHp() <= 0) {
            handleBattleEnd(false);
        }
        
        // Update enemy (includes cooldown management)
        enemy.update();
    }

    private void handleBattleEnd(boolean playerWon) {
        player.reset();
        // Add any additional end-game logic here
    }

}