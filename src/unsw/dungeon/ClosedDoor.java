package unsw.dungeon;

public class ClosedDoor implements Door{
    
    private int id;


    public ClosedDoor(int id){
        this.id=id;
    }


    @Override
    public boolean passable(){
        return false;
    }
    @Override
    public boolean unlock(Key key){
        if(key.getID() == getID())
            return true;
        return false;
    }

    public int getID(){
        return id;
    }

    @Override
    public boolean locked(){
        return true;
    }

}