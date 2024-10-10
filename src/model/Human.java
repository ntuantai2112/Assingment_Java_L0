package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Human {


    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private Double height;
    private Double weight;


    private Academic_Performance roleUser;






    public Human() {
    }

    public Human(Integer id, String name, LocalDate dateOfBirth, String address, Double height, Double weight) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.height = height;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDateOfBirth = dateOfBirth.format(formatter);

        return "Information of a student:"  +
                " Id: " + id +
                ", Name: '" + name + '\'' +
                ", DateOfBirth: " + formattedDateOfBirth +
                ", Address: '" + address + '\'' +
                ", Height: " + height +
                ", Weight: " + weight
                ;
    }


    public Academic_Performance getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Academic_Performance roleUser) {
        this.roleUser = roleUser;
    }
}
