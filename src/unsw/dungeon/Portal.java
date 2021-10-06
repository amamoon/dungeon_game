package unsw.dungeon;

public class Portal extends Entity{

    private int id;
    private int landingX, landingY;

    public Portal(Dungeon dungeon, int x, int y, int id){
        super(dungeon, x,y, false);
        this.id = id;
    }

    public int getID(){
        return this.id;
    }

    public int getLandingX(){
        return landingX;
    }

    public int getLandingY(){
        return landingY;
    }

    public void setLandingX(int x){
        landingX = x;
    }

    public void setLandingY(int y){
        landingY = y;
    }

    public boolean setLandingZone(Direction d){
        int startX = this.getX();
        int startY = this.getY();

        Direction curr = d;
        for (int i = 0; i<4; i++) {
            switch (curr) {
            
            case UP:
                if((startY > 0) && getDungeon().checkMove(startX, startY-1, curr)){
                    setLandingX(startX);
                    setLandingY(startY-1);
                    return true;
                }
                curr = Direction.RIGHT;
                break;

            case RIGHT:
                if((startX < getDungeon().getWidth()) && getDungeon().checkMove(startX+1, startY, curr)){
                    setLandingX(startX+1);
                    setLandingY(startY);
                    return true;
                }
                curr = Direction.DOWN;
                break;

            case DOWN:
                if((startY < getDungeon().getHeight()) && getDungeon().checkMove(startX, startY+1, curr)){
                    setLandingX(startX);
                    setLandingY(startY+1);
                    return true;
                }
                curr = Direction.LEFT;
                break;

            case LEFT:
                if((startX > 0) && getDungeon().checkMove(startX-1, startY, curr)){
                    setLandingX(startX-1);
                    setLandingY(startY);
                    return true;
                }
                curr = Direction.UP;
                break;

            }
        }

        return false;
    }

    public boolean setBoulderLanding(Boulder b, Direction d){
        int startX = this.getX();
        int startY = this.getY();

        Direction curr = d;
        for (int i = 0; i<4; i++) {
            switch (curr) {

            case UP:
                if((startY > 0) && getDungeon().boulderCheck(startX, startY-1, b, curr)){
                    setLandingX(startX);
                    setLandingY(startY-1);
                    return true;
                }
                curr = Direction.RIGHT;
                break;

            case RIGHT:
                if((startX < getDungeon().getWidth()) && getDungeon().boulderCheck(startX+1, startY, b, curr)){
                    setLandingX(startX+1);
                    setLandingY(startY);
                    return true;
                }
                curr = Direction.DOWN;
                break;
            
            case DOWN:
                if((startY < getDungeon().getHeight()) && getDungeon().boulderCheck(startX, startY+1, b, curr)){
                    setLandingX(startX);
                    setLandingY(startY+1);
                    return true;
                }
                curr = Direction.LEFT;
                break;

            case LEFT:
                if((startX > 0) && getDungeon().boulderCheck(startX-1, startY, b, curr)){
                    setLandingX(startX-1);
                    setLandingY(startY);
                    return true;
                }
                curr = Direction.UP;
                break;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean isPortal(){
        return true;
    }
    
}