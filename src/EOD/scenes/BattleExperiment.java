package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;
import java.util.Random;
import javax.swing.Timer;

public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private BattleUI battleUI; // Reference to BattleUI for updating button states
    private Timer enemyTurnTimer;
    private Timer playerTurnTimer;
    private int enemyTurnDuration; 
    private int playerTurnDuration; 

    public BattleExperiment(Protagonist player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;

        this.enemyTurnDuration = enemy.getTurnDuration();
        this.playerTurnDuration = player.getTurnDuration();

        enemyTurnTimer = new Timer(enemyTurnDuration, e -> performEnemyTurn());
        enemyTurnTimer.setRepeats(false);

        playerTurnTimer = new Timer(playerTurnDuration, e -> startEnemyTurn());
        playerTurnTimer.setRepeats(false);

        player.setEnemy(enemy);
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public void setBattleUI(BattleUI battleUI) {
        this.battleUI = battleUI;
    }

    // Unified method for handling skills
    private void handleSkill(int skillNumber, boolean damageEnemy) {
        if (player.useSkill(skillNumber)) {
            int damage = damageEnemy ? player.getDamageDealt() : 0;
            if (damage > 0) {
                enemy.takeDamage(damage);
            }

            if (player.isWizard() && skillNumber == 3 || player.isKnight() && skillNumber == 4) {
                enemy.missedTurn = true;
            }

            battleUI.showAction(player.getAction());
            battleUI.setSkillButtonsEnabled(false);

            player.getAnimator().triggerSkillAnimation(skillNumber, (int) player.getXFactor());
            player.getAnimator().setMovingRight(true);

            battleUI.updateTurnIndicator("Your Turn");
            playerTurnTimer.start();
        }
    }

    // Optimized skill methods using handleSkill
    public void skill1() { handleSkill(1, true); }  // Basic attack
    public void skill2() { handleSkill(2, false); } // Buff skill
    public void skill3() { handleSkill(3, !player.isKnight()); }  // Signature skill
    public void skill4() { handleSkill(4, true); }  // Ultimate skill

    private void startEnemyTurn() {
        if (enemy.getHp() <= 0) {
            player.reset();
            return;
        }
        if (!enemy.missedTurn) {
            int skillNumber = new Random().nextInt(2) + 1;
            callRandomEnemySkill(skillNumber);

            int damage = enemy.getDamageDealt();
            if (player.isDamageReducerActive()) {
                damage *= 0.4;
                if (damage > player.getAttributes().getHp() * 0.2) {
                    System.out.println("Gain 30 Soul Shards");
                    player.getAttributes().setMoney(player.getAttributes().getMoney() + 30);
                }
                player.resetDamageReducer();
            }
            player.takeDamage(damage);

            battleUI.showAction("Enemy attacks for " + damage + " damage!");
            enemy.getAnimator().triggerSkillAnimation(skillNumber, (int) enemy.getXFactor());
        } else {
            enemy.missedTurn = false; // Reset after missed turn
        }

        enemyTurnTimer.start();
    }

    private void callRandomEnemySkill(int skillNumber) {
        if (skillNumber == 1) {
            enemy.skill1();
        } else if (skillNumber == 2) {
            enemy.skill2();
        }
    }

    private void performEnemyTurn() {
        enemy.getAnimator().setMovingRight(false); 
        player.attributeTurnChecker(); // Decrease cooldowns, etc.
        battleUI.setSkillButtonsEnabled(true);
        battleUI.updateTurnIndicator("Your Turn");
    }
}
