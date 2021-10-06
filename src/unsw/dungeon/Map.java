package unsw.dungeon;

public class Map {

    private Dungeon dungeon;
    private int width;
    private int height;
    private int[][] grid;
    private boolean[][] visited;

    public Map(Dungeon d) {
        dungeon = d;
        width = d.getWidth();
        height = d.getHeight();
        setGrid(new int[width][height]);
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
    
    public Dungeon getDungeon() {
        return this.dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public Player getPlayer(){
        return dungeon.getPlayer();
    }

    public boolean[][] isVisited() {
        return this.visited;
    }

    public void setVisited(boolean[][] visited) {
        this.visited = visited;
    }

}