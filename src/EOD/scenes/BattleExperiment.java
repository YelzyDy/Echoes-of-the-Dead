package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;


public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;

    private boolean isBattleStopped;
    public BattleExperiment(Protagonist player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        isBattleStopped = false;
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

    public void setIsBattleStopped(boolean isBattleStopped){  this.isBattleStopped = isBattleStopped; }

    public boolean getIsBattleStopped(){  return isBattleStopped; }

}
