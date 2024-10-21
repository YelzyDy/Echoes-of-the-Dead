package EOD.characters;

import EOD.scenes.SceneBuilder;

public class Skeleton extends Enemy {
    public Skeleton(String name, SceneBuilder panel, int posX, int posY, 
                   double minRange, double maxRange, int numIdleSprites, 
                   int numWalkSprites, Protagonist protagonist) {
        super(name, "skeleton", panel, posX, posY, minRange, maxRange, 
              numIdleSprites, numWalkSprites, protagonist);
    }

    @Override
    protected void onBattleStart() {
        panel.configureBattle(this);
    }
}