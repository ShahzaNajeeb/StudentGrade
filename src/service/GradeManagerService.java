package service;

import model.InvalidGradeException;

import java.util.*;
import java.util.stream.Collectors;

public class GradeManagerService {

    private final Map<String, List<Double>> studentGrades;

    public GradeManagerService() {
        studentGrades = new HashMap<>();
    }
    public void validateGrade(double grade) throws InvalidGradeException {
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException("Grade must be between 0 and 100. Given: " + grade);
        }
    }


    public void addGrade(String studentId, double grade) throws InvalidGradeException {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        validateGrade(grade);
        studentGrades.putIfAbsent(studentId, new ArrayList<>());
        studentGrades.get(studentId).add(grade);
    }


    public List<Double> getGrades(String studentId) {
        return new ArrayList<>(studentGrades.getOrDefault(studentId, Collections.emptyList()));
    }

    public double calculateAverage(String studentId) {
        List<Double> grades = studentGrades.getOrDefault(studentId, Collections.emptyList());
        return grades.isEmpty() ? 0.0 : grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }


    public List<String> getTopStudents(int topN) {
        if (studentGrades.isEmpty()) {
            return Collections.emptyList();
        }
        return studentGrades.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(
                        calculateAverage(e2.getKey()), calculateAverage(e1.getKey())))
                .limit(topN)
                .map(e -> "Student ID: " + e.getKey() + ", Average Grade: " + calculateAverage(e.getKey()))
                .collect(Collectors.toList());
    }


    public List<String> generateReport(String studentId) {
        List<Double> grades = getGrades(studentId);
        List<String> report = new ArrayList<>();
        if (grades.isEmpty()) {
            report.add("No grades available for student: " + studentId);
        } else {
            report.add("Grades for student " + studentId + ": " + grades);
            report.add("Average grade: " + calculateAverage(studentId));
        }
        return report;
    }

    public List<String> getStudents() {
        if (studentGrades.isEmpty()) {
            return Collections.emptyList();
        }
        return studentGrades.entrySet().stream()
                .map(entry -> "Student ID: " + entry.getKey() + ", Grades: " + entry.getValue())
                .collect(Collectors.toList());
    }
}
