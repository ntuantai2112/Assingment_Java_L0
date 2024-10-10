package service;

import model.Academic_Performance;
import model.Student;
import util.InputHandler;
import view.Menu;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

import static constraints.Constraints.MAX_STUDENT_LIST_SIZE;

public class DynamicArrayStudentService implements StudentService {


    private static final List<Student> dynamicStudenList = new ArrayList<Student>();
    private static Integer autoStudentId = 0;

    private static Scanner scanner;


    public DynamicArrayStudentService() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void create() {

        String name = InputHandler.inputName();
        LocalDate dateOfBirth = InputHandler.inputDateOfBirth();
        String address = InputHandler.inputAddress();
        Double height = InputHandler.inputHeight();
        Double weight = InputHandler.inputWeight();
        String studentCode = InputHandler.dynamicStudentCode(dynamicStudenList);
        String school = InputHandler.inputSchool();
        Integer startYear = InputHandler.inputYear();
        Double cumulativeGPA = InputHandler.inputGPA();
        autoStudentId++;

        Academic_Performance performance = InputHandler.inputAcademicPerformance(cumulativeGPA);

        Student student = new Student(autoStudentId, name, dateOfBirth, address, height, weight, studentCode, school, startYear, cumulativeGPA, performance);
        System.out.println("Creat student successfully");
        dynamicStudenList.add(student);
        System.out.println(student);

    }


    @Override
    public void update() {
        Student student = readFile();
        if (student != null) {

            updateInfo(student);
            System.out.println("Update student successfully");
            System.out.println(student);
        }

    }


    @Override
    public void delete() {

        if (dynamicStudenList.size() == 0) {
            System.out.println("The list is empty. Cannot perform deletion");
            return;
        }

        Integer studentId = InputHandler.deleteID();

        if (studentId <= 0 || studentId > autoStudentId) {
            System.out.println("Cannot find student!");
        } else {
            if (dynamicStudenList.stream().noneMatch(student -> student.getId() == studentId)) {
                System.out.println("Cannot find student!");
                return;
            }
            System.out.println("Delete student successfully!");
            dynamicStudenList.removeIf(student -> student.getId() == studentId);
            printAllStudents();
        }


    }

    @Override
    public void printAllStudents() {
        System.out.println("================================= List of student ====================================");
        if (dynamicStudenList.size() == 0) {
            System.out.println("The list is empty!");
        }
        dynamicStudenList.forEach(student -> System.out.println(student));

    }

    @Override
    public Student readFile() {

        Integer studentId = InputHandler.inputId();
        if (studentId <= 0 || studentId > autoStudentId) {
            System.out.println("Cannot file student!");
            return null;
        } else {
            for (Student student : dynamicStudenList) {
                if (student.getId() == studentId) {
                    System.out.println("Read student successfully!");
                    System.out.println(student);
                    return student;
                }
            }
        }

        System.out.println("Cannot find student");

        return null;
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
                case 6 -> student.setStudentCode(InputHandler.dynamicStudentCode(dynamicStudenList));
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

//      Làm chức năng: Hiển thị % học lực của các sinh viên trong danh sách sắp xếp từ cao xuống thấp

    public static void showPercentageOfPerformance() {
        System.out.println("""
                =========================================== Percentage of performance ======================================
                """);
        Map<Academic_Performance, Integer> mapPerformance = new HashMap<>();
        // Duyệt danh sách học sinh đếm số lượng học sinh theo mỗi loại hiệu xuất, nếu học sinh đó có hiệu xuất trong Map thì tăng lên 1, không có thì gán trị bằng 1
        for (Student student : dynamicStudenList) {
            if (mapPerformance.containsKey(student.getPerformance())) {
                mapPerformance.replace(student.getPerformance(), mapPerformance.get(student.getPerformance()) + 1);
            } else {
                mapPerformance.put(student.getPerformance(), 1);
            }
        }

        int size = dynamicStudenList.size();
        if (size == 0) {
            return;
        }

        List<Map.Entry<Academic_Performance, Double>> percentageList = new ArrayList<>();
        for (Map.Entry<Academic_Performance, Integer> entry : mapPerformance.entrySet()) {
            // TÍnh giá trị % cho cho tung hieu xuat voi so luong học sinh số lượng nhân với 100 chi cho tổng số luong sinh vien
            double percentage = (double) entry.getValue() * 100 / size;
            Map.Entry<Academic_Performance, Double> pair = new AbstractMap.SimpleEntry<>(entry.getKey(), percentage);
            percentageList.add(pair);
        }

        // Săp xếp danh sách kế quả % hiệu xuất học sinh
        percentageList.sort((s1, s2) -> {
            if (s1.getValue().compareTo(s2.getValue()) == 0) {
                return s1.getKey().compareTo(s2.getKey());
            }
            return -s1.getValue().compareTo(s2.getValue());

        });

        // Hiển thị kết quả
        DecimalFormat df = new DecimalFormat("##.##");
        percentageList.forEach(entry -> System.out.println(entry.getKey() + " : " + df.format(entry.getValue()) + "%"));


    }


    // Làm chuc nang : Hiển thị % điểm trung bình của các sinh viên trong danh sách
    //Ví dụ có 5 bạn sinh viên có điểm trung bình lần lượt là: 1,2,3,2,4
    //-> Kết quả đầu ra: 1: 20%, 2: 40%, 3: 20%, 4: 20%.
    // ( Gợi ý dùng Map)
    public static void showPercentageOfGPA() {
        System.out.println("""
                =========================================== Percentage of GPA ======================================
                """);
        Map<Double, Integer> mapGPA = new HashMap<>();
        // Duyệt danh sách học sinh đếm số lượng học sinh theo mỗi loại hiệu xuất, nếu học sinh đó có hiệu xuất trong Map thì tăng lên 1, không có thì gán trị bằng 1
        for (Student student : dynamicStudenList) {
            if (mapGPA.containsKey(student.getCumulativeGPA())) {
                mapGPA.replace(student.getCumulativeGPA(), mapGPA.get(student.getCumulativeGPA()) + 1);
            } else {
                mapGPA.put(student.getCumulativeGPA(), 1);
            }
        }

        int size = dynamicStudenList.size();
        if (size == 0) {
            return;
        }

        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (Map.Entry<Double, Integer> entry : mapGPA.entrySet()) {
            double percentage = (double) entry.getValue() * 100 / size;

            System.out.println(entry.getKey() + " : " + decimalFormat.format(percentage) + "%");
        }

    }

    // Tạo hàm làm chức năng In ra màn hình danh sách các sinh viên tuỳ theo học lực nhập từ bàn phím
    public static void showStudentWithPerformanceFromConsole() {
        Academic_Performance performance = InputHandler.inputPerformance();
        System.out.println("""
                =============================== Student List ===========================
                """);

        // Sử dụng Hàm noneMath trong stream để kiểm tra kết quả trả về,Kiểm tra xem không có sinh viên nào trong danh sách có kết quả học tập bằng với kết quả mà người dùng đã nhập.
        if (dynamicStudenList.stream().noneMatch(student -> student.getPerformance() == performance)) {
            System.out.println("None of the student have this performance");
        } else {
            dynamicStudenList.forEach(student -> {
                if (student.getPerformance().equals(performance)) {
                    System.out.println(student);
                }
            });
        }


    }

    // Tạo hàm Writetofile
    private static void writeToFile(OutputStream outputStream, String student) throws IOException {

        for (Character c : student.toCharArray()){
            outputStream.write(c);
        }

        outputStream.write('\n');

    }

    // Tạo hàm ghi gia tri vào File
    public static void expordToFile(OutputStream outputStream) throws IOException {

        if(dynamicStudenList.size() == 0){
            System.out.println("The list student is empty");
            return;
        }

        for (Student student : dynamicStudenList){
                writeToFile(outputStream,student.toString());
        }

        System.out.println("Save information successfully!");



    }

}
