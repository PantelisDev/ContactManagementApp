import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection connect() {
        Connection conn = null;
        try {
        	String url = "jdbc:sqlite:C:\\Users\\user\\Desktop\\DB.Browser.for.SQLite-v3.13.1-win64\\contacts.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
