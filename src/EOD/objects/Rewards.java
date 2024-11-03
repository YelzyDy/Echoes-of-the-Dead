package EOD.objects;
import EOD.objects.profiles.AllyProfiles;
import EOD.scenes.BattleExperiment;
public class Rewards {
    private String playerType;
    private String enemyType;
    private AllyProfiles allyProfiles;
    public Rewards(BattleExperiment battle){
        this.playerType = battle.getPlayer().getWorld().getPlayer().getCharacterType();
        this.enemyType = battle.getEnemy().getCharacterType();
        this.allyProfiles = battle.getPlayer().getWorld().getPlayer().getAllyProfiles();
    }   

    public void getAllyRewards(){
        if(playerType.equals("knight")){
            if(enemyType.equals("necromancer")){
                allyProfiles.showPriestProfile();
            }else if(enemyType.equals("death")){
                allyProfiles.showWizardProfile();
            }
        }else if(playerType.equals("priest")){
            if(enemyType.equals("necromancer")){
                allyProfiles.showKnightProfile();
            }else if(enemyType.equals("death")){
                allyProfiles.showWizardProfile();
            }
        }else{
            if(enemyType.equals("necromancer")){
                allyProfiles.showPriestProfile();
            }else if(enemyType.equals("death")){
                allyProfiles.showKnightProfile();
            }
        }
    }
}
