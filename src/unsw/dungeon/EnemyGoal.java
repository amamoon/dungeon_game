package unsw.dungeon;

public class EnemyGoal extends Goal{

    @Override
    public boolean hasMetGoals(Dungeon dungeon, Player player) {
        // TODO Auto-generated method stub
        //System.out.println("enemy goal met: "+(dungeon.getNumberOfEnemies()==0));
        if(dungeon.getNumberOfEnemies() == 0){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Kill All Enemies";
    }
}