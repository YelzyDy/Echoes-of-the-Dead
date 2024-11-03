package EOD.characters.enemies;

import EOD.animator.EnemyAnimator;
import EOD.characters.Character;
import EOD.characters.Player;
import EOD.gameInterfaces.EnemyBlueprint;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import java.awt.event.MouseEvent;

public abstract class Enemy extends Character implements MouseInteractable, EnemyBlueprint{
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
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    @Override
    public double getXFactor(){
        return xFactor;
    }

    @Override
    public double getYFactor(){
        return yFactor;
    }

    public abstract double getOffsetX(int skill);
    public abstract double getOffsetY(int skill);
    public abstract double getOffsetW(int skill);
    public abstract double getOffsetH(int skill);

    @Override
    public void setIsDefeated(boolean isDefeated){
        this.isDefeated = isDefeated;
    }

    @Override
    public boolean getIsDefeated(){
        return isDefeated;
    }

    public abstract void update();

    public abstract int decideSkill();

    @Override
    public int getDamageDealt(){
        return damageDealt;
    }

    @Override
    public void setBaseHp(int newHp) {
        health = newHp;
    }

    @Override
    public void setAttack(int newAttack) {
        attack = newAttack;
    }

    @Override
    public int getBaseAttack() {
        return baseAttack;
    }

    @Override
    public void setBaseAttack(int newBaseAttack) {
        baseAttack = newBaseAttack;
    }

    @Override
    public int getBaseHp(){
        return baseHp;
    }

    @Override
    public int getLastUsedSkill(){
        return lastUsedSkill;
    }

    @Override
    public void takeDamage(int damage){
        health -= damage;
    }

    @Override
    public void skill1(){

    } 
    
    @Override
    public void skill2(){

    }

    @Override
    public void skill3(){

    }

    @Override
    public int getHp() {
        return health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getMoneyDrop() {
        return moneyDrop;
    }

    @Override
    public void setHp(int health) {
        this.health = health;
    }

    @Override
    public EnemyAnimator getAnimator() {
        return animator;
    }

    @Override
    public String getAction(){
        return actionString;
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

    @Override
    public void positionForBattle() {
        setPosX(screenSize.width * 0.55);
        animator.setMovingRight(false);
        animator.stopMovement();
    }

    @Override
    public void onHover(MouseEvent e) {
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
    }
    
    @Override
    public void onExit(MouseEvent e) {
        if(animator.getIsInBattle()) return;
        animator.startMovement();
        animator.setPaused(false);
        animator.setInteracting(false);
    }

    protected abstract void onBattleStart();
}