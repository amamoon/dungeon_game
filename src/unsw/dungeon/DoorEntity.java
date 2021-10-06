package unsw.dungeon;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DoorEntity extends Entity{

    private ObjectProperty<Door> type;

    public DoorEntity(Dungeon dungeon, int x, int y, int id){
        super(dungeon, x,y, false);
        type = new SimpleObjectProperty<Door>(new ClosedDoor(id));
    }

    public ObjectProperty<Door> type() {
        return type;
    }

    public Door getType(){
        return type.get();
    }

    public int getID(){
        return type.get().getID();
    }
    
    private void setType(Door type){
        this.type.set(type);
    }

    public void unlock(){
        this.setType(new OpenDoor());
        this.movable = true;
    }

    public boolean closed(){
        return type.get().locked();
    }

    @Override
    public boolean isLockedDoor() {
        return closed();
    }
}