package Services;

import DAOs.Controllers.ChatDAO;
import Models.Chat;
import Services.Controllers.ChatCon;
import java.util.List;

public class ChatService implements ChatCon{
    private ChatDAO cdao;

    @Override
    public Chat getChat(int chatID) {
        return cdao.getChat(chatID);
    }

    @Override
    public Chat getChat(String chatNumber) {
        return cdao.getChat(chatNumber);
    }

    @Override
    public boolean addChat(Chat chat) {
        return cdao.insertChat(chat);
    }

    @Override
    public boolean updateChat(Chat chat) {
        return cdao.updateChat(chat);
    }

    @Override
    public boolean deleteChat(int chatID) {
        return cdao.deleteChat(chatID);
    }

    @Override
    public List<Chat> getAllChats() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}