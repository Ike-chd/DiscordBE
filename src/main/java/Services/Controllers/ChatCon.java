package Services.Controllers;

import Models.Chat;
import java.util.List;

public interface ChatCon {
    public Chat getChat(int chatID);
    public Chat getChat(String chatNumber);
    public boolean addChat(Chat chat);
    public boolean updateChat(Chat chat);
    public boolean deleteChat(int chatID);
    public List<Chat> getAllChats();
}