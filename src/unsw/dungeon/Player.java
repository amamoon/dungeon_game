package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private int initX, initY;
    private boolean invincibleStatus;
    private ArrayList<Item> inventory;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private Direction direction;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, true);
        initX = x;
        initY = y;
        this.inventory = new ArrayList<Item>();
        invincibleStatus = false;
    }

    public void moveUp() {
        if (getY() > 0 && dungeon.checkMove(getX(), getY()-1, Direction.UP)) {
            y().set(getY() - 1);
            setDirection(Direction.UP);
            dungeon.checkPickup(getX(), getY(), this);
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && dungeon.checkMove(getX(), getY() + 1, Direction.DOWN)) {
            y().set(getY() + 1);
            setDirection(Direction.DOWN);
            dungeon.checkPickup(getX(), getY(), this);
        }
    }

    public void moveLeft() {
        if (getX() > 0 && dungeon.checkMove(getX() - 1, getY(), Direction.LEFT)) {
            x().set(getX() - 1);
            setDirection(Direction.LEFT);
            dungeon.checkPickup(getX(), getY(), this);
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && dungeon.checkMove(getX()  + 1, getY(), Direction.RIGHT)) {
            x().set(getX() + 1);
            setDirection(Direction.RIGHT);
            dungeon.checkPickup(getX(), getY(), this);
        }
    }

    public int getPlayerTreasureCount(){
        int treasureCount = 0;
        for(Item i:inventory){
            if(i.isTreasure()){
                treasureCount++;
            }
        }
        return treasureCount;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void removeFromInventory(Item i) {
        i.setX(dungeon.getWidth()+1);
        i.setY(dungeon.getHeight()+1);
        inventory.remove(i);
    }

    public boolean pickUp(Item i) {
        if (i.isTreasure() || i.isPotion()) {
            inventory.add(i);
            if(i.isPotion()){
                attach(new InvincibilityObserver(this));
                updateAll();
            }
            return true;
        }

        for (Item item : inventory) {
            if ((item.isSword() && i.isSword()) || (item.isKey() && i.isKey())) {
                return false;
            }
        }

        inventory.add(i);
        return true;
    }

    public void swingSword() {
        for (Item i : inventory) {
            if (!i.isSword()) {
                continue;
            }
            Sword sword = (Sword) i;
            if (!sword.usable()) {
                removeFromInventory(i);
            }
            dungeon.killEnemies(getX(), getY(), sword);
            
        }
    }

    public void setInvincible(){
        invincibleStatus = true;
        //TODO remove item from inv
        updateAll();
    }

    public boolean getInvicibilityStatus(){
        return invincibleStatus;
    }

    public void removeInvicible(){
        invincibleStatus = false;
    }

    public void attach(Observer o){
        this.observers.add(o);
    }

    public void updateAll(){
        for(Observer o: observers) o.update();
    }
    
    public void setDirection(Direction d){
        direction = d;
    }

    public Direction getDirection(){
        return direction;
    }

    public void killedBy(Enemy enemy) {
        setX(initX);
        setY(initY);
        enemy.resetPos();
    }

}
