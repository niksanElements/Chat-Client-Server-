package Message.Friend;

import Message.Message;

import java.util.List;

/**
 * Created by Nikolay on 1/26/2017.
 */
public class GiveMeAllUsers extends Message {
    public static final String TYPE = "GIVEMEALLUSERS";

    private String name;
    private List<String> names;

    public GiveMeAllUsers(String name) {
        super(GiveMeAllUsers.TYPE);
        this.name = name;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getName() {
        return name;
    }

    public List<String> getNames() {
        return names;
    }
}
