package Message.Friend;

import Message.Message;

/**
 * Created by Nikolay on 1/26/2017.
 */
public class AddFriend extends Message {
    public static final String TYPE = "ADDFRIEND";

    private String name;

    public AddFriend(String name) {
        super(AddFriend.TYPE);
        this.name = name;
    }

    public String getName(){return this.name;}
}
