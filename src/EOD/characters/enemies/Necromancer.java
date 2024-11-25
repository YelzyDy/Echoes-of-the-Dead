package EOD.characters.enemies;

import EOD.characters.Player;
import EOD.objects.ClickableObjects;
import EOD.objects.SkillEffects;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

public class Necromancer extends Enemy {    
    // private static final int BASE_ATTACK = 15;
    // private static final int BASE_HEALTH = 250;
    private static final int BASE_ATTACK = 20;
    private static final int BASE_HEALTH = 25;
    // Skill cooldowns
    private int skill2Cooldown = 0;
    private static final int SKILL2_MAX_COOLDOWN = 3;

    public Necromancer(String name, int posX, int posY, 
            double minRange, double maxRange, Player protagonist) {
            super(name, "necromancer", posX, posY, minRange, maxRange, protagonist);
            configureSprites();
            health = BASE_HEALTH;
            attack = BASE_ATTACK;
            baseHp = BASE_HEALTH;
            baseAttack = BASE_ATTACK;
            moneyDrop = 50;
            animator.setMovementMultiplier(2);
            animator.setDeathAnimationSpeedMultiplier(5);
            animator.setSkillAnimationSpeedMultiplier(3);
    }

    public void configureSprites(){
        animator.importSprites("character_asset", "walk", (int)(screenSize.height * 0.007), 10);
        animator.importSprites("character_asset", "idle", (int)(screenSize.height * 0.007), 50);
        animator.importSprites("character_asset", "dead", (int)(screenSize.height * 0.007), 52);
        animator.importSprites("character_asset","hurt", (int)(screenSize.height * 0.007), 9);
        animator.importSkillSprites(1, "character_asset", (int)(screenSize.height * 0.007), 47);
        animator.importSkillSprites(2, "character_asset", (int)(screenSize.height * 0.007), 47);
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
        skill1Effects = new SkillEffects(  "effects",
        (int)(screenSize.width * 0.1), (int)(screenSize.width * 0.1),
        (int) (screenSize.width * 0.2),
        (int) (screenSize.width * 0.2),
        "smoke",
        11,
        player.getPanel()
        );
        skill1Effects.setLooping(false);

        skill2Effects = new SkillEffects(  "effects",
        (int)(screenSize.width * 0.1), (int)(screenSize.width * 0.1),
        (int) (screenSize.width * 0.13),
        (int) (screenSize.width * 0.07),
        "summonSkelArcher",
        8,
        player.getPanel()
        );
        skill2Effects.setLooping(false);
    }

    @Override 
    public void skill1() {
        //sfxPlayer.playSFX("src/audio_assets/sfx/miniboss/necromancerskill1.wav");
        damageDealt = attack + (int)(Math.random() * 3);
        actionString = getName() + " used shadow grasp, dealt " + damageDealt + " damage!";
        lastUsedSkill = 1;
        skill1Effects.bindToTarget(player, -player.getWidth() * 1, -player.getHeight() * 1);
        playerHurtDelay = 2700;
        Timer effectsTimer = new Timer(2500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            skill1Effects.play();
            skill1Effects.setStopFrame(11);
            ((Timer)e.getSource()).stop(); // Stop the timer after playing effects
        }
        });
        effectsTimer.setRepeats(false);
        effectsTimer.start();
    }

    @Override 
    public void skill2() {
        //sfxPlayer.playSFX("src/audio_assets/sfx/miniboss/necromancerbasicatk.wav");
        int baseSkill2Damage = (int)(attack * 1.5);
        damageDealt = baseSkill2Damage + (int)(Math.random() * 4);
        
        actionString = getName() + " used Summon, dealt " + damageDealt + " damage!";
        lastUsedSkill = 2;
        skill2Cooldown = SKILL2_MAX_COOLDOWN;
        skill2Effects.bindToTarget(player, -player.getWidth() * -1.5, -player.getHeight() * 0.1);
        playerHurtDelay = 1200;
        Timer effectsTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            skill2Effects.play();
            skill2Effects.setStopFrame(8);
            ((Timer)e.getSource()).stop(); // Stop the timer after playing effects
        }
        });
        effectsTimer.setRepeats(false);
        effectsTimer.start();
    }


    @Override 
    public double getOffsetX(int skill){
        // increasing this moves skill effects to the left
        if(skill == 1){
            if(player.isKnight()){
                return 0.35;
            }else{
                return 0.3;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.35;
            }else{
                return 0.15;
            }
        }else if(skill == 3){
            if(player.isWizard()){
                return 0.3;
            }else{
                return 0.22;
            }
        }else{
            if(player.isWizard()){
                return 0.2;
            }else{
                return 0.3;
            }
        }
    }

    @Override 
    public double getOffsetY(int skill){
         //increasing this moves skill effects up
        if(skill == 1){
            if(player.isKnight()){
                return 0.05;
            }else{
                return 0.2;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.4;
            }else if(player.isWizard()){
                return 0.4;
            }else{
                return 0.3;
            }
        }else if(skill == 3){
            if(player.isKnight()){
                return 0.3;
            }else if(player.isWizard()){
                return 0.2;
            }else{
                return -0.1;
            }
        }else{
            if(player.isKnight()){
                return 0.1;
            }else if(player.isWizard()){
                return 0.2;
            }else{
                return 0.32;
            }
        }
    }

    @Override 
    public double getOffsetW(int skill){
        if(skill == 2) {
            if(player.isKnight()){
                return 0.2;
            }else if(player.isWizard()){
                return 0.15;
            } else {
                return 0.25;
            }
        }else if(skill == 3){
            if(player.isKnight()){
                return 0.15;
            }else if(player.isWizard()){
                return 0.3;
            }else{
                return 0.17;
            }
        }else{
            if(player.isWizard()){
                return 0.2;
            }else if(player.isKnight()){
                return 0.28;
            }else{
                return 0.3;
            }
        }
    }

    @Override 
    public double getOffsetH(int skill){
        if(skill == 2) {
            if(player.isKnight()) {
                return 0.15;
            } else if(player.isWizard()){
                return 0.15;
            }else{
                return 0.07;
            }
        }else if(skill == 3){
            if(player.isKnight()){
                return 0.15;
            }else if(player.isWizard()){
                return 0.3;
            }else{
                return 0.17;
            }
        }else{
            if(player.isKnight()){
                return 0.22;
            }
            if(player.isWizard()){
                return 0.25;
            }else{
                return 0.28;
            }
        }
    }

    @Override
    public double getXFactor(){
        return screenSize.width * 0.75;
    }


    @Override
    public void update() {
        if (skill2Cooldown > 0) {
            skill2Cooldown--;
        }
    }

    // Decision making for skill usage
    @Override
    public int decideSkill() {
        if (skill2Cooldown > 0) {
            return 1;
        }
        // Otherwise, 70% chance for skill1, 30% chance for skill2
        return (Math.random() < 0.9) ? 2 : 1;
    }
    
    @Override
    protected void onBattleStart() {
        ClickableObjects portal = getPanel().objList.get(2);
        getPanel().configureBattle(this, portal);
    }

    @Override
    public void onClick(MouseEvent e) {
        super.onClick(e);
        if (animator.getIsDead()) {

            // First click initialization
            if (!isClicked) {
                handleFirstClick();
            }

            // Make dialogue visible
            dialogues.setVisible(true);

            // Adjust dimensions based on click count or specific dialogue requirements
            if (i == 14) {
               handleI14();
            } else if (i == 15) {
                handleI15();
            }else if (i != 4 && i != 9){
                handleDots();
            }else{
                if(i == 4)
                dialogues.setDimension((int)(getPanel().getWidth() * 0.4), 
                                     (int)(getPanel().getHeight() * 0.1));
                else
                dialogues.setDimension((int)(getPanel().getWidth() * 0.4), 
                                     (int)(getPanel().getHeight() * 0.15));
                autoCloseDelay = 3000;
            }
            dialogues.handleSetText();
            if(allowDialogues) resetAndStartTimer();
            if (i != 15) i++;
        }
    }

    private void handleFirstClick(){
        dialogues.displayDialogues(10, world);
        dialogues.setFontSize(getWidth() * 0.18);
        // Set initial dimensions and coordinates
        dialogues.setDimension((int)(getPanel().getWidth() * 0.4), 
                             (int)(getPanel().getHeight() * 0.1));
        dialogues.setCoordinates(getPosX(), 
                               getPosY() - getPanel().getHeight() * 0.2);
        isClicked = true;
    }

    private void handleDots(){
        dialogues.setDimension((int)(getPanel().getWidth() * 0.15), 
        (int)(getPanel().getHeight() * 0.1));
        autoCloseDelay = 1500;
    }

    private void handleI14(){
        dialogues.setDimension((int)(getPanel().getWidth() * 0.4), 
                                     (int)(getPanel().getHeight() * 0.21));
        autoCloseDelay = 5000;
    }

    private void handleI15(){
        dialogues.setDimension((int)(getPanel().getWidth() * 0.4), 
        (int)(getPanel().getHeight() * 0.35));
        dialogues.setCoordinates(dialogues.getStoryJDialog().getX(), getPanel().getHeight() * 0.1);
        autoCloseDelay = 10000;
        System.out.println("auto close delay " + autoCloseDelay);
        if(allowDialogues) world.getPlayer().getAttributes().setMana(world.getPlayer().getAttributes().getMana() + 10);
        allowDialogues = false;
    }

}