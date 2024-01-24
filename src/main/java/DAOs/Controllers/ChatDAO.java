package DAOs.Controllers;

import Models.Chat;
import java.util.List;

public interface ChatDAO {
    public Chat getChat(int chatID);
    public Chat getChat(String chatNumber);
    public List<Chat> getChatsByChatNumber(String chatNumber);
    public List<Chat> getChatsByChatName(String chatName);
    public List<Chat> getChatsWithoutPassword();
    public boolean insertChat(Chat chat);
    public boolean deleteChat(int chat);
    public boolean updateChat(Chat chat);
    public boolean enterChat(int chatID, int accountID);
    public boolean exitChat(int chatID, int accountID);
    public List<Chat> getAllChats();
}