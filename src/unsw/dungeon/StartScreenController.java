package unsw.dungeon;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
public class StartScreenController {

    ObservableList<String> levelList = makeLevelList();

    @FXML
    private ChoiceBox<String> levelSelect;

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;

    @FXML
    private Button settingsButton;

    private SettingMenu settingsMenu;

    private DungeonApplication app = new DungeonApplication();

    private double volume = 0;

    @FXML
    private void initialize() {
        levelSelect.setValue("Tutorial 1 - Exits");
        levelSelect.setItems(levelList);
    }

    @FXML
    public void handleStartButton(ActionEvent event) throws IOException {
        String level = levelSelect.getValue();
        app.start(level, volume);
    }
    @FXML
    public void handleQuitButton(ActionEvent event){
        System.exit(0);
    }
    @FXML
    public void handleSettingsButton(ActionEvent event) throws IOException {
        settingsMenu.start(volume);
    }

    public void setSettingsMenu(SettingMenu sm){
        settingsMenu = sm;
    }

    private ObservableList<String> makeLevelList() {
        ObservableList<String> levels = FXCollections.observableArrayList();

        //add tutorial levels
        levels.add("Tutorial 1 - Exits");
        levels.add("Tutorial 2 - Enemies");
        levels.add("Tutorial 3 - Invincibility");
        levels.add("Tutorial 4 - Treasure");
        levels.add("Tutorial 5 - Boulders");
        levels.add("Tutorial 6 - Doors");
        levels.add("Tutorial 7 - Portals");
        levels.add("Tutorial 8 - Advanced Boulders");
        levels.add("Tutorial 9 - Compound Goals");
        levels.add("Tutorial 10 - Mirror Enemies");

        //add default levels
        levels.add("maze");
        levels.add("items");
        levels.add("boulders");
        levels.add("advanced");
        

        return levels;
    }

    public void setVolume(double vol) {
        volume = vol;
    }


}