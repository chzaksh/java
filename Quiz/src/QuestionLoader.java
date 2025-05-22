import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionLoader {

    public static List<Question> loadQuestionsFromFile(String filename) {
        List<Question> questions = new ArrayList<>();

        File file = new File(filename);
        if (!file.exists()) {  
            System.out.println("File not found: " + filename);
            return questions;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String questionText = fileScanner.nextLine();
                List<String> choices = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    if (fileScanner.hasNextLine()) {
                        choices.add(fileScanner.nextLine());
                    }
                }
                if (choices.size() == 4) {
                    questions.add(new Question(questionText, choices));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading questions: " + e.getMessage());
        }

        return questions;
    }
}
