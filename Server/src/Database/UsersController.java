package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for users.
 * Which user prepared statements for interaction with the user scheme.
 *
 * Created by Nikolay on 1/26/2017.
 */
public class UsersController extends BaseController {

    private PreparedStatement getAllFriendsStatement;
    private PreparedStatement addFriendStatement;
    private PreparedStatement getAllInviteStatement;
    private PreparedStatement inviteFriendStatement;
    private PreparedStatement getAllUsersStatement;

    public UsersController(){
        super();
        try {
            this.getAllFriendsStatement = this.connDatabase.prepareStatement("");
            this.getAllUsersStatement = this.connDatabase.prepareStatement
                    ("SELECT username FROM users WHERE username LIKE ?");
            this.inviteFriendStatement = this.connDatabase.prepareStatement
                    ("INSERT INTO friends(id1_user,id2_user) VALUES (?,?)");
            this.getAllFriendsStatement = this.connDatabase.prepareStatement
                    ("SELECT * FROM friends WHERE id1_user = ? OR id2_user = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param name
     * @return all users wich are masing some name.
     */
    public List<String> getAllUsers(String name){
        List<String> result = new ArrayList<String>();
        try{
            this.getAllUsersStatement.setString(1,"%" + name + "%");
            ResultSet set = this.getAllUsersStatement.executeQuery();
            while(set.next()){
                result.add(set.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param firsName
     * @param secondName
     * @return true if the invite is complete or false if it's not.
     */
    public boolean inviteFriendShip(String firsName,String secondName){
        try{
            this.inviteFriendStatement.setString(1,firsName);
            this.inviteFriendStatement.setString(2,secondName);
            int result = this.inviteFriendStatement.executeUpdate();
            if(result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param username
     * @return all friends on the user.
     */
    public List<String> getMyFriends(String username){
        List<String> result = new ArrayList<String>();
        try{
            this.getAllFriendsStatement.setString(1,username);
            this.getAllFriendsStatement.setString(2,username);
            ResultSet set = this.getAllFriendsStatement.executeQuery();
            while(set.next()){
                String name1 = set.getString("id1_user");
                String name2 = set.getString("id2_user");
                if(username.equals(name2)){
                    result.add(name1);
                }
                else if(username.equals(name1)){
                    result.add(name2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
