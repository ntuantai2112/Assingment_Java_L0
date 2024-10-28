package util;

import model.Academic_Performance;
import model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import static constraints.Constraints.*;

public class InputHandler {


    // Khai báo Scanner
    public static final Scanner scan = new Scanner(System.in);

    // Khai báo trường dữ liệu để đổi màu thông báo lỗi
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    // Tạo hàm Validate trường dữ liệu Name
    //  + Tên - chuỗi < 100 ký tự, không rỗng
    //        + Ngày sinh - ngày giờ từ năm 1900, không null
    //        + Địa chỉ - chuỗi < 300 kí tự
    //        + Chiều cao (đơn vị cm): từ 50.0 - 300.0
    //        + Cân nặng (đơn vị kg): từ 5.0 - 1000.0
    //
    //- Sinh viên kế thừa Người, gồm thêm các thông tin:
    //        + Mã sinh viên - Chuỗi 10 kí tự không trùng, không null
    //        + Trường đang theo học - chuỗi < 200 kí tự, không null
    //        + Năm bắt đầu học đại học: số 4 chữ số từ 1900, không null
    //        + Điểm trung bình tích luỹ - từ 0.0 đến 10.0


    public static String inputName() {
        System.out.println("Input your name: ");
        String name = scan.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid name!, Please enter a valid name!" + ANSI_RESET);
            return inputName();
        } else if (name.length() > MAX_NAME_LENGTH) {
            System.out.println(ANSI_RED + "Invalid name!, The name cannot exceed 100 characters!" + ANSI_RESET);
            return inputName();
        }
        return name;
    }


    public static LocalDate inputDateOfBirth() {
        System.out.println("Input your date of birth(dd/MM/yyyy): ");
        String birthDayStr = scan.nextLine().trim();
        if (birthDayStr.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid date of birth!, Please enter a valid date of birth!" + ANSI_RESET);
            return inputDateOfBirth();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {

            String[] parts = birthDayStr.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (day <= 0 || day > 31 || month <= 0 || month > 12) {
                System.out.println(ANSI_RED + "Invalid date of birth!, Please enter a valid date of birth!" + ANSI_RESET);
                return inputDateOfBirth();
            }

            switch (month) {
                case 4, 6, 9, 11:
                    if (day > 30) {
                        System.out.println(ANSI_RED + "Invalid date of birth!, Please enter a valid date of birth!" + ANSI_RESET);
                        return inputDateOfBirth();
                    }
                    break;

                case 2:
                    if (checkLeapYear(year)) {
                        if (day > 29) {
                            System.out.println(ANSI_RED + "Invalid date of birth!, Please enter a valid date of birth!" + ANSI_RESET);
                            return inputDateOfBirth();
                        }
                    } else {
                        if (day > 28) {
                            System.out.println(ANSI_RED + "Invalid date of birth!, Please enter a valid date of birth!" + ANSI_RESET);
                            return inputDateOfBirth();
                        }
                    }
                    break;
            }

            LocalDate birthDay = LocalDate.parse(birthDayStr, formatter);

            if (year < 1900) {
                System.out.println(ANSI_RED + "Invalid date of birth!, Year of birth must be after 1900" + ANSI_RESET);
                return inputDateOfBirth();
            }

            if (year >= LocalDate.now().getYear()) {
                System.out.println(ANSI_RED + "Invalid date of birth!,Year of birth must be less than or equal to the current year!" + ANSI_RESET);
            }

//            if (birthDay.isAfter(LocalDate.now())) {
//                System.out.println(ANSI_RED + "Invalid date of birth!,Year of birth must be less than or equal to the current year!" + ANSI_RESET);
//                return inputDateOfBirth();
//            }

            return birthDay;
        } catch (DateTimeParseException e) {
            System.out.println(ANSI_RED + "Invalid date of birth!,The day of birth must be used properly(dd/MM/yyyy)!" + ANSI_RESET);
            return inputDateOfBirth();
        }


    }

    // Ham Validate address
    public static String inputAddress() {
        System.out.println("Input your address: ");
        String address = scan.nextLine().trim();
        if (address.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid address!, Please enter a valid address!" + ANSI_RESET);
            return inputAddress();
        } else if (address.length() > MAX_ADDRESS_LENGTH) {
            System.out.println(ANSI_RED + "Invalid address!, The address cannot exceed 300 characters!" + ANSI_RESET);
            return inputAddress();
        }
        return address;
    }

    // Ham Validate chieu cao
    public static Double inputHeight() {
        System.out.println("Input your height: ");
        String heightStr = scan.nextLine().trim();
        try {
            Double height = Double.parseDouble(heightStr);
            if (heightStr.isEmpty()) {
                System.out.println(ANSI_RED + "Invalid height!, Please enter a valid height!" + ANSI_RESET);
                return inputHeight();
            } else if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
                System.out.println(ANSI_RED + "Invalid height!, The height of the male must range from 50cm to 300cm!" + ANSI_RESET);
                return inputHeight();
            }
            return height;
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid height!, The height must be a number!" + ANSI_RESET);
            return inputHeight();
        }

    }

    // Ham Validate can nang
    public static Double inputWeight() {
        System.out.println("Input your weight: ");
        String weightStr = scan.nextLine().trim();
        try {
            Double weight = Double.parseDouble(weightStr);
            if (weightStr.isEmpty()) {
                System.out.println(ANSI_RED + "Invalid weight!, Please enter a valid weight!" + ANSI_RESET);
                return inputWeight();
            } else if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
                System.out.println(ANSI_RED + "Invalid weight!, The weight of the male must range from 5kg to 1000kg!" + ANSI_RESET);
                return inputWeight();
            }
            return weight;
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid height!, The weight must be a number!" + ANSI_RESET);
            return inputHeight();
        }

    }

    //Validate Ma sinh vien voi List( mang dong)
    private static String inputStudentCode(List<Student> students) {
        System.out.println("Input your student code: ");
        String studentCode = scan.nextLine().trim();
        if (studentCode.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid student code!, Please enter a valid student code!" + ANSI_RESET);
            return inputStudentCode(students);
        } else if (studentCode.length() != MAX_STUDENT_ID_LENGTH) {
            System.out.println(ANSI_RED + "Invalid student code!, Student code must contain 10 characters!" + ANSI_RESET);
            return inputStudentCode(students);
        } else if (students.stream().anyMatch(student -> student != null && student.getStudentCode().equals(studentCode))) {
            System.out.println(ANSI_RED + "Student ID has existed! Please enter again!" + ANSI_RESET);
            return inputStudentCode(students);
        }
        return studentCode;
    }

    // Validate Ma SV voi mang tĩnh Array[]
    public static String staticStudentCode(Student[] students, Integer currentSize) {
        // Lấy giá trị nhập vào từ bàn phím, tra ve gia tri studentCode de so sanh voi cac phan tu trong mang
        String studentCode = inputStudentCode(Arrays.asList(students));
        for (int i = 0; i < currentSize; i++) {
            if (students[i].getStudentCode().equals(studentCode)) {
                System.out.println(ANSI_RED + "Student ID has existed! Please enter again!" + ANSI_RESET);
                return staticStudentCode(students, currentSize);
            }
        }

        return studentCode;

    }

    // Validate Ma SV voi mang dong List
    public static String dynamicStudentCode(List<Student> students) {
        String studentCode = inputStudentCode(students);
        if (students.stream().anyMatch(student -> student != null && student.getStudentCode().equals(studentCode))) {
            System.out.println(ANSI_RED + "Student ID has existed! Please enter again!" + ANSI_RESET);
            return dynamicStudentCode(students);
        }
        return studentCode;

    }

    // Validate truong du lieu School Name
    public static String inputSchool() {
        System.out.println("Input your school's name: ");
        String schoolName = scan.nextLine().trim();
        if (schoolName.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid school name!, Please enter a valid school name!" + ANSI_RESET);
            return inputSchool();
        } else if (schoolName.length() > MAX_SCHOOL_NAME_LENGTH) {
            System.out.println(ANSI_RED + "Invalid school name!, School names cannot exceed 200 characters!" + ANSI_RESET);
            return inputSchool();
        }

        return schoolName;
    }


    // validate trường dữ liệu Năm
    public static Integer inputYear() {
        System.out.println("Input your year: ");
        String yearStr = scan.nextLine().trim();
        try {
            if (yearStr.isEmpty()) {
                System.out.println(ANSI_RED + "Invalid year, Please enter a valid year!" + ANSI_RESET);
                return inputYear();
            }
            if (yearStr.length() != 4 || !yearStr.matches("\\d{4}")) {
                System.out.println(ANSI_RED + "Invalid year, The year must consist of 4 digits!" + ANSI_RESET);
                return inputYear();
            }

            Integer year = Integer.parseInt(yearStr);
            if (year < MIN_DAY_OF_BIRTH_AND_START_YEAR || year > MAX_START_YEAR) {
                System.out.println(ANSI_RED + "Invalid year, The year must be between 1990 and the current year!" + ANSI_RESET);
                return inputYear();
            }

            if (year > Calendar.getInstance(Locale.ROOT).get(Calendar.YEAR)) {
                System.out.println(ANSI_RED + "Invalid year, The year cannot exceed the current year!" + ANSI_RESET);
                return inputYear();
            }


            return year;
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid year, The year must be a number!" + ANSI_RESET);
            return inputYear();
        }


    }

    // Validate trương du lieu GPA
    public static Double inputGPA() {
        System.out.println("Input your cumulativeGPA: ");
        String gpaStr = scan.nextLine().trim();
        if (gpaStr.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid gpa, Please enter a gpa!" + ANSI_RESET);
            return inputGPA();
        }

        Double gpa = Double.parseDouble(gpaStr);
        if (gpa < MIN_GPA || gpa > MAX_GPA) {
            System.out.println(ANSI_RED + "Invalid gpa, GPA must be between 0 and 10!" + ANSI_RESET);
            return inputGPA();
        }

        return gpa;
    }

    // Ham sap xep nang luc sinh vien theo diem
//    private static Acade


    // Ham Validate gia tri nhap vao khi chon Menu
    public static Integer modeValidate() {
        String modeStr = scan.nextLine().trim();
        if (modeStr.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid mode, Please enter again!" + ANSI_RESET);
            return modeValidate();
        }

        Integer mode = -1;

        try {
            mode = Integer.parseInt(modeStr);
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid mode, Please enter again!" + ANSI_RESET);
            return modeValidate();
        }

        return mode;
    }

    //Xay dung ham Nhap vao ID muon tim kiem
    public static Integer inputId() {
        System.out.println("Input ID that you want to find:");
        return readOfDeleteValidate();

    }

    // Xay dung ham xoa
    public static Integer deleteID() {
        System.out.println("Input ID that you want to delete:");
        return readOfDeleteValidate();

    }


    // Xay dung ham doc du lieu hoac xoa
    public static Integer readOfDeleteValidate() {
        String idStr = scan.nextLine().trim();
        if (idStr.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid id, Please enter again!" + ANSI_RESET);
            return modeValidate();
        }

        Integer mode = -1;

        try {
            mode = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println(ANSI_RED + "Invalid Id, Please enter again!" + ANSI_RESET);
            return inputId();
        }

        return mode;
    }

    // Thêm hàm nhap vao gia tri Diem de tra ve ket qua hoc tap
    public static Academic_Performance inputAcademicPerformance(Double gpa) {
        if (gpa < 3) {
            return Academic_Performance.WEAK;
        } else if (gpa >= 3 && gpa < 5) {
            return Academic_Performance.POOR;
        } else if (gpa >= 5 && gpa < 6.5) {
            return Academic_Performance.AVERAGE;
        } else if (gpa >= 6.5 && gpa < 7.5) {
            return Academic_Performance.GOOD;
        } else if (gpa >= 7.5 && gpa < 9) {
            return Academic_Performance.GREAT;
        }
        return Academic_Performance.EXCELLENT;
    }

    // Ham nhap vao ten file
    public static String inputFileName() {
        System.out.println("Input the file name:");
        String fileName = scan.nextLine().trim();

        if (fileName.isEmpty()) {
            System.out.println(ANSI_RED + "Invalid input file name! Please try again!" + ANSI_RESET);
            return inputFileName();
        }
        return fileName;

    }

    // Hàm nhập vào học lực kết quả học tập để trả về danh sách sinh viên theo học lực
    public static Academic_Performance inputPerformance() {

        System.out.println("Input academic qualification: ");
        Academic_Performance performance;
        String input = scan.nextLine().toUpperCase();
        try {
            performance = Academic_Performance.valueOf(input);
        } catch (IllegalArgumentException e) {
            System.out.println(ANSI_RED + "Invalid academic performance, please try again!" + ANSI_RESET);
            return inputPerformance();

        }
        return performance;
    }


    private static boolean checkLeapYear(int year) {

        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }

        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        }

        return false;
    }


}
