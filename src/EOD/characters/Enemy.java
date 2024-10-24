package EOD.characters;

import EOD.MouseInteractable;
import EOD.animator.EnemyAnimator;
import EOD.listeners.MouseClickListener;
import EOD.scenes.SceneBuilder;
import java.awt.event.MouseEvent;

public abstract class Enemy extends Character implements MouseInteractable {
    protected Protagonist protagonist;
    protected int health;
    protected int attack;
    protected int moneyDrop;
    protected EnemyAnimator animator;
    private int index;
    protected int turnDuration;
    protected int damageDealt;
    public boolean missedTurn = false;
    private boolean isDefeated;

    public Enemy(String name, String characterType, SceneBuilder panel, int posX, int posY, 
        double minRange, double maxRange,
        Protagonist protagonist) {        
        super(name, characterType, panel, posX, posY);
        this.protagonist = protagonist;
        this.animator = new EnemyAnimator(this);
        setAnimator(animator);
        this.addMouseListener(new MouseClickListener(this));
        setVisible(true);
        animator.setRange(minRange, maxRange);
        isDefeated = false;
    }

    public abstract double getXFactor();
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

    public int getDamageDealt(){
        return damageDealt;
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    public abstract void skill1(); // basic attack
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