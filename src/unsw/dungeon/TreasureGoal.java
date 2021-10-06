package unsw.dungeon;

public class TreasureGoal extends Goal{

    @Override
    public boolean hasMetGoals(Dungeon dungeon, Player player) {
        // TODO Auto-generated method stub
        //System.out.println("treasure goal met: "+(player.getPlayerTreasureCount() == dungeon.getTreasureCount()));
        if(player.getPlayerTreasureCount() == dungeon.getTreasureCount()){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Collect all Treasure";
    }

}