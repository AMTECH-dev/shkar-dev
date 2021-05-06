package fox.door;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBase {


    public DataBase() throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password")) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
