package unsw.dungeon;

public class Sword extends Item {

    int durability;
    
    public Sword(Dungeon dungeon, int x, int y) {
        super(dungeon, x,y);
        durability = 5;
    }

    public void reduceDurability() {
        durability--;
    }

    public boolean usable() {
        return (durability > 0);
    }

    @Override
    public boolean isSword() {
        return true;
    }
}