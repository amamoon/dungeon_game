package test;

import org.junit.Assert;
import org.junit.Test;

import unsw.dungeon.*;

//US 1.2, 1.3, 1.4
//Player Avatar + Movement + Solid Obstacles

public class Exploration {
    Dungeon dungeon;
    Player player;

    @Test
    public void Movement(){
        dungeon = new Dungeon(4,4);
        player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);

        dungeon.addEntity(new Wall(dungeon, 0,0));
        dungeon.addEntity(new Wall(dungeon, 0,1));
        dungeon.addEntity(new Wall(dungeon, 0,2));
        dungeon.addEntity(new Wall(dungeon, 0,3));
        dungeon.addEntity(new Wall(dungeon, 1,0));
        dungeon.addEntity(new Wall(dungeon, 2,0));
        dungeon.addEntity(new Wall(dungeon, 3,0)); 

        player.moveDown();
        Assert.assertEquals(1,player.getX());
        Assert.assertEquals(2,player.getY());

        player.moveLeft();
        Assert.assertEquals(1, player.getX());
        Assert.assertEquals(2, player.getY());

        player.moveRight();
        Assert.assertEquals(2, player.getX());
        Assert.assertEquals(2, player.getY());

        player.moveUp();
        Assert.assertEquals(2, player.getX());
        Assert.assertEquals(1, player.getY());
    }

    @Test
    public void InventoryEmpty(){

        dungeon = new Dungeon(4,4);
        player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);

        dungeon.addEntity(new Wall(dungeon, 0,0));
        dungeon.addEntity(new Wall(dungeon, 0,1));
        dungeon.addEntity(new Wall(dungeon, 0,2));
        dungeon.addEntity(new Wall(dungeon, 0,3));
        dungeon.addEntity(new Wall(dungeon, 1,0));
        dungeon.addEntity(new Wall(dungeon, 2,0));
        dungeon.addEntity(new Wall(dungeon, 3,0)); 

        Assert.assertEquals(0,player.getInventory().size());
    }
    @Test
    public void pickUpItems(){
        dungeon = new Dungeon(4,4);
        player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);

        dungeon.addEntity(new Wall(dungeon, 0,0));
        dungeon.addEntity(new Wall(dungeon, 0,1));
        dungeon.addEntity(new Wall(dungeon, 0,2));
        dungeon.addEntity(new Wall(dungeon, 0,3));
        dungeon.addEntity(new Wall(dungeon, 1,0));
        dungeon.addEntity(new Wall(dungeon, 2,0));
        dungeon.addEntity(new Wall(dungeon, 3,0)); 
        dungeon.addEntity(new Treasure(dungeon,1,2));
        dungeon.addEntity(new Sword(dungeon, 1, 3));

        player.moveDown();
        Assert.assertEquals(1, player.getInventory().size());
        player.moveDown();
        Assert.assertEquals(2, player.getInventory().size());
    }
}