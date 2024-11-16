package EOD.scenes;

import javax.swing.Timer;

import EOD.characters.Player;
import EOD.characters.enemies.Enemy;
import EOD.worlds.World;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent; // -z
import java.awt.event.ActionListener; // -z

import EOD.gameInterfaces.Skillable;
import EOD.objects.Rewards;

public class BattleExperiment implements Skillable{
    private Enemy enemy;
    private Player player;
    private Rewards rewards;
    private BattleUI battleUI;
    private int turnCount = 0;
    private boolean isProcessingTurn = false;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected boolean battleEnded = false;

    public boolean isProcessingTurn(){
        return isProcessingTurn;
    }

    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Enemy getEnemy(){
        return enemy;
    }

    public Player getPlayer(){
        return player;
    }

    public void setBattleUI(BattleUI battle){
        this.battleUI = battle;
    }

    public void setRewards(Rewards rewards){
        this.rewards = rewards;
    }

    private void handleSkill(int skillNumber, boolean damageEnemy) {
        if (isProcessingTurn) return;  // Prevent multiple skill uses while animation is playing
        
        if (player.useSkill(skillNumber)) {
            isProcessingTurn = true;
            turnCount++;
            
            if (damageEnemy) {
                int damage = player.getDamageDealt();
                enemy.takeDamage(damage);
                
                if ((player.isWizard() && skillNumber == 3) || 
                    (player.isKnight() && skillNumber == 4)) {
                    enemy.missedTurn = true;
                }else{
                    
                }
            }

            battleUI.setSkillButtonsEnabled(false);
            player.getWorld().getPlayer().getAllyProfiles().setAllProfileEnabled(false);
            
            // Set callback for player's animation completion
            player.getAnimator().setOnAnimationComplete(() -> {
                if (enemy.getHp() <= 0) {
                    handleBattleEnd(true);
                    return;
                }
                startEnemyTurn();
            });
            
            player.getAnimator().triggerSkillAnimation(skillNumber, (int)player.getXFactor());
            player.playSfx(player, skillNumber);
            player.getAnimator().setMovingRight(true);
            battleUI.updateCooldowns();
            battleUI.updateTurnIndicator("Turn " + turnCount + " - Enemy Turn");
        }
        battleUI.showAction("Turn " + turnCount + ": " + player.getAction());
    }


    // Skill methods remain unchanged
    public void skill1() { handleSkill(1, true); }
    public void skill2() { handleSkill(2, false); }
    public void skill3() { handleSkill(3, !player.isKnight()); }
    public void skill4() { handleSkill(4, true); }

    private void startEnemyTurn() {
        if (!enemy.missedTurn) {
            determineAndExecuteEnemyAction();
        } else {
            enemy.missedTurn = false;
            battleUI.showAction("Turn " + turnCount + ": Enemy's turn was skipped!");
            finishEnemyTurn();
        }
    }

    private void determineAndExecuteEnemyAction() {
        int chosenSkill = enemy.decideSkill();
        
        switch (chosenSkill) {
            case 1:
                enemy.skill1();
                break;
            case 2:
                enemy.skill2();
                break;
        }

        int damage = enemy.getDamageDealt();
        
        if (player.isDamageReducerActive()) {
            damage = (int)(damage * 0.5);
            if (damage > (player.getAttributes().getHp() * 0.2)) {
                player.getAttributes().addMoney(30);
                battleUI.showAction("Turn " + turnCount + ": Effect activated! Get 30 Soul Shards");
            }
            player.resetDamageReducer();
        }
        
        player.takeDamage(damage);
        battleUI.showAction("Turn " + turnCount + ": " + enemy.getAction());
        
        // Set callback for enemy's animation completion
        enemy.getAnimator().setOnAnimationComplete(this::finishEnemyTurn);
        enemy.getAnimator().triggerSkillAnimation(
            enemy.getLastUsedSkill(), 
            (int)enemy.getXFactor()
        );
        enemy.getAnimator().setMovingRight(false);
    }

    private void finishEnemyTurn() {
        for(Player player : player.getWorld().getPlayer().getAllyProfiles().getPlayerList()){
            player.attributeTurnChecker();
        }
        battleUI.setSkillButtonsEnabled(true);
        player.getWorld().getPlayer().getAllyProfiles().setAllProfileEnabled(true);
        battleUI.updateTurnIndicator("Turn " + turnCount + " - Your turn");
        battleUI.updateCooldowns();
        
        if (player.attributes.getHp() <= 0) {
            System.out.println("handle on battle end");
            handleBattleEnd(false);
            return;
        }
        
        enemy.update();
        isProcessingTurn = false;  // Turn is complete, allow next action
    }

    private void handleBattleEnd(boolean playerWon) {
        if (battleEnded) return;
        battleEnded = true;     
        isProcessingTurn = false;
        World world = player.getWorld();
        
        if(playerWon){
            world.callVictory();
            player.getAttributes().addMoney(enemy.getMoneyDrop());
            handleWin();
        }else{
            world.callDefeat();
            handleLose();
        }
        
        for(Player player : world.getPlayerList()){
            player.reset(playerWon);
            player.setPosX(screenSize.width * 0.4);
        }
        player.getWorld().getPlayer().getAllyProfiles().setAllProfileEnabled(true);
        battleUI.setSkillButtonsEnabled(false);
    }

    private double getEnemyDeathPosY(Enemy enemy){
        if(enemy.getName().equals("Skeleton2")){
            return 0.15;
        }else if(enemy.getName().equals("Necromancer")){
            return 20;
        }else if(enemy.getName().equals("Skeleton1")){
            return 0.5;
        }
        return 0.0;
    }

    private int getPortalIndex(String name){
        if(name.equals("portal")){
            return 3;
        }else if(name.equals("portalMiniBoss")){
            return 4;
        }
        return -1;
    }

    private void handleWin(){
        double enemyDeathY = getEnemyDeathPosY(enemy);
        String portalName = battleUI.getPortal().getName();
        int portalIndex = getPortalIndex(portalName);
        battleUI.getPortal().setIndex(portalIndex);
        enemy.setIsDefeated(true);
        System.out.println("You won");
        Timer deathAnimationTimer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemy.getAnimator().triggerDeathAnimation(enemy.getPosY() + enemy.getPosY() * enemyDeathY);
            }
        });
        deathAnimationTimer.setRepeats(false); // Ensure the timer only fires once
        deathAnimationTimer.start();
        handleRewards();
    }

    private void handleLose(){
        player.getAnimator().setMoving(false);
        enemy.getAnimator().setIsInBattle(false);
        enemy.getAnimator().setMoving(true);
        enemy.setPosX((int) (screenSize.width * 0.65));
        enemy.onHover(null);
        enemy.onExit(null);
        //respawn at portal if defeated
        player.getPanel().setCurrentSceneIndex(battleUI.getPortal().getIndex());
        for(Player player : player.getWorld().getPlayer().getAllyProfiles().getPlayerList()){
            player.getAnimator().setIsInBattle(false);
        }
        battleUI.toggleInventoryOff();
        System.out.println("You lose");
        
    }

    private void handleRewards(){
        rewards.getAllyRewards();
    }
}