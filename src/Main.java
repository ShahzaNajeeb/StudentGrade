import model.InvalidGradeException;
import service.GradeManagerService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GradeManagerService gradeService = new GradeManagerService();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("=== Welcome to the Student Grade Management System ===");

        while (choice != 7) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a grade for a student");
            System.out.println("2. View all grades for a student");
            System.out.println("3. View average grade for a student");
            System.out.println("4. View top N students");
            System.out.println("5. Generate report for a student");
            System.out.println("6. View all students and their grades");
            System.out.println("7. Exit");
            System.out.print("Your choice: ");


            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:

                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();

                    System.out.print("Enter grade (0-100): ");
                    double grade = Double.parseDouble(scanner.nextLine());

                    try {
                        gradeService.addGrade(studentId, grade);
                        System.out.println("Grade added successfully!");
                    } catch (InvalidGradeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();

                    List<Double> grades = gradeService.getGrades(studentId);
                    if (grades.isEmpty()) {
                        System.out.println("No grades for this student.");
                    } else {
                        System.out.println("Grades for " + studentId + ": " + grades);
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();

                    double average = gradeService.calculateAverage(studentId);
                    System.out.println("Average grade for " + studentId + ": " + average);
                    break;

                case 4:
                    System.out.print("How many top students do you want to see? ");
                    int topCount = Integer.parseInt(scanner.nextLine());

                    List<String> topStudents = gradeService.getTopStudents(topCount);
                    if (topStudents.isEmpty()) {
                        System.out.println("No students yet.");
                    } else {
                        for (String studentInfo : topStudents) {
                            System.out.println(studentInfo);
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.nextLine();

                    List<String> report = gradeService.generateReport(studentId);
                    for (String line : report) {
                        System.out.println(line);
                    }
                    break;

                case 6:
                    List<String> allStudents = gradeService.getStudents();
                    if (allStudents.isEmpty()) {
                        System.out.println("No students available.");
                    } else {
                        for (String student : allStudents) {
                            System.out.println(student);
                        }
                    }
                    break;

                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Please enter a valid number (1-7).");
                    break;
            }
        }

        scanner.close();
    }
}

