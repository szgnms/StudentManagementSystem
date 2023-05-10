import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Repo extends Student{
   static String url= "jdbc:postgresql://localhost:5432/student_db";
    static String username="szgn";
   static String password= "22442017Ms*.";
   static Connection conn  ;
   static Statement st  ;
   static {

       try {
           conn = DriverManager.getConnection(url,username,password);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


       try {
            st=conn.createStatement();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }

    public   void createTable(){
        try {
            st.execute("create table if not exists t_student" +
                    "(id serial unique ," +
                    "name varchar(50) not null check(length(name)>0)," +
                    "lastname varchar(50) not null check(length(lastname)>0)," +
                    "city varchar(20) not null check(length(city)>0)," +
                    "age int not null check(age>0) " +
                    ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
