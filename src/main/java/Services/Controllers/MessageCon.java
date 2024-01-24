package Services.Controllers;

import Models.Message;
import java.util.Set;

public interface MessageCon {
    public Set<Message> messagesInChat(int chatID);
    public boolean deleteMessage(int messageID);
    public boolean addMessage(Message message, int chatID);
}