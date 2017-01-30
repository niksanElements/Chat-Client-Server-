package Chat;

import Message.Chat.ChatMessage;

import javax.swing.*;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

/**
 * Created by Nikolay on 1/27/2017.
 */
public class WriteContorller {

    private Map<String,JTextArea> areas;

    public WriteContorller(Map<String,JTextArea> area){
        this.areas = area;
    }

    public void performed(ChatMessage message){
        if(message != null) {
            this.areas.get(message.getFrom()).append("\n");
            this.areas.get(message.getFrom()).append(message.getFrom()+": ");
            this.areas.get(message.getFrom()).append(message.getText()+"\n");
        }
    }
}
