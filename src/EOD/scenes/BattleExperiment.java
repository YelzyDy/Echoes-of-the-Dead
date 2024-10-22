package EOD.scenes;

import EOD.characters.Enemy;
import EOD.characters.Protagonist;
import java.awt.Dimension;
import java.awt.Toolkit;

public class BattleExperiment {
    private Enemy enemy;
    private Protagonist player;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private boolean isSkillUsable;
    public BattleExperiment(Protagonist player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        isSkillUsable = true;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public double getXFactor(){
        switch(player.getCharacterType()){
            case "knight":
            return screenSize.width * 0.5;
            case "wizard":
            return screenSize.width * 0.1;
        }
        return 0;
    }

    public void skill1(){
        if(player.skill1()) {
            player.getAnimator().triggerSkillAnimation(1, (int)(getXFactor()));
            player.getAnimator().setMovingRight(true);
        } else {
            isSkillUsable = false;
        }
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

    public void setIsSkillUsable(boolean isSkillUsable){  this.isSkillUsable = isSkillUsable; }

    public boolean getIsSkillUsable(){  return isSkillUsable; }

}
