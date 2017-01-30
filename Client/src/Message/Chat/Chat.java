package Message.Chat;

import Message.Message;

/**
 * Created by Nikolay on 1/26/2017.
 */
public class Chat extends Message {
    public static final String TYPE = "CHAT";

    private String username;

    public Chat(String username) {
        super(Chat.TYPE);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
