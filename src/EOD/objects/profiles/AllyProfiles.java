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
    private ArrayList<Player> playerList;
    private Player player;
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
        this.player = world.getPlayer();
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
        for(Player p : playerList){
            p.free();
            p = null;
        }
    }

    public boolean isAllyVisible(String type){
        switch(type){
            case "knight": return knightProfile.isVisible();
            case "wizard": return wizardProfile.isVisible();
            case "priest": return priestProfile.isVisible();
        }
        return false;
    }

    private void setUpYCoordinates() {
        yCoordinates = new double[3];
        yCoordinates[0] = panel.getWidth() * 0.137;
        yCoordinates[1] = panel.getWidth() * 0.762;
        yCoordinates[2] = panel.getWidth() * 1.34;
    }

    public void addAlly(String ally){
        allyList.add(ally);
        if(ally.equals("knight")) setKnightProfileVisible();
        else if(ally.equals("priest")) setPriestProfileVisible();
        else if(ally.equals("wizard")) setWizardProfileVisible();
    }

    public void addProfile(String type, int index) {
        EchoesObjects profile = new EchoesObjects(
            "profile",
            (int)(panel.getWidth() * X_COORDINATE), (int) yCoordinates[index],
            (int)(panel.getWidth() * 0.47), (int)(panel.getWidth() * 0.47),
            type, false, true, 2
        );

        if(allyList.contains(type)){
            profile.setVisible(true);
        }else{
            profile.setVisible(false);
        }
        panel.add(profile);
        profile.addMouseListener(new MouseClickListener(this));

        switch (type) {
            case "knight" ->{
                knightProfile = profile;
            }
            case "priest" -> priestProfile = profile;
            case "wizard" ->{
                wizardProfile = profile;
            }
        }
        panel.repaint();
    }

    public void changeYCoordinate(String type, int index) {
        double newYCoordinate = yCoordinates[index];
        switch (type) {
            case "knight" ->{
                knightProfile.setPosY(newYCoordinate);
            }
            case "wizard" ->{ 
                wizardProfile.setPosY(newYCoordinate);
            }
            case "priest" ->{ 
                priestProfile.setPosY(newYCoordinate);
            }
        }
    }

    public void setWorld(World world) {
        this.battle = world.getBattle();
        this.panel = battle.getSidePanel();
        this.scene = world.getScene();
        this.world = world;
        setUpYCoordinates();
    }

    public void setPlayer(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setKnightProfileVisible(){
        knightProfile.setVisible(true);
    }

    public void setPriestProfileVisible(){
        priestProfile.setVisible(true);
    }

    public void setWizardProfileVisible(){
        wizardProfile.setVisible(true);
    }

    public void showAllProfiles() {
        if (world.getPlayer().isKnight()){
            addProfile("knight", 1);
            knightProfile.setVisible(true);
            addProfile("wizard", 2);
            addProfile("priest", 0);
            
        }
        if (world.getPlayer().isWizard()){
            addProfile("wizard", 1);
            wizardProfile.setVisible(true);
            addProfile("knight", 2);
            addProfile("priest", 0);
            
        }
        if (world.getPlayer().isPriest()){
            addProfile("priest", 1);
            priestProfile.setVisible(true);
            addProfile("wizard", 0);
            addProfile("knight", 2);
            
        }
    }
    public void setAllProfileEnabled(boolean enabled) {
        if(knightProfile != null)knightProfile.setEnabled(enabled);
        if(wizardProfile != null)wizardProfile.setEnabled(enabled);
        if(priestProfile != null)priestProfile.setEnabled(enabled);
    }

    public void setProfileEnabled(String type, boolean enabled){
        switch(type){
            case "knight" -> knightProfile.setEnabled(enabled);
            case "wizard" -> wizardProfile.setEnabled(enabled);
            case "priest" -> priestProfile.setEnabled(enabled);
        }
    }

    private void setAttributesOfInventory(){
        Player player;
        player = this.player.getPanel().getPlayer();
        this.player.getInventory().setAttributes(player.getAttributes());
        //this sets attributes of inventory depending on the player of the sceneBuilder
    }

    public void clickPlayerProfile(String type){
        int index = 0;
        switch(type){
            case "knight" -> index = 0;
            case "priest" -> index = 1;
            case "wizard" -> index = 2;
        }
        battle.setPlayer(playerList.get(index));
        scene.setPlayer(playerList, index);
        quests.setPlayer(playerList.get(index));
    }


    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if(player.getCharacterType().equals("knight") && !knightProfile.getEnabled()){
            return;
        }else if(player.getCharacterType().equals("wizard") && !wizardProfile.getEnabled()){
            return;
        }else if(player.getCharacterType().equals("priest") && !priestProfile.getEnabled()){
            return;
        }

        if (source == knightProfile) {
            clickPlayerProfile("knight");
        } else if (source == wizardProfile) {
            clickPlayerProfile("wizard");
        } else if(source == priestProfile){
            clickPlayerProfile("priest");
        }
        setAttributesOfInventory();
    }

    @Override
    public void onHover(MouseEvent e) {
        Object source = e.getSource();
        if (source == knightProfile) {
            System.out.println("knightProfileHovered1");
        } else if (source == wizardProfile) {
            System.out.println("WizardProfileHovered1");
        } else if(source == priestProfile){
            System.out.println("PriestProfileHovered1");
        }
    }

    @Override
    public void onExit(MouseEvent e) {
        // Exit functionality here if needed
    }
}
