package EOD.characters.enemies;
import EOD.characters.Player;
import EOD.objects.EchoesObjects;
import java.awt.event.MouseEvent;
public class Skeleton1 extends Enemy {
    // Constants for better maintainability
    private static final int BASE_ATTACK = 10;
    private static final int BASE_HEALTH = 100;
    
    // Skill cooldowns
    private int skill2Cooldown = 0;
    private static final int SKILL2_MAX_COOLDOWN = 3;

    public Skeleton1(String name, int posX, int posY, 
            double minRange, double maxRange, Player protagonist) {
            super(name, "skeleton1", posX, posY, minRange, maxRange, 
           protagonist);
        configureSprites();
        
        // Base stats - weaker than protagonist but not trivial
        attack = BASE_ATTACK;
        health = BASE_HEALTH;
        setBaseHp(BASE_HEALTH);
        setBaseAttack(BASE_ATTACK);
        moneyDrop = 15;
        animator.setSpeedMultiplier(1);
    }

    public void configureSprites() {
        int baseSize = (int)(screenSize.height * 0.007);
        
        // Import all sprite sets
        animator.importSprites("character_asset", "walk", baseSize, 8);
        animator.importSprites("character_asset", "idle", baseSize, 6);
        animator.importSprites("character_asset", "dead", baseSize, 4);
        animator.importSkillSprites(1, "character_asset", baseSize, 8);
        animator.importSkillSprites(2, "character_asset", baseSize, 9);
    
        
        animator.startMovement();
        animator.chooseNewDirection();
        animator.updateBounds();
    }

    @Override 
    public void skill1() {
        // Basic attack - consistent but low damage
        //sfxPlayer.playSFX("src/audio_assets/sfx/skeletons/skeleton1basicatk.wav");
        damageDealt = attack + (int)(Math.random() * 3); // 8-10 damage
        actionString = getName() + " used small boney punch for " + damageDealt + " damage!";
        lastUsedSkill = 1;
    }

    @Override 
    public void skill2() {
        // Special attack - bone throw with damage variation
        if (skill2Cooldown > 0) {
            // Fallback to basic attack if on cooldown
            skill1();
            return;
        }
        // Slightly higher damage than basic attack but unreliable
        int baseSkill2Damage = (int)(attack * 1.5);
        damageDealt = baseSkill2Damage + (int)(Math.random() * 4) - 2; // 10-14 damage with variation
        
        actionString = getName() + " swings its rusty dull sword for " + damageDealt + " damage!";
        lastUsedSkill = 2;
        skill2Cooldown = SKILL2_MAX_COOLDOWN;
        //sfxPlayer.playSFX("src/audio_assets/sfx/skeletons/skeleton1skill1.wav");
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
        // 70% chance to use basic attack
        // 30% chance to try skill2 (will fall back to basic if on cooldown)
        return Math.random() < 0.7 ? 1 : 2;
    }

    @Override
    public double getXFactor() {
        return screenSize.width * 0.35;
    }

    @Override 
    public double getOffsetX(int skill) {
        // increasing this moves skill effects to the left
        if(skill == 1){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.1;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.35;
            }else if(player.isWizard()){
                return 0.15;
            }else{
                return 0.25;
            }
        }else if(skill == 3) {
            if(player.isWizard()) {
                return 0.3;
            } else{
                return 0.25;
            }
        } else {
            if(player.isKnight()){
                return 0.3;
            }else if(player.isWizard()){
                return 0.25;
            }else{
                return 0.4;
            }
        }
    }

    @Override 
    public double getOffsetY(int skill) {
        //increasing this moves skill effects up
        if(skill == 1){
            if(player.isKnight()){
                return 0.4;
            }else{
                return 0.1;
            }
        }else if(skill == 2){
            if(player.isKnight()){
                return 0.4;
            }else if(player.isWizard()){
                return 0.4;
            }else{
                return 0.3;
            }
        } else if(skill == 3){
            if(player.isKnight()){
                return 0.3;
            }else if(player.isWizard()){
                return 0.3;
            }  else{
                return -0.7;
            }
        }else{
            if(player.isKnight()){
                return 0.4;
            }else if(player.isWizard()){
                return 0.35;
            }else{
                return 0.6;
            }
        }
    }

    @Override 
    public double getOffsetW(int skill) {
        if(skill == 1){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.2;
            }
        }else if(skill == 2) {
            if(player.isKnight()){
                return 0.2;
            }else if(player.isWizard()){
                return 0.15;
            } else {
                return 0.17;
            }
        } else if(skill == 3) {
            if(player.isKnight()) {
                return 0.15;
            } else if(player.isWizard()) {
                return 0.2;
            } else {
                return 0.12;
            }
        } else {
            if(player.isKnight()) {
                return 0.2;
            } else if(player.isWizard()){
                return 0.15;
            }else{
                return 0.2;
            }
        }
    }

    @Override 
    public double getOffsetH(int skill){
        if(skill == 1){
            if(player.isKnight()){
                return 0.3;
            }else{
                return 0.2;
            }
        }else if(skill == 2) {
            if(player.isKnight()) {
                return 0.15;
            } else {
                return 0.15;
            }
        } else if(skill == 3) {
            if(player.isKnight()) {
                return 0.15;
            } else if(player.isWizard()) {
                return 0.2;
            } else {
                return 0.05;
            }
        }else {
            if(player.isKnight()) {
                return 0.2;
            } else if(player.isWizard()){
                return 0.15;
            }else{
                return 0.2;
            }
        }
        
    }

    @Override
    protected void onBattleStart() {
        EchoesObjects portal = getPanel().objList.get(1);
        getPanel().configureBattle(this, portal);
    }

    
    @Override
    public void onClick(MouseEvent e) {
        super.onClick(e);
        // if(!allowDialogues) return;
        
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
                dialogues.setDimension((int)(getPanel().getWidth() * 0.4), 
                                     (int)(getPanel().getHeight() * 0.2));
                autoCloseDelay = 3000;
            }
            dialogues.handleSetText();
            if(allowDialogues) resetAndStartTimer();
            if (i != 15) i++;
        }
    }

    private void handleFirstClick(){
        dialogues.displayDialogues(9, world);
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
        dialogues.setDimension((int)(getPanel().getWidth() * 0.4), (int)(getPanel().getHeight() * 0.21));
        autoCloseDelay = 6000;
    }

    private void handleI15(){
        dialogues.setDimension((int)(getPanel().getWidth() * 0.4), (int)(getPanel().getHeight() * 0.40));
        dialogues.setCoordinates(dialogues.getStoryJDialog().getX(), getPanel().getHeight() * 0.1);
        autoCloseDelay = 11000;
        if(allowDialogues) world.getPlayer().getAttributes().setHp(world.getPlayer().getAttributes().getHp() + 10);
        allowDialogues = false;
    }

}