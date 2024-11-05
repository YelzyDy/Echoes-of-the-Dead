package EOD.characters.enemies;

import EOD.animator.EnemyAnimator;
import EOD.characters.Character;
import EOD.characters.Player;
import EOD.gameInterfaces.MouseInteractable;
import EOD.gameInterfaces.Skillable;
import EOD.gameInterfaces.Entity;
import EOD.listeners.MouseClickListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import EOD.dialogues.*;

public abstract class Enemy extends Character implements MouseInteractable, Skillable, Entity{
    protected Player player;
    protected int health;
    protected int attack;
    protected int moneyDrop;
    protected int baseHp;
    protected int baseAttack;
    protected EnemyAnimator animator;
    protected int damageDealt;
    public boolean missedTurn = false;
    private boolean isDefeated;
    protected String actionString;
    protected int lastUsedSkill;
    protected double xFactor;
    protected double yFactor;
    protected Dialogues dialogues = new Dialogues();
    protected boolean isClicked;
    protected int i = 0;
    protected Timer autoCloseTimer; // Timer to auto-close the dialogue
    protected int autoCloseDelay = 0; // Delay in milliseconds (e.g., 3 seconds)

    public Enemy(String name, String characterType, int posX, int posY, 
        double minRange, double maxRange,
        Player player) {        
        super(name, characterType, posX, posY);
        this.player = player;
        this.animator = new EnemyAnimator(this, 2);
        setAnimator(animator);
        this.addMouseListener(new MouseClickListener(this));
        setVisible(true);
        animator.setRange(minRange, maxRange);
        isDefeated = false;
        isClicked = false;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public double getXFactor(){
        return xFactor;
    }

    public double getYFactor(){
        return yFactor;
    }

    public Dialogues getDialogues(){
        return dialogues;
    }

    public abstract double getOffsetX(int skill);
    public abstract double getOffsetY(int skill);
    public abstract double getOffsetW(int skill);
    public abstract double getOffsetH(int skill);

    public void setIsDefeated(boolean isDefeated){
        this.isDefeated = isDefeated;
    }

    public boolean getIsDefeated(){
        return isDefeated;
    }

    public abstract void update();

    public abstract int decideSkill();

    public int getDamageDealt(){
        return damageDealt;
    }

    public void setBaseHp(int newHp) {
        health = newHp;
    }

    public void setAttack(int newAttack) {
        attack = newAttack;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int newBaseAttack) {
        baseAttack = newBaseAttack;
    }

    public int getBaseHp(){
        return baseHp;
    }

    public int getLastUsedSkill(){
        return lastUsedSkill;
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    @Override public void skill1(){}
    @Override public void skill2(){}
    @Override public void skill3(){}
    @Override public void skill4(){}

    public int getHp() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getMoneyDrop() {
        return moneyDrop;
    }

    public void setHp(int health) {
        this.health = health;
    }

    @Override
    public EnemyAnimator getAnimator() {
        return animator;
    }

    public String getAction(){
        return actionString;
    }

    public void resetAndStartTimer() {
        // Stop existing timer if running
        if (autoCloseTimer != null && autoCloseTimer.isRunning()) {
            autoCloseTimer.stop();
        }
    
        // Create or update the timer
        if (autoCloseTimer == null) {
            autoCloseTimer = new Timer(autoCloseDelay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialogues.setVisible(false);
                    ((Timer)e.getSource()).stop();
                    System.out.println("auto close delay inside super class: " + autoCloseDelay);
                }
            });
            autoCloseTimer.setRepeats(false);
        } else {
            // Update the delay if the timer already exists
            autoCloseTimer.setInitialDelay(autoCloseDelay);
        }
    
        // Start the timer
        autoCloseTimer.restart();
    }
    

    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();

        if (animator.getIsInBattle()) return;
        player.getAnimator().setIsInBattle(true);
        animator.setIsInBattle(true);
        positionForBattle();
        onBattleStart();
    }

    public void positionForBattle() {
        setPosX(screenSize.width * 0.55);
        animator.setMovingRight(false);
        animator.stopMovement();
    }

    @Override
    public void onHover(MouseEvent e) {
        if(animator.getIsDead()) return;
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
    }
    
    @Override
    public void onExit(MouseEvent e) {
        if(animator.getIsInBattle() || animator.getIsDead()) return;
        animator.startMovement();
        animator.setPaused(false);
        animator.setInteracting(false);
    }

    protected abstract void onBattleStart();
}