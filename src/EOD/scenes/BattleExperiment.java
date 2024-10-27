package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;
import EOD.characters.ProtagonistAttributes;

import java.util.Random;
import javax.swing.Timer;

public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private BattleUI battleUI;
    private Random random = new Random();
    private int turnCount = 0;
    private boolean isProcessingTurn = false;

    public BattleExperiment(Protagonist player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        player.setEnemy(enemy);
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public Protagonist getPlayer(){
        return player;
    }

    public void setBattleUI(BattleUI battle){
        this.battleUI = battle;
    }

    private void handleSkill(int skillNumber, boolean damageEnemy) {
        if (isProcessingTurn) return;  // Prevent multiple skill uses while animation is playing
        
        if (player.useSkill(skillNumber)) {
            isProcessingTurn = true;
            turnCount++;
            
            if (damageEnemy) {
                int damage = player.getDamageDealt();
                enemy.takeDamage(damage);
                battleUI.setEnemyHealth(enemy.getHp());
                battleUI.setPlayerMana(player.getAttributes().getMana());
                
                if ((player.isWizard() && skillNumber == 3) || 
                    (player.isKnight() && skillNumber == 4)) {
                    enemy.missedTurn = true;
                }
            }

            battleUI.showAction(player.getAction());
            battleUI.setSkillButtonsEnabled(false);
            
            // Set callback for player's animation completion
            player.getAnimator().setOnAnimationComplete(() -> {
                if (enemy.getHp() <= 0) {
                    handleBattleEnd(true);
                    return;
                }
                startEnemyTurn();
            });
            
            player.getAnimator().triggerSkillAnimation(skillNumber, (int)player.getXFactor());
            player.getAnimator().setMovingRight(true);
            battleUI.updateCooldowns();
            battleUI.updateTurnIndicator("Your Turn");
        }
    }


    // Skill methods remain unchanged
    public void skill1() { handleSkill(1, true); }
    public void skill2() { handleSkill(2, false); }
    public void skill3() { handleSkill(3, !player.isKnight()); }
    public void skill4() { handleSkill(4, true); }

    private void startEnemyTurn() {
        if (!enemy.missedTurn) {
            determineAndExecuteEnemyAction();
        } else {
            enemy.missedTurn = false;
            battleUI.showAction("Enemy's turn was skipped!");
            finishEnemyTurn();
        }
    }


    private void determineAndExecuteEnemyAction() {
        int chosenSkill = enemy.decideSkill();
        
        switch (chosenSkill) {
            case 1:
                enemy.skill1();
                break;
            case 2:
                enemy.skill2();
                break;
        }

        int damage = enemy.getDamageDealt();
        
        if (player.isDamageReducerActive()) {
            damage = (int)(damage * 0.4);
            if (damage > player.getAttributes().getHp() * 0.2) {
                player.getAttributes().setMoney(
                    player.getAttributes().getMoney() + 30
                );
            }
            player.resetDamageReducer();
        }
        
        player.takeDamage(damage);
        battleUI.showAction(enemy.getAction());
        battleUI.setPlayerHealth(player.getAttributes().getHp());
        
        // Set callback for enemy's animation completion
        enemy.getAnimator().setOnAnimationComplete(this::finishEnemyTurn);
        
        enemy.getAnimator().triggerSkillAnimation(
            enemy.getLastUsedSkill(), 
            (int)enemy.getXFactor()
        );
        enemy.getAnimator().setMovingRight(false);
    }

    private void finishEnemyTurn() {
        player.attributeTurnChecker();
        battleUI.setSkillButtonsEnabled(true);
        battleUI.updateTurnIndicator("Your Turn");
        battleUI.updateCooldowns();
        
        if (player.attributes.getHp() <= 0) {
            handleBattleEnd(false);
            return;
        }
        
        enemy.update();
        isProcessingTurn = false;  // Turn is complete, allow next action
    }

    private void handleBattleEnd(boolean playerWon) {
        isProcessingTurn = false;
        player.reset();
        // Add any additional end-game logic here
    }
}