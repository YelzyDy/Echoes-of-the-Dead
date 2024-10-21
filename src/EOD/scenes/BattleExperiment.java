package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;


public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private boolean battleStopped;
    public BattleExperiment(Protagonist player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        battleStopped = false;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public void skill1(){
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
