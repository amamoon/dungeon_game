package test;

import org.junit.Test;

import org.junit.Assert;
import unsw.dungeon.*;

public class Combat {
    Dungeon dungeon;
    Player player;
    
    @Test
    public void EnemiesTest(){

        /**      X
         *   0 1 2 3 4 5
         *   1 P W   E 1
         *   2 S       2
         * Y 3         3
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
        
        Enemy e1 = new Enemy(dungeon, 4, 1);
        Enemy e2 = new Enemy(dungeon, 1, 4);

        dungeon.addEnemy(e1);
        dungeon.addEnemy(e2);
        dungeon.addEntity(e1);
        dungeon.addEntity(e2);

        Sword s = new Sword(dungeon, 1,2);
        dungeon.addEntity(s);

        player.moveDown();
        Assert.assertEquals(4, e1.getX());
        Assert.assertEquals(4, e2.getY());
        Assert.assertEquals(1, player.getInventory().size());
        player.moveDown();
        player.swingSword();
        Assert.assertEquals(1, dungeon.getNumberOfEnemies());

        dungeon.moveEnemies();
        dungeon.moveEnemies();
        dungeon.moveEnemies();
        Assert.assertNotEquals(4, e1.getX());
        dungeon.moveEnemies();
        dungeon.moveEnemies();
        dungeon.moveEnemies();
        dungeon.moveEnemies();
        dungeon.moveEnemies();
        dungeon.moveEnemies();
        Assert.assertEquals(1, player.getX());
    }

    @Test
    public void SwordDurability(){
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

        Enemy e1 = new Enemy(dungeon, 1, 3);
        Enemy e2 = new Enemy(dungeon, 1, 4);
        Enemy e3 = new Enemy(dungeon, 3, 1);
        Enemy e4 = new Enemy(dungeon, 4, 1);
        Enemy e5 = new Enemy(dungeon, 4, 3);

        dungeon.addEnemy(e1);
        dungeon.addEnemy(e2);
        dungeon.addEnemy(e3);
        dungeon.addEnemy(e4);
        dungeon.addEnemy(e5);
        dungeon.addEntity(e1);
        dungeon.addEntity(e2);
        dungeon.addEntity(e3);
        dungeon.addEntity(e4);
        dungeon.addEntity(e5);

        Sword s = new Sword(dungeon, 1, 2);
        dungeon.addEntity(s);

        player.moveDown();
        Assert.assertEquals(1, player.getInventory().size());

        player.swingSword();
        
        player.moveDown();
        player.swingSword();

        player.moveUp();
        player.moveUp();
        player.moveRight();
        player.swingSword();

        player.moveRight();
        player.swingSword();
        
        player.moveRight();
        player.moveDown();

        Assert.assertEquals(true, s.usable());
        player.swingSword();
        Assert.assertEquals(false, s.usable());
    }


    @Test
    public void Invincibility(){
        /**      X
         *   0 1 2 3 4 5
         *   1 P   E E 1
         *   2 p     E 2
         * Y 3 E       3
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

        Potion p = new Potion(dungeon, 1,2);
        Enemy e1 = new Enemy(dungeon, 1,3);
        dungeon.addEntity(p);
        dungeon.addEnemy(e1);
        dungeon.addEntity(e1);

        player.moveDown();
        Assert.assertEquals(true, player.getInvicibilityStatus());
        dungeon.moveEnemies();
        Assert.assertEquals(4, e1.getY());
        player.moveDown();
    }

}