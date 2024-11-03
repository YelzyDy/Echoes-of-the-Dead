package EOD.objects.profiles;

import java.awt.event.MouseEvent;

import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import javax.swing.JPanel;
import EOD.scenes.BattleUI;
import EOD.scenes.SceneBuilder;
import EOD.characters.Player;
import java.util.ArrayList;
import EOD.worlds.World;

public class AllyProfiles implements MouseInteractable{
    private EchoesObjects knightProfile;
    private EchoesObjects wizardProfile;
    private EchoesObjects priestProfile;
    private JPanel panel;
    private BattleUI battle;
    private SceneBuilder scene;
    private ArrayList <Player> player;
    private World world;

    public AllyProfiles(World world){
        this.battle = world.getBattle();
        this.battle.setAllyProfiles(this);
        this.panel = battle.getSidePanel();
        this.scene = world.getScene();
        this.world = world;
        addKnightProf();;
        addWizardProf();
        addPriestProf();
    }

    public void setPlayer(ArrayList<Player> player){
        this.player = player;
    }

    public ArrayList<Player> getPlayerList(){
        return player;
    }

    private void addKnightProf(){
        knightProfile = new EchoesObjects("profile", (int)(panel.getWidth() * 0.28), (int)(panel.getWidth() * 0.1), (int)(panel.getWidth() * 0.45), (int)(panel.getWidth() * 0.45), "profKnight", false, true, 2);
        knightProfile.setVisible(false);
        panel.add(knightProfile);
        knightProfile.addMouseListener(new MouseClickListener(this));
    }

    private void addWizardProf(){
        wizardProfile = new EchoesObjects("profile", (int) (panel.getWidth() * 0.28), (int)(panel.getWidth() * 0.7), (int)(panel.getWidth() * 0.45), (int)(panel.getWidth() * 0.45), "profWizard", false, true, 2);
        wizardProfile.setVisible(false);
        panel.add(wizardProfile);
        wizardProfile.addMouseListener(new MouseClickListener(this));
    }

    private void addPriestProf(){
        priestProfile = new EchoesObjects("profile", (int)(panel.getWidth() * 0.28), (int)(panel.getWidth() * 1.3),(int)(panel.getWidth() * 0.45), (int)(panel.getWidth() * 0.45), "profPriest", false, true, 2);
        priestProfile.setVisible(false);
        panel.add(priestProfile);
        priestProfile.addMouseListener(new MouseClickListener(this));
    }

    public void ShowAllProfiles(){
        knightProfile.setVisible(true);
        wizardProfile.setVisible(true);
        priestProfile.setVisible(true);
    }

    public void showKnightProfile(){
        knightProfile.setVisible(true);
    }

    public void showWizardProfile(){
        wizardProfile.setVisible(true);
    }

    public void showPriestProfile(){
        priestProfile.setVisible(true);
    }

    public void setAllProfileEnabled(boolean enabled){
        knightProfile.setEnabled(enabled);
        wizardProfile.setEnabled(enabled);
        priestProfile.setEnabled(enabled);
    }
    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        if(!knightProfile.getEnabled()){
            return;
        }
        if(source == knightProfile){
            battle.setPlayer(player.get(0));
            scene.setPlayer(player, 0);
            world.setPlayer(player.get(0));
        }else if(source == wizardProfile){
            battle.setPlayer(player.get(2));
            scene.setPlayer(player, 2);
            world.setPlayer(player.get(2));
        }else{
            battle.setPlayer(player.get(1));
            scene.setPlayer(player, 1);
            world.setPlayer(player.get(1));
        }
    }
    @Override
    public void onHover(MouseEvent e) {
       
    }
    @Override
    public void onExit(MouseEvent e) {
   
    }
    
}
