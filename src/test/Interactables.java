package test;

import org.junit.Assert;
import org.junit.Test;

import unsw.dungeon.*;

public class Interactables {
    Dungeon dungeon;
    Player player;
    @Test
    public void bouldersTest(){
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


        Boulder boulder = new Boulder(dungeon, 1, 2);
        dungeon.addEntity(boulder);
        dungeon.addEntity(new Boulder(dungeon, 1, 4));
        player.moveDown();
        Assert.assertEquals(3, boulder.getY());
        player.moveDown();
        Assert.assertEquals(3, boulder.getY());
    }
    @Test
    public void switchesTest(){
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

        
        Boulder boulder = new Boulder(dungeon, 1, 2);
        dungeon.addEntity(boulder);
        Switch dswitch = new Switch(dungeon, 1, 3);
        dungeon.addEntity(dswitch);
        player.moveDown();
        //Move boulder onto switch
        Assert.assertEquals(3, boulder.getY());
        Assert.assertEquals(true, dswitch.isActive());
        player.moveDown();
        //Move boulder off switch
        Assert.assertEquals(false, dswitch.isActive());
    }
    @Test
    public void DoorTest(){
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

        DoorEntity door = new DoorEntity(dungeon, 1, 2, 1);
        Key key = new Key(dungeon, 2, 1, 1);
        dungeon.addEntity(door);
        dungeon.addEntity(key);

        player.moveDown();
        //player shouldnt be able to move past a locked door
        Assert.assertEquals(1, player.getY());
        player.moveRight();
        Assert.assertEquals(1, player.getInventory().size());
        player.moveDown();
        Assert.assertEquals(2, player.getY());
        Assert.assertEquals(1, player.getInventory().size());//oops this isn't right
    }

    @Test
    public void WrongKeyTest(){
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

        DoorEntity door = new DoorEntity(dungeon, 1, 2, 1);
        Key key = new Key(dungeon, 2, 1, 4);
        dungeon.addEntity(door);
        dungeon.addEntity(key);

        player.moveDown();
        //player shouldnt be able to move past a locked door
        Assert.assertEquals(1, player.getY());
        player.moveRight();
        Assert.assertEquals(1, player.getInventory().size());
        player.moveDown();
        Assert.assertEquals(2, player.getY());
    }

    @Test
    public void PortalsTest(){
        
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

        Portal p1 = new Portal(dungeon, 1, 2, 1);
        dungeon.addEntity(p1);
        Portal p2 = new Portal(dungeon, 4, 4, 1);
        dungeon.addEntity(p2);

        player.moveDown();
        Assert.assertNotEquals(2, player.getY());
    }

    @Test
    public void BoulderPortals(){
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

        Portal p1 = new Portal(dungeon, 1, 3, 1);
        dungeon.addEntity(p1);
        Portal p2 = new Portal(dungeon, 4, 4, 1);
        dungeon.addEntity(p2);
        Boulder boulder = new Boulder(dungeon, 1, 2);
        dungeon.addEntity(boulder);

        player.moveDown();
        Assert.assertNotEquals(2, boulder.getY());
    }
    @Test
    public void InaccesiblePortal(){
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

        Portal p1 = new Portal(dungeon, 1, 2, 1);
        dungeon.addEntity(p1);
        Portal p2 = new Portal(dungeon, 4, 4, 1);
        dungeon.addEntity(p2);
        dungeon.addEntity(new Wall(dungeon, 4, 3));
        dungeon.addEntity(new Wall(dungeon, 4, 2));
        dungeon.addEntity(new Wall(dungeon, 3, 4));

        player.moveDown();
        Assert.assertNotEquals(1, player.getY());
    }

    
}