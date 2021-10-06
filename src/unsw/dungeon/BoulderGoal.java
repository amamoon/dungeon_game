package unsw.dungeon;

public class BoulderGoal extends Goal{

    @Override
    public boolean hasMetGoals(Dungeon dungeon, Player player) {
        // TODO Auto-generated method stub
        //System.out.println("enemy goal met: "+(dungeon.getNumberOfEnemies()==0));
        if(dungeon.getInactiveSwitches() == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Activate all the switches";
    }
    
}