package EOD.scenes;

import EOD.characters.Necromancer;
import EOD.characters.Skeleton;
import EOD.characters.Protagonist;
import EOD.animator.Animator;
public class BattleExperiment {
    private Necromancer miniboss;
    private Skeleton minion;
    private Protagonist player;
    private Animator animator;
    private boolean battleStopped;
    public BattleExperiment(Protagonist player, Skeleton minion){
        this.player = player;
        this.minion = minion;
        animator = player.getAnimator();
        battleStopped = false;
    }

    public int getEnemyHp(){
        return minion.getHp();
    }

    public int getPlayerHp(){
        return player.getHp();
    }

    public void skill1(){
        int enemyHp = minion.getHp();

        switch(player.getCharacterType()){
            case "knight":
                minion.setHp(enemyHp - 10);
            break;
        }
    }

    public void skill4(){
        int enemyHp = minion.getHp();
        switch(player.getCharacterType()){
            case "knight":
                minion.setHp(enemyHp - 40);
            break;
        }
    }

    public boolean getIsBattleStopped(){
        return battleStopped;
    }

}
