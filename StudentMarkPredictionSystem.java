import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Abstract Class
abstract class Person {
    protected String name;
    protected String dateOfBirth;

    public Person(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public abstract void displayDetails();
}

// Encapsulated Student Class
class Student extends Person {
    private String rollNumber;
    private String parentContact;
    private double tamilMarks;
    private double englishMarks;
    private double mathsMarks;
    private double scienceMarks;
    private double socialMarks;

    public Student(String name, String dateOfBirth, String rollNumber, String parentContact,
                   double tamilMarks, double englishMarks, double mathsMarks, double scienceMarks, double socialMarks) {
        super(name, dateOfBirth);
        this.rollNumber = rollNumber;
        this.parentContact = parentContact;
        this.tamilMarks = tamilMarks;
        this.englishMarks = englishMarks;
        this.mathsMarks = mathsMarks;
        this.scienceMarks = scienceMarks;
        this.socialMarks = socialMarks;
    }

    public double getTotalMarks() {
        return tamilMarks + englishMarks + mathsMarks + scienceMarks + socialMarks;
    }

    public double getAverageMarks() {
        return getTotalMarks() / 5;
    }

    public String getGrade() {
        double averageMarks = getAverageMarks();
        if (averageMarks >= 90) {
            return "A";
        } else if (averageMarks >= 75) {
            return "B";
        } else if (averageMarks >= 50) {
            return "C";
        } else {
            return "D";
        }
    }

    @Override
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Parent's Contact: " + parentContact);
        System.out.println("Tamil Marks: " + tamilMarks + " / 100");
        System.out.println("English Marks: " + englishMarks + " / 100");
        System.out.println("Maths Marks: " + mathsMarks + " / 100");
        System.out.println("Science Marks: " + scienceMarks + " / 100");
        System.out.println("Social Science Marks: " + socialMarks + " / 100");
        System.out.println("Total Marks: " + getTotalMarks() + " / 500");
        System.out.printf("Average Marks: %.2f\n", getAverageMarks());
        System.out.println("Overall Grade: " + getGrade());
    }
}

// Main class with Collection Framework usage
public class StudentMarkPredictionSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println("\n--- Student Marks Summary ---");
            student.displayDetails();
        }
    }

    public static void main(String[] args) {
        StudentMarkPredictionSystem system = new StudentMarkPredictionSystem();
        Scanner scanner = new Scanner(System.in);

        int numStudents = 0;
        while (true) {
            try {
                System.out.print("Enter the number of students: ");
                numStudents = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Enter Student Name: ");
            String studentName = scanner.nextLine();

            System.out.print("Enter Roll Number: ");
            String rollNumber = scanner.nextLine();

            System.out.print("Enter Date of Birth (dd/mm/yyyy): ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Enter Parent's Contact Number: ");
            String parentContact = scanner.nextLine();

            double tamilMarks = getValidMarks(scanner, "Tamil");
            double englishMarks = getValidMarks(scanner, "English");
            double mathsMarks = getValidMarks(scanner, "Maths");
            double scienceMarks = getValidMarks(scanner, "Science");
            double socialMarks = getValidMarks(scanner, "Social Science");

            // Create a new student and add to the system
            Student student = new Student(studentName, dateOfBirth, rollNumber, parentContact,
                                          tamilMarks, englishMarks, mathsMarks, scienceMarks, socialMarks);
            system.addStudent(student);
        }

        // Display all students' information
        system.displayAllStudents();
        scanner.close();
    }

    // Helper method to validate and get marks input
    private static double getValidMarks(Scanner scanner, String subject) {
        double marks = 0;
        while (true) {
            try {
                System.out.print("Enter marks for " + subject + " (out of 100): ");
                marks = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid input. Please enter a number between 0 and 100.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return marks;
    }
}
