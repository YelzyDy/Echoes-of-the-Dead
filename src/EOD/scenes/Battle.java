package EOD.scenes;

import java.util.Scanner;
import java.util.Random;

import EOD.characters.MiniBoss;
import EOD.characters.Minions;
import EOD.characters.Protagonist;


// Used to interact with other "Entity/s"
public class Battle{
    private Scanner scan = new Scanner(System.in);
    private Random random = new Random();
    public boolean battleOver = false;
    
    //player attributes
    private int playerHp;
    private int playerMana;
    private int skill2Cd = 0;
    private int skill3Cd = 0;
    private int playerAttack;
    private int playerMoney;
    private Protagonist player;
    private boolean skillIsUseable = true;
    //knight
    private double playerDamageReducer;
    //wizard
    private int chance = 1000;
    //priestess
    private int playerExtraHp = 0;
    private int playerLostHp = 0;

    //enemy attributes
    private int enemyHp;
    private MiniBoss miniboss;
    private Minions minion;
    private int enemyMissTurn = 0;

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
    public Battle(Protagonist player, Minions minion){
        this.player = player;
        this.minion = minion;
        enemyHp = minion.getHp();
        playerHp = player.getHp();
    }

    //testing for enemy attacking
    public int skillChooseEnemy(){
        System.out.println("Enemy attacks!");
        return 30;
    }

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
                            skillIsUseable = false;
                        }else{
                            System.out.println("Can only be used once per battle");
                        }
                        break;
                    case 2:
                        if(skill2Cd==0){
                            playerDamageReducer = player.skill2();
                            skill2Cd = 2;
                        }else{
                            System.out.println("Can't use it yet!");
                        }
                        break;
                    case 3:
                        if(skill3Cd==0){
                            enemyHp -= (playerAttack * 2) + (int)(playerMoney * player.skill3());
                            
                            enemyMissTurn++;
                            skill3Cd = 3;
                        }else{
                            System.out.println("Can't use it yet!");
                        }
                        break;
                    default:
                        enemyHp -= playerAttack;
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
                        if(skill2Cd==0){
                            chance = random.nextInt(100);
                            if(chance < 60){
                                enemyHp -= 40;
                                playerMana += 50;
                            }
                            skill2Cd = 2;
                        }
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
                            skillIsUseable = false;
                        }else{
                            System.out.println("Can only be used once per battle");
                        }
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
            if(skill2Cd!=0){
                skill2Cd--;
            }
            if(skill3Cd!=0){
                skill3Cd--;
            }
            
           System.out.println("Player Hp: " + playerHp);
           System.out.println("Enemy Hp: " + enemyHp);
           skillChoosePlayer();

           if((chance != 1000 && chance < 60) || enemyMissTurn == 1){
                System.out.println("Enemy miss a turn!");
                chance = 1000;
                enemyMissTurn--;
                continue;
           }

           int damage = skillChooseEnemy();

           if(playerDamageReducer!=0){
                playerHp -= damage * playerDamageReducer;
                playerDamageReducer = 0;
           }else{
                playerHp -= damage;
           }
        }
        
        if(playerHp>0){
            System.out.println("You defeated the enemy!");
            player.setHp(playerHp);
        }else{
            System.out.println("You are defeated!");
        }
        battleOver = true;
    }
}