package Message.Authorization;

/**
 * Created by Nikolay on 1/25/2017.
 */
public class Login extends Authorize {
    public final static String TYPE = "LOGIN";

    private String password;

    public Login(String username,String password) {
        super(Login.TYPE,username);
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
