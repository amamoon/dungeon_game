package unsw.dungeon;

public class MirrorEnemy extends Enemy {

    public MirrorEnemy(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    @Override
    public Direction track() {
        Direction d = dungeon.getPlayer().getDirection();
        Direction mirrorDir = null;

        switch(d){
            case UP: //UP
                mirrorDir = Direction.DOWN;
                break;
            case DOWN: //DOWN
                mirrorDir = Direction.UP;
                break;
            case LEFT: //LEFT
                mirrorDir = Direction.RIGHT;
                break;
            case RIGHT: //RIGHT
                mirrorDir = Direction.LEFT;
                break;
            default:
                break;
        }

        return mirrorDir;
    }
    
}