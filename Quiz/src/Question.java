import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Question.java - Represents a single multiple-choice question
public class Question {
    private String questionText;      // The text of the question
    private List<String> choices;     // The list of 4 choices (after shuffle)
    private String correctAnswer;     // The correct answer (tracked after shuffle)

    // Constructor
    public Question(String questionText, List<String> originalChoices) {
        this.questionText = questionText;

        // Copy choices and shuffle them
        this.choices = new ArrayList<>(originalChoices);
        Collections.shuffle(this.choices);

        // The correct answer was the first one in the original list
        this.correctAnswer = originalChoices.get(0);
    }

    // Getter for question text
    public String getQuestionText() {
        return questionText;
    }

    // Getter for choices
    public List<String> getChoices() {
        return choices;
    }

    // Getter for correct answer
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
