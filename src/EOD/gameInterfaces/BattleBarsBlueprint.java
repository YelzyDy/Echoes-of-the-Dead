package EOD.gameInterfaces;

import EOD.objects.bars.*;

public interface BattleBarsBlueprint {

    public PlayerBar getPlayerStats();

    public EnemyBar getEnemyStats();

    public void setPlayerHealth(int playerHealth);

    public void setPlayerMana(int playerMana);

    public void setEnemyHealth(int enemyHealth);

    public void setPlayerStats(int playerHP, int playerMP);

    public void setEnemyStats(int enemyHP);
    
    public void animatePlayerHealth(int targetHealth);

    public void animatePlayerMana(int targetMana);

    public void animateEnemyHealth(int targetHealth);
}
