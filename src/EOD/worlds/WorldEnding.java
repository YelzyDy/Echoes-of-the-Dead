/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.worlds;
import EOD.characters.*;
import EOD.listeners.MouseClickListener;
import EOD.scenes.SceneBuilder;
import EOD.utils.BGMPlayer;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author zendy
 */
public class WorldEnding extends World {
    public WorldEnding(Player player, ArrayList<Player> playerList){
        super("worldEnding");
        scene = new SceneBuilder(this);
        bgmPlayer = new BGMPlayer();
        bgmPlayer.playBGM("src/audio_assets/world1.wav");
        
        // Set up main player
        this.player = player;
        this.playerList = playerList;
        
        // Configure player position and visibility
        player.setPosX(0);
        player.setPosY((int)(screenSize.height * 0.24));
        player.setVisible(true);
        player.setpanel(scene);
        player.setWorld(this);
        player.configureSkills();
        
        System.out.println("Player type in world2: " + player.getCharacterType());
        
        // Add player to scene
        scene.add(player);
        scene.setPlayer(player);
        scene.setComponentZOrder(player, 0);
        scene.addMouseListener(new MouseClickListener(player));
        
        // Set up player attributes
        this.playerName = player.getName();
        configureShopAndInventory();
        setMoney(player.getAttributes().getMoney());
        setMoneyLabel(player.getAttributes().getMoney() + "");
        
        Welcome();
    }

    @Override
    public void initializeAllyProfiles() {
        player.getAllyProfiles().setWorld(this);
    }
    
    @Override
    public void initializePlayerProfile(){
        player.getPlayerProfile().setPanel(getLayeredPane());
    }

    
    @Override
    public void initializeProtagonist(){
        for (Player p : playerList) {
            if (p != player) {  // Skip the main player
                p.setpanel(scene);
                p.setVisible(false);
                p.setWorld(this);
                p.configureSkills();
                p.setPosY((int)(screenSize.height * 0.24));
                scene.add(p);
                scene.addMouseListener(new MouseClickListener(p));
                scene.setComponentZOrder(p, 0);
            }
        }
    }

    @Override
    public void initializeObjects(){
            // none for now, no portals, no shop
    }

    @Override
    public void initializeWorldChars(){
        // no npcs for now
    }

    @Override
    public void initializeEnemies(){
        //no enemies
    }

    @Override
    public void onClick(MouseEvent e) {
        super.onClick(e);
        Object source = e.getSource();
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
       
    }
}
