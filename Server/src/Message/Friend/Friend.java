package Message.Friend;

import Message.Message;

/**
 * Created by Nikolay on 1/26/2017.
 */
public class Friend extends Message {
    public static final String TYPE = "FRIEND";

    public Friend() {
        super(Friend.TYPE);
    }
}
