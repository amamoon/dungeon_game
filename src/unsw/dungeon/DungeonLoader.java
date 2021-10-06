package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height+1);

        JSONArray jsonEntities = json.getJSONArray("entities");
        JSONObject jsonGoals = json.getJSONObject("goal-condition");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        dungeon.addGoal(jsonGoals);
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(dungeon, x, y);
            onLoad(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x,y);
            onLoad(enemy);
            dungeon.addEnemy(enemy);
            entity=enemy;
            break;
        case "sword":
            Sword sword = new Sword(dungeon, x,y);
            onLoad(sword);
            entity=sword;
            break;
        case "key":
            int keyid = json.getInt("id");
            Key key = new Key(dungeon, x,y,keyid);
            onLoad(key);
            entity=key;
            break;
        case "treasure":
            Treasure treasure = new Treasure(dungeon, x,y);
            onLoad(treasure);
            dungeon.incrementTreasure();
            entity=treasure;
            break;
        case "potion":
            Potion potion = new Potion(dungeon, x,y);
            onLoad(potion);
            entity=potion;
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x,y);
            onLoad(boulder);
            entity=boulder;
            break;
        case "switch":
            Switch dswitch = new Switch(dungeon, x,y); //"dswitch" for "dungeon switch" because "switch" is a problematic variable name
            onLoad(dswitch);
            entity=dswitch;
            break;
        case "portal":
            int portalid = json.getInt("id");
            Portal portal = new Portal(dungeon, x,y, portalid);
            onLoad(portal);
            entity=portal;
            break;
        case "door":
            int doorid = json.getInt("id");
            DoorEntity door = new DoorEntity(dungeon, x,y, doorid);
            onLoad(door);
            entity=door;
            break;
        case "exit":
            Exit exit = new Exit(dungeon, x,y);
            onLoad(exit);
            dungeon.setExit(exit);
            entity=exit;
            break;
        case "mirrorenemy":
            MirrorEnemy mirrorEnemy = new MirrorEnemy(dungeon, x,y);
            onLoad(mirrorEnemy);
            dungeon.addEnemy(mirrorEnemy);
            entity=mirrorEnemy;
            break;
        }
        dungeon.addEntity(entity);
    }
    
    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Potion potion);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Switch dswitch);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(DoorEntity door);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(MirrorEnemy mirrorEnemy);

}
