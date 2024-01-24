package Models;

import lombok.Builder;

@Builder
public class Message implements Comparable<Message>{
    private int messageID;
    private String message;
    private Long time;
    private Account account;
    
    public Message(){}

    public Message(String message, long time, Account account) {
        this.message = message;
        this.time = time;
        this.account = account;
    }
    
    public Message(int messageID, String message, long time, Account account) {
        this.messageID = messageID;
        this.message = message;
        this.time = time;
        this.account = account;
    }

    public int getMessageID() {
        return messageID;
    }
    
    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int compareTo(Message o) {
        return o.time.compareTo(o.time);
    }
}