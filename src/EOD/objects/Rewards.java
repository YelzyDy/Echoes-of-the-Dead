package EOD.objects;

import EOD.characters.Npc;
import EOD.gameInterfaces.Freeable;
import EOD.objects.chests.Chest;
import EOD.objects.chests.MiniBossChest;
import EOD.objects.chests.MinionChest;
import EOD.objects.chests.QuestsChest;
import EOD.objects.profiles.AllyProfiles;
import EOD.scenes.BattleExperiment;
import EOD.scenes.SceneBuilder;
import EOD.worlds.World;

public class Rewards implements Freeable{
    private static final String KNIGHT = "knight";
    private static final String PRIEST = "priest";
    private static final String WIZARD = "wizard";
    private static final String NECROMANCER = "necromancer";
    private static final String DEATH = "death";
    private String playerType;
    private String enemyType;
    private AllyProfiles allyProfiles;
    private SceneBuilder panel;
    private World world;
    private Chest questTreasure;
    private Chest minionTreasure;
    private Chest minibossTreasure;

    public Rewards(World world) {
        this.world = world;
        this.playerType = world.getPlayer().getCharacterType();
        this.allyProfiles = world.getPlayer().getAllyProfiles();
        this.panel = world.getScene();
        questTreasure = new QuestsChest();
        questTreasure.setWorld(world);
        minionTreasure = new MinionChest();
        minionTreasure.setWorld(world);
        minibossTreasure = new MiniBossChest();
        minibossTreasure.setWorld(world);
        world.getScene().add(questTreasure);
        world.getScene().add(minionTreasure);
        world.getScene().add(minibossTreasure);
    }

    public void setBattle(BattleExperiment battle){
        this.enemyType = battle.getEnemy().getCharacterType();
    }

    @Override
    public void free(){
        if(allyProfiles != null){
            allyProfiles.free();
            allyProfiles = null;
        }
    }

    public void getQuestsRewards(int goldValue, double x, double y){
        questTreasure.setGold(goldValue);
        questTreasure.setLocation((int)x, (int) y);
    }

    public void getEnemyRewards(String enemyType, int goldValue, double x, double y){
        switch (enemyType) {
            case "minions":
                minionTreasure.setGold(goldValue);
                minionTreasure.setLocation((int)x, (int)y);
                break;
            case "miniboss":
                minibossTreasure.setGold(goldValue);
                minibossTreasure.setLocation((int)x, (int)y);
            break;
            default:
                break;
        }

    }

    public Chest getQuestChest(){
        return questTreasure;
    }

    public Chest getMinionsChest(){
        return minionTreasure;
    }

    public Chest getMiniBossChest(){
        return minibossTreasure;
    }


    public void getAllyRewards() {
        if(!isMiniBoss()) return;
        switch (playerType) {
            case KNIGHT -> handleKnightAllyAdding();
            case PRIEST -> handlePriestAllyAdding();
            case WIZARD -> handleWizardAllyAdding();
            default -> System.out.println("Unknown player type: " + playerType);
        }
    }

    private void addPriestToScene(){
        panel.ally = new Npc("Priest", "priest", (int)(panel.getWidth() * 0.8), (int)(panel.getHeight() * 0.6), (int)(panel.getWidth() * 0.6), (int)(panel.getWidth() * 0.6));
        handleAddingofAlly();
    }

    private void addWizardToScene(){
        panel.ally = new Npc("Wizard", "wizard", (int)(panel.getWidth() * 0.8), (int)(panel.getHeight() * 0.6), (int)(panel.getWidth() * 0.6), (int)(panel.getWidth() * 0.6));
        handleAddingofAlly();
    }

    private void addKnightToScene(){
        panel.ally = new Npc("Knight", "knight", (int)(panel.getWidth() * 0.8), (int)(panel.getHeight() * 0.6), (int)(panel.getWidth() * 0.6), (int)(panel.getWidth() * 0.6));
        handleAddingofAlly();
    }
    
    private boolean isMiniBoss(){
        return (enemyType.equals(DEATH) || enemyType.equals(NECROMANCER));
    }

    private void handleKnightAllyAdding() {
        if (NECROMANCER.equals(enemyType)) {
            addPriestToScene();
        } else if (DEATH.equals(enemyType)) {   
            addWizardToScene();
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
        handleAddingofAlly();
    }

    private void handlePriestAllyAdding() {
        if (NECROMANCER.equals(enemyType)) {
            addKnightToScene();
        } else if (DEATH.equals(enemyType)) {
            addWizardToScene();
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
        handleAddingofAlly();
    }
  
    private void handleWizardAllyAdding() {
        if (NECROMANCER.equals(enemyType)) {
            addPriestToScene();
        } else if (DEATH.equals(enemyType)) {
            addKnightToScene();
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
        handleAddingofAlly();
    }

    private void handleAddingofAlly(){
        panel.ally.setVisible(true);
        panel.ally.getAnimator().setMovingRight(false);
        panel.ally.getAnimator().stopMovement();
        panel.ally.setStatic(true);
        panel.ally.setWorld(world);
        panel.ally.setIndex(panel.ally.getWorld().getBattle().getPortal().getIndex());
        panel.add(panel.ally);
    }

    private void handleKnightRewards() {
        if (NECROMANCER.equals(enemyType)) {
            allyProfiles.setPriestProfileVisible();
            allyProfiles.addAlly(PRIEST);
        } else if (DEATH.equals(enemyType)) {
            allyProfiles.setWizardProfileVisible();
            allyProfiles.addAlly(WIZARD);
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
    }

    private void handlePriestRewards() {
        if (NECROMANCER.equals(enemyType)) {
            allyProfiles.setKnightProfileVisible();
            allyProfiles.addAlly(KNIGHT);
        } else if (DEATH.equals(enemyType)) {
            allyProfiles.setWizardProfileVisible();
            allyProfiles.addAlly(WIZARD);
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
    }

    private void handleWizardRewards() {
        if (NECROMANCER.equals(enemyType)) {
            allyProfiles.setPriestProfileVisible();
            allyProfiles.addAlly(PRIEST);
        } else if (DEATH.equals(enemyType)) {
            allyProfiles.setKnightProfileVisible();
            allyProfiles.addAlly(KNIGHT);
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
    }

    public void handleAllyProfileRewards(){
        switch (playerType) {
            case KNIGHT -> {
                handleKnightRewards();
            }
            case PRIEST -> handlePriestRewards();
            case WIZARD -> handleWizardRewards();
            default -> System.out.println("Unknown player type: " + playerType);
        }
    }
}
