package Database;

import Models.MD5Converter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller for the database which is use for authorize the user.
 * It uses Prepared Statements for login and registration.
 *
 * Created by Nikolay on 1/25/2017.
 */
public class AuthorizeController extends BaseController {
    private PreparedStatement loginStatement;
    private PreparedStatement registrationStatement;

    public AuthorizeController(){
        super();
        try {
            this.loginStatement = this.connDatabase.prepareStatement("SELECT username,password FROM users WHERE username = ?");
            this.registrationStatement = this.connDatabase.prepareStatement("INSERT INTO users(username,password,firstName,lastName)" +
                    " VALUES (?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Login
     *
     * @param username
     * @param password
     * @return true if the user is loged of false if it is not.
     */
    public boolean login(String username,String password){
        try{
            this.loginStatement.setString(1,username);
            ResultSet set = this.loginStatement.executeQuery();
            if(set.first()){
                String pass = set.getString("password");
                if(MD5Converter.convert(password).equals(pass)){
                    return true;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Registrate
     *
     * @param username
     * @param password
     * @param firstName
     * @param secondName
     * @return true if the user is registrate or false if it's not.
     */
    public boolean registration(String username,String password,String firstName,String secondName){
        try{
            this.registrationStatement.setString(1,username);
            this.registrationStatement.setString(2, MD5Converter.convert(password));
            this.registrationStatement.setString(3,firstName);
            this.registrationStatement.setString(4,secondName);
            int result = this.registrationStatement.executeUpdate();
            if(result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void close(){
        try {
            this.registrationStatement.close();
            this.loginStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.close();
    }
}
