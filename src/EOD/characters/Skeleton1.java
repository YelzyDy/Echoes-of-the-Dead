package EOD.characters;

import EOD.scenes.SceneBuilder;
import EOD.objects.EchoesObjects;
public class Skeleton1 extends Enemy {
    public Skeleton1(String name, SceneBuilder panel, int posX, int posY, 
            double minRange, double maxRange, int numIdleSprites, 
            int numWalkSprites, Protagonist protagonist) {
            super(name, "skeleton1", panel, posX, posY, minRange, maxRange, 
            numIdleSprites, numWalkSprites, protagonist);
        configureSprites();
        attack = 10;
        health = 100;
        turnDuration = 3000;
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), numWalkSprites);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), numIdleSprites);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.007), 3);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.006), 7);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.006), 7);
        animator.scaleDownSprites("walk", 0.3);
        animator.scaleDownSprites("idle", 0.3);
        animator.scaleDownSprites("dead", 0.3);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }

    @Override
    public void skill1(){
        damageDealt = 10;
        System.out.println("SKill1 " + damageDealt);
    }

    @Override
    public void skill2(){
        damageDealt = 10;
        System.out.println("SKill2 " + damageDealt);
    }

    @Override
    protected void onBattleStart() {
        EchoesObjects portal = panel.objList.get(1);
        panel.configureBattle(this, portal);
    }
}