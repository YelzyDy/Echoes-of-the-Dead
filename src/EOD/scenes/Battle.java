package EOD.scenes;

import EOD.characters.MiniBoss;
import EOD.characters.Minions;
import EOD.characters.Protagonist;
import java.util.Random;
import java.util.Scanner;


// Used to interact with other "Entity/s"
public class Battle{
    private Scanner scan = new Scanner(System.in);
    private Random random = new Random();
    public boolean battleOver = false;
    
    //player attributes
    private int playerHp;
    private int playerBaseHp;
    private int playerMana;
    private int playerBaseMana;
    private int skill2Cd = 0;
    private int skill3Cd = 0;
    private int playerAttack;
    private int playerMoney;
    private Protagonist player;
    private boolean skillIsUseable = true;

    //knight
    private double playerDamageReducer = 0;

    //priestess
    private int playerLostHp = 0;

    //enemy attributes
    private int enemyHp;
    private int enemyBaseHp = enemyHp;
    private int enemyAttack;
    private MiniBoss miniboss;
    private Minions minion;
    private int enemyMissTurn = 0;
    private int moneyDrop;

    //if the enemy is a miniboss
    public Battle(Protagonist player, MiniBoss miniboss){
        this.player = player;
        this.miniboss = miniboss;
        enemyHp = miniboss.getHp();
        enemyAttack = miniboss.getAttack();
        moneyDrop = miniboss.getMoneyDrop();
        playerHp = player.getHp();
        playerBaseHp = player.getBaseHp();
        playerAttack = player.getAttack();
        playerMana = player.getMana();
        playerBaseMana = player.getBaseMana();
        playerMoney = player.getMoney();
    }

    //if the enemy is a minion
    public Battle(Protagonist player, Minions minion){
        this.player = player;
        this.minion = minion;
        enemyHp = minion.getHp();
        enemyAttack = minion.getAttack();
        moneyDrop = minion.getMoneyDrop();
        playerHp = player.getHp();
        playerBaseHp = player.getBaseHp();
        playerAttack = player.getAttack();
        playerMana = player.getMana();
        playerBaseMana = player.getBaseMana();
        playerMoney = player.getMoney();
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
                            if(playerMoney >= 15){
                                player.skill1();
                                playerAttack += 15;
                                playerMoney -= 15;
                                skillIsUseable = false;
                            }else{
                                System.out.println("Not enough money!");
                            }
                        }else{
                            System.out.println("Can only be used once per battle!");
                        }
                        break;
                    case 2:
                        if(skill2Cd==0){
                            if(playerMana >= 30){
                                playerDamageReducer = player.skill2();
                                playerMana -= 30;
                                skill2Cd = 2;
                            }else{
                                System.out.println("Not enough mana!");
                            }
                        }else{
                            System.out.println("Can't use it yet!");
                        }
                        break;
                    case 3:
                        if(skill3Cd==0){
                            if(playerMana >= 50){
                                enemyHp -= (playerAttack * 2) + (int)(playerMoney * player.skill3());
                                enemyMissTurn++;
                                playerMana -= 50;
                                playerMoney -= 10;
                                skill3Cd = 3;
                            }else{
                                System.out.println("Not enough mana!");
                            }
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
                            if(playerMana >= 10){
                                player.skill1();
                                playerAttack += 15;
                                playerMana -= 10;
                                skillIsUseable = false;
                            }else{
                                System.out.println("Not enough mana!");
                            }
                        }else{
                            System.out.println("Can only be used once per battle");
                        }
                        skillIsUseable = false;
                        break;
                    case 2:
                        if(skill2Cd==0){
                            if(playerMana >= 30){
                                playerMana -= 30;
                                skill2Cd = 2;
                                if(random.nextInt(100) < 60){
                                    enemyHp -= (int)player.skill2();
                                    playerMana += 80;
                                    enemyMissTurn++;
                                }else{
                                    System.out.println("Shift Failed!");
                                }
                            }else{
                                System.out.println("Not enough mana!");
                            }
                        }else{
                            System.out.println("Can't use it yet!");
                        }
                        break;
                    case 3:
                        if(skill3Cd==0){
                            if(playerMana >= 50){
                                enemyHp -= 60 + (int)((player.skill3())*playerBaseMana);
                                playerMana -= 50;
                                skill3Cd = 3;
                            }else{
                                System.out.println("Not enough mana!");
                            }
                        }else{
                            System.out.println("Can't use it yet!");
                        }
                        break;
                    default:
                        enemyHp -= playerAttack;
                }
                break;
            case "priest":
                switch(choose){
                    case 1:
                        if(skillIsUseable){
                            if(playerHp >= 40){
                                player.skill1();
                                playerAttack += 15;
                                playerHp -= 10;
                                skillIsUseable = false;
                            }else{
                                System.out.println("Soul Energy is low!");
                            }
                        }else{
                            System.out.println("Can only be used once per battle");
                        }
                        break;
                    case 2:
                        if(skill2Cd==0){
                            if(playerMana >= 50){
                                int healDamage = (int)(playerBaseHp * player.skill2());
                                playerHp += healDamage;
                                enemyHp -= healDamage;
                                playerMana -= 50;
                                skill2Cd = 3;
                            }else{
                                System.out.println("Not enough mana!");
                            }
                        }else{
                            System.out.println("Can't use it yet!");
                        }
                        break;
                    case 3:
                        if(skill3Cd==0){
                            if(playerMana >= 70){
                                enemyHp -= (int)((playerBaseHp - playerHp) * player.skill3());
                                playerHp += (int)(playerBaseHp * 0.4);
                                playerMana -= 70;
                                skill3Cd = 5;
                            }else{
                                System.out.println("Not enough mana!");
                            }
                        }else{
                            System.out.println("Can't use it yet!");
                        }
                        break;
                    default:
                        enemyHp -= playerAttack;
                }    
                break;
        }
    }

    public void start(){
        boolean firstTurn = true;
        while(playerHp > 0 && enemyHp > 0){
            if(skill2Cd!=0){
                skill2Cd--;
            }
            if(skill3Cd!=0){
                skill3Cd--;
            }
            
            if(!firstTurn){
                playerMana += 15;
            }else{
                if(player.getCharacterType() == "knight"){
                    playerMoney += 15;
                }
            }
            
            System.out.println("Player Hp: " + playerHp);
            System.out.println("Mana: " + playerMana);
            System.out.println("Enemy Hp: " + enemyHp);

            skillChoosePlayer();

            if(playerHp > playerBaseHp){
                playerHp = playerBaseHp;
            }

           if(playerMana > playerBaseMana){
                playerMana = playerBaseMana;
            }

            if(enemyHp <= 0){
                break;
            }

            //for knight skill3 and wizard skill2
           if(enemyMissTurn == 1){
                System.out.println("Enemy missed a turn!");
                enemyMissTurn--;
                continue;
           }

           int damage = skillChooseEnemy();
           int damageCalculated;
           //for knight during the turn of using skill2

           if(playerDamageReducer!=0){
                damageCalculated = (int)(damage * playerDamageReducer);
                playerHp -= damageCalculated;
                if((int)(playerHp * 0.2) < damageCalculated){
                    System.out.println("Effect activated! Gain 15 Soul Shards");
                    playerMoney += 15;
                }
                playerDamageReducer = 0;
           }else{
                playerHp -= damage;
           }
        }

        if(playerHp > 0){
            System.out.println("You defeated the enemy!");
            player.setHp(playerHp);
            player.setMana(playerMana);
            player.setMoney(moneyDrop);
        }else{
            System.out.println("You are defeated!");
            System.out.println(playerHp);
        }

        battleOver = true;
    }
}