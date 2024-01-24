package DAOs.Controllers;

import Models.Message;
import java.util.Set;

public interface MessageDAO {

    public Message getMessage(int messageID);

    public Set<Message> getAllMessagesUnderChat(int chatID);

    public Set<Message> getAllMessagesBySearch(String message);

    public boolean insertMessage(Message message, int chatID);

    public boolean updateMessage(Message message);

    public boolean deleteMessage(int messageID);

    public int getMessageSender(int messageID);
}