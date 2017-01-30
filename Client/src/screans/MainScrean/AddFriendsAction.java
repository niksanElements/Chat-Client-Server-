package screans.MainScrean;

import Message.Friend.Friend;
import Message.Friend.GiveMeAllUsers;
import Message.Message;
import Modles.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Action listener for adding friends
 * The adding is done by clicking twice on the username
 *
 * Created by Nikolay on 1/26/2017.
 */
public class AddFriendsAction extends JFrame implements ActionListener {
    /**
     * List for displaying the the founded names.
     */
    private DefaultListModel<String> model;
    private JList<String> list;

    /**
     * List with already existent friends.
     */
    private DefaultListModel<String> friends;

    private JTextField search;

    private User user;

    public  AddFriendsAction(User user,JTextField search,DefaultListModel<String> friends,DefaultListModel<String> addFriendList){
        this.user = user;
        this.search = search;
        this.friends = friends;
        addFriendList.clear();
        this.model = addFriendList;
        this.list = new JList<>(this.model);
        this.add(this.list);
        this.list.setVisibleRowCount(10);
        JScrollPane scrollPane = new JScrollPane(this.list);
        this.add(scrollPane);
        this.pack();
        this.setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String name = this.search.getText();
        if(name.length() != 0){
            this.user.write(new GiveMeAllUsers(name));
            /**
             * Sending invite action.
             */
            this.list.addListSelectionListener(new SendInviteAction(this.user,this.list,this.model,this.friends));
        }
        this.setVisible(true);
    }
}
