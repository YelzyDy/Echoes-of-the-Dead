 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.event.MouseEvent;
import java.util.Random;
/**
 *
 * @author Joana
 */

public class Npc extends Character implements MouseInteractable {
    private Random random;
    public Npc(String name, String characterType, SceneBuilder panel, int posX, int posY){
        super(name, characterType, panel, posX, posY);
        setVisible(false);
        this.random = new Random(); // Initialize Random object
    }
    
    @Override
    public void moveTo(int targetX) {
        if (isMoving) {
            return; // If the NPC is already moving, skip.
        }

        int randomX = random.nextInt((int) (panel.screenSize.width * 0.8)); // Random X target within 80% of screen width
        // Call the superclass moveTo method with the random X value
        super.moveTo(randomX);
    }
    
    @Override
    public void onClick(MouseEvent e) {
     
    }

    @Override
    public void onHover(MouseEvent e) {
        
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }

    @Override
    public void initializeSprites(String assetPackage, String type, int scale) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeSprites'");
    }

    @Override
    public void initializeSprites(String assetPackage, int width, int height) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeSprites'");
    }
}
