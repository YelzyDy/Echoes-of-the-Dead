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

    public void skill1(){
        int enemyHp = minion.getHp();
        if(enemyHp <= 0){
            battleStopped = true;
            player.revertPosition();
            minion.revertPosition();      
            animator.setIsInBattle(false);
            return;
        }

        switch(player.getCharacterType()){
            case "knight":
                System.out.println(enemyHp);
                minion.setHp(enemyHp - 10);
            break;
        }
    }

    public void skill4(){
        int enemyHp = minion.getHp();
        if(enemyHp <= 0){
            battleStopped = true;
            player.revertPosition();
            minion.revertPosition();      
            animator.setIsInBattle(false);
            return;
        }

        switch(player.getCharacterType()){
            case "knight":
                System.out.println(enemyHp);
                minion.setHp(enemyHp - 50);
            break;
        }
    }

    public boolean getIsBattleStopped(){
        return battleStopped;
    }

}
