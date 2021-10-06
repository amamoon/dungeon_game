package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SettingMenu {
    private Stage stage = new Stage();
    private String title;
    private SettingController controller;
    private Scene scene;

    public SettingMenu(Stage stage) throws IOException {
        this.stage = stage;
        title = "Settings";
        controller = new SettingController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root, 500,300);
    }

    public SettingMenu() throws IOException {
        title = "Settings";
        controller = new SettingController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root,500,300);
    }

    public void start(double volume){
        stage.setTitle(title);
        stage.setScene(scene);
        controller.setVolume(volume);
        stage.show();
    }

    public SettingController getController(){
        return controller;
    }

}