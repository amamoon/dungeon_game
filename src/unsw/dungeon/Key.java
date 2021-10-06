package unsw.dungeon;

public class Key extends Item {
    
    private int id;

    public Key(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x,y);
        this.id = id;
    }

    public int getID() {
        return id;
    }

    @Override
    public boolean isKey() {
        return true;
    }
}