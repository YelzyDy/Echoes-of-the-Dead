package EOD.scenes;

import EOD.characters.Player;
import EOD.characters.enemies.Enemy;
import EOD.gameInterfaces.Skillable;
import EOD.objects.Rewards;
import EOD.objects.profiles.AllyProfiles;
import EOD.utils.SFXPlayer;
import EOD.worlds.World; // -z
import java.awt.Dimension; // -z
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class BattleExperiment implements Skillable{
    private Enemy enemy;
    private Player player;
    private Rewards rewards;
    private BattleUI battleUI;
    private int turnCount = 1;
    private boolean isProcessingTurn = false;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private SFXPlayer sfxPlayer = SFXPlayer.getInstance();
    protected boolean battleEnded = false;
    private boolean isKnightDead = false, isPriestDead = false, isWizardDead = false;
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
        rewards = battleUI.rewards;
    }

    private void handleSkill(int skillNumber, boolean damageEnemy) {
        if (isProcessingTurn) return;
        
        if (player.useSkill(skillNumber)) {
            isProcessingTurn = true;
            
            battleUI.setSkillButtonsEnabled(false);
            player.getWorld().getPlayer().getAllyProfiles().setAllProfileEnabled(false);
            
            final int damage = player.getDamageDealt();
            
            // Set callback for player's animation completion
            player.getAnimator().setOnAnimationComplete(() -> {
                // Apply damage after animation completes
                if (damageEnemy) {
                    enemy.takeDamage(damage);
                    
                    if ((player.isWizard() && skillNumber == 3) || 
                        (player.isKnight() && skillNumber == 4)) {
                        enemy.missedTurn = true;
                    }
                }
                
                if (enemy.getHp() <= 0) {
                    handleBattleEnd(true);
                    return;
                }
                startEnemyTurn();
            });
            
            // Trigger animation and SFX
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
            case 1 -> enemy.skill1();
            case 2 -> enemy.skill2();
        }

        final int initialDamage = enemy.getDamageDealt();
        final int damage = player.isDamageReducerActive() ? 
            (int)(initialDamage * 0.5) : initialDamage;

        // Set callback for enemy's animation completion
        enemy.getAnimator().setOnAnimationComplete(() -> {
            // Apply damage after animation completes
            player.takeDamage(damage);
            
            if (player.isDamageReducerActive() && 
                damage > (player.getAttributes().getHp() * 0.2)) {
                player.getAttributes().addMoney(30);
                battleUI.showAction("Turn " + turnCount + ": Effect activated! Get 30 Soul Shards");
            }
            player.resetDamageReducer();
            
            finishEnemyTurn();
        });

        enemy.getAnimator().triggerSkillAnimation(
            enemy.getLastUsedSkill(), 
            (int)enemy.getXFactor()
        );
        enemy.getAnimator().setMovingRight(false);
        battleUI.showAction("Turn " + turnCount + ": " + enemy.getAction());
    }

    private void setAvailableAlliesEnabled(boolean enabled){
        AllyProfiles allyProfiles = player.getWorld().getPlayer().getAllyProfiles();
        for(Player player : allyProfiles.getPlayerList()){
            if(player.getAttributes().getHp() > 0){
                if(player.isKnight()){
                    allyProfiles.setProfileEnabled("knight", enabled);
                }else if(player.isWizard()){
                    allyProfiles.setProfileEnabled("wizard", enabled);
                }else if(player.isPriest()){
                    allyProfiles.setProfileEnabled("priest", enabled);
                }
            }
        }
    }

    private void switchToRemainingAlly(){
        AllyProfiles allyProfiles = player.getWorld().getPlayer().getAllyProfiles();
        for(Player player : allyProfiles.getPlayerList()){
            if(player.getAttributes().getHp() > 0){
                if(player.isKnight() && allyProfiles.isAllyVisible("knight")){
                    allyProfiles.clickPlayerProfile("knight");
                }else if(player.isWizard() && allyProfiles.isAllyVisible("wizard")){
                    allyProfiles.clickPlayerProfile("wizard");
                }else if(player.isPriest() && allyProfiles.isAllyVisible("priest")){
                    allyProfiles.clickPlayerProfile("priest");
                }
            }
        }
    }

    private void finishEnemyTurn() {
        AllyProfiles allyProfiles = player.getWorld().getPlayer().getAllyProfiles();

        for(Player player : allyProfiles.getPlayerList()){
            player.attributeTurnChecker();
            if(player.getAttributes().getHp() <= 0){
                switch(player.getCharacterType()){
                    case "knight" -> isKnightDead = true;
                    case "priest" -> isPriestDead = true;
                    case "wizard" -> isWizardDead = true;
                }
                allyProfiles.setProfileEnabled(player.getCharacterType(), false);
                switchToRemainingAlly();
            }
        }
        
        // Increment turn count after enemy's turn is complete
        turnCount++;
        
        battleUI.setSkillButtonsEnabled(true);
        setAvailableAlliesEnabled(true);
        battleUI.updateTurnIndicator("Turn " + turnCount + " - Your turn");
        battleUI.updateCooldowns();
        
        // Check if all visible allies are dead
        boolean areAllVisibleAlliesDead = true;

        if (allyProfiles.isAllyVisible("knight") && !isKnightDead) {
            areAllVisibleAlliesDead = false;
        }
        if (allyProfiles.isAllyVisible("wizard") && !isWizardDead) {
            areAllVisibleAlliesDead = false;
        }
        if (allyProfiles.isAllyVisible("priest") && !isPriestDead) {
            areAllVisibleAlliesDead = false;
        }

        if (areAllVisibleAlliesDead) {
            handleBattleEnd(false);
            return;
        }
        
        enemy.update();
        isProcessingTurn = false;
    }

    private String getEnemyType(){
        switch(enemy.getCharacterType()){
            case "skeleton1" ->{
                return "minions";
            }case "skeleton2" ->{
                return "minions";
            }case "skeleton3" ->{
                return "minions";
            }case "death" ->{
                return "miniboss";
            }case "necromancer" ->{
                return "miniboss";
            }default->{
                return null;
            }
        }
    }

    private void handleBattleEnd(boolean playerWon) {
        if (battleEnded) return;
        battleEnded = true;     
        isProcessingTurn = false;
        World world = player.getWorld();
        
        if(playerWon){
            world.callVictory();
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
            return 0.15;
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
                switch (enemy.getName()){
                    case "Necromancer":
                        sfxPlayer.playSFX("src/audio_assets/sfx/miniboss/necromancerdead.wav");
                        break;
                    case "Death":
                        break;
                    case "Skeleton1":
                        sfxPlayer.playSFX("src/audio_assets/sfx/skeletons/skeleton1dead.wav");
                        break;
                    case "Skeleton2":
                        sfxPlayer.playSFX("src/audio_assets/sfx/skeletons/skeleton2dead.wav");
                        break;
                    case "Skeleton3":
                        break;
                    default:
                        break;
                }
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