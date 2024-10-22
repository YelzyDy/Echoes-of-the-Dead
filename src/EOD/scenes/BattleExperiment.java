package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Necromancer;
import EOD.characters.Protagonist;
import EOD.characters.Skeleton2;


public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private boolean battleStopped;

    //player attributes
    private int playerHp;
    private int playerBaseHp;
    private int playerMana;
    private int playerBaseMana;
    private int skill2Cd = 0;
    private int skill3Cd = 0;
    private int playerAttack;
    private int playerMoney;
    private boolean skillIsUseable = true;

    //knight
    private double playerDamageReducer = 0;

    //priestess
    private int playerLostHp = 0;

    //enemy attributes
    private int enemyHp;
    private int enemyAttack;
    private int enemyMissTurn = 0;
    private int moneyDrop;

    public BattleExperiment(Protagonist player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        enemyHp = enemy.getHp();
        enemyAttack = enemy.getAttack();
        moneyDrop = enemy.getMoneyDrop();
        playerHp = player.getHp();
        playerBaseHp = player.getBaseHp();
        playerAttack = player.getAttack();
        playerMana = player.getMana();
        playerBaseMana = player.getBaseMana();
        playerMoney = player.getMoney();
        battleStopped = false;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public void skill1(){
        switch(player.getCharacterType()){
            case "knight":
                enemy.setHp(enemyHp - playerAttack);
            break;
        }
    }

    public void skill2(){
        int enemyHp = enemy.getHp();

        switch(player.getCharacterType()){
            case "knight":
                enemy.setHp(enemyHp - 10);
            break;
        }
    }

    public void skill3(){
        int enemyHp = enemy.getHp();

        switch(player.getCharacterType()){
            case "knight":
                enemy.setHp(enemyHp - 10);
            break;
        }
    }

    public void skill4(){
        int enemyHp = enemy.getHp();
        switch(player.getCharacterType()){
            case "knight":
                enemy.setHp(enemyHp - 40);
            break;
        }
    }

    public boolean getIsBattleStopped(){
        return battleStopped;
    }

}
