package unsw.dungeon;

public class Treasure extends Item {
    
    public Treasure(Dungeon dungeon, int x, int y) {
        super(dungeon, x,y);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}