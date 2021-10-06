/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private ArrayList<Enemy> enemies;
    private int treasureCount = 0;
    private Goal goals;
    private Exit exit;
    private SoundManager sm;

    private boolean loadAudio;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        enemies = new ArrayList<Enemy>();
        goals = null;
    }

    public String getGoalsAsString(){
        return goals.toString();
    }

    public void setSoundManager(SoundManager sm){
        this.sm = sm;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public int getNumberOfEnemies(){
        return enemies.size();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addEnemy(Enemy e){
        enemies.add(e);
    }

    public int getTreasureCount(){
        return treasureCount;
    }

    public void incrementTreasure(){
        treasureCount++;
    }

    public void setExit(Exit e){
        exit = e;
    }

    public boolean checkMove(int x, int y, Direction d){
        ArrayList<Entity> entitiesAtCoord = entityAtCoord(x, y);
        if(entitiesAtCoord.isEmpty()) return true;

        for(Entity e: entitiesAtCoord){
            if(e.isWall()) return false;
            if(e.isEnemy()) {
                if (player.getInvicibilityStatus()) {
                    ((Enemy) e).killEnemy();
                    return true;
                }
                else {
                    player.killedBy((Enemy) e);
                    return false;
                }   
            }
            if(e.isPortal()){
                Portal ePortal = (Portal) e;
                Portal ePortalTwin = findTwinPortal(ePortal);
                if(ePortalTwin == null) {
                    System.err.println("twin portal found was null\ncheck the finding function");
                    return false;
                }
                if (ePortalTwin.setLandingZone(d)) { //landing zone calculated
                    teleportPlayer(ePortalTwin.getLandingX(), ePortalTwin.getLandingY());
                    return false;//player doesn't need to move after they've been teleported!
                }
                else return false;//no landing zone available
            }
            if(e.isItem() && ((Item) e).isPotion()){
                player.setInvincible();
                return true;
            }
            if(e.isBoulder()) {
                if (((Boulder) e).moveBoulder(d) == true) { //if the boulder is successfully moved
                    return true;
                }
                else return false;
            }
            if(e.isLockedDoor()) {
                return tryUnlock((DoorEntity) e, player);
            }
        }

        return true;
    }

    public boolean boulderCheck(int x, int y, Boulder b, Direction d) {
        ArrayList<Entity> entitiesAtCoord = entityAtCoord(x, y);
        if(entitiesAtCoord.isEmpty()) return true;

        for (Entity e: entitiesAtCoord) {
            if (e.isWall() || e.isBoulder() || e.isEnemy() || e.isItem() || e.isLockedDoor()) {
                return false;
            }
            if (e.isPortal()) {
                Portal ePortal = (Portal) e;
                Portal ePortalTwin = findTwinPortal(ePortal);
                if(ePortalTwin == null) {
                    System.err.println("twin portal found was null\ncheck the finding function");
                    return false;
                }
                if (ePortalTwin.setBoulderLanding(b, d)) { //landing zone calculated
                    int boulderDestX = ePortalTwin.getLandingX();
                    int boulderDestY = ePortalTwin.getLandingY();
                    switch (d) {
                        case UP:
                            boulderDestY += 1;
                            break;
                        case DOWN:
                            boulderDestY -= 1;
                            break;
                        case LEFT:
                            boulderDestX += 1;
                            break;
                        case RIGHT:
                            boulderDestX -= 1;
                            break;

                    }
                    teleportBoulder(b, boulderDestX, boulderDestY);
                    return true;
                }
                else return false;//no landing zone available
            }
        }

        //run through the check a second time to make sure a switch isn't activated if the boulder can't move there...
        //there's definitely a better way to do this.....
        /*for (Entity e: entitiesAtCoord) {
            if (e.isSwitch()) {
                ((Switch) e).activate(true);
            }
        }*/

        return true;
    }
    

    public ArrayList<Entity> entityAtCoord(int x, int y){
        ArrayList<Entity> entitiesAtCoord = new ArrayList<Entity>();
        for(Entity e:entities){
            if(e == null) continue;
            if(e.getX()==x && e.getY()==y){
                entitiesAtCoord.add(e);
            }
        }
        return entitiesAtCoord;
    }

    public void checkPickup(int x, int y, Player p) {
        ArrayList<Entity> entitiesAtCoord = entityAtCoord(x, y);
        if(entitiesAtCoord.isEmpty()) return;

        for (Entity e : entitiesAtCoord) {
            if (e.isItem()) {
                if (p.pickUp((Item) e)) {
                    removeEntity(e);
                }
            }
        }

    }

    public void removeEntity(Entity e) {
        if (e.isItem()) {
            Item i = (Item) e;
            if (i.isSword()) {
                i.setX(0);
                i.setY(height-1);
            }
            else if(i.isKey()) {
                i.setX(1);
                i.setY(height-1);
            }
            else {
                i.setX(height+1);
                i.setY(width+1);
            }
        }
        else {
            e.setX(height+1);
            e.setY(width+1);
        }
        entities.remove(e);
    }

    private Portal findTwinPortal(Portal initialPortal){
        for(Entity e:entities){
            if (e == null) continue;
            if(e.isPortal()){
                Portal ePortal = (Portal) e;
                if(PortalCoordinateComparison(initialPortal, ePortal)){
                    return ePortal;
                }
            }
        }
        return null;
    }
    //in place of a proper equals function for now in portal itself
    private boolean PortalCoordinateComparison(Portal p1, Portal p2){
        if(p1.getID() != p2.getID()){
            return false;//they have different IDs
        }
        
        if((p1.getX() == p2.getX()) && (p1.getY() == p2.getY())){
            return false; // they are the same
        }
        return true;//they are not the same
    }

    private void teleportPlayer(int x, int y){
            player.setX(x);
            player.setY(y);
    }

    private void teleportBoulder(Boulder b, int x, int y){
        b.setX(x);
        b.setY(y);
    }

    private boolean tryUnlock(DoorEntity door, Player player) {
        for (Item i:player.getInventory()) {
            if (!i.isKey()) {
                continue;
            }
            Key key = (Key) i;
            if (door.getType().unlock(key)) {
                door.unlock();
                player.removeFromInventory(i);
                return true;
            }
        }
        return false;//if player has no key or key has the wrong id
    }

    public void removeEnemy(Enemy e){
        enemies.remove(e);
    }

    public void killEnemies(int x, int y, Sword s) {
        for (Entity e : entityAtCoord(x, y-1)) {
            if (e.isEnemy() && s.usable()) {
                ((Enemy) e).killEnemy();
                s.reduceDurability();
            }
        }
        for (Entity e : entityAtCoord(x+1, y)) {
            if (e.isEnemy() && s.usable()) {
                ((Enemy) e).killEnemy();
                s.reduceDurability();
            }
        }
        for (Entity e : entityAtCoord(x, y+1)) {
            if (e.isEnemy() && s.usable()) {
                ((Enemy) e).killEnemy();
                s.reduceDurability();
            }
        }
        for (Entity e : entityAtCoord(x-1, y)) {
            if (e.isEnemy() && s.usable()) {
                ((Enemy) e).killEnemy();
                s.reduceDurability();
            }
        }
    }

    public void moveEnemies() {
        for (Enemy e : enemies) {
            if (e.isActive()) {
                if(!player.getInvicibilityStatus()){
                    e.move();
                }else if(player.getInvicibilityStatus()){
                    e.runAway();
                }
            }
        }
    }

	public void addGoal(JSONObject jsonGoals) {
        //goals.addSubGoal(jsonGoals);
        String goal = jsonGoals.getString("goal");
        switch (goal) {
            case "exit":
                goals = new ExitGoal();
                break;
            case "enemies":
                goals = new EnemyGoal();
                break;
            case "treasure":
                goals = new TreasureGoal();
                break;
            case "boulders":
                goals = new BoulderGoal();
                break;
            case "AND":
                AndGoal andGoal = new AndGoal();
                andGoal.addSubGoals(jsonGoals.getJSONArray("subgoals"));
                goals = andGoal;
                break;
            case "OR":
                OrGoal orGoal = new OrGoal();
                orGoal.addSubGoals(jsonGoals.getJSONArray("subgoals"));
                goals = orGoal;
                break;
        }
        System.out.println(getGoalsAsString());
    }
    
    public ArrayList<GoalInterface> getGoals(){
        return goals.getGoals();
    }

    public boolean playerAtExit(){
        if (exit == null) {
            System.err.println("Looking for an exit, but there isn't one! Did you remember to add the right goal conditions?");
            return false;
        }
        if(player.getX()==exit.getX() && player.getY()==exit.getY()){
            return true;
        }
        return false;
    }

    public void updateSwitches() {
        for (Entity e : entities) {
            if (e == null || !e.isSwitch()) {
                continue;
            }

            ((Switch) e).notifyAllObservers();
        }
    }

    public int getInactiveSwitches() {
        int inactive = 0;
        for (Entity e : entities) {
            if (e == null || !e.isSwitch()) continue;

            Switch s = (Switch) e;
            if (!s.isActive()) {
                inactive++;
            }
        }
        return inactive;
    }

    public void enemyContact(Player player) {
        for (Entity e : entityAtCoord(player.getX(), player.getY())) {
            if (!e.isEnemy()) {
                continue;
            }

            Enemy enemy = (Enemy) e;
            if (player.getInvicibilityStatus()) {
                (enemy).killEnemy();
            }
            else {
                player.killedBy(enemy);
                sm.playSoundEffect("playerDeath");
                return;
            }
        }
    }
        

    public boolean xWithinBounds(int x){
        return (x >= 0 && x < getWidth());
    }

    public boolean yWithinBounds(int y){
        return (y >= 0 && y < getHeight());
    }

    public boolean checkComplete() {
        return goals.hasMetGoals(this, player);
    }

    public void playSoundEffect(String key){
        sm.playSoundEffect(key);
    }

    public boolean loadAudio() {
        return loadAudio;
    }

    public void setAudio(boolean audio) {
        loadAudio = audio;
    }

}
