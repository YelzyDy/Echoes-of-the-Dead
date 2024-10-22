package EOD.characters;

import EOD.MouseInteractable;
import EOD.animator.EnemyAnimator;
import EOD.listeners.MouseClickListener;
import EOD.scenes.SceneBuilder;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

public abstract class Enemy extends Character implements MouseInteractable {
    protected Protagonist protagonist;
    protected int health;
    protected int attack;
    protected int moneyDrop;
    protected EnemyAnimator animator;
    protected SceneBuilder panel;
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected double minRange;
    protected double maxRange;
    protected int numIdleSprites;
    protected int numWalkSprites;
    private int index;
    protected int turnDuration;
    protected int damageDealt;
    public Enemy(String name, String characterType, SceneBuilder panel, int posX, int posY, 
        double minRange, double maxRange, int numIdleSprites, int numWalkSprites, 
        Protagonist protagonist) {
            
        super(name, characterType, panel, posX, posY);
        this.protagonist = protagonist;
        this.panel = panel;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.numIdleSprites = numIdleSprites;
        this.numWalkSprites = numWalkSprites;
        this.animator = new EnemyAnimator(this);
        setAnimator(animator);
        this.addMouseListener(new MouseClickListener(this));
        setVisible(true);
    }

    public int getDamageDealt(){
        return damageDealt;
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    public abstract void skill1();
    public abstract void skill2();
    
    public int getTurnDuration(){
        return turnDuration;
    }

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex(){
        return index;
    }

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

    public EnemyAnimator getAnimator() {
        return animator;
    }

    public double getMinRange() {
        return minRange;
    }

    public double getMaxRange() {
        return maxRange;
    }

    @Override
    public void onClick(MouseEvent e) {
        animator.stopMovement();
        if (animator.getIsInBattle()) return;
        protagonist.getAnimator().setIsInBattle(true);
        animator.setIsInBattle(true);
        positionForBattle();
        onBattleStart();
    }

    protected void positionForBattle() {
        protagonist.getAnimator().stopMovement();
        protagonist.setPosX(screenSize.width * 0.35);
        setPosX(screenSize.width * 0.55);
        protagonist.getAnimator().setMovingRight(true);
        animator.setMovingRight(false);
    }

    @Override
    public void onHover(MouseEvent e) {
        if (animator.getIsInBattle()) return;
        animator.stopMovement();
    }

    @Override
    public void onExit(MouseEvent e) {
        if (animator.getIsInBattle()) return;
        animator.startMovement();
    }

    protected abstract void onBattleStart();
}