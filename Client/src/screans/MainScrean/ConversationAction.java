package screans.MainScrean;

import Message.Authorization.Authorize;
import Message.Chat.Welcome;
import Modles.User;
import TextCases.AthorizeFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;

/**
 * Created by Nikolay on 1/26/2017.
 */
public class ConversationAction extends Thread implements MouseListener {

    private JList<String> friends;
    private MessagePanle messagePanle;



    public ConversationAction(JList<String> friends,MessagePanle messagePanle){
        this.friends = friends;
        this.messagePanle = messagePanle;
}

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String name = this.friends.getSelectedValue();
            this.messagePanle.showArea(name);
            System.out.println(name);
        }
    }

    private void startConversation(String name) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
