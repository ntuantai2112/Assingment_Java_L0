package service;

import model.Academic_Performance;
import model.Student;
import util.InputHandler;
import view.Menu;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

import static constraints.Constraints.MAX_STUDENT_LIST_SIZE;

public class StaticArrayStudentService implements StudentService {


    private static final Student[] staticStudentList = new Student[MAX_STUDENT_LIST_SIZE];

    private static Integer counter = 0;

    private static Integer autoStudentId = 0;

    private final Scanner scanner;

    public StaticArrayStudentService() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void create() {
        if (counter == MAX_STUDENT_LIST_SIZE) {
            System.out.println("Your array reaches limit size");
            return;
        }

        String name = InputHandler.inputName();
        LocalDate dateOfBirth = InputHandler.inputDateOfBirth();
        String address = InputHandler.inputAddress();
        Double height = InputHandler.inputHeight();
        Double weight = InputHandler.inputWeight();
        String studentCode = InputHandler.staticStudentCode(staticStudentList, counter);
        String school = InputHandler.inputSchool();
        Integer startYear = InputHandler.inputYear();
        Double cumulativeGPA = InputHandler.inputGPA();
        autoStudentId++;

        Academic_Performance performance = InputHandler.inputAcademicPerformance(cumulativeGPA);

        staticStudentList[counter] = new Student(autoStudentId, name, dateOfBirth, address, height, weight, studentCode, school, startYear, cumulativeGPA,performance);
        System.out.println("Create Student Successfully!");
        System.out.println(staticStudentList[counter++]);

    }

    @Override
    public void update() {
        Student student = readFile();
        if (student != null) {
            updateInfo(student);
            System.out.println("Update Student Successfully!");
            System.out.println(student);
        }
    }

    private void updateInfo(Student student) {
        student.setId(student.getId());
        while (true) {
            Menu.updateMenu();
            Integer option = scanner.nextInt();

            switch (option) {
                case 1 -> student.setName(InputHandler.inputName());
                case 2 -> student.setDateOfBirth(InputHandler.inputDateOfBirth());
                case 3 -> student.setAddress(InputHandler.inputAddress());
                case 4 -> student.setHeight(InputHandler.inputHeight());
                case 5 -> student.setWeight(InputHandler.inputWeight());
                case 6 -> student.setStudentCode(InputHandler.staticStudentCode(staticStudentList, counter));
                case 7 -> student.setSchool(InputHandler.inputSchool());
                case 8 -> student.setStartYear(InputHandler.inputYear());
                case 9 -> {
                    student.setCumulativeGPA(InputHandler.inputGPA());
                    student.setPerformance(InputHandler.inputAcademicPerformance(student.getCumulativeGPA()));
                }

            }
            Menu.continueMenu();
            Integer choice = scanner.nextInt();
            if (choice == 2) {
                break;
            }

        }


    }


    @Override
    public void delete() {
        // Nhạp iD muon xoa, kiem tra ID trươc khi xoa
        // Duyejt qua mang, tao câu dien kien so sánh và tim kiesm, nesu tìm thấy thì gán giá trị vào index, và biến found = true
        // Duyet qua các phan tu trong mang tu vi tri index vừa mới được gán tới vị tri cuoi cung cua mang (counter - 1);
        // Gan giá trị (phan tu thứ) trước vi trí i+i và vi tri i
        // Ben ngoai vong lap gan gia tri ở vị trí cuối cùng(couter - 1) == null
        // Trừ counter đi 1 gia tri counter--
        // Kierm tra hàm founde neu khong tim thay thi thong báo


         int index =0;
        boolean found = false;
        Integer studentID = InputHandler.deleteID();
        if( studentID <=0 || studentID > autoStudentId){
            System.out.println("Can not found student!");
        }

        for (int i = 0;i < counter ;i++){
            if(staticStudentList[i].getId() == studentID){
                System.out.println("Delete Student Successfully!");
                found = true;
                index = i;
                break;
            }
        }

        for (int i = index; i < counter - 1; i++){
            staticStudentList[i] = staticStudentList[ i + 1];
        }

        staticStudentList[counter - 1] = null;
        counter--;

        if(found == false){
            System.out.println("Can not found student!");
        }


    }

    @Override
    public void printAllStudents() {
        System.out.println("==================================List of student===============================");
        if (counter == 0) {
            System.out.println("The list is empty");
            return;
        }

        Arrays.stream(staticStudentList).forEach(student -> {
            if (student != null) {
                System.out.println(student);
            }
        });

    }

    @Override
    public Student readFile() {
        // Tìm kiếm đối tượng sinh viên
        // Gọi hàm nhập dữ liệu từ bàn phím, kiểm tra nếu nhỏ hơn hoac =0 hoac >= autoID thì báo loi
        // nguoc lai, su dung vong lap, tìm kiesm, kiem tra va so sanh,neu id tìm kiem = id trong CSDL thì trả về đối tuong, in thong tin ra man hinh
        Integer studentID = InputHandler.inputId();
        if (studentID <= 0 || studentID > autoStudentId) {
            System.out.println("Can not find student!");
            return null;
        } else {
            for (int i = 0; i < counter; i++) {
                if (staticStudentList[i].getId() == studentID) {
                    System.out.println("Read Student Successfully!");
                    System.out.println(staticStudentList[i]);
                    return staticStudentList[i];
                }
            }
        }

        System.out.println("Can not find student!");
        return null;
    }
}
