package EOD.utils;

import java.awt.Dimension;
import EOD.characters.Player;
import EOD.characters.enemies.Enemy;
import EOD.objects.QuestableObjects;
import EOD.characters.Npc;

import java.util.ArrayList;
import EOD.scenes.SceneBuilder;
public class SceneTransitionHandler {
    public final double RIGHT_BOUNDARY_THRESHOLD = 0.9;
    public final double LEFT_BOUNDARY_THRESHOLD = 0.05;
    public final double SPAWN_RIGHT_POSITION = 0.9;
    public final double SPAWN_LEFT_POSITION = 0.03;
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
    public void handleSceneTransition(SceneBuilder panel, double currentPosX, ArrayList<Player> playerList, 
                                    ArrayList<QuestableObjects> objList,
                                    ArrayList<Npc> npcList,
                                    ArrayList<Enemy> enemyList) {

        if (isInTransition || IsInEnemyIndex(panel, enemyList)) {
            return;
        }
        
        int currentScene = panel.getCurrentSceneIndex(); 
        int maxPanel = panel.getNumOfScenes() - 3;
        
        // Check for right boundary transition
        if (currentPosX >= (screenSize.width * RIGHT_BOUNDARY_THRESHOLD) && currentScene < maxPanel - 1) {
            isInTransition = true;
            panel.incCurrentScene();
            for(Player player : playerList){
                player.setPosX(screenSize.width * SPAWN_LEFT_POSITION);
                player.getAnimator().stopMovement();
            }
            updateObjectVisibility(panel, objList, npcList, enemyList);
            return;
        } 
        // Check for left boundary transition
        else if (currentScene > 0 && currentPosX <= (screenSize.width * LEFT_BOUNDARY_THRESHOLD)) {
            isInTransition = true;
            panel.decCurrentScene();
            for(Player player : playerList){
                player.setPosX(screenSize.width * SPAWN_RIGHT_POSITION);
                player.getAnimator().stopMovement();
            }
            updateObjectVisibility(panel, objList, npcList, enemyList);
            return;
        }
        
        return;
    }

    public boolean shouldTransitionAtEdge(Player player, int currentSceneIndex) {
        boolean isAtLeftBoundary = player.getPosX() <= (screenSize.width * LEFT_BOUNDARY_THRESHOLD);
        boolean isAtRightBoundary = player.getPosX() >= (screenSize.width * RIGHT_BOUNDARY_THRESHOLD);
        System.out.println("Boolean if player is clicking at edge: " + (isAtLeftBoundary && player.clickX <= player.getPosX() || isAtRightBoundary && player.clickX >= player.getPosX()));
        // If player is already at an edge and clicks beyond that edge

        if (isAtLeftBoundary) {
            if(player.clickX <= screenSize.width * LEFT_BOUNDARY_THRESHOLD){
                return false;
            }
        }

        if (isAtRightBoundary) {
            if(player.clickX >= screenSize.width * RIGHT_BOUNDARY_THRESHOLD){
                return false;
            }
        }

        return true;
    }

     /**
     * Checks if player is at a position that would toggle isInTransition to false so we can transition again
     */

    public boolean isAtNonTransitionPoint(double posX) {
        double leftBoundary = screenSize.width * LEFT_BOUNDARY_THRESHOLD;
        double rightBoundary = screenSize.width * RIGHT_BOUNDARY_THRESHOLD;
        // Check if posX is within the range where no transition is triggered
        return posX > leftBoundary && posX < rightBoundary; // returns true if player is not at the end of the panel/screen
    }
    

    private boolean IsInEnemyIndex(SceneBuilder panel, ArrayList<Enemy> enemyList){
        if(enemyList == null) return false;
        for(Enemy enemy : enemyList){
            if(panel.getCurrentSceneIndex() == enemy.getIndex()){
                return true;
            }
        }
        return false;
    }
    
    private void updateObjectVisibility(SceneBuilder panel,
                                      ArrayList<QuestableObjects> objList,
                                      ArrayList<Npc> npcList,
                                      ArrayList<Enemy> enemyList) {
        int currentSceneIndex = panel.getCurrentSceneIndex();
        boolean isInBattleScene = (currentSceneIndex == 3 || currentSceneIndex == 4);

        if (objList != null) {
            for (QuestableObjects obj : objList) {
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