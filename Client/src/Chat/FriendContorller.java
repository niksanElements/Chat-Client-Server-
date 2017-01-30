package Chat;

import Message.Answer.Success;
import Message.Friend.AddFriend;
import Message.Friend.GiveMeAllUsers;
import Message.Message;
import screans.MainScrean.SendInviteAction;

import javax.swing.*;
import java.util.List;

/**
 * Friend controller
 * Created by Nikolay on 1/27/2017.
 */
public class FriendContorller {

    /**
     * List on all friends
     */
    private JList<String> list;
    private DefaultListModel<String> model;

    public FriendContorller(DefaultListModel<String> model){
        this.model = model;
    }

    public void addAllFriends(GiveMeAllUsers message){
        if(message != null){
            List<String> result = message.getNames();
            for(int i = 0;i < result.size();i++){
                this.model.addElement(result.get(i));
            }
        }
    }

    public void addFriend(AddFriend message){
        if(message != null){
            this.model.addElement(message.getName());
        }
    }

    public void mark(String name){
    }
}
