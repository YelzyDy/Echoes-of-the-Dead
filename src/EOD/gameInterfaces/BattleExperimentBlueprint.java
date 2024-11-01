package EOD.gameInterfaces;

import EOD.characters.Player;
import EOD.characters.enemies.Enemy;
import EOD.scenes.BattleUI;

public interface BattleExperimentBlueprint extends Skills{
    public void setEnemy(Enemy enemy);

    public void setPlayer(Player player);

    public Enemy getEnemy();

    public Player getPlayer();

    public void setBattleUI(BattleUI battle);

}
