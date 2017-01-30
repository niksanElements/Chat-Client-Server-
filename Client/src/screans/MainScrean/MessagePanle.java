package screans.MainScrean;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikolay on 1/27/2017.
 */
public class MessagePanle extends JPanel {

    private Map<String,JTextArea> messagesAreas;
    private Map<String,JPanel> panels;
    private CardLayout layout;

    private int countAreas;


    public MessagePanle(JList<String> names){
        this.layout = new CardLayout();
        this.setLayout(this.layout);
        this.countAreas = 0;
        this.messagesAreas = new HashMap<>();
        this.panels = new HashMap<>();
        ListModel<String> model = names.getModel();
        for(int i = 0;i < model.getSize();i++) {
            this.addJTextArea(model.getElementAt(i));
        }
    }

    public void addJTextArea(String name){

        JTextArea messagesArea = new JTextArea();
        messagesArea.setColumns(46);
        messagesArea.setRows(25);
        messagesArea.setEditable(false);
        messagesArea.setLineWrap(true);
        messagesArea.setWrapStyleWord(true);
        JScrollPane textAreaScroll = new JScrollPane(messagesArea);

        /**
         * add on change event
         */



        //this.add(messagesArea);
        this.add(textAreaScroll);

        this.layout.addLayoutComponent(textAreaScroll,name);
        this.messagesAreas.put(name,messagesArea);
    }

    public Map<String,JTextArea> getMessagesAreas(){
        return this.messagesAreas;
    }

    public void showArea(String name){
        this.layout.show(this,name);
    }
}
