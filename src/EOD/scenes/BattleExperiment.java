package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

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

    //this is for chaning the x value of the mc
    public double getPlayerXFactor() {
        switch(player.getCharacterType()) {
            case "knight":
                return screenSize.width * 0.5;
            case "wizard":
                return screenSize.width * 0.1;
            default:
                return screenSize.width * 0.3;
        }
    }

    public double getEnemyXFactor() {
        switch(enemy.getCharacterType()) {
            case "skeleton1":
                return screenSize.width * 0.7;
            case "necromacer":
                return screenSize.width * 0.1;
            default:
                return 0;
        }
    }


    public void skill1() { // these are all the buff skills
        if (player.skill1()) {
            // Disable skill buttons
            int damage = player.getDamageDealt();
            enemy.takeDamage(damage);
            battleUI.setSkillButtonsEnabled(false);

            // Trigger skill animation
            player.getAnimator().triggerSkillAnimation(1, (int)(getPlayerXFactor()));
            player.getAnimator().setMovingRight(true);

            // Start player turn timer
            battleUI.updateTurnIndicator("Your Turn");
            playerTurnTimer.start();  // Start timer for player's turn

            // Once the timer ends, enemy's turn will start automatically
        }
    }

    public void skill2() { // attack skill? 
        if (player.skill2()) {
            // Disable skill buttons
            
            battleUI.setSkillButtonsEnabled(false);

            // Trigger skill animation
            player.getAnimator().triggerSkillAnimation(2, (int)(getPlayerXFactor()));
            player.getAnimator().setMovingRight(true);

            // Start player turn timer
            battleUI.updateTurnIndicator("Your Turn");
            playerTurnTimer.start();  
        }
    }

    public void skill3() {
        if (player.skill3()) { // damage reduction?? so far para pani sila sa knight i modify lang skill 2 -4
            // Disable skill buttons
            battleUI.setSkillButtonsEnabled(false);

            // Trigger skill animation
            player.getAnimator().triggerSkillAnimation(3, (int)(getPlayerXFactor()));
            player.getAnimator().setMovingRight(true);

            // Start player turn timer
            battleUI.updateTurnIndicator("Your Turn");
            playerTurnTimer.start();  
        }
    }

    public void skill4() {
        if (player.skill2()) { // burst or unsa?
            // Disable skill buttons
            battleUI.setSkillButtonsEnabled(false);

            // Trigger skill animation
            player.getAnimator().triggerSkillAnimation(4, (int)(getPlayerXFactor()));
            player.getAnimator().setMovingRight(true);

            // Start player turn timer
            battleUI.updateTurnIndicator("Your Turn");
            playerTurnTimer.start(); 
        }
    }

    private void startEnemyTurn() {
        Random random = new Random();
        int skillNumber = random.nextInt(2) + 1;
        battleUI.updateTurnIndicator("Enemy's Turn");

        callRandomEnemySkill(skillNumber);
        double damage = enemy.getDamageDealt();

        System.out.println("Enemy damage: " + damage );
        player.takeDamage((int) damage);

        battleUI.showEnemyAction("Enemy attacks for " + damage + " damage!");

        enemy.getAnimator().triggerSkillAnimation(skillNumber, (int)getEnemyXFactor());
        enemy.getAnimator().setMovingRight(false);
        enemyTurnTimer.start();  // Start enemy turn after player's turn ends
    }

    public void callRandomEnemySkill(int skillNumber){
        switch (skillNumber) {
            case 1:
                enemy.skill1();
                break;
            case 2:
                enemy.skill2();
                break;
            default:
                break;
        }
    }

    // Perform enemy's attack and return to player's turn
    private void performEnemyTurn() {
        // After enemy's turn, enable skill buttons for the player
        double damage = enemy.getDamageDealt();

        if(player.damageReducer != false){
            damage *= 0.4;
            player.damageReducer = false;
        }

        player.takeDamage((int) damage);
        
     
        battleUI.setSkillButtonsEnabled(true);
        battleUI.updateTurnIndicator("Your Turn");
    }
}