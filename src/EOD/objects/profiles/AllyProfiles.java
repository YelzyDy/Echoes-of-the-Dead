package EOD.objects.profiles;

import java.awt.event.MouseEvent;

import EOD.gameInterfaces.Freeable;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import javax.swing.JPanel;
import EOD.scenes.BattleUI;
import EOD.scenes.SceneBuilder;
import EOD.characters.Player;
import java.util.ArrayList;
import EOD.worlds.World;
import EOD.objects.Quests;
public class AllyProfiles implements MouseInteractable, Freeable {
    private static final double X_COORDINATE = 0.28;
    private EchoesObjects knightProfile;
    private EchoesObjects wizardProfile;
    private EchoesObjects priestProfile;
    private JPanel panel;
    private BattleUI battle;
    private SceneBuilder scene;
    private ArrayList<Player> player;
    private World world;
    private double[] yCoordinates;
    private ArrayList<String> allyList;
    private Quests quests;
    public AllyProfiles(World world) {
        this.battle = world.getBattle();
        this.panel = battle.getSidePanel();
        this.scene = world.getScene();
        this.world = world;
        this.quests = world.quests;
        allyList = new ArrayList<>();
        setUpYCoordinates();
    }

    @Override
    public void free(){
        if(knightProfile != null){
            knightProfile.free();
            knightProfile = null;
        }
        if(wizardProfile != null){
            wizardProfile.free();
            wizardProfile = null;
        }
        if(priestProfile != null){
            priestProfile.free();
            priestProfile = null;
        }
        for(String item : allyList){
            item = null;
        }
        for(Player p : player){
            p.free();
            p = null;
        }
    }

    private void setUpYCoordinates() {
        yCoordinates = new double[3];
        yCoordinates[0] = panel.getWidth() * 0.137;
        yCoordinates[1] = panel.getWidth() * 0.762;
        yCoordinates[2] = panel.getWidth() * 1.34;
    }

    public void addAlly(String ally){
        allyList.add(ally);
    }

    public void addProfile(String type, int index) {
        EchoesObjects profile = new EchoesObjects(
            "profile",
            (int)(panel.getWidth() * X_COORDINATE), (int) yCoordinates[index],
            (int)(panel.getWidth() * 0.47), (int)(panel.getWidth() * 0.47),
            type, false, true, 2
        );
        profile.setVisible(true);
        panel.add(profile);
        profile.addMouseListener(new MouseClickListener(this));

        switch (type) {
            case "knight" -> knightProfile = profile;
            case "wizard" -> wizardProfile = profile;
            case "priest" -> priestProfile = profile;
        }
        panel.repaint();
    }

    public void changeYCoordinate(String type, int index) {
        double newYCoordinate = yCoordinates[index];
        switch (type) {
            case "knight" -> knightProfile.setPosY(newYCoordinate);
            case "wizard" -> wizardProfile.setPosY(newYCoordinate);
            case "priest" -> priestProfile.setPosY(newYCoordinate);
        }
    }

    public void setWorld(World world) {
        this.battle = world.getBattle();
        this.panel = battle.getSidePanel();
        this.scene = world.getScene();
        this.world = world;
        setUpYCoordinates();
    }

    public void setPlayer(ArrayList<Player> player) {
        this.player = player;
    }

    public ArrayList<Player> getPlayerList() {
        return player;
    }

    public void showAllProfiles() {
        if (world.getPlayer().isKnight()){
            addProfile("knight", 1);
            for(String allies : allyList){
                if(allies.equals("wizard")){
                    addProfile(allies, 2);
                }else if(allies.equals("priest")){
                    addProfile(allies, 0);
                }
            }
        }
        if (world.getPlayer().isWizard()){
            addProfile("wizard", 1);
            for(String allies : allyList){
                if(allies.equals("knight")){
                    addProfile(allies, 2);
                }else if(allies.equals("priest")){
                    addProfile(allies, 0);
                }
            }
        }
        if (world.getPlayer().isPriest()){
            addProfile("priest", 1);
            for(String allies : allyList){
                if(allies.equals("wizard")){
                    addProfile(allies, 0);
                }else if(allies.equals("knight")){
                    addProfile(allies, 2);
                }
            }
        }
    }
    public void setAllProfileEnabled(boolean enabled) {
        if(knightProfile != null)knightProfile.setEnabled(enabled);
        if(wizardProfile != null)wizardProfile.setEnabled(enabled);
        if(priestProfile != null)priestProfile.setEnabled(enabled);
    }

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if (!knightProfile.getEnabled()) {
            return;
        }
        if (source == knightProfile) {
            battle.setPlayer(player.get(0));
            scene.setPlayer(player, 0);
            quests.setPlayer(player.get(0));
        } else if (source == wizardProfile) {
            battle.setPlayer(player.get(2));
            scene.setPlayer(player, 2);
            quests.setPlayer(player.get(2));
        } else {
            battle.setPlayer(player.get(1));
            scene.setPlayer(player, 1);
            quests.setPlayer(player.get(1));
        }
    }

    @Override
    public void onHover(MouseEvent e) {
        // Hover functionality here if needed
    }

    @Override
    public void onExit(MouseEvent e) {
        // Exit functionality here if needed
    }
}
