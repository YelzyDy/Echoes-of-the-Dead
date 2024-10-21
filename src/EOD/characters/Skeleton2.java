package EOD.characters;

import EOD.scenes.SceneBuilder;

public class Skeleton2 extends Enemy {
    public Skeleton2(String name, SceneBuilder panel, int posX, int posY, 
            double minRange, double maxRange, int numIdleSprites, 
            int numWalkSprites, Protagonist protagonist) {
            super(name, "skeleton", panel, posX, posY, minRange, maxRange, 
            numIdleSprites, numWalkSprites, protagonist);
        configureSprites();
        health = 100;
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), numWalkSprites);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), numIdleSprites);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.006), 4);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }

    @Override
    protected void onBattleStart() {
        // panel.configureBattle(this,);
    }
}