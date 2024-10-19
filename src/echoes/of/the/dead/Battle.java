package echoes.of.the.dead;



// Used to interact with other "Entity/s"
public class Battle{
    private int enemyHp;
    private int playerHp;
    private Protagonist player;
    private MiniBoss miniboss;
    private Minions minion;
    public Battle(Protagonist player, MiniBoss miniboss){
        this.player = player;
        this.miniboss = miniboss;
        enemyHp = miniboss.getHp();
        playerHp = player.getHp();
    }

    public void damageEnemy(){
        enemyHp -= player.skill1();
    }

    public void start(){
        while(playerHp > 0 && enemyHp > 0){
            damageEnemy();
        }
        System.out.println("You defeated the enemy!");
    }
}