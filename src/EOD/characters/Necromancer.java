package EOD.characters;

import EOD.objects.EchoesObjects;
import EOD.scenes.SceneBuilder;

public class Necromancer extends Enemy {    
    public Necromancer(String name, SceneBuilder panel, int posX, int posY, 
            double minRange, double maxRange, Protagonist protagonist) {
            super(name, "necromancer", panel, posX, posY, minRange, maxRange, protagonist);
            configureSprites();
            health = 200;
            attack = 10;
            turnDuration = 3000;
    }
    @Override
    public double getXFactor(){
        return screenSize.width * 0.5;
    }

    @Override
    public void skill1(){
        damageDealt = 10;
    }

    @Override
    public void skill2(){
        damageDealt = 10; //this is just a sample... 
    }
    
    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), 10);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), 50);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.007), 52);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.007), 47);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.007), 47);
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