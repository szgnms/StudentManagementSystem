
import java.sql.ResultSet;
import java.sql.SQLException;

public class Methods extends Depot {

    public static void createStudentTable(){
        Repo repo =new Repo();
        repo.createTable();
    }

    public static void start() {


        System.out.println("""
                ============================================
                STUDENT MANAGEMENT SYSTEM
                ============================================""");
        System.out.println("""
                Please Select
                1-Register Student
                2-List All Students
                3-Update Student
                4-Delete Student
                5-Quit""");
        select = scan.next();

        switch (select) {
            case "1":
                registerStudent();
            case "2":
                listAllStudent();
            case "3":
                updateStudent();
            case "4":
                deleteStudent();
            case "5":
                exitProg();
            default:
                while (count<4) {
                    System.out.println("please select correct");
                    count++;
                    start();
                }
                 exitMenu();

        }

    }

    private static void deleteStudent() {
        selectStudent();
        System.out.println("please select id for delete");
        select = scan.next();

        try {
            st.execute("delete from t_student where id=" + select);
        } catch (SQLException e) {
            System.out.println("please select correct id for delete");
        }

        selectDeleteMenu();


    }

    private static void selectStudent() {

        String query = ("select *  from t_student");

        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-----------------------------------------------------------");
        try {
            System.out.printf("%-5s %-15.15s %-15.15s %-15s %-4s  \n",
                    rs.getMetaData().getColumnName(1),
                    rs.getMetaData().getColumnName(2),
                    rs.getMetaData().getColumnName(3),
                    rs.getMetaData().getColumnName(4),
                    rs.getMetaData().getColumnName(5));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-----------------------------------------------------------");
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.printf("%-5s %-15.15s %-15.15s %-15s %-4s\n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("-----------------------------------------------------------");


    }

    private static void updateStudent() {
        selectStudent();
        System.out.println("Please enter id of student");
        select = scan.next();
        System.out.println("""
                1-change Name
                2-change LastName
                3-change City
                4-change Age
                5-Main Menu
                """);
        String change = scan.next();
        switch (change) {
            case "1":
                changeName(select);
            case "2":
                changeLastName(select);
            case "3":
                changeCity(select);
            case "4":
                changeAge(select);
            case "5":
                start();
            default:
                System.out.println("Please enter correct");
                redirectMenu();
                start();


        }


    }

    private static void updateStudentIn() {
        System.out.println("""
                1-change Name
                2-change LastName
                3-change City
                4-change Age
                5-Main Menu
                """);
        String change = scan.next();
        switch (change) {
            case "1":
                changeName(select);
            case "2":
                changeLastName(select);
            case "3":
                changeCity(select);
            case "4":
                changeAge(select);
            case "5":
                start();
            default:
                System.out.println("Please enter correct");
                redirectMenu();
                start();


        }


    }


    private static void changeAge(String select) {
        System.out.println("please enter age");
        String age = scan.next();
        String query = "update t_student set age =?  where id= ?";
        try {
            prst = conn.prepareStatement(query);
            prst.setInt(1, Integer.parseInt(age));
            prst.setInt(2, Integer.parseInt(select));
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         updateStudentIn();
    }

    private static void changeCity(String select) {
        System.out.println("please enter city");
        String city = scan.next();
        String query = "update t_student set city =?  where id= ?";
        try {
            prst = conn.prepareStatement(query);
            prst.setString(1, city);
            prst.setInt(2, Integer.parseInt(select));
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        updateStudentIn();
    }

    private static void changeLastName(String select) {
        System.out.println("please enter LastName");
        String lastName = scan.next();
        String query = "update t_student set lastname =?  where id= ?";
        try {
            prst = conn.prepareStatement(query);
            prst.setString(1, lastName);
            prst.setInt(2, Integer.parseInt(select));
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateStudentIn();
    }

    private static void changeName(String select) {
        System.out.println("please enter name");
        String name = scan.next();
        String query = "update t_student set name =?  where id= ?";
        try {
            prst = conn.prepareStatement(query);
            prst.setString(1, name);
            prst.setInt(2, Integer.parseInt(select));
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        updateStudentIn();


    }


    private static void listAllStudent() {
        selectStudent();
        start();
    }

    private static void registerStudent() {
        Student std = new Student();
        System.out.println("Please enter the Name of the student");
        std.setName(scan.next());
        System.out.println("Please enter the Lastname of the student");
        std.setLastName(scan.next());
        System.out.println("Please enter the City of the student");
        std.setCity(scan.next());
        System.out.println("Please enter the Age of the student");
        std.setAge(scan.nextInt());

        addTable(std);


    }

    private static void addTable(Student std) {
        String query = "INSERT INTO t_student (name,lastname,city, age) values (?,?,?,?)";
        try {
            prst = conn.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            prst.setString(1, std.getName());
            prst.setString(2, std.getLastName());
            prst.setString(3, std.getCity());
            prst.setInt(4, std.getAge());

            prst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        selectRegisterMenu();
    }

    private static void selectRegisterMenu() {
        System.out.println("""
                Please select
                1-Add  student
                2-return menu
                """);
        select = scan.next();
        switch (select) {
            case "1":
                registerStudent();
            case "2":
                start();
            default:
                System.out.println("please select correct\n redirecting to Menu");
                redirectMenu();
                start();
        }
    }

    private static void selectDeleteMenu() {
        System.out.println("""
                Please select
                1-Delete  student
                2-return menu
                """);
        select = scan.next();
        switch (select) {
            case "1":
                deleteStudent();
            case "2":
                start();
            default:
                System.out.println("please select correct\n redirecting to Menu");
                redirectMenu();
                start();
        }
    }

    private static void redirectMenu() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(".");
        }
        System.out.println();
    }

    private static void exitMenu() {
        System.out.println("entered wrong 3 times \n program is closed");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(".");
        }
        System.out.println();
        System.exit(0);
    }

    private static void exitProg() {
        System.out.println("program is closed");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(".");
        }
        System.out.println();
        System.exit(0);
    }


}