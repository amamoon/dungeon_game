package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    private Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws IOException {

        stage = primaryStage;
        primaryStage.setTitle("Dungeon");

        SettingMenu settings = new SettingMenu(primaryStage);
        StartScreen mainMenu = new StartScreen(primaryStage);
        mainMenu.getController().setSettingsMenu(settings);
        settings.getController().setStartScreen(mainMenu);

        mainMenu.start(0);

    }

    public void start(String levelName, double volume) throws IOException {
        String levelFile = levelName + ".json";

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(levelFile);
        DungeonController controller = dungeonLoader.loadController();

        controller.setVolume(volume);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        root.requestFocus();

        stage.setTitle("Dungeon Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
