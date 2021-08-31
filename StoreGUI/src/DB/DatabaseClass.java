package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseClass {
    private Connection con;
    private static DatabaseClass thisInstance;

    private DatabaseClass() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_store_project", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseClass getInstance() {
        if (thisInstance == null)
            thisInstance = new DatabaseClass();
        return thisInstance;
    }

    public Connection getConnection() {
        return this.con;
    }

}
