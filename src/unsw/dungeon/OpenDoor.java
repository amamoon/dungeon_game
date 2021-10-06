package unsw.dungeon;

public class OpenDoor implements Door{
    
    @Override
    public boolean passable(){
        return true;
    }
    @Override
    public boolean unlock(Key key){
        return false;
    }

    @Override
    public boolean locked(){
        return false;
    }

    @Override
    public int getID(){
        return 0;
    }

}