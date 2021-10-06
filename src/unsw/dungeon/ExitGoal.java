package unsw.dungeon;

public class ExitGoal extends Goal{


    @Override
    public boolean hasMetGoals(Dungeon dungeon, Player player) {
        //System.out.println("player at exit: "+(dungeon.playerAtExit()));
        // TODO Auto-generated method stub
        return dungeon.playerAtExit();
    }
    
    
    @Override
    public String toString(){
        return "Reach the Exit";
    }
}