package Services;

import DAOs.Controllers.AccountDAO;
import DAOs.Controllers.MessageDAO;
import Models.Message;
import Services.Controllers.MessageCon;
import java.util.Set;

public class MessageServices implements MessageCon{
    private MessageDAO mdao;
    private AccountDAO adao;

    @Override
    public Set<Message> messagesInChat(int chatID) {
        Set<Message> messages = mdao.getAllMessagesUnderChat(chatID);
        for (Message message : messages) {
            getWhoSentTheMessage(message);
        }
        return messages;
    }
    
    public void getWhoSentTheMessage(Message message){
        message.setAccount(adao.getAccount(mdao.getMessageSender(message.getMessageID())));
    }

    @Override
    public boolean deleteMessage(int messageID) {
        return mdao.deleteMessage(messageID);
    }

    @Override
    public boolean addMessage(Message message, int chatID) {
        return mdao.insertMessage(message, chatID);
    }
}