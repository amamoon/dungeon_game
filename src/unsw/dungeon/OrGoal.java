package unsw.dungeon;

public class OrGoal extends Goal{

    @Override
    public boolean hasMetGoals(Dungeon dungeon, Player player) {
        // TODO Auto-generated method stub
        for(GoalInterface g:getGoals()){
            if(g.hasMetGoals(dungeon, player)) return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        String toReturn = "Goals: ";
        for(GoalInterface g:getGoals()){
            toReturn = toReturn + " or " + g.toString();
        }
        return toReturn;
    }
    
    
}