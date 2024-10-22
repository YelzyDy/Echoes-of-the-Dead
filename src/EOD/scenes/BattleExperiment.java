package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;
import java.awt.Dimension;
import java.awt.Toolkit;

public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
        double xFactor = 0;
        switch(player.getCharacterType()){
            case "knight":
                enemy.setHp(enemyHp - 10);
                xFactor = screenSize.width * 0.5;
            break;
            case "wizard":
            enemy.setHp(enemyHp - 10);
            xFactor = screenSize.width * 0.1;
            break;
        }
        player.getAnimator().triggerSkillAnimation(1, (int)(xFactor));
        player.getAnimator().setMovingRight(true);
    }

    public void skill2(){
        int enemyHp = enemy.getHp();
        double xFactor = 0;
        switch(player.getCharacterType()){
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
    }

    public void skill3(){
        int enemyHp = enemy.getHp();
        double xFactor = 0;
        switch(player.getCharacterType()){
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
    }

    public void skill4(){
        int enemyHp = enemy.getHp();
        double xFactor = 0;
        switch(player.getCharacterType()){
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
    }

    public void setIsBattleStopped(boolean isBattleStopped){  this.isBattleStopped = isBattleStopped; }

    public boolean getIsBattleStopped(){  return isBattleStopped; }

}
