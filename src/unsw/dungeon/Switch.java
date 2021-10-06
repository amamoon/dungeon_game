package unsw.dungeon;

import java.util.ArrayList;

public class Switch extends Entity {

    private ArrayList<Observer> observers = new ArrayList<Observer>();
    //state
    private boolean active;

    public Switch(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y, false);
        this.active = false;
        attach(new SwitchObserver(dungeon, this));
    }

    public boolean isActive() {
        return active;
    }

    public void activate(boolean b) {
        active = b;
        //System.err.println("a switch is now "+b);
        //notifyAllObservers();
    }



    @Override
    public boolean isSwitch() {
        return true;
    }

    public void attach(Observer o){
        observers.add(o);
    }

    public void notifyAllObservers(){
        for(Observer o: observers){
            o.update();
        }
    }
    
}