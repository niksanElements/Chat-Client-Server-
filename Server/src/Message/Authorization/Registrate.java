package Message.Authorization;

import Message.Message;

/**
 * Created by Nikolay on 1/25/2017.
 */
public class Registrate extends Authorize {
    public final static String TYPE = "REGISTRATE";

    public String password;
    public String firstName;
    public String lastName;

    public Registrate(String username, String password, String firstName, String lastName) {
        super(Registrate.TYPE,username);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
