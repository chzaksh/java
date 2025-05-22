import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the Matrix Drawer.
 * Loads the FXML layout and initializes the JavaFX stage.
 */
public class MatrixApp extends Application {
	
	//Starts the JavaFX application. Loads the FXML file and sets up the main scene.
	public void start(Stage stage) throws Exception{
		Parent root = (Parent)
		FXMLLoader.load(getClass().getResource("matrix_drawer.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Matrix Drawer");
		stage.setScene(scene);
		stage.show();
		}
    //  The main entry point of the application. Launches the JavaFX application.
    public static void main(String[] args) {
        launch(args);
    }
}


