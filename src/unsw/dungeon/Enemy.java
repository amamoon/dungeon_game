package unsw.dungeon;

import java.util.LinkedList;
import java.util.Timer;


public class Enemy extends Entity {
    
    Timer t;
    private boolean active;
    private int initX, initY;
    

    public Enemy(Dungeon dungeon, int x, int y){
        super(dungeon, x,y,true);
        initX = x;
        initY = y;
        t = new Timer();
        active = true;
    }

    @Override
    public boolean isEnemy(){
        return true;
    }
    /**
     * BFS from https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
     * @return Direction on which way for the enemy to move next
     */
    public Direction track(){
        Node[][] closed = new Node[dungeon.getWidth()][dungeon.getHeight()];
        boolean found = false;
        LinkedList<Node> queue = new LinkedList<Node>();
        Player target = dungeon.getPlayer();
        Direction d = Direction.UP;
        int startX = getX();
        int startY = getY();

        Node enemyStart = new Node(this.getX(), this.getY());
        Node current = enemyStart;

        closed[startX][startY]= enemyStart;
        queue.add(enemyStart);

        while(queue.size() != 0 && !found){
            current = queue.poll();
            LinkedList<Node> neighbours = new LinkedList<Node>();
            instantiateNeighbours(neighbours, current);

            for(Node node: neighbours){
                if(node.getX() == target.getX() && node.getY() == target.getY()){
                    found = true;
                    closed[node.getX()][node.getY()] = current;
                    current = node;
                }else if(closed[node.getX()][node.getY()]==null && !pathBlocked(node)){
                    closed[node.getX()][node.getY()] = current;
                    queue.add(node);
                }
            }
        }

        while(closed[current.getX()][current.getY()] != enemyStart){
            current = closed[current.getX()][current.getY()];
        }

        d = getDirectionFromSearch(current);

        return d;
    }

    private Direction getDirectionFromSearch(Node current){

        Direction d = Direction.UP;

        if(current.getY() < this.getY()){
            d = Direction.UP;
        }else if(current.getY() > this.getY()){
            d = Direction.DOWN;
        }else if(current.getX() < this.getX()){
            d =  Direction.LEFT;
        }else if(current.getX() >  this.getX()){
            d = Direction.RIGHT;
        }

        return d;
    }


    private boolean pathBlocked(Node node) {
        int x = node.getX();
        int y = node.getY();
        boolean blocked = false;
        if(!(dungeon.xWithinBounds(x)&&dungeon.yWithinBounds(y))) return true; 
        for(Entity e:dungeon.entityAtCoord(x, y)){
            if(e == null) continue;
            if(!e.movable) blocked = true;
        }
		return blocked;
    }
    
    public LinkedList<Node> instantiateNeighbours(LinkedList<Node> neighbours, Node current){
        //IP
        if(current.getY() > 0)  
            neighbours.add(new Node(current.getX(),current.getY()-1));
        //DOWN
        if(current.getY() < dungeon.getHeight()-1)   
            neighbours.add(new Node(current.getX(), current.getY()+1));
        //LEFT
        if(current.getX() > 0)  
            neighbours.add(new Node(current.getX()-1, current.getY()));
        //RIGHT
        if(current.getX() < dungeon.getWidth()-1)   
            neighbours.add(new Node(current.getX()+1, current.getY()));
        
        return neighbours;
    }

    public void runAway(){
        Direction d = getPlayer().getDirection();
        switch(d){
            case UP: //UP
                move(getX(), getY()-1,d);
                break;
            case DOWN: //DOWN
                move(getX(), getY()+1,d);
                break;
            case LEFT: //LEFT
                move(getX()-1, getY(),d);
                break;
            case RIGHT: //RIGHT
                move(getX()+1, getY(),d);
                break;
            default:
                break;
        }
    }
    
    public void move(int targetX, int targetY, Direction d){
        if(dungeon.checkMove(targetX, targetY, d)){
            setX(targetX);
            setY(targetY);
        }else{
            //dont move
            return;
        }
    }

    public void move(){
        Direction d = track();
        switch(d){
            case UP: //UP
                move(getX(), getY()-1, d);
                break;
            case DOWN: //DOWN
                move(getX(), getY()+1, d);
                break;
            case LEFT: //LEFT
                move(getX()-1, getY(), d);
                break;
            case RIGHT: //RIGHT
                move(getX()+1, getY(), d);
                break;
            default:
                break;
        }
    }

    private Player getPlayer(){
        return getDungeon().getPlayer();
    }

    public boolean isActive() {
        return active;
    }

    public void killEnemy() {
        setX(dungeon.getWidth()+1);
        setY(dungeon.getHeight()+1);
        dungeon.removeEntity(this);
        dungeon.removeEnemy(this);
        active = false;
        if (dungeon.loadAudio()) {
            dungeon.playSoundEffect("enemyDeath");
        }
    }

    public void resetPos() {
        setX(initX);
        setY(initY);
    }
}