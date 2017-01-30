package screans.MainScrean;

import Message.Friend.GiveMeMyFriends;
import Modles.Read;
import Modles.User;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Nikolay on 1/25/2017.
 */
public class MainScrean extends JFrame {

    private static final String MESSAGE_CARD = "Message";

    private static final int SIZE_X = 700;
    private static final int SIZE_Y = 500;

    private final CardLayout cl = new CardLayout();

    private JPanel main = new JPanel();
    private JPanel messagesPanel = new JPanel();
    private JPanel textArea = new JPanel();
    private JPanel list = new JPanel();
    private JPanel searchPanel = new JPanel();
    private MessagePanle textAreasPanel;

    DefaultListModel<String> userModel = new DefaultListModel<>();

    private JList<String> userList = new JList<String>(userModel);

    private JTextField send = new JTextField();
    private JTextField search = new JTextField(20);

    private JButton addFriends = new JButton("Add Friends");

    private User user;

    public MainScrean(User user){
        this.user = user;
        this.main.setLayout(this.cl);
        /**
         * Seters
         */
        this.setMessagePanel();
        this.setSearchPanel();
        this.addAllFrineds();

        this.main.add(this.messagesPanel);

        this.textAreasPanel = new MessagePanle(this.userList);
        this.textArea.add(this.textAreasPanel);

        this.userList.addMouseListener(new ConversationAction(this.userList,this.textAreasPanel));

        this.add(main);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(SIZE_X,SIZE_Y));
    }

    private void setSearchPanel() {
        JLabel username = new JLabel("Username: "+this.user.getUsername());
        username.setBackground(Color.BLUE);

        this.searchPanel.setLayout(new BorderLayout());
        this.searchPanel.add(username,BorderLayout.EAST);
        this.searchPanel.add(this.search,BorderLayout.CENTER);
        this.searchPanel.add(this.addFriends,BorderLayout.WEST);
        this.messagesPanel.add(this.searchPanel,BorderLayout.NORTH);
    }

    private void setMessagePanel() {
        //this.textArea.add(this.messagesArea);
        this.list.add(this.userList);
        this.list.setPreferredSize(new Dimension(SIZE_X/6,SIZE_Y));
        this.userList.setVisibleRowCount(23);
        this.textArea.setPreferredSize(new Dimension((int)(SIZE_X*0.6),(int)(SIZE_Y*0.85)));
        this.userList.setPreferredSize(new Dimension(SIZE_X/7,SIZE_Y));

        JScrollPane listScroll = new JScrollPane(this.userList);

        this.list.add(listScroll);
        this.messagesPanel.setLayout(new BorderLayout());
        this.messagesPanel.add(this.list,BorderLayout.WEST);
        this.messagesPanel.add(this.send,BorderLayout.SOUTH);
        this.messagesPanel.add(this.textArea,BorderLayout.CENTER);
        this.cl.addLayoutComponent(this.messagesPanel,MESSAGE_CARD);
    }

    public void setCenter(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public void addAllFrineds(){
        this.user.write(new GiveMeMyFriends(this.user.getUsername()));
        GiveMeMyFriends myFriendsMessage = (GiveMeMyFriends)this.user.read();
        if(myFriendsMessage != null){
            java.util.List<String> myFriends = myFriendsMessage.getNames();
            for(int i = 0;i < myFriends.size();i++){
                this.userModel.addElement(myFriends.get(i));
            }
        }
    }

    public void setChat(){
        /**
         * set action for sending messages
         */
        this.send.addKeyListener(new SendAction(this.textAreasPanel.getMessagesAreas(),this.send,this.userList,this.user));
        /**
         * set action for adding friends
         */
        DefaultListModel<String> model = new DefaultListModel<>();
        this.addFriends.addActionListener(new AddFriendsAction(this.user,this.search,this.userModel,model));
        /**
         * run read thread for listening for the message form server
         */
        Read read = new Read(this.user.getIn(),this.textAreasPanel.getMessagesAreas(),model);
        read.run();
    }
}
