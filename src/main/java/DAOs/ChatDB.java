package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.Controllers.ChatDAO;
import DBConnection.DBConnection;
import Models.Chat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatDB extends DBConnection implements ChatDAO {
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Chat getChat(int chatID) {
        try {
            ps = con.prepareStatement("SELECT * FROM chats "
                    + "WHERE chatID = ?");
            ps.setInt(1, chatID);
            rs = ps.executeQuery();
            return extractChatFromResultSet(rs);
        } catch (SQLException ex) {
            return new Chat();
        }
        finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
    }

    @Override
    public Chat getChat(String chatNumber) {
        try {
            ps = con.prepareStatement("SELECT * FROM chats "
                    + "WHERE chatNumber = ?");
            ps.setString(1, chatNumber);
            rs = ps.executeQuery();
            return extractChatFromResultSet(rs);
        } catch (SQLException ex) {
            return new Chat();
        }
        finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
    }

    @Override
    public List<Chat> getChatsByChatNumber(String chatNumber) {
        List<Chat> chats = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM chats "
                    + "WHERE chatNumber LIKE %?%");
            ps.setString(1, chatNumber);
            rs = ps.executeQuery();
            while(rs.next()){
                chats.add(extractChatFromResultSet(rs));
            }
        } catch (SQLException ex) {}
        finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
        return chats;
    }

    @Override
    public List<Chat> getChatsByChatName(String chatName) {
        List<Chat> chats = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM chats "
                    + "WHERE chatName LIKE %?%");
            ps.setString(1, chatName);
            rs = ps.executeQuery();
            while(rs.next()){
                chats.add(extractChatFromResultSet(rs));
            }
        } catch (SQLException ex) {}
        finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
        return chats;
    }

    @Override
    public List<Chat> getChatsWithoutPassword() {
        List<Chat> chats = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM chats "
                    + "WHERE password LIKE ''");
            rs = ps.executeQuery();
            while(rs.next()){
                chats.add(extractChatFromResultSet(rs));
            }
        } catch (SQLException ex) {}
        finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
        return chats;
    }

    @Override
    public boolean insertChat(Chat chat) {
        int updated = 0;
        try {
            ps = con.prepareStatement("INSERT INTO chats(chatName, chatNumber, password) "
                    + "VALUES(?, ?, ?, ?)");
            updated = ps.executeUpdate();
        } catch (SQLException ex) {}
        return updated == 1;
    }

    @Override
    public boolean deleteChat(int chatID) {
        int updated = 0;
        try {
            ps = con.prepareStatement("DELETE FROM chats WHERE chatID = ?");
            ps.setInt(1, chatID);
            updated = ps.executeUpdate();
        } catch (SQLException ex) {}
        return updated == 1;
    }

    @Override
    public boolean updateChat(Chat chat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean enterChat(int chatID, int accountID) {
        int updated = 0;
        try {
            ps = con.prepareStatement("INSERT INTO chat_members VALUES(?, ?)");
            ps.setInt(1, chatID);
            ps.setInt(2, accountID);
            updated = ps.executeUpdate();
        } catch (SQLException ex) {}
        return updated == 1;
    }

    @Override
    public boolean exitChat(int chatID, int accountID) {
        int updated = 0;
        try {
            ps = con.prepareStatement("DELETE FROM chat_members WHERE chatID = ? AND accountID = ?");
            ps.setInt(1, chatID);
            ps.setInt(2, accountID);
            updated = ps.executeUpdate();
        } catch (SQLException ex) {}
        return updated == 1;
    }

    @Override
    public List<Chat> getAllChats() {
        List<Chat> chats = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM chats");
            rs = ps.executeQuery();
            while(rs.next()){
                chats.add(extractChatFromResultSet(rs));
            }
        } catch (SQLException ex) {}
        finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
        return chats;
    }

    public Chat extractChatFromResultSet(ResultSet rs) throws SQLException {
        return Chat.builder()
                .chatID(rs.getInt("chatID"))
                .chatName(rs.getString("chatName"))
                .chatNumber(rs.getString("chatNumber"))
                .password(rs.getString("password"))
                .build();
    }
}
