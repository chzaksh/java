import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX application class that runs the "WarGame" game.
 * This class loads the graphical user interface from an FXML file
 * and displays the main application window.
 */
public class WarGameApp extends Application {

    //The main JavaFX start method, called when the application launches.
    public void start(Stage stage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("WarGame.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("WarGame");
        stage.setScene(scene);
        stage.show();
    }

    //The main entry point of the application.
    public static void main(String[] args) {
        launch(args);
        System.out.println();
    }
}
