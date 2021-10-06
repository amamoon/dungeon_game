package unsw.dungeon;

import java.util.ArrayList;

public class SwitchObserver extends Observer {

    boolean boulderOnTop = false;
    private Dungeon dungeon;
    private Switch sw;

    public SwitchObserver(Dungeon d, Switch s){
        dungeon = d;
        entity = s;
        sw = s;
    }


    public void update(){
        if(checkBoulder(entity.getX(),entity.getY())){
            sw.activate(true);
        }else{
            sw.activate(false);
        }
    }

    public boolean checkBoulder(int x, int y){
        ArrayList<Entity> entities = dungeon.entityAtCoord(x, y);
        for(Entity e: entities){
            if(e.isBoulder()){
                return true;
            }
        }

        return false;
    }
}