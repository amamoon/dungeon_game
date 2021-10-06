package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class InvincibilityObserver extends Observer {

    int invincibilityDuration = 7;
    Player player;
    Timer duration;
    int interval;

    public InvincibilityObserver(Player p) {
        entity = p;
        player = p;
        duration = new Timer();
        interval = 7;
    }

    // TODO update ui with time remaining
    @Override
    public void update(){
        //TODO set timer
        if(invincibilityDuration != 0){
            //TODO add reversal of enemy ai
            /** 
            while(invincibilityDuration != 0){
                invincibilityDuration--;
                System.out.println(invincibilityDuration);
            }*/
            duration.scheduleAtFixedRate(new TimerTask(){
                public void run(){
                    setInterval();
                    System.out.println(interval);
                }
            }, 1000, 1000);



        }

        if(invincibilityDuration == 0) {
            player.removeInvicible();
            invincibilityDuration = 7;
        }
        
    }

    private final int setInterval() {
        if (interval == 1){
            duration.cancel();
            System.out.println("Invincibility Over");
            player.removeInvicible();
        }
        return interval--;
    }
}