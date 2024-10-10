package model;

import java.time.LocalDate;

public class Student extends Human{

    private String studentId;
    private String school;
    private Integer startYear;
    private Double cumulativeGPA;

    private Academic_Performance performance;




    public Student(Integer id, String name, LocalDate dateOfBirth, String address, Double height, Double weight ) {
        super(id, name, dateOfBirth, address, height, weight);
    }

    public Student(String studentId, String school, Integer startYear, Double cumulativeGPA) {
        this.studentId = studentId;
        this.school = school;
        this.startYear = startYear;
        this.cumulativeGPA = cumulativeGPA;
    }

    public Student(Integer id, String name, LocalDate dateOfBirth, String address, Double height, Double weight, String studentId, String school, Integer startYear, Double cumulativeGPA, Academic_Performance performance) {
        super(id, name, dateOfBirth, address, height, weight);
        this.studentId = studentId;
        this.school = school;
        this.startYear = startYear;
        this.cumulativeGPA = cumulativeGPA;
        this.performance = performance;
    }


    public String getStudentCode() {
        return studentId;
    }

    public void setStudentCode(String studentId) {
        this.studentId = studentId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Double getCumulativeGPA() {
        return cumulativeGPA;
    }

    public void setCumulativeGPA(Double cumulativeGPA) {
        this.cumulativeGPA = cumulativeGPA;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", StudentId: '" + studentId + '\'' +
                ", School:'" + school + '\'' +
                ", StartYear: " + startYear +
                ", CumulativeGPA: " + cumulativeGPA +
                ", Performance: " + performance.toString();
    }

    public static void main(String[] args) {

    }

    public Academic_Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Academic_Performance performance) {
        this.performance = performance;
    }
}
