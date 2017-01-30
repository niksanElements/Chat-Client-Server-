package screans.MainScrean;

import Message.Chat.ChatMessage;
import Modles.User;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Dictionary;
import java.util.Map;

/**
 * Send action is KeyListener for listening a event indicate
 * sending message.
 * Created by Nikolay on 1/27/2017.
 */
public class SendAction implements KeyListener {

    /**
     * This are structure wich are use for writing or sending message.
     */
    private Map<String,JTextArea> messagesArea;
    private JTextField send;
    private JList<String> userList;

    private User user;

    public SendAction(Map<String,JTextArea> messagesArea, JTextField send, JList<String> userList, User user) {
        this.messagesArea = messagesArea;
        this.send = send;
        this.userList = userList;
        this.user = user;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER == e.getKeyCode()){

            String text = send.getText();
            if(text.length() != 0){
                String toName = userList.getSelectedValue();
                user.write(new ChatMessage(user.getUsername(),toName,text));

                messagesArea.get(toName).append("\n");
                messagesArea.get(toName).append(user.getUsername()+": ");
                messagesArea.get(toName).append(text+"\n");
                this.send.setText("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
