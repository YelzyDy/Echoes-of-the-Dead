package EOD.scenes;

import EOD.Ending;
import EOD.characters.Player;
import EOD.characters.enemies.Enemy;
import EOD.dialogues.FullScreenDialogues;
import EOD.gameInterfaces.Skillable;
import EOD.objects.Rewards;
import EOD.objects.profiles.AllyProfiles;
import EOD.utils.BGMPlayer;
import EOD.utils.SFXPlayer;
import EOD.worlds.World; // -z

import java.awt.Dialog;
import EOD.worlds.World1;
import EOD.worlds.World2;
import EOD.worlds.World3;

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
    protected SFXPlayer sfxPlayer = SFXPlayer.getInstance();
    protected BGMPlayer bgmPlayer = BGMPlayer.getInstance();
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


    private void performPriestPoison(int damageHolder[]){
        System.out.println("damageHolder[0] = " + damageHolder[0]);
        System.out.println("Poison Stacks: " + player.getPoisonStacks());
        int poisonStacks = Math.min(player.getPoisonStacks(), 3);
        System.out.println("Posion Stacks from Math.min: " + poisonStacks);
        // Create a Timer for delayed poison tick damage
        Timer poisonTimer = new Timer(1000, new ActionListener() { // 1 second delay
            @Override
            public void actionPerformed(ActionEvent e) {
                int poisonTickDamage = (int) ((player.attributes.getBaseHp() * 0.05) * poisonStacks);
                enemy.takeDamage(poisonTickDamage);
                enemy.getAnimator().triggerHurtAnimation();
                // Check enemy death after poison damage
                if (enemy.getHp() <= 0) {
                    ((Timer)e.getSource()).stop();
                    handleBattleEnd(true);
                }
                
                // Optional: Show poison damage in UI
                battleUI.showAction("Turn " + turnCount + ": " + "Poison deals " + poisonTickDamage + " damage!");
            }
        });
        poisonTimer.setRepeats(false); // Only trigger once
        poisonTimer.start();  
    }
    
    private void handleSkill(int skillNumber, boolean damageEnemy) {
        if (isProcessingTurn) return;
        
        if (player.useSkill(skillNumber)) {
            isProcessingTurn = true;
            
            battleUI.setSkillButtonsEnabled(false);
            player.getWorld().getPlayer().getAllyProfiles().setAllProfileEnabled(false);
            
            final int initialDamage = player.getDamageDealt();
            final int[] damageHolder = { initialDamage };

            // Create a Timer to check for skill execution
            Timer skillCheckTimer = new Timer(16, new ActionListener() { // 60 FPS check
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player.getAnimator().isExecutingSkill()) {
                        player.playSfx(skillNumber);

                        if (damageEnemy) {
                            enemy.takeDamage(damageHolder[0]);
                            enemy.getAnimator().triggerHurtAnimation();
                                

                            // Check enemy death immediately after damage
                            if (enemy.getHp() <= 0) {
                                ((Timer)e.getSource()).stop();
                                handleBattleEnd(true);
                                return;
                            }
                            
                            if ((player.isWizard() && skillNumber == 3) || 
                                (player.isKnight() && skillNumber == 4)) {
                                enemy.missedTurn = true;
                            }
                        }
                        ((Timer)e.getSource()).stop();
                    }
                }
            });
            skillCheckTimer.start();
            
            // Set callback for player's animation completion - only proceed to enemy turn if enemy is still alive
            player.getAnimator().setOnAnimationComplete(() -> {
                if (!battleEnded) {  // Only start enemy turn if battle hasn't ended
                    startEnemyTurn();
                }
            });
            
            // Trigger animation and SFX
            player.getAnimator().triggerSkillAnimation(skillNumber, (int)player.getXFactor());
            player.getAnimator().setMovingRight(true);
            
            battleUI.updateCooldowns();
            battleUI.updateTurnIndicator("Turn " + turnCount + " - Enemy Turn");
        }
        battleUI.showAction("Turn " + turnCount + ": " + player.getAction());
    }

    private void determineAndExecuteEnemyAction() {
        int chosenSkill = enemy.decideSkill();
        
        switch (chosenSkill) {
            case 1 -> enemy.skill1();
            case 2 -> enemy.skill2();
        }
    
        final int initialDamage = enemy.getDamageDealt();
        final int damageHolder[] = {initialDamage};

        final int initialPlayerDamage = player.getDamageDealt();
        final int playerDamageHolder[] = {initialPlayerDamage};

        battleUI.showAction("Turn " + turnCount + ": " + enemy.getAction());

        if (player.getWorld().getPlayerList().get(0).isDamageReducerActive()){ 
            damageHolder[0] = (int)(initialDamage * 0.4);
            battleUI.showAction("Damage reduced! Only " + damageHolder[0] + " damage received!");
        }

        Timer skillCheckTimer = new Timer(enemy.playerHurtDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enemy.getAnimator().isExecutingSkill()) {
                    enemy.playSfx(enemy, chosenSkill);
                    if (player.getWorld().getPlayerList().get(1).isPoisonDebufferActive()) {
                        performPriestPoison(playerDamageHolder);
                    }

                    player.takeDamage(damageHolder[0]);
                    player.getAnimator().triggerHurtAnimation();

                    if (player.getWorld().getPlayerList().get(0).isDamageReducerActive() && 
                        damageHolder[0] > (player.getAttributes().getHp() * 0.2)) {
                        player.getAttributes().addMoney(15);
                        battleUI.showAction("Turn " + turnCount + ": Effect activated! Get 15 Soul Shards");
                    }
                    player.resetDamageReducer();

                    
                    // Check for player death immediately after damage
                    AllyProfiles allyProfiles = player.getWorld().getPlayer().getAllyProfiles();
                    boolean areAllVisibleAlliesDead = checkAllVisibleAlliesDead(allyProfiles);
                    if (areAllVisibleAlliesDead) {
                        ((Timer)e.getSource()).stop();
                        handleBattleEnd(false);
                        return;
                    }
                    
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        skillCheckTimer.start();
        // Set callback for enemy's animation completion - only finish turn if battle hasn't ended
        enemy.getAnimator().setOnAnimationComplete(() -> {
            if (!battleEnded) {  // Only finish enemy turn if battle hasn't ended
                finishEnemyTurn();
            }
        });
    
        enemy.getAnimator().triggerSkillAnimation(
            enemy.getLastUsedSkill(), 
            (int)enemy.getXFactor()
        );
        enemy.getAnimator().setMovingRight(false);
    }

    private boolean checkAllVisibleAlliesDead(AllyProfiles allyProfiles) {
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
        
        return areAllVisibleAlliesDead;
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
            enemy.update();
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
        isProcessingTurn = false;
    }


    private void handleBattleEnd(boolean playerWon) {
        if (battleEnded) return;
        battleEnded = true;     
        isProcessingTurn = false;
        World world = player.getWorld();
        
        for(Player player : world.getPlayerList()){
            player.reset(playerWon);
        }
          // Enable all profiles explicitly
        AllyProfiles allyProfiles = player.getWorld().getPlayer().getAllyProfiles();
        allyProfiles.resetDeathStates();
        for(Player player : allyProfiles.getPlayerList()){
            if(player.getAttributes().getHp() <= 0){
                switch(player.getCharacterType()){
                    case "knight" -> isKnightDead = false;
                    case "priest" -> isPriestDead = false;
                    case "wizard" -> isWizardDead = false;
                }
            }
        }
        battleUI.setSkillButtonsEnabled(false);

        if(playerWon){
            world.callVictory();
            handleWin();
        }else{
            Timer loseTimer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!world.defeatBanner.isVisible()){
                        handleLose();
                        player.getAnimator().revertDeath();
                        ((Timer)e.getSource()).stop();
                    }
                }
            });
            loseTimer.setRepeats(true);
            loseTimer.start();

            if(enemy.getCharacterType().equals("killer")){
                Timer reverseDeathTimer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        world.applyAdvancedGlitchEffect();
                        player.getAnimator().reverseDeathAnimation();
                        ((Timer)e.getSource()).stop();
                    }
                });
                reverseDeathTimer.setRepeats(false);
                reverseDeathTimer.start();
                return;
            }
            world.callDefeat();
            player.getAnimator().triggerDeathAnimation(player.getPosY());
        }
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
        String portalName = battleUI.getPortal().getName();
        int portalIndex = getPortalIndex(portalName);
        World world = player.getWorld();
        battleUI.getPortal().setIndex(portalIndex);
        enemy.setIsDefeated(true);
        Timer deathAnimationTimer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (enemy.getName()){
                    case "Necromancer":
                        sfxPlayer.playSFX("src/audio_assets/sfx/miniboss/necromancerdead.wav");
                        break;
                    case "Death":
                        sfxPlayer.playSFX("src/audio_assets/sfx/miniboss/deathdead.wav");
                        break;
                    case "Skeleton1":
                        sfxPlayer.playSFX("src/audio_assets/sfx/skeletons/skeletondead.wav");
                        break;
                    case "Skeleton2":
                        sfxPlayer.playSFX("src/audio_assets/sfx/skeletons/skeletondead.wav");
                        break;
                    case "Skeleton3":
                        sfxPlayer.playSFX("src/audio_assets/sfx/skeletons/skeletondead.wav");
                        break;
                    case "Killer":
                        sfxPlayer.playSFX("src/audio_assets/sfx/miniboss/killerdead.wav");
                        break;
                    default:
                        break;
                }
                enemy.getAnimator().triggerDeathAnimation(enemy.getPosY());
            }
        });
        deathAnimationTimer.setRepeats(false); // Ensure the timer only fires once
        deathAnimationTimer.start();

        if (enemy.getCharacterType().equals("killer")) {
            FullScreenDialogues dialogues = new FullScreenDialogues();
            dialogues.setPlayerType(player.getCharacterType());
        
            Timer dialogueAndEndTimer = new Timer(200, new ActionListener() {
                private boolean dialogueTriggered = false;
        
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!world.victoryBanner.isVisible() && !dialogueTriggered) {
                        dialogues.displayDialogue(1);
                        dialogueTriggered = true; 
                    }
                    if (dialogueTriggered && !dialogues.isVisible()) {
                        new Ending(true, world).setVisible(true);
                        world.setVisible(false);
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
        
            dialogueAndEndTimer.setRepeats(true);
            dialogueAndEndTimer.start();
        }
        handleRewards();
    }


    private void handleLose(){
        bgmPlayer.stopBGM();
        if (player.getWorld() instanceof World1){
            bgmPlayer.playBGM("src/audio_assets/bgm/world1bgm.wav");
        } else if (player.getWorld() instanceof  World2){
            bgmPlayer.playBGM("src/audio_assets/bgm/world2bgm.wav");
        } else if (player.getWorld() instanceof  World3){
            bgmPlayer.playBGM("src/audio_assets/bgm/world3bgm.wav");
        }

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
        
    }

    private void handleRewards(){
        rewards.getAllyRewards();
    }
}