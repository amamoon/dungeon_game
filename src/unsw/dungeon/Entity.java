package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.

    private IntegerProperty x, y;
    protected boolean movable;
    protected Dungeon dungeon;


    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     * @param move , is the object movable ie player, enemies vs immobile walls or potions
     */
    public Entity(Dungeon dungeon, int x, int y, boolean move) {
        this.dungeon = dungeon;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.movable = move;
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public void setX(int x){
        x().set(x);
    }
    
    public void setY(int y){
        y().set(y);
    }

    public boolean isMovable() {
        return this.movable;
    }

    public boolean isWall(){
        return false;
    }

    public boolean isEnemy(){
        return false;
    }

    public boolean isItem(){
        return false;
    }

    public boolean isBoulder(){
        return false;
    }

    public boolean isSwitch() {
        return false;
    }

    public boolean isPortal(){
        return false;
    }

    public boolean isLockedDoor() {
        return false;
    }

    public boolean isExit() {
        return false;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }

    /*
    public void setX(IntegerProperty x){
        this.x = x;
    }
    
    public void setY(IntegerProperty y){
        this.y = y;
    }
    */
}
