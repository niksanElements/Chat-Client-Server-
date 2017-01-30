package Message.Friend;

import Message.Message;

import java.util.List;

/**
 * Created by Nikolay on 1/26/2017.
 */
public class AddFriends extends Message {
    public static final String TYPE = "ADDFRIENDS";

    private List<String> names;

    public AddFriends(List<String> names) {
        super(AddFriends.TYPE);
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }
}
