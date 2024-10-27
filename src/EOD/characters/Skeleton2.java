package EOD.characters;
public class Skeleton2 extends Enemy {
    private static final int BASE_ATTACK = 8;
    private static final int BASE_HEALTH = 100;
    private static final double SPRITE_SCALE = 0.3; // Keep the smaller scale for minion appearance
    
    // Skill cooldowns
    private int skill2Cooldown = 0;
    private static final int SKILL2_MAX_COOLDOWN = 3;
    public Skeleton2(String name, int posX, int posY, 
            double minRange, double maxRange, int numIdleSprites, 
            int numWalkSprites, Protagonist protagonist) {
            super(name, "skeleton", posX, posY, minRange, maxRange, 
            protagonist);
        configureSprites();
        health = 100;
    }

    @Override
    public double getXFactor(){
        return screenSize.width * 0.4;
    }
    
    
    @Override 
    public void skill1() {
        // Basic attack - consistent but low damage
        damageDealt = attack + (int)(Math.random() * 3); // 8-10 damage
        actionString = getName() + " swings its rusty sword for " + damageDealt + " damage!";
        lastUsedSkill = 1;
    }

    @Override 
    public void skill2() {
        // Special attack - bone throw with damage variation
        if (skill2Cooldown > 0) {
            // Fallback to basic attack if on cooldown
            skill1();
            return;
        }

        // Slightly higher damage than basic attack but unreliable
        int baseSkill2Damage = (int)(attack * 1.5);
        damageDealt = baseSkill2Damage + (int)(Math.random() * 4) - 2; // 10-14 damage with variation
        
        actionString = getName() + " throws a bone for " + damageDealt + " damage!";
        lastUsedSkill = 2;
        skill2Cooldown = SKILL2_MAX_COOLDOWN;
    }


    @Override
    public void update() {
        if (skill2Cooldown > 0) {
            skill2Cooldown--;
        }
    }

    // Decision making for skill usage
    @Override
    public int decideSkill() {
        // 70% chance to use basic attack
        // 30% chance to try skill2 (will fall back to basic if on cooldown)
        return Math.random() < 0.7 ? 1 : 2;
    }

     @Override 
    public double getOffsetX(int skill){
        if(skill != 4){
            if(protagonist.isWizard() && skill == 3){
                return 0.1;
            }else{
                return 0.25;
            }
        }else{
            return 0.35;
        }
    }

    @Override 
    public double getOffsetY(int skill){
        if(skill != 4){
            return 0.30;
        }else{
            return 0.3;
        }
    }

    @Override 
    public double getOffsetW(int skill){
        if(skill == 2){
            if(!protagonist.isPriest()){
                return 0.15;
            }else{
                return 0.25;
            }
        }else if(skill == 3){
            if(protagonist.isKnight()){
                return 0.15;
            }else if(protagonist.isWizard()){
                return 0.2;
            }else{
                return 0.17;
            }
        }else{
            if(!protagonist.isPriest()){
                return 0.2;
            }else{
                return 0.15;
            }
        }
    }

    @Override 
    public double getOffsetH(int skill){
        if(skill == 2){
            if(!protagonist.isPriest()){
                return 0.15;
            }else{
                return 0.25;
            }
        }else if(skill == 3){
            if(protagonist.isKnight()){
                return 0.15;
            }else if(protagonist.isWizard()){
                return 0.2;
            }else{
                return 0.17;
            }
        }else{
            if(!protagonist.isPriest()){
                return 0.2;
            }else{
                return 0.15;
            }
        }
    }


    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), 8);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), 6);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.007), 4);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }

    @Override
    protected void onBattleStart() {
        // panel.configureBattle(this,);
    }
}