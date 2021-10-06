package unsw.dungeon;


public interface Door{
    boolean passable();
    boolean unlock(Key key);
    boolean locked();
    int getID();
}