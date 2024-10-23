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
        player.getSkill3Effect().setIndex(enemy.getIndex());
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
                return screenSize.width * 0.4;
            case "necromacer":
                return screenSize.width * 0.1;
            default:
                return 0;
        }
    }

    public void skill1() { // basic attacks
        if (player.skill1()) {
            //this is for dealing damage. Apply this code if you want to deal damage to enemy - ji
            int damage = player.getDamageDealt();
            enemy.takeDamage(damage);
            // Disable skill buttons
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

    public void skill2() { // buff skills
        if (player.skill2()) {
            
            // Disable skill buttons
            battleUI.setSkillButtonsEnabled(false);

            // Trigger skill animation
            player.getAnimator().triggerSkillAnimation(2, (int)player.getPosX());
            player.getAnimator().setMovingRight(true);

            // Start player turn timer
            battleUI.updateTurnIndicator("Your Turn");
            playerTurnTimer.start();  
        }
    }

    public void skill3() { // signature skills
        if (player.skill3()) { 
            // Disable skill buttons
            if(player.getCharacterType() == "wizard" || player.getCharacterType() == "priest"){
                int damage = player.getDamageDealt();
                enemy.takeDamage(damage);
                if(player.getCharacterType() == "wizard"){
                    enemy.missedTurn = true;
                    System.out.println("Enemy missed a turn!");
                }
            }

            battleUI.setSkillButtonsEnabled(false);
            // Trigger skill animation
            player.getPanel().setComponentZOrder(player, 0);
            player.getAnimator().triggerSkillAnimation(3, (int)player.getPosX());
            player.getAnimator().setMovingRight(true);
            player.getPanel().setComponentZOrder(player.getSkill3Effect(), 0);
            // Start player turn timer
            battleUI.updateTurnIndicator("Your Turn");
            playerTurnTimer.start();  
        }
    }

    public void skill4() {
        if (player.skill4()) { // ultimate
            if(player.getCharacterType() == "knight"){
                enemy.missedTurn = true;
                System.out.println("Enemy missed a turn!");
            }
            int damage = player.getDamageDealt();
            enemy.takeDamage(damage);
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

        //doesnt run after enemy death
        if(enemy.getHp() <= 0){
            return;
        }

        if(enemy.missedTurn == false){
            Random random = new Random();
            int skillNumber = random.nextInt(2) + 1;
            battleUI.updateTurnIndicator("Enemy's Turn");

            callRandomEnemySkill(skillNumber);
            double damage = enemy.getDamageDealt();

            if(player.damageReducer == true){
                damage *= 0.4;
                if(damage > (int)(player.getHp()*0.2)){
                    // padisplay nya ko blair -jm
                    System.out.println("Gain 30 Soul Shards");
                    player.setMoney(30);
                }
                player.damageReducer = false;
            }

            player.takeDamage((int) damage);
            
            System.out.println("Enemy damage: " + damage );

            battleUI.showEnemyAction("Enemy attacks for " + damage + " damage!");

            enemy.getAnimator().triggerSkillAnimation(skillNumber, (int)getEnemyXFactor());
        }else{
            enemy.missedTurn = false;
        }
        
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

        //decrease cd everytime it is the enemy turn
        player.attributeTurnChecker();
        
        // After enemy's turn, enable skill buttons for the player
        battleUI.setSkillButtonsEnabled(true);
        enemy.getAnimator().setMovingRight(false);
        battleUI.updateTurnIndicator("Your Turn");
    }
}