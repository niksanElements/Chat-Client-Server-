package Comunication.Handlers;

import Comunication.Server;
import Database.UsersController;
import Message.Answer.Success;
import Message.Friend.AddFriend;
import Message.Friend.AddFriends;
import Message.Friend.GiveMeAllUsers;
import Message.Friend.GiveMeMyFriends;
import Message.Message;
import Models.User;
import Message.Answer.Error;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Handler for adding friends.
 * It use help for UserController
 *
 * Created by Nikolay on 1/26/2017.
 */
public class AddFriendsHandler{

    private UsersController usersController;
    private User user;

    public AddFriendsHandler(User user, UsersController usersController){
        this.user = user;
        this.usersController = usersController;
    }

    public void getAllUsers(GiveMeAllUsers message){
        synchronized (this.usersController) {
            message.setNames(this.usersController.getAllUsers(message.getName()));
        }
        this.user.write(message);
    }

    public void snedInviteFriendShip(AddFriend message){
        synchronized (this.usersController) {
            if (this.usersController.inviteFriendShip(this.user.getUsername(), message.getName())) {
                this.user.write(new Success(this.user.getUsername(), "You add friend!"));
                if (Server.users.containsKey(message.getName())) {
                    Server.users.get(message.getName()).write(new AddFriend(this.user.getUsername()));
                }
            } else {
                this.user.write(new Error("Error"));
            }
        }
    }

    public void getMyFriends(GiveMeMyFriends message){
        synchronized (this.usersController) {
            message.setNames(this.usersController.getMyFriends(message.getUsername()));
        }
        this.user.write(message);
    }
}
