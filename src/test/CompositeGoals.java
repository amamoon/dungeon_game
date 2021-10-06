package test;

import org.junit.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import unsw.dungeon.*;

public class CompositeGoals {
    Dungeon dungeon;
    Player player;
    @Test
    public void OrGoal(){
                /**      X
         *   0 1 2 3 4 5
         *   1 P   E E 1
         *   2 S     E 2
         * Y 3 E       3
         *   4 E       4
         *   5 1 2 3 4 5
         */
        dungeon = new Dungeon(6,6);
        player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);

        dungeon.addEntity(new Wall(dungeon, 0,0));
        dungeon.addEntity(new Wall(dungeon, 0,1));
        dungeon.addEntity(new Wall(dungeon, 0,2));
        dungeon.addEntity(new Wall(dungeon, 0,3));
        dungeon.addEntity(new Wall(dungeon, 0,4));
        dungeon.addEntity(new Wall(dungeon, 0,5));
        dungeon.addEntity(new Wall(dungeon, 1,0));
        dungeon.addEntity(new Wall(dungeon, 2,0));
        dungeon.addEntity(new Wall(dungeon, 3,0));
        dungeon.addEntity(new Wall(dungeon, 4,0));
        dungeon.addEntity(new Wall(dungeon, 5,0));
        dungeon.addEntity(new Wall(dungeon, 1,5));
        dungeon.addEntity(new Wall(dungeon, 2,5));
        dungeon.addEntity(new Wall(dungeon, 3,5));
        dungeon.addEntity(new Wall(dungeon, 4,5));
        dungeon.addEntity(new Wall(dungeon, 5,5));
        dungeon.addEntity(new Wall(dungeon, 5,0));
        dungeon.addEntity(new Wall(dungeon, 5,1));
        dungeon.addEntity(new Wall(dungeon, 5,2));
        dungeon.addEntity(new Wall(dungeon, 5,3));
        dungeon.addEntity(new Wall(dungeon, 5,4));
        dungeon.addEntity(new Wall(dungeon, 5,5));

        Enemy e1 = new Enemy(dungeon, 4,4);
        Treasure t1 = new Treasure(dungeon,1,2);
        dungeon.addEnemy(e1);
        dungeon.addEntity(e1);
        dungeon.addEntity(t1);
        dungeon.incrementTreasure();

        JSONObject OrGoal = new JSONObject();
        JSONArray subgoals = new JSONArray();
        JSONObject goal1 = new JSONObject();
        JSONObject goal2 = new JSONObject();
        goal1.put("goal", "enemies");
        goal2.put("goal", "treasure");
        subgoals.put(goal1);
        subgoals.put(goal2);
        OrGoal.put("goal", "OR");
        OrGoal.put("subgoals",subgoals);
        dungeon.addGoal(OrGoal);

        player.moveDown();
        player.moveDown();
        player.moveDown();
        Assert.assertEquals(true, dungeon.checkComplete());
    }

    @Test
    public void AndGoal(){
                /**      X
         *   0 1 2 3 4 5
         *   1 P       1
         *   2 B       2
         * Y 3 S T     3
         *   4 e       4
         *   5 1 2 3 4 5
         */
        dungeon = new Dungeon(6,6);
        player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);

        dungeon.addEntity(new Wall(dungeon, 0,0));
        dungeon.addEntity(new Wall(dungeon, 0,1));
        dungeon.addEntity(new Wall(dungeon, 0,2));
        dungeon.addEntity(new Wall(dungeon, 0,3));
        dungeon.addEntity(new Wall(dungeon, 0,4));
        dungeon.addEntity(new Wall(dungeon, 0,5));
        dungeon.addEntity(new Wall(dungeon, 1,0));
        dungeon.addEntity(new Wall(dungeon, 2,0));
        dungeon.addEntity(new Wall(dungeon, 3,0));
        dungeon.addEntity(new Wall(dungeon, 4,0));
        dungeon.addEntity(new Wall(dungeon, 5,0));
        dungeon.addEntity(new Wall(dungeon, 1,5));
        dungeon.addEntity(new Wall(dungeon, 2,5));
        dungeon.addEntity(new Wall(dungeon, 3,5));
        dungeon.addEntity(new Wall(dungeon, 4,5));
        dungeon.addEntity(new Wall(dungeon, 5,5));
        dungeon.addEntity(new Wall(dungeon, 5,0));
        dungeon.addEntity(new Wall(dungeon, 5,1));
        dungeon.addEntity(new Wall(dungeon, 5,2));
        dungeon.addEntity(new Wall(dungeon, 5,3));
        dungeon.addEntity(new Wall(dungeon, 5,4));
        dungeon.addEntity(new Wall(dungeon, 5,5));


        Boulder boulder = new Boulder(dungeon, 1,2);
        Switch dswitch = new Switch(dungeon, 1,3);
        Treasure t1 = new Treasure(dungeon,2,3);
        dungeon.addEntity(boulder);
        dungeon.addEntity(dswitch);
        dungeon.addEntity(t1);
        dungeon.incrementTreasure();

        JSONObject AndGoal = new JSONObject();
        JSONArray subgoals = new JSONArray();
        JSONObject goal1 = new JSONObject();
        JSONObject goal2 = new JSONObject();
        goal1.put("goal", "boulders");
        goal2.put("goal", "treasure");
        subgoals.put(goal1);
        subgoals.put(goal2);
        AndGoal.put("goal", "AND");
        AndGoal.put("subgoals",subgoals);
        dungeon.addGoal(AndGoal);

        player.moveDown();
        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveRight();
        player.moveDown();
        player.moveDown();
        player.moveLeft();
        Assert.assertEquals(true, dungeon.checkComplete());
    }

}