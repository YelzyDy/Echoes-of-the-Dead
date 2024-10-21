package EOD.characters;

import EOD.scenes.SceneBuilder;

public class Necromancer extends Enemy {
    private int attack = 20;
    private int health = 200;

    public int getHp() {
        return health;
    }

    public int getAttack() {
        return attack;
    }
    
    public Necromancer(String name, SceneBuilder panel, int posX, int posY, 
                    double minRange, double maxRange, int numIdleSprites, 
                    int numWalkSprites, Protagonist protagonist) {
        super(name, "necromancer", panel, posX, posY, minRange, maxRange, 
              numIdleSprites, numWalkSprites, protagonist);
              configureSprites();
            

    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), numWalkSprites);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), numIdleSprites);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.007), 52);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }
    
    @Override
    protected void onBattleStart() {
        panel.configureBattle(this);
        animator.performSpecialAttack();
    }
}