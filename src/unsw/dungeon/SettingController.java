package unsw.dungeon;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class SettingController {
    @FXML
    private CheckBox soundOption;

    @FXML
    private CheckBox quitButton;

    @FXML
    private Button backButton;

    private StartScreen mainMenu;

    private double volume = 0;
 
    //private DungeonController dc;

    @FXML
    public void handleSound(ActionEvent event) throws IOException {
        //TODO
        if(soundOption.isSelected()){
            volume = 1.0;
        }else{
            volume = 0;
        }
    }

    @FXML
    public void handleQuitButton(ActionEvent event){
        System.exit(0);
    }

    @FXML
    public void handleBackButton(ActionEvent event){
        mainMenu.start(volume);
    }

    public void setStartScreen(StartScreen ss){
        mainMenu = ss;
    }

    public void setVolume(double vol) {
        volume = vol;
    }

    /*public void setDungeonController(DungeonController dc){
        this.dc = dc;
    }*/

}