package unsw.dungeon;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreen {
    private Stage stage;
    private String title;
    private StartScreenController controller;
    private Scene scene;

    public StartScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Main Menu";
        controller = new StartScreenController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        loader.setController(controller);
        
        Parent root = loader.load();
        scene = new Scene(root, 500,300);
        scene.getStylesheets().add(this.getClass().getResource("mainmenu.css").toExternalForm());

    }

    public void start(double volume){
        stage.setTitle(title);
        stage.setScene(scene);
        controller.setVolume(volume);
        stage.show();
    }

    public StartScreenController getController(){
        return controller;
    }


}