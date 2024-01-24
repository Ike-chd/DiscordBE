package Models;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@Builder
public class Chat {
    
    private int chatID;
    private String chatName, chatNumber, password;
    private List<Message> messages = new ArrayList<>();
    private List<Account> members = new ArrayList<>();
    
    public Chat(){}

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Account> getMembers() {
        return members;
    }

    public void setMembers(List<Account> members) {
        this.members = members;
    }

    public Chat(String chatName, String chatNumber, String password) {
        this.chatName = chatName;
        this.chatNumber = chatNumber;
        this.password = password;
    }
    
    public Chat(int chatID, String chatName, String chatNumber, String password) {
        this.chatID = chatID;
        this.chatName = chatName;
        this.chatNumber = chatNumber;
        this.password = password;
    }

    public int getChatID() {
        return chatID;
    }
    
    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getChatNumber() {
        return chatNumber;
    }

    public void setChatNumber(String chatNumber) {
        this.chatNumber = chatNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}