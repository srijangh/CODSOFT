import java.util.Scanner;
public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Student Grade Calculator -----");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        int[] marks = new int[numSubjects];
        int totalMarks = 0;
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            int mark = scanner.nextInt();
            while (mark < 0 || mark > 100) {
                System.out.print("Invalid marks. Please enter a value between 0 and 100: ");
                mark = scanner.nextInt();
            }
            marks[i] = mark;
            totalMarks += mark;
        }
        double averagePercentage = (double) totalMarks / numSubjects;
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("\n----- Result -----");
        System.out.println("Total Marks: " + totalMarks + " out of " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }
}
