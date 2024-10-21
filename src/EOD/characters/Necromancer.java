package EOD.characters;

import EOD.objects.EchoesObjects;
import EOD.scenes.SceneBuilder;

public class Necromancer extends Enemy {    
    public Necromancer(String name, SceneBuilder panel, int posX, int posY, 
            double minRange, double maxRange, int numIdleSprites, 
            int numWalkSprites, Protagonist protagonist) {
            super(name, "necromancer", panel, posX, posY, minRange, maxRange, 
            numIdleSprites, numWalkSprites, protagonist);
            configureSprites();
            health = 200;
            attack = 10;

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
        EchoesObjects portal = panel.objList.get(2);
        panel.configureBattle(this, portal);
    }
}