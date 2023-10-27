package lab8;

import lab8.entity.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentBE studentBE = StudentBE.getInstance();
        int choice;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            saveStudentsData(studentBE.getStudents());
        }));

        do {
            System.out.println("|=========================================MENU=========================================|");
            System.out.println("| 1. Add the student in the list students.                                             |");
            System.out.println("| 2. Display all students in the list.                                                 |");
            System.out.println("| 3. Display all students pass through math lesson in the list students.               |");
            System.out.println("| 4. Find information a student by ID in the list students.                            |");
            System.out.println("| 5. Edit information of the students                                                  |");
            System.out.println("| 6. Delete information of the students                                                |");
            System.out.println("| 7. Display all students to have the same age in the list                             |");
            System.out.println("| 8. Display all students to have the same level in the list                           |");
            System.out.println("| 0. Exit app.                                                                         |");
            System.out.println("|======================================================================================|");
            System.out.println("Please choose the right function : ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    studentBE.addStudentInList();
                    break;
                }
                case 2: {
                    studentBE.displayListStudents();
                    break;
                }
                case 3: {
                    studentBE.displayStudentPasserMath();
                    break;
                }
                case 4: {
                    studentBE.findStudentByID();
                    break;
                }
                case 5: {
                    studentBE.editInfoStudent();
                    break;
                }
                case 6: {
                    studentBE.deleteInfoStudent();
                    break;
                }
                case 7: {
                    studentBE.findStudentsByAge();
                    break;
                }
                case 8: {
                    studentBE.findStudentsByAgeLevel();
                    break;
                }
                case 0: {
                    System.out.println("See you again!");
                }
                default:
                    System.out.println("Please again choose the right function!");
            }
        } while (choice != 0);
    }

    private static void saveStudentsData(ArrayList<Student> students) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("students_data.txt");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(students.toString());
            System.out.println("Students data saved to students_data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
