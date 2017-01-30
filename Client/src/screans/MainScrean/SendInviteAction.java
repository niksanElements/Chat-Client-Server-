package screans.MainScrean;

import Message.Answer.Error;
import Message.Answer.Success;
import Message.Friend.AddFriend;
import Modles.User;
import Message.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Inviting friends.
 * Created by Nikolay on 1/26/2017.
 */
public class SendInviteAction implements ListSelectionListener {

    /**
     * List for displaying the the founded names.
     */
    private DefaultListModel<String> model;
    private JList<String> list;
    /**
     * List with already existent friends.
     */
    private DefaultListModel<String> friends;

    private User user;

    public SendInviteAction(User user,JList<String> list,DefaultListModel<String> model,DefaultListModel<String> friends){
        this.user = user;
        this.list = list;
        this.model = model;
        this.friends = friends;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            /**
             * getting selected value
             */
            int indexSelected = this.list.getSelectedIndex();
            String name = this.list.getSelectedValue();
            /**
             * Sending invite message
             */
            if(name != null) {
                System.out.println(name);
                this.friends.addElement(name);
                //this.user.write(new AddFriend(name));
                this.model.remove(indexSelected);
            }
        }
    }
}
