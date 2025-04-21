import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String questionText;
    String[] options;
    char correctOption;

    public Question(String questionText, String[] options, char correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizAppWithTimer {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static final int TIME_LIMIT_SECONDS = 10;

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                new String[]{"A. Paris", "B. London", "C. Rome", "D. Berlin"}, 'A'));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"A. Earth", "B. Mars", "C. Jupiter", "D. Venus"}, 'B'));
        questions.add(new Question("What is 2 + 2?",
                new String[]{"A. 3", "B. 4", "C. 5", "D. 6"}, 'B'));

        int questionNumber = 1;
        Map<Integer, Boolean> answerSummary = new HashMap<>();

        for (Question q : questions) {
            System.out.println("\nQuestion " + questionNumber + ": " + q.questionText);
            for (String option : q.options) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up!");
                    System.exit(0); // Exit if time runs out
                }
            };

            timer.schedule(task, TIME_LIMIT_SECONDS * 1000);
            System.out.print("Enter your answer (A/B/C/D): ");
            String userAnswer = scanner.nextLine().trim().toUpperCase();
            timer.cancel();

            if (userAnswer.length() == 1 && userAnswer.charAt(0) == q.correctOption) {
                System.out.println("Correct!");
                score++;
                answerSummary.put(questionNumber, true);
            } else {
                System.out.println("Incorrect! Correct answer was: " + q.correctOption);
                answerSummary.put(questionNumber, false);
            }
            questionNumber++;
        }

        System.out.println("\nQuiz Complete!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        System.out.println("Answer Summary:");
        for (int i = 1; i <= questions.size(); i++) {
            System.out.println("Question " + i + ": " + (answerSummary.get(i) ? "Correct" : "Incorrect"));
        }
    }
}