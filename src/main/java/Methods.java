import java.awt.event.WindowStateListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Methods extends Depot {

    public static void start() {


        System.out.println("============================================\n" +
                "STUDENT MANAGEMENT SYSTEM\n" +
                "============================================");
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
                System.exit(0);
            default:
                System.out.println("please select correct");
                start();

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
        } finally {
            deleteStudent();
        }


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

    }

    private static void listAllStudent() {
        selectStudent();
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

    }

    public static int getStudentId() {
        System.out.println("Enter Student Id");
        student_id = scan.nextInt();
        scan.nextLine();
        return student_id;
    }

}