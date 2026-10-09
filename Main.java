
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    String getGrade() {
        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 40) {
            return "D";
        } else {
            return "F";
        }
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Summary Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    showSummary();
                    break;

                case 4:
                    System.out.println("Thank you for using Student Grade Tracker!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }

    static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid whole number: ");
            sc.nextLine();
        }

        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }

    static double readMarks() {
        while (true) {
            if (sc.hasNextDouble()) {
                double marks = sc.nextDouble();
                sc.nextLine();

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
            } else {
                sc.nextLine();
            }

            System.out.print("Invalid marks! Enter marks from 0 to 100: ");
        }
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter marks (0-100): ");
        double marks = readMarks();

        students.add(new Student(name, marks));

        System.out.println("Student added successfully!");
    }

    static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n----- STUDENT RECORDS -----");

        System.out.printf("%-20s %-10s %-10s%n",
                "Name", "Marks", "Grade");

        for (Student s : students) {
            System.out.printf("%-20s %-10.2f %-10s%n",
                    s.name, s.marks, s.getGrade());
        }
    }

    static void showSummary() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        double total = 0;
        Student highest = students.get(0);
        Student lowest = students.get(0);

        for (Student s : students) {
            total += s.marks;

            if (s.marks > highest.marks) {
                highest = s;
            }

            if (s.marks < lowest.marks) {
                lowest = s;
            }
        }

        double average = total / students.size();

        System.out.println("\n===== SUMMARY REPORT =====");
        System.out.println("Total Students: " + students.size());
        System.out.printf("Average Marks: %.2f%n", average);
        System.out.println("Highest Scorer: " + highest.name
                + " (" + highest.marks + ")");
        System.out.println("Lowest Scorer: " + lowest.name
                + " (" + lowest.marks + ")");

        displayStudents();
    }
}