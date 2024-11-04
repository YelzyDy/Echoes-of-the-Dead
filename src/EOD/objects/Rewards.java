package EOD.objects;

import EOD.objects.profiles.AllyProfiles;
import EOD.scenes.BattleExperiment;
import EOD.scenes.SceneBuilder;

import java.awt.event.MouseEvent;

import EOD.characters.Npc;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.worlds.World;
public class Rewards implements MouseInteractable{
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

    public Rewards(BattleExperiment battle) {
        this.playerType = battle.getPlayer().getWorld().getPlayer().getCharacterType();
        this.enemyType = battle.getEnemy().getCharacterType();
        this.allyProfiles = battle.getPlayer().getWorld().getPlayer().getAllyProfiles();
        this.panel = battle.getPlayer().getPanel();
        this.world = battle.getPlayer().getWorld();
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
        panel.ally.addMouseListener(new MouseClickListener(this));
        panel.add(panel.ally);
    }

    private void handleKnightRewards() {
        if (NECROMANCER.equals(enemyType)) {
            allyProfiles.addProfile(PRIEST, 0);
            allyProfiles.addAlly(PRIEST);
        } else if (DEATH.equals(enemyType)) {
            allyProfiles.addProfile(WIZARD, 2);
            allyProfiles.addAlly(WIZARD);
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
    }

    private void handlePriestRewards() {
        if (NECROMANCER.equals(enemyType)) {
            allyProfiles.addProfile(KNIGHT, 0);
            allyProfiles.addAlly(KNIGHT);
        } else if (DEATH.equals(enemyType)) {
            allyProfiles.addProfile(WIZARD, 2);
            allyProfiles.addAlly(WIZARD);
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
    }

    private void handleWizardRewards() {
        if (NECROMANCER.equals(enemyType)) {
            allyProfiles.addProfile(PRIEST, 0);
            allyProfiles.addAlly(PRIEST);
        } else if (DEATH.equals(enemyType)) {
            allyProfiles.addProfile(KNIGHT, 2);
            allyProfiles.addAlly(KNIGHT);
        } else {
            System.out.println("Unknown enemy type: " + enemyType);
        }
    }



    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if(source == panel.ally){
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



    @Override
    public void onHover(MouseEvent e) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'onHover'");
    }



    @Override
    public void onExit(MouseEvent e) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'onExit'");
    }
}
