import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.*;
import javafx.scene.layout.GridPane; 


public class QuizController {

    @FXML
    private GridPane  quizContainer;
    @FXML
    private Label resultLabel;
    @FXML
    private Button restartButton; 
    
    @FXML
    private Button submitButton; 
    
    

    private List<Question> selectedQuestions = new ArrayList<>();
    private List<ToggleGroup> answerGroups = new ArrayList<>();

    @FXML   
    public void initialize() {
        List<Question> allQuestions = QuestionLoader.loadQuestionsFromFile("questions.txt");

        Collections.shuffle(allQuestions);
        selectedQuestions = allQuestions.subList(0, Math.min(10, allQuestions.size()));

        int questionNumber = 1;
        int row = 0;
        int col = 0;

        for (int i = 0; i < selectedQuestions.size(); i++) {
            Question q = selectedQuestions.get(i);

            VBox questionBox = new VBox(5);
            Label questionLabel = new Label(questionNumber++ + ". " + q.getQuestionText());
            questionBox.getChildren().add(questionLabel);

            ToggleGroup group = new ToggleGroup();
            answerGroups.add(group);

            List<String> choices = new ArrayList<>(q.getChoices());
            Collections.shuffle(choices);

            for (String choice : choices) {
                RadioButton rb = new RadioButton(choice);
                rb.setToggleGroup(group);
                questionBox.getChildren().add(rb);
            }

            quizContainer.add(questionBox, col, row);

            row++;
            if (row == 5) {
                row = 0;
                col++;
            }
        }
    }



    @FXML
    private void handleSubmit() {
        int score = 0;

        for (int i = 0; i < selectedQuestions.size(); i++) {
            ToggleGroup group = answerGroups.get(i);
            Question question = selectedQuestions.get(i);

            RadioButton selected = (RadioButton) group.getSelectedToggle();
            String selectedAnswer = (selected != null) ? selected.getText() : "";

            List<RadioButton> buttons = new ArrayList<>();
            for (Toggle toggle : group.getToggles()) {
                buttons.add((RadioButton) toggle);
            }

            for (RadioButton button : buttons) {
                if (button.getText().equals(question.getCorrectAnswer())) {
                    button.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
                }
            }

            if (selected != null) {
                if (selectedAnswer.equals(question.getCorrectAnswer())) {
                    selected.setStyle("-fx-border-color: green; -fx-border-width: 3px;");
                    score++;
                } else {
                    selected.setStyle("-fx-border-color: red; -fx-border-width: 3px;");
                }
            }

            for (RadioButton button : buttons) {
                button.setDisable(true);
            }
        }
        submitButton.setDisable(true);
        double percentage = (double) score / selectedQuestions.size() * 100;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Result");
        alert.setHeaderText("Quiz Completed!");
        alert.setContentText("Score: " + score + "/" + selectedQuestions.size() +
                             "\nSuccess Rate: " + String.format("%.2f", percentage) + "%");
        alert.showAndWait();
        
        restartButton.setDisable(false);
    }
    @FXML
    private void handleRestart() {
    	quizContainer.getChildren().clear();
        answerGroups.clear();
        resultLabel.setText("");
        restartButton.setDisable(true);
        submitButton.setDisable(false);
        initialize();
    }


   

}
    
