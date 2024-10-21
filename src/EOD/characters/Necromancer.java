package EOD.characters;

import EOD.scenes.SceneBuilder;

public class Necromancer extends Enemy {
    public Necromancer(String name, SceneBuilder panel, int posX, int posY, 
                    double minRange, double maxRange, int numIdleSprites, 
                    int numWalkSprites, Protagonist protagonist) {
        super(name, "necromancer", panel, posX, posY, minRange, maxRange, 
              numIdleSprites, numWalkSprites, protagonist);
    }

    @Override
    protected void onBattleStart() {
        // Implement MiniBoss specific battle start logic here
        // For example:
        // panel.configureMiniBossBattle(this);
        animator.performSpecialAttack();
    }
}