package Message.Chat;

import Message.Message;

/**
 * Created by Nikolay on 1/19/2017.
 */
public class Welcome extends Message {

    public static final String TYPE = "WELCOME";

    private String nameFrom;
    private String nameTo;

    public Welcome(String from,String to) {
        super(Welcome.TYPE);
        this.nameFrom = from;
        this.nameTo = to;
    }

    public String getFrom(){
        return this.nameFrom;
    }

    public String getTo(){
        return this.nameTo;
    }
}
