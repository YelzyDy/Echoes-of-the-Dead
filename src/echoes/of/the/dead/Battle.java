package echoes.of.the.dead;

import java.util.Scanner;


// Used to interact with other "Entity/s"
public class Battle{
    private Scanner scan = new Scanner(System.in);

    private boolean skillIsUseable = true;
    //player attributes
    private int playerHp;
    private int playerExtraHp;
    private double playerDamageReducer;
    private int playerMana;
    private int playerAttack;
    private int playerMoney;
    private Protagonist player;
    public boolean battleOver = false;
    //enemy attributes
    private int enemyHp;
    private MiniBoss miniboss;
    private Minions minion;

    //if the enemy is a miniboss
    public Battle(Protagonist player, MiniBoss miniboss){
        this.player = player;
        this.miniboss = miniboss;
        enemyHp = miniboss.getHp();
        playerHp = player.getHp();
        playerAttack = player.getAttack();
        playerMana = player.getMana();
        playerMoney = player.getMoney();

    }

    //if the enemy is a minion (to be implemented soon)
    // public Battle(Protagonist player, Minions minion){
    //     this.player = player;
    //     this.minion = minion;
    //     enemyHp = minion.getHp();
    //     playerHp = player.getHp();
    // }

    public void skillChoosePlayer(){
        System.out.print("Choose skill: ");
        int choose = scan.nextInt();
        switch(player.getCharacterType()){
            case "knight":
                switch(choose){
                    case 1:
                        if(skillIsUseable){
                            player.skill1();
                            playerAttack += 15;
                        }else{
                            System.out.println("Can only be used once per battle");
                        }
                        skillIsUseable = false;
                        break;
                    case 2:
                        playerDamageReducer = player.skill2();
                        break;
                    case 3:
                        player.skill3();
                        enemyHp -= 100;
                    default:
                }
                break;
            case "wizard":
                switch(choose){
                    case 1:
                        if(skillIsUseable){
                            player.skill1();
                            playerAttack += 15;
                        }else{
                            System.out.println("Can only be used once per battle");
                        }
                        skillIsUseable = false;
                        break;
                    case 2:
                        player.skill2();
                        break;
                    case 3:
                        player.skill3();
                    default:
                }
                break;
            case "priest":
                switch(choose){
                    case 1:
                        if(skillIsUseable){
                            player.skill1();
                            playerAttack += 15;
                        }else{
                            System.out.println("Can only be used once per battle");
                        }
                        skillIsUseable = false;
                        break;
                    case 2:
                        player.skill2();
                        break;
                    case 3:
                        player.skill3();
                    default:
                }    
                break;
        }
        
    }

    public void start(){
        while(playerHp > 0 && enemyHp > 0){
           skillChoosePlayer();
        }
        System.out.println("You defeated the enemy!");
        if(playerHp>0){
            player.setHp(playerHp);
        }
        battleOver = true;
    }
}