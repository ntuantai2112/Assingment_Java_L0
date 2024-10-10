package view;

public class Menu {

        // Tạo ra menu để chọn kho lưu trũ, mảng tĩnh hay động
        public static void storageMenu(){
            String menu = """
                    |-------------------------Choose type of storage---------------------------------|
                    | 1. Static array                                                                |
                    | 2. Dynamic array                                                               |
                    |--------------------------------------------------------------------------------|
                    Choose an option (1 or 2):""";
            System.out.println(menu);
        }

        // Tạp ra menu của static array
        public static void staticArrayMenu(){
            System.out.print("""
                    |-------------------------Choose service you want to use-------------------------|
                    | 1.Insert a student                                                             |
                    | 2.Get info of a student                                                        |
                    | 3.Update a student                                                             |
                    | 4.Delete a student                                                             |
                    | 5.Print all students                                                           |
                    | 6.Exit                                                                         |
                    |--------------------------------------------------------------------------------|
                    Choose an option (1-6):""");
        }

        // Tạo ra menu của dynamic array
        public static void dynamicArrayMenu(){
            System.out.print("""
                    |-------------------------Choose service you want to use-------------------------|
                    | 1.Insert a student                                                             |
                    | 2.Get info of a student                                                        | 
                    | 3.Update a student                                                             |
                    | 4.Delete a student                                                             | 
                    | 5.Print all students                                                           | 
                    | 6.Show percentage of academic performance in the list                          |
                    | 7.Show percentage of GPA                                                       |
                    | 8.Show list of students according to performance from console                  |
                    | 9.Export to file and exit                                                      |
                    |--------------------------------------------------------------------------------|
                    Choose an option (1-9):""");
        }


        // Tạo ra menu cập nhật theo nhiều tiêu chí khác nhau
        public static void updateMenu(){
            String menu = """
                |------------------------------What do you want to update ?--------------------------------|
                |1. Name                              2. Day of birth                3. Address            |
                |4. Height                            5. Weight                      6. StudentID          |
                |7. School                            8. Start year                  9. Gpa                |
                |------------------------------------------------------------------------------------------|
                Choose an option (1-9):""";
            System.out.print(menu);
        }

        // Tạo ra Confirm với người dùng có cập nhật tiếp khong
        public static void continueMenu(){
            String menu = """
                |------------------------------Do you want to continue update ?----------------------------|
                |    1. Yes                                                                      2. No     |
                |------------------------------------------------------------------------------------------|
                Choose an option (1 or 2):""";
            System.out.print(menu);
        }


}
