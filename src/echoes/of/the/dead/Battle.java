package echoes.of.the.dead;



// Used to interact with other "Entity/s"
public class Battle{
    private int enemyHp;
    private int playerHp;
    private Protagonist player;
    private MiniBoss enemy;
    public Battle(Protagonist player, MiniBoss enemy){
        this.player = player;
        this.enemy = enemy;
        enemyHp = enemy.getHp();
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