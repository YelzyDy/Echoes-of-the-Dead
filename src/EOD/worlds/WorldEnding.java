/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.worlds;
import EOD.characters.*;
import EOD.listeners.MouseClickListener;
import EOD.scenes.SceneBuilder;

/**
 *
 * @author zendy
 */
public class WorldEnding extends World{
    public WorldEnding(Player player){
        super("worldEnding");
        scene = new SceneBuilder(this);
        this.player = player;
        player.setPosX(0);
        player.setPosY((int)(screenSize.height * 0.24));
        player.setVisible(true);
        player.setpanel(scene);
        player.setWorld(this);
        scene.add(player);
        scene.setPlayer(player);
        scene.setComponentZOrder(player, 0);
        scene.addMouseListener(new MouseClickListener(player));
        this.playerName = player.getName();
        System.out.println("WorldEnding instantiated");
    }

    @Override
    public void initializeProtagonist() {
        }

    @Override
    public void initializeAllyProfiles() {
       }

    @Override
    public void initializePlayerProfile() {
       }

    @Override
    public void initializeObjects() {
      }

    @Override
    public void initializeWorldChars() {
        }

    @Override
    public void initializeEnemies() {
        }
}
