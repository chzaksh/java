import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

//Controller class for the War card game, Manages user interactions and game flow.
public class WarGameController {
    private WarGameLogic gameLogic;     

    @FXML
    private Button nextRoundButton; 
    
    @FXML
    private Button startButton; 

    /**
     * Starts a new game, initializes the game logic, 
     * enables the "Next Round" button, and plays the first round.
     */
    @FXML
    void startGame(ActionEvent event) {
        gameLogic = new WarGameLogic(); 
        nextRoundButton.setDisable(false);
        startButton.setText("New Game"); 
        playNextRound(null); 
    }  

    /**
     * Plays the next round if the game is not finished.
     * Displays the game log and checks if the game has ended.
     */
    @FXML
    void playNextRound(ActionEvent event) {
        if (!gameLogic.isGameFinished()) {
        	gameLogic.playRound();
            showGameDialog(gameLogic.getGameLog()); 
            
            if (gameLogic.isGameFinished()) {
                nextRoundButton.setDisable(true);
                showGameDialog("The game has ended!");
            }
        }
    }

    //Displays a game dialog with a given message.
    private void showGameDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Progress");
        alert.setHeaderText(null);        
        alert.setContentText(message);
        alert.showAndWait();
    }

    
}
