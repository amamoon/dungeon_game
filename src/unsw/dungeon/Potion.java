package unsw.dungeon;

public class Potion extends Item {
    
    public Potion(Dungeon dungeon, int x, int y) {
        super(dungeon, x,y);
    }

    @Override
    public boolean isPotion() {
        return true;
    }
}