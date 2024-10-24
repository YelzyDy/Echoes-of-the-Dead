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
            turnDuration = 3500;
            animator.setSpeedMultiplier(2);
    }


    @Override 
    public double getOffsetX(int skill){
        if(skill != 4){
            if(protagonist.isWizard() && skill == 3){
                return 0.3;
            }else{
                return 0.22;
            }
        }else{
            return 0.3;
        }
    }

    @Override 
    public double getOffsetY(int skill){
        if(skill != 4){
            if(protagonist.isKnight()){
                return 0.3;
            }else{
                return 0.0;
            }
        }else{
            if(protagonist.isKnight()){
                return 0.15;
            }else{
                return 0.0;
            }
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
                return 0.3;
            }else{
                return 0.17;
            }
        }else{
            if(!protagonist.isPriest()){
                return 0.3;
            }else{
                return 0.28;
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
                return 0.3;
            }else{
                return 0.17;
            }
        }else{
            if(!protagonist.isPriest()){
                return 0.3;
            }else{
                return 0.28;
            }
        }
    }

    @Override
    public double getXFactor(){
        return screenSize.width * 0.4;
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