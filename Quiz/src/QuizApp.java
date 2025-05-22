import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Main application class for the Quiz
public class QuizApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	Parent root = (Parent) FXMLLoader.load(getClass().getResource("quiz_layout.fxml"));
    	Scene scene = new Scene(root);
    	stage.setTitle("Quiz Application");
    	 stage.setScene(scene);
         stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



