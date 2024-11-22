package EOD.objects;

import EOD.characters.Player;
import EOD.gameInterfaces.Questable;
import EOD.scenes.SceneBuilder;
import java.awt.Component;
import java.awt.event.MouseEvent;
public abstract class QuestableObjects extends EchoesObjects implements Questable{
    public double targetX;
    protected boolean isClicked;
    public boolean doneInteraction;
    public QuestableObjects (String assetPackage, double x, double y, int width, int height, String type, boolean isAnimated, boolean isState, int numOfSprites){
        super(assetPackage, x, y, width, height, type, isAnimated, isState, numOfSprites);
        targetX = -15;
        isClicked = false;
        doneInteraction = false;
    }

    public void onClick(MouseEvent e){
        SceneBuilder scene = world.getScene();
        Player player = scene.getPlayer();
        int currentScene =  scene.getCurrentSceneIndex();
        targetX = 0;
        Component targetComponent = null;
        if(currentScene > getIndex()){
            targetX = screenSize.width * 0.05;
            targetComponent = scene;
            System.out.println("should be here");
        }else if(currentScene < getIndex()){
            targetX = screenSize.width * 0.9;
            targetComponent = scene;
        }else{
            if(player.getX() > getX()) targetX = getX() * 1.1;
            else targetX = getX() * 0.9;
            targetComponent = this;
        }
        // System.out.println("current scene: " + currentScene + "npc index: " + getIndex());

        MouseEvent fakeClickEvent = new MouseEvent(
            targetComponent,                            // Target component
            MouseEvent.MOUSE_CLICKED,       // Event type
            System.currentTimeMillis(),     // Event time
            0,                              // Modifiers (no modifiers here)
            (int) targetX,                        // Specified X position
            targetComponent.getY(),                        // Specified Y position
            1,                              // Click count
            false                           // Not a popup trigger
        );
    
        // Call the world's click handler with the created event
        player.onClick(fakeClickEvent);

        isClicked = false;
    }
}
