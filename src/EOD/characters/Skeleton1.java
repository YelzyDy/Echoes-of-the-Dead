package EOD.characters;

import EOD.scenes.SceneBuilder;
import EOD.objects.EchoesObjects;
public class Skeleton1 extends Enemy {
    public Skeleton1(String name, SceneBuilder panel, int posX, int posY, 
            double minRange, double maxRange, Protagonist protagonist) {
            super(name, "skeleton1", panel, posX, posY, minRange, maxRange, 
           protagonist);
        configureSprites();
        attack = 10;
        health = 150;
        turnDuration = 3000;
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), 8);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), 7);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.007), 3);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.006), 7);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.006), 7);
        animator.scaleDownSprites("walk", 0.3);
        animator.scaleDownSprites("idle", 0.3);
        animator.scaleDownSprites("dead", 0.3);
        animator.scaleDownSprites("skill1", 0.3);
        animator.scaleDownSprites("skill2", 0.3);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }

    @Override
    public void skill1(){
        damageDealt = attack;
    }

    @Override
    public void skill2(){
        damageDealt = 15;
        attack += 5;
    }

    @Override
    protected void onBattleStart() {
        EchoesObjects portal = panel.objList.get(1);
        panel.configureBattle(this, portal);
    }
}