package EOD.characters;

import EOD.objects.SkillEffects;

public class ProtagonistAttributes {
    protected int attack;
    protected int health;
    protected int baseHealth;
    protected int mana;
    protected int baseMana;
    protected int money;
    protected int s1num;
    protected int s2num;
    protected int s3num;
    protected int s4num;
    protected int turnDuration;
    public SkillEffects skillEffects1;
    public SkillEffects skillEffects2;
    public SkillEffects skillEffects3;
    public SkillEffects skillEffects4;
    protected Protagonist player;
    protected int skill3Cd;
    protected int skill4Cd;

    public ProtagonistAttributes(Protagonist player) {
        this.player = player;
        configure(player.getCharacterType());
    }

   public void configure(String characterType){
        //buffs depending on characterType
        switch(characterType){
            case "knight": 
                attack = 20;
                health = 150;
                baseHealth = health;
                mana = 100;
                baseMana = mana;
                money = 40;
                s1num = 7;
                s2num = 4;
                s3num = 4;
                s4num = 11;
                turnDuration = 3000;
                skillEffects2 = new SkillEffects(
                    "effects",                           
                    (int)(player.screenSize.width * 0.4),             
                    (int)(player.screenSize.height * 0.2),      
                        (int)(player.screenSize.width * 0.15),      
                    (int)(player.screenSize.width * 0.15),      
                    "kbuff",                           
                    15,                                                           
                    player.panel                               
                );
                skillEffects2.setLooping(false);  // Make it loop while active

                skillEffects3 = new SkillEffects(
                    "effects",                           
                    (int)(player.getPosX() * 0.9),             
                    (int)(player.screenSize.width * 0.08),      
                        (int)(player.screenSize.width * 0.15),      
                    (int)(player.screenSize.width * 0.15),      
                    "shield",                           
                    13,                                                           
                    player.panel                               
                );
                skillEffects3.setLooping(true);  // Make it loop while active
                break;
            case "wizard":
                attack = 20;
                health = 150;
                baseHealth = health;
                mana = 130;
                baseMana = mana;
                money = 0;
                s1num = 6;
                s2num = 6;
                s3num = 6;
                s4num = 6;
                turnDuration = 3000;
                skillEffects2 = new SkillEffects(
                    "effects",                           
                    (int)(player.getPosX() * 0.9),             
                    (int)(player.screenSize.width * 0.08),      
                        (int)(player.screenSize.width * 0.15),      
                    (int)(player.screenSize.width * 0.15),      
                    "wbuff",                           
                    14,                                                           
                    player.panel                               
                );
                skillEffects2.setLooping(false);  // Make it loop while active

                skillEffects3 = new SkillEffects(
                    "effects",                           
                    (int)(player.getPosX() * 0.9),             
                    (int)(player.screenSize.width * 0.08),      
                        (int)(player.screenSize.width * 0.1),      
                    (int)(player.screenSize.width * 0.1),      
                    "zawardo",                           
                    14,                                                                  
                    player.panel                               
                );
                skillEffects3.setLooping(false);  // Make it loop while active

                skillEffects4 = new SkillEffects(
                    "effects",                           
                    (int)(player.getPosX() * 0.9),             
                    (int)(player.screenSize.width * 0.08),      
                        (int)(player.screenSize.width * 0.2),      
                    (int)(player.screenSize.width * 0.2),      
                    "explosion",                           
                    12,                                                                  
                    player.panel                               
                );
                skillEffects4.setLooping(false);  // Make it loop while active
                break;
            case "priest":
                attack = 20;
                health = 180; 
                baseHealth = health;
                mana = 100;
                baseMana = mana;
                attack = 20;
                money = 0;
                s1num = 9;
                s2num = 9;
                s3num = 6;
                s4num = 9;
                turnDuration = 3000;
                
                break;
        }
    }

    //get hp for battle sequence
    public int getHp(){
        return health;
    }

    //set hp after battle sequence
    public void setHp(int newHealth){
        health = newHealth;
    }

    //get base hp for battle sequence
    public int getBaseHp(){
        return baseHealth;
    }

    //set basehp after getting potion or item
    public void setBaseHp(int addHp){
        baseHealth += addHp;
    }

    //get mana for battle sequence
    public int getMana(){
        return mana;
    }

    //set mana after battle sequence
    public void setMana(int newMana){
        mana = newMana;
    }

    //get basemana for battle sequence
    public int getBaseMana(){
        return baseMana;
    }

    //set basemana after getting potion or item
    public void setBaseMana(int addMana){
        baseMana += addMana;
    }

    //get money for battle sequence
    public int getMoney(){
        return money;
    }

    //set hp after battle sequence
    public void setMoney(int newMoney){
        money += newMoney;
    }

    //get atk for battle sequence
    public int getAttack(){
        return attack;
    }
}
