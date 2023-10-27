package lab8;

import lab8.entity.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentBE {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //    public StudentBE() {
//        students = new ArrayList<>();
//        scanner = new Scanner(System.in);
//        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    }
    // create singleton pattern of StudentBE
    private static StudentBE instance = null;

    private StudentBE() {

    }

    public static StudentBE getInstance() {
        if (instance == null) {
            instance = new StudentBE();
        }
        return instance;
    }

    public void addStudentInList() {
        String choose;
        do {
            students.add(new Student(Long.parseLong(getValue("student ID : ")),
                    getValue("name of the student : "),
                    getValue("address of the student : "),
                    getValidCellPhoneNumber("cellphone number of the student (7 digits only) : "),
                    getDate("date of birth of the student (yyyy-MM-dd) : "),
                    getDate("the enter date of the student (yyyy-MM-dd) : "),
                    Double.parseDouble(getValue("math scores of student : "))));

            System.out.println("Enter 1 to continue to add a student in the list.");
            System.out.println("Enter 0 to end to add the student in the list.");
            choose = scanner.nextLine();
        } while (choose.equals("1"));
    }

    public void displayListStudents() {
        if (students.isEmpty()) {
            System.err.println("The list students is empty!");
        } else {
//            Collections.sort(students, new Comparator<Student>() {
//                @Override
//                public int compare(Student student1, Student student2) {
//                    return Long.compare(student1.getId(), student2.getId());
//                }
//            });
            students.sort((student1, student2) -> Long.compare(student1.getId(), student2.getId()));
            System.out.println("The list students : ");
            int count = 1;
            for (Student student : students) {
                System.out.println(String.format("The student %d is in the students list ", count));
                student.outputInfoStudent();
                count++;
            }
        }
    }

    public void displayStudentPasserMath() {
        if (students.isEmpty()) {
            System.err.println("The list students is empty!");
        } else {
            System.out.println("The list students pass through math lesson : ");
            int count = 0;
            for (Student student : students) {
                if (student.getMathScores() >= 4.0) {
                    System.out.println(String.format("The student number %d is in the students list ", count));
                    student.outputInfoStudent();
                    count++;
                }
            }
            if (count == 0) {
                System.err.println("Don't have anyone pass through math lesson.");
            }
        }
    }

    public void findStudentByID() {
        if (students.isEmpty()) {
            System.err.println("The list students is empty!");
        } else {
            long IDToFind = Long.parseLong(getValue("student ID you need to find : "));
            for (Student student : students) {
                if (student.getId() == IDToFind) {
                    student.outputInfoStudent();
                    return;
                }
            }
            System.err.println(String.format("Don't find ID : %d in the list students.", IDToFind));
        }
    }

    public void editInfoStudent() {
        boolean isValid = false;
        if (students.isEmpty()) {
            System.err.println("The list students is empty!");
        } else {
            long IDToEdit = Long.parseLong(getValue("student ID you need to edit : "));
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId() == IDToEdit) {
                    isValid = true;
                    students.get(i).setId(Long.parseLong(getValue("student ID : ")));
                    students.get(i).setName(getValue("name of the student : "));
                    students.get(i).setAddress(getValue("address of the student : "));
                    students.get(i).setTel(getValidCellPhoneNumber("cellphone number of the student : "));
                    students.get(i).setDOB(getDate("date of birth of the student : "));
                    students.get(i).setEnterDate(getDate("date of the student : "));
                    students.get(i).setMathScores(Double.parseDouble(getValue("math scores of the student : ")));
                }
            }
            if (!isValid) {
                System.err.println(String.format("Don't find ID : %d in the list students.", IDToEdit));
            } else {
                System.err.println(String.format("The student have ID : %d to edited in the list", IDToEdit));
            }
        }
    }

    public void deleteInfoStudent() {
        boolean isValid = false;
        if (students.isEmpty()) {
            System.err.println("The list students is empty!");
        } else {
            long IDToDelete = Long.parseLong(getValue("student ID you need to delete : "));
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId() == IDToDelete) {
                    isValid = true;
                    students.remove(i);
                    break;
                }
            }
            if (!isValid) {
                System.err.println(String.format("Don't find ID : %d in the list students.", IDToDelete));
            } else {
                System.err.println(String.format("The student have ID : %d to edited in the list", IDToDelete));
            }
        }
    }

    public void findStudentsByAge() {
        if (students.isEmpty()) {
            System.err.println("The list students is empty!");
        } else {
            Integer ageToFind = Integer.parseInt(getValue("age of the student you need to find : "));
            for (Student student : students) {
                if (student.getAge() == ageToFind) {
                    student.outputInfoStudent();
                    return;
                }
            }
            System.err.println(String.format("Don't find age : %d in the list students.", ageToFind));
        }
    }

    public void findStudentsByAgeLevel() {
        if (students.isEmpty()) {
            System.err.println("The list students is empty!");
        } else {
            Integer ageLevelToFind = Integer.parseInt(getValue("age of the student you need to find : "));
            for (Student student : students) {
                if (student.getAgeLevel() == ageLevelToFind) {
                    student.outputInfoStudent();
                    return;
                }
            }
            System.err.println(String.format("Don't find level : %d in the list students.", ageLevelToFind));
        }
    }

    private String getValidCellPhoneNumber(String x) {
        String cellphoneNumber = null;
        boolean isValid = false;
        while (!isValid) {
            cellphoneNumber = getValue(x);
            if (isValidCellPhoneNumber(cellphoneNumber)) {
                isValid = true;
            } else {
                System.err.println("Invalid cellphone number format. Please enter 7 digits with no letters.");
            }
        }
        return cellphoneNumber;
    }

    private boolean isValidCellPhoneNumber(String cellphoneNumber) {
        if (cellphoneNumber.length() == 7) {
            Pattern pattern = Pattern.compile("\\d{7}");
            Matcher matcher = pattern.matcher(cellphoneNumber);
            return matcher.matches();
        }
        return false;
    }

    private Date getDate(String x) {
        Date date = null;
        boolean isValid = false;
        while (!isValid) {
            try {
                String dateString = getValue(x);
                date = dateFormat.parse(dateString);
                isValid = true;
            } catch (ParseException e) {
                System.err.println("Invalid date format. Please use yyyy-MM-dd format.");
            }
        }
        return date;
    }

    private String getValue(String x) {
        System.out.println("Enter " + x);
        return scanner.nextLine();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
