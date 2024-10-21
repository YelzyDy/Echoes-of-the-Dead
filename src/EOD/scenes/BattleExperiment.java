package EOD.scenes;

import EOD.characters.MiniBoss;
import EOD.characters.Minions;
import EOD.characters.Protagonist;
import EOD.animator.Animator;
public class BattleExperiment {
    private MiniBoss miniboss;
    private Minions minion;
    private Protagonist player;
    private Animator animator;
    private boolean battleStopped;
    public BattleExperiment(Protagonist player, Minions minion){
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
