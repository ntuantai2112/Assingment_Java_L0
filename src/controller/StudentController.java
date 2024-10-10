package controller;

import service.DynamicArrayStudentService;
import service.StaticArrayStudentService;
import service.StudentService;
import util.InputHandler;
import view.Menu;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StudentController {

    private StudentService studentService;

    private static OutputStream file;

    private static String fileName;


    private void initialize(StudentService studentService){
        file = null;
        fileName = null;
        this.studentService = studentService;
    }

    public void start()  {
        Menu.storageMenu();
        int type = InputHandler.modeValidate();
        if(type == 1){
            initialize(new StaticArrayStudentService());
            staticMode();
        }else if(type == 2) {
            initialize(new DynamicArrayStudentService());
            dynamicMode();
        }else {
            System.out.println("Invalid mode!, Please enter number 1 or 2! ");
            start();
        }

    }


    private void staticMode(){
        while(true){
            Menu.staticArrayMenu();
            int choice = InputHandler.modeValidate();
            if(choice == 6){
                System.out.println("Exit the program successfully");
                break;
            }
            switch (choice){
                case 1 -> studentService.create();
                case 2 -> studentService.readFile();
                case 3 -> studentService.update();
                case 4 -> studentService.delete();
                case 5 -> studentService.printAllStudents();
            }
        }
    }

    private void dynamicMode()   {
        while(true){
            Menu.dynamicArrayMenu();
            int choice = InputHandler.modeValidate();
            if(choice == 9){
                try {
                    StudentController.fileName = InputHandler.inputFileName();
                    file = new FileOutputStream(StudentController.fileName);
                    DynamicArrayStudentService.expordToFile(file);
                    file.close();
                    System.out.println("Exit the program successfully");
                    break;
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
            switch (choice){
                case 1 -> studentService.create();
                case 2 -> studentService.readFile();
                case 3 -> studentService.update();
                case 4 -> studentService.delete();
                case 5 -> studentService.printAllStudents();
                case 6 ->  DynamicArrayStudentService.showPercentageOfPerformance();
                case 7 ->  DynamicArrayStudentService.showPercentageOfGPA();
                case 8 ->  DynamicArrayStudentService.showStudentWithPerformanceFromConsole();
            }
        }
    }








}
