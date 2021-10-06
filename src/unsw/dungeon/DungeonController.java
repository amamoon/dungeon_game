package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    //true to enable audio for local device
    private boolean loadAudio = false;
    //false to disable audio for submit

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private AudioClip playerDeathSound;
    private AudioClip enemyDeathSound;
    private AudioClip swordSwingSound;
    private AudioClip footstepSound;
    public SoundManager sm;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);

        if (loadAudio) {
            dungeon.setAudio(true);
            playerDeathSound = new AudioClip(new File("sounds/playerdeath.wav").toURI().toString());
            enemyDeathSound = new AudioClip(new File("sounds/enemydeath.wav").toURI().toString());
            swordSwingSound = new AudioClip(new File("sounds/swordswing.wav").toURI().toString());
            footstepSound = new AudioClip(new File("sounds/footstep.wav").toURI().toString());
            sm = new SoundManager();

            this.dungeon.setSoundManager(sm);
            loadSounds();
        }
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            player.setDirection(Direction.UP);
            dungeon.moveEnemies();
            dungeon.enemyContact(player);
            if (loadAudio) sm.playSoundEffect("footsteps");
            break;
        case DOWN:
            player.moveDown();
            player.setDirection(Direction.DOWN);
            dungeon.moveEnemies();
            dungeon.enemyContact(player);
            if (loadAudio) sm.playSoundEffect("footsteps");
            break;
        case LEFT:
            player.moveLeft();
            player.setDirection(Direction.LEFT);
            dungeon.moveEnemies();
            dungeon.enemyContact(player);
            if (loadAudio) sm.playSoundEffect("footsteps");
            break;
        case RIGHT:
            player.moveRight();
            player.setDirection(Direction.RIGHT);
            dungeon.moveEnemies();
            dungeon.enemyContact(player);
            if (loadAudio) sm.playSoundEffect("footsteps");
            break;
        case Z:
            player.swingSword();
            if (loadAudio) sm.playSoundEffect("swordSwing");
            break;
        default:
            break;
        }

        if (dungeon.checkComplete()) {
            System.out.println("Success! Well done!");
            handleDungeonCompletion();
        }
        //else System.out.println("not complete yet");
    }


    public void handleDungeonCompletion() {
        Stage primaryStage = (Stage)squares.getScene().getWindow();
        primaryStage.hide();
    }

    public void loadSounds(){
        sm.addSoundEffect("playerDeath", playerDeathSound);
        sm.addSoundEffect("enemyDeath", enemyDeathSound);
        sm.addSoundEffect("swordSwing", swordSwingSound);
        sm.addSoundEffect("footsteps", footstepSound);
    }

    public SoundManager getSoundManager(){
        return sm;
    }

    public void setSoundManager(SoundManager sm){
        this.sm = sm;
    }

    public void setVolume(double newVolume){
        if (loadAudio) sm.changeVolume(newVolume);
    }


}

