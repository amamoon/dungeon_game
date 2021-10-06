package unsw.dungeon;

public class Node {
    private final int x;
    private final int y;

    
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}