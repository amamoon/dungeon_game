package unsw.dungeon;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Goal implements GoalInterface{
    private ArrayList<GoalInterface> goals;


    public Goal(){
        goals = new ArrayList<GoalInterface>();
    }

    public void addGoal(GoalInterface g){
        goals.add(g);
    }

    public ArrayList<GoalInterface> getGoals(){
        return goals;
    }

    public void addSubGoals(JSONArray subgoals){
        //System.out.println("adding subgoals");
        //System.out.println(subgoals.length());
        for(int i=0; i< subgoals.length();i++){
            JSONObject goalObj = subgoals.getJSONObject(i);
            String goal = goalObj.getString("goal");
           // System.out.println("subgoal "+i+" is "+goal);
            
            switch (goal) {
                case "exit":
                    goals.add(new ExitGoal());
                    break;
                case "enemies":
                    //System.out.println("adding enemy subgoal");
                    goals.add(new EnemyGoal());
                    break;
                case "treasure":
                    goals.add(new TreasureGoal());
                    break;
                case "boulders":
                    goals.add(new BoulderGoal());
                    break;
                case "AND":
                    AndGoal andGoal = new AndGoal();
                    andGoal.addSubGoals(goalObj.getJSONArray("subgoals"));
                    goals.add(andGoal);
                    break;
                case "OR":
                    OrGoal orGoal = new OrGoal();
                    orGoal.addSubGoals(goalObj.getJSONArray("subgoals"));
                    goals.add(orGoal);
                    break;
            }
        }
        //System.out.println("subgoals are now "+goals);
    }

    public void addSubGoal(JSONObject subgoals){
        /*for(int i=0; i< subgoals.length();i++){
            
        }*/
        String goal = subgoals.getString("goal");
        switch (goal) {
            case "exit":
                goals.add(new ExitGoal());
                break;
            case "enemies":
                //System.out.println("adding enemy subgoal");
                goals.add(new EnemyGoal());
                break;
            case "treasure":
                goals.add(new TreasureGoal());
                break;
            case "boulders":
                goals.add(new BoulderGoal());
                break;
            case "AND":
                AndGoal andGoal = new AndGoal();
                andGoal.addSubGoals(subgoals.getJSONArray("subgoals"));
                goals.add(andGoal);
                break;
            case "OR":
                OrGoal orGoal = new OrGoal();
                orGoal.addSubGoals(subgoals.getJSONArray("subgoals"));
                goals.add(orGoal);
                break;
        }
    }

    public void setGoals(ArrayList<GoalInterface> goals) {
        this.goals = goals;
    }


}