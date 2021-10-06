package unsw.dungeon;

public class AndGoal extends Goal{

    @Override
    public boolean hasMetGoals(Dungeon dungeon, Player player) {
        //System.out.println(getGoals());
        // TODO Auto-generated method stub
        for(GoalInterface g:getGoals()){
            if(g.hasMetGoals(dungeon, player)!=true) return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String toReturn = "Goals: ";
        for(GoalInterface g:getGoals()){
            toReturn = toReturn + " and " + g.toString();
        }
        return toReturn;
    }
    
}