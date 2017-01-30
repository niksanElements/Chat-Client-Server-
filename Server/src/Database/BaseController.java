package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Base Controller which establish the connection
 * with the database.
 *
 * Created by Nikolay on 1/25/2017.
 */
public abstract class BaseController {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/chat(client-server)";

    public static final String username = "root";
    public static final String password = "";
    protected Connection connDatabase;

    public BaseController(){
        try {
            Class.forName(JDBC_DRIVER);
            this.connDatabase = DriverManager.getConnection(DB_URL,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();// or do log
        } catch (SQLException e) {
            e.printStackTrace();// or do log
        }
    }

    public void close(){
        try {
            this.connDatabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
