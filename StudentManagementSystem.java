
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class StudentManagementSystem {

    static ArrayList<String> studentIds = new ArrayList<>();
    static ArrayList<String> studentNames = new ArrayList<>();
    static ArrayList<Integer> studentMarks = new ArrayList<>();

    static LinkedList<String> attendance = new LinkedList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Remove Student");
            System.out.println("6. Add Student Attendance");
            System.out.println("7. Display Attendance");
            System.out.println("8. Remove Attendance");
            System.out.println("9. Display Student Statistics");
            System.out.println("10. Sort Student Names");
            System.out.println("11. Clear All Records");
            System.out.println("12. Exit");

            choice = readInt("Enter Your Choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateMarks();
                    break;

                case 5:
                    removeStudent();
                    break;

                case 6:
                    addAttendance();
                    break;

                case 7:
                    displayAttendance();
                    break;

                case 8:
                    removeAttendance();
                    break;

                case 9:
                    displayStatistics();
                    break;

                case 10:
                    sortStudents();
                    break;

                case 11:
                    clearAllRecords();
                    break;

                case 12:
                    System.out.println("Thank You for Using Student Management System!");
                    break;

                default:
                    System.out.println("Invalid Choice! Please Try Again.");
            }

        } while (choice != 12);

        sc.close();
    }

    // Method to safely read integer input
    static int readInt(String message) {

        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } else {
                System.out.println("Invalid Input! Please Enter a Number.");
                sc.nextLine();
            }
        }
    }

    // 1. Add Student
    static void addStudent() {

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Student ID Cannot Be Empty!");
            return;
        }

        if (studentIds.contains(id)) {
            System.out.println("Student ID Already Exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student Name Cannot Be Empty!");
            return;
        }

        int marks = readInt("Enter Student Marks: ");

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid Marks! Enter Marks Between 0 and 100.");
            return;
        }

        studentIds.add(id);
        studentNames.add(name);
        studentMarks.add(marks);

        System.out.println("Student Added Successfully!");
    }

    // 2. Display All Students
    static void displayStudents() {

        if (studentNames.isEmpty()) {
            System.out.println("No Student Records Found!");
            return;
        }

        System.out.println("\n--------------- STUDENT RECORDS ---------------");

        System.out.printf("%-15s %-20s %-10s%n",
                "Student ID", "Student Name", "Marks");

        for (int i = 0; i < studentNames.size(); i++) {

            System.out.printf("%-15s %-20s %-10d%n",
                    studentIds.get(i),
                    studentNames.get(i),
                    studentMarks.get(i));
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Total Students: " + studentNames.size());
    }

    // 3. Search Student
    static void searchStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        int index = studentNames.indexOf(name);

        if (index != -1) {

            System.out.println("\nStudent Found!");
            System.out.println("Student ID: " + studentIds.get(index));
            System.out.println("Student Name: " + studentNames.get(index));
            System.out.println("Student Marks: " + studentMarks.get(index));

        } else {
            System.out.println("Student Not Found!");
        }
    }

    // 4. Update Student Marks
    static void updateMarks() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        int index = studentNames.indexOf(name);

        if (index == -1) {
            System.out.println("Student Not Found!");
            return;
        }

        int newMarks = readInt("Enter New Marks: ");

        if (newMarks < 0 || newMarks > 100) {
            System.out.println("Invalid Marks! Enter Marks Between 0 and 100.");
            return;
        }

        studentMarks.set(index, newMarks);

        System.out.println("Student Marks Updated Successfully!");
        System.out.println("Name: " + studentNames.get(index));
        System.out.println("Updated Marks: " + studentMarks.get(index));
    }

    // 5. Remove Student
    static void removeStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        int index = studentNames.indexOf(name);

        if (index == -1) {
            System.out.println("Student Not Found!");
            return;
        }

        studentIds.remove(index);
        studentNames.remove(index);
        studentMarks.remove(index);

        attendance.remove(name);

        System.out.println("Student Removed Successfully!");

        displayStudents();
    }

    // 6. Add Student Attendance
    static void addAttendance() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        if (!studentNames.contains(name)) {
            System.out.println("Student Is Not Registered!");
            return;
        }

        if (attendance.contains(name)) {
            System.out.println("Attendance Already Marked!");
            return;
        }

        System.out.println("1. Add at Beginning");
        System.out.println("2. Add at End");

        int choice = readInt("Enter Your Choice: ");

        if (choice == 1) {

            attendance.addFirst(name);
            System.out.println("Attendance Added at Beginning!");

        } else if (choice == 2) {

            attendance.addLast(name);
            System.out.println("Attendance Added at End!");

        } else {
            System.out.println("Invalid Choice!");
        }
    }

    // 7. Display Attendance
    static void displayAttendance() {

        if (attendance.isEmpty()) {
            System.out.println("No Attendance Records Found!");
            return;
        }

        System.out.println("\n--------- ATTENDANCE LIST ---------");

        for (String name : attendance) {
            System.out.println(name);
        }

        System.out.println("Total Present Students: " + attendance.size());
    }

    // 8. Remove Attendance
    static void removeAttendance() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        if (attendance.contains(name)) {

            attendance.remove(name);
            System.out.println("Attendance Removed Successfully!");

        } else {
            System.out.println("Student Not Found in Attendance!");
        }
    }

    // 9. Display Student Statistics
    static void displayStatistics() {

        if (studentMarks.isEmpty()) {
            System.out.println("No Student Records Available!");
            return;
        }

        int highest = Collections.max(studentMarks);
        int lowest = Collections.min(studentMarks);

        int sum = 0;
        int above75 = 0;

        for (int marks : studentMarks) {

            sum += marks;

            if (marks > 75) {
                above75++;
            }
        }

        double average = (double) sum / studentMarks.size();

        System.out.println("\n--------- STUDENT STATISTICS ---------");

        System.out.println("Total Students: " + studentNames.size());
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
        System.out.printf("Average Marks: %.2f%n", average);
        System.out.println("Students Above 75: " + above75);
    }

    // 10. Sort Student Names
    static void sortStudents() {

        if (studentNames.isEmpty()) {
            System.out.println("No Students Available!");
            return;
        }

        ArrayList<String> sortedNames = new ArrayList<>(studentNames);

        Collections.sort(sortedNames);

        System.out.println("\nStudents in Alphabetical Order:");

        for (String name : sortedNames) {
            System.out.println(name);
        }

        Collections.reverse(sortedNames);

        System.out.println("\nStudents in Reverse Order:");

        for (String name : sortedNames) {
            System.out.println(name);
        }
    }

    // 11. Clear All Records
    static void clearAllRecords() {

        if (studentNames.isEmpty() && attendance.isEmpty()) {
            System.out.println("All Lists Are Already Empty!");
            return;
        }

        studentIds.clear();
        studentNames.clear();
        studentMarks.clear();
        attendance.clear();

        System.out.println("All Student and Attendance Records Cleared Successfully!");
    }
}
