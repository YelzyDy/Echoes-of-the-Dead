package EOD.characters;

import EOD.MouseInteractable;
import EOD.animator.EnemyAnimator;
import EOD.listeners.MouseClickListener;
import java.awt.event.MouseEvent;

public abstract class Enemy extends Character implements MouseInteractable {
    protected Protagonist protagonist;
    protected int health;
    protected int attack;
    protected int moneyDrop;
    protected int baseHp;
    protected EnemyAnimator animator;
    private int index;
    protected int damageDealt;
    public boolean missedTurn = false;
    private boolean isDefeated;
    protected String actionString;
    protected int lastUsedSkill;

    public Enemy(String name, String characterType, int posX, int posY, 
        double minRange, double maxRange,
        Protagonist protagonist) {        
        super(name, characterType, posX, posY);
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

    public abstract void update();

    public abstract int decideSkill();

    public int getDamageDealt(){
        return damageDealt;
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

    public abstract void skill1(); // basic attack
    public abstract void skill2();

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

    public String getAction(){
        return actionString;
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
        animator.stopMovement();
        animator.setPaused(true);
        animator.setInteracting(true);
    }
    
    @Override
    public void onExit(MouseEvent e) {
        animator.startMovement();
        animator.setPaused(false);
        animator.setInteracting(false);
    }

    protected abstract void onBattleStart();
}