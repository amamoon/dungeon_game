package unsw.dungeon;

/**
 * 
 * Item entities
 * 
 */
public class Item extends Entity {

    public Item(Dungeon dungeon, int x, int y) {
        super(dungeon, x,y, false);
    }

    @Override
    public boolean isItem() {
        return true;
    }

    public boolean isSword() {
        return false;
    }

    public boolean isKey() {
        return false;
    }

    public boolean isTreasure() {
        return false;
    }
    
    public boolean isPotion() {
        return false;
    }

}