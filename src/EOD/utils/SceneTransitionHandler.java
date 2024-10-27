package EOD.utils;

import java.awt.Dimension;
import EOD.characters.Protagonist;
import EOD.objects.EchoesObjects;
import EOD.characters.Npc;
import EOD.characters.Enemy;
import java.util.ArrayList;
import EOD.scenes.SceneBuilder;
public class SceneTransitionHandler {
    private final double RIGHT_BOUNDARY_THRESHOLD = 0.9;
    private final double LEFT_BOUNDARY_THRESHOLD = 0.05;
    private final double SPAWN_RIGHT_POSITION = 0.9;
    private final double SPAWN_LEFT_POSITION = 0.03;
    private boolean isInTransition = false;
    
    private Dimension screenSize;
    
    public SceneTransitionHandler(Dimension screenSize) {
        this.screenSize = screenSize;
    }
    
    public void setIsInTransition(boolean isInTransition){
        this.isInTransition = isInTransition;
    }

    public boolean getIsInTransition(){
        return isInTransition;
    }

    /**
     * Handles scene transitions and returns whether a transition occurred
     * @return true if a scene transition occurred
     */
    public boolean handleSceneTransition(SceneBuilder panel, Protagonist player, 
                                    ArrayList<EchoesObjects> objList,
                                    ArrayList<Npc> npcList,
                                    ArrayList<Enemy> enemyList) {
                                        System.out.println("In Transition? " + isInTransition);
        if (isInTransition) {
            return false;
        }
        
        int currentScene = panel.getCurrentSceneIndex();
        int maxPanel = panel.getNumOfScenes() - 3;
        double currentPosX = player.getPosX();
        
        // Check for right boundary transition
        if (currentPosX >= (screenSize.width * RIGHT_BOUNDARY_THRESHOLD) && currentScene < maxPanel - 1) {
            isInTransition = true;
            panel.incCurrentScene();
            player.setPosX(screenSize.width * SPAWN_LEFT_POSITION);
            player.getAnimator().stopMovement();
            updateObjectVisibility(panel, objList, npcList, enemyList);
            return true;
        } 
        // Check for left boundary transition
        else if (currentScene > 0 && currentPosX <= (screenSize.width * LEFT_BOUNDARY_THRESHOLD)) {
            isInTransition = true;
            panel.decCurrentScene();
            player.setPosX(screenSize.width * SPAWN_RIGHT_POSITION);
            player.getAnimator().stopMovement();
            updateObjectVisibility(panel, objList, npcList, enemyList);
            return true;
        }
        
        return false;
    }

     /**
     * Checks if player is at a position that would toggle isInTransition to false so we can transition again
     */

    public boolean isAtNonTransitionPoint(double posX) {
        double leftBoundary = screenSize.width * LEFT_BOUNDARY_THRESHOLD;
        double rightBoundary = screenSize.width * RIGHT_BOUNDARY_THRESHOLD;
        // Check if posX is within the range where no transition is triggered
        return posX > leftBoundary && posX < rightBoundary;
    }
    
    
    /**
     * Checks if player is at a position that would trigger a scene transition
     */

    public boolean isAtTransitionPoint(double posX, int currentScene, int maxPanel) {
        if (isInTransition) {
            return false;
        }
        
        return (posX >= (screenSize.width * RIGHT_BOUNDARY_THRESHOLD) && currentScene < maxPanel - 1) ||
               (currentScene > 0 && posX <= (screenSize.width * LEFT_BOUNDARY_THRESHOLD));
    }
    
    private void updateObjectVisibility(SceneBuilder panel,
                                      ArrayList<EchoesObjects> objList,
                                      ArrayList<Npc> npcList,
                                      ArrayList<Enemy> enemyList) {
        int currentSceneIndex = panel.getCurrentSceneIndex();
        boolean isInBattleScene = (currentSceneIndex == 3 || currentSceneIndex == 4);

        if (objList != null) {
            for (EchoesObjects obj : objList) {
                boolean isObjectForCurrentScene = obj.getIndex() == currentSceneIndex;
                boolean isPortalObject = obj.getName().equals("portal") || 
                                       obj.getName().equals("portalMiniBoss") ||
                                       obj.getName().equals("portalNextWorld");
                
                if (isPortalObject) {
                    obj.setVisible(isObjectForCurrentScene && !isInBattleScene);
                } else {
                    obj.setVisible(isObjectForCurrentScene);
                }
            }
        }

        if (npcList != null) {
            for (Npc npc : npcList) {
                npc.setVisible(npc.getIndex() == currentSceneIndex && !isInBattleScene);
            }
        }

        if (enemyList != null) {
            for (Enemy enemy : enemyList) {
                boolean isInCorrectBattleScene = 
                    (currentSceneIndex == 3 && enemy.getName().equals("Skeleton")) ||
                    (currentSceneIndex == 4 && enemy.getName().equals("Necromancer"));
                
                enemy.setVisible(isInCorrectBattleScene || enemy.getIndex() == currentSceneIndex);
            }
        }
    }
}
