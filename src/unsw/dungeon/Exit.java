package unsw.dungeon;

public class Exit extends Entity {

    public Exit(Dungeon dungeon, int x, int y) {
        super(dungeon, x,y, false);
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}