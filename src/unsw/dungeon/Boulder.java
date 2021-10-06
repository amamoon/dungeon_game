package unsw.dungeon;

public class Boulder extends Entity {

    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false);
    }

    public boolean moveBoulder(Direction d) {
        boolean moved = false;

        switch(d) {
            case UP:
                if ((getY() > 0) && dungeon.boulderCheck(getX(), getY()-1, this, d)) {
                    setY(getY()-1);
                    moved = true;
                }
                break;
            case DOWN:
                if ((getY() < dungeon.getHeight()) && dungeon.boulderCheck(getX(), getY()+1, this, d)) {
                    setY(getY()+1);
                    moved = true;
                }
                break;
            case LEFT:
                if ((getX() > 0) && dungeon.boulderCheck(getX()-1, getY(), this, d)) {
                    setX(getX()-1);
                    moved = true;
                }
                break;
            case RIGHT:
                if ((getX() < dungeon.getWidth()) && dungeon.boulderCheck(getX()+1, getY(), this, d)) {
                    setX(getX()+1);
                    moved = true;
                }
                break;
            default:
                moved = false;;
        }

        if (moved) dungeon.updateSwitches();
        
        return moved;
    }

    @Override
    public boolean isBoulder() {
        return true;
    }
    
}