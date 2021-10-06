package test;

import org.junit.Test;
import org.json.JSONObject;
import org.junit.Assert;
import unsw.dungeon.*;

public class Completion {

    Dungeon dungeon;
    Player player;
    
    @Test
    public void Exit(){
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

        Exit exit = new Exit(dungeon,1,2);
        dungeon.addEntity(exit);
        dungeon.setExit(exit);
        JSONObject exitGoal = new JSONObject();

        exitGoal.put("goal", "exit");
        dungeon.addGoal(exitGoal);

        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveDown();
        Assert.assertEquals(true, dungeon.checkComplete());
    }

    @Test
    public void Switches(){
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

        Switch dswitch = new Switch(dungeon, 1, 3);
        Boulder boulder = new Boulder(dungeon, 1, 2);
        dungeon.addEntity(dswitch);
        dungeon.addEntity(boulder);

        JSONObject boulderGoal = new JSONObject();
        boulderGoal.put("goal", "boulders");
        dungeon.addGoal(boulderGoal);

        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveDown();
        Assert.assertEquals(true, dungeon.checkComplete());
    }
    @Test
    public void MultipleSwitches(){
        /**      X
         *   0 1 2 3 4 5
         *   1 P       1
         *   2 B B S   2
         * Y 3 S       3
         *   4         4
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

        Switch dswitch = new Switch(dungeon, 1, 3);
        Boulder boulder = new Boulder(dungeon, 1, 2);
        Switch dSwitch2 = new Switch(dungeon, 3, 2);
        Boulder boulder2 = new Boulder(dungeon, 2, 2);
        dungeon.addEntity(dswitch);
        dungeon.addEntity(boulder);
        dungeon.addEntity(dSwitch2);
        dungeon.addEntity(boulder2);

        JSONObject boulderGoal = new JSONObject();
        boulderGoal.put("goal", "boulders");
        dungeon.addGoal(boulderGoal);

        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveDown();
        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveRight();
        Assert.assertEquals(true, dungeon.checkComplete());
    }

    @Test
    public void EnemyGoal(){
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

        Enemy e1 = new Enemy(dungeon, 1,3);
        Sword s = new Sword(dungeon, 1,2);
        dungeon.addEnemy(e1);
        dungeon.addEntity(e1);
        dungeon.addEntity(s);


        JSONObject enemyGoal = new JSONObject();
        enemyGoal.put("goal", "enemies");
        dungeon.addGoal(enemyGoal);

        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveDown();
        player.swingSword();
        Assert.assertEquals(true, dungeon.checkComplete());
    }
    @Test
    public void MultipleEnemyGoal(){
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

        Enemy e1 = new Enemy(dungeon, 1,3);
        Enemy e2 = new Enemy(dungeon, 1, 4);
        Sword s = new Sword(dungeon, 1,2);
        dungeon.addEnemy(e1);
        dungeon.addEnemy(e2);
        dungeon.addEntity(e1);
        dungeon.addEntity(e2);
        dungeon.addEntity(s);


        JSONObject enemyGoal = new JSONObject();
        enemyGoal.put("goal", "enemies");
        dungeon.addGoal(enemyGoal);

        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveDown();
        player.swingSword();
        player.moveDown();
        player.swingSword();
        Assert.assertEquals(true, dungeon.checkComplete());
    }

    @Test
    public void TreasureGoal(){
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

        Treasure t1 = new Treasure(dungeon, 1,2);
        dungeon.addEntity(t1);
        dungeon.incrementTreasure();

        JSONObject treasureGoal = new JSONObject();
        treasureGoal.put("goal", "treasure");
        dungeon.addGoal(treasureGoal);

        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveDown();
        Assert.assertEquals(true, dungeon.checkComplete());
        Assert.assertEquals(1, player.getPlayerTreasureCount());
    }
    @Test
    public void MultipleTreasureGoal(){
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

        Treasure t1 = new Treasure(dungeon, 1,2);
        Treasure t2 = new Treasure(dungeon, 1,3);
        dungeon.addEntity(t1);
        dungeon.addEntity(t2);
        dungeon.incrementTreasure();
        dungeon.incrementTreasure();

        JSONObject treasureGoal = new JSONObject();
        treasureGoal.put("goal", "treasure");
        dungeon.addGoal(treasureGoal);

        Assert.assertEquals(false, dungeon.checkComplete());
        player.moveDown();
        Assert.assertEquals(false, dungeon.checkComplete());
        Assert.assertEquals(1, player.getPlayerTreasureCount());
        player.moveDown();
        player.moveDown();
        Assert.assertEquals(true, dungeon.checkComplete());
    }
}