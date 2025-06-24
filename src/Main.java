import service.GradeManagerService;
import model.InvalidGradeException;

public class Main {
    public static void main(String[] args) {
        GradeManagerService gradeManager = new GradeManagerService();

        try {
            gradeManager.addGrade("123", 85.5);
            gradeManager.addGrade("123", 90.0);
            gradeManager.addGrade("456", 78.0);
            gradeManager.addGrade("456", 82.5);

            System.out.println("Grades for student 123: " + gradeManager.getGrades("123"));
            System.out.println("Average for student 123: " + gradeManager.calculateAverage("123"));

            System.out.println("\nTop students:");
            gradeManager.getTopStudents(2).forEach(System.out::println);

            System.out.println("\nReport for student 456:");
            gradeManager.generateReport("456").forEach(System.out::println);

            System.out.println("\nAll students:");
            gradeManager.getStudents().forEach(System.out::println);

        } catch (InvalidGradeException e) {
            System.err.println(e.getMessage());
        }
    }
}
