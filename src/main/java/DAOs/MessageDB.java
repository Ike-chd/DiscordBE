package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.Controllers.MessageDAO;
import DBConnection.DBConnection;
import Models.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

public class MessageDB extends DBConnection implements MessageDAO {

    PreparedStatement ps;
    ResultSet rs;

    @Override
    public Message getMessage(int messageID) {
        try {
            ps = con.prepareStatement("SELECT * FROM mesages "
                    + "WHERE messageID = ?");
            ps.setInt(1,messageID);
            rs = ps.executeQuery();
            if(rs.next()) return extractMessagesFromResultSet(rs);
            else return new Message();
        } catch (SQLException ex) {
            return new Message();
        }
        finally{
            CloseStreams.ClosePsRs(ps, rs);
        }
    }

    @Override
    public Set<Message> getAllMessagesUnderChat(int chatID) {
        Set<Message> messages = new TreeSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM mesages "
                    + "WHERE chatID = ?");
            ps.setInt(1,chatID);
            rs = ps.executeQuery();
            while (rs.next()) {
                messages.add(extractMessagesFromResultSet(rs));
            }
        } catch (SQLException ex) {}
        finally{
            CloseStreams.ClosePsRs(ps, rs);
        }
        return messages;
    }

    @Override
    public Set<Message> getAllMessagesBySearch(String message) {
        Set<Message> messages = new TreeSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM mesages "
                    + "WHERE message LIKE %?%");
            ps.setString(1,message);
            rs = ps.executeQuery();
            while (rs.next()) {
                messages.add(extractMessagesFromResultSet(rs));
            }
        } catch (SQLException ex) {}
        finally{
            CloseStreams.ClosePsRs(ps, rs);
        }
        return messages;
    }

    @Override
    public boolean insertMessage(Message message, int chatID) {
        int updated = 0;
        try {
            ps = con.prepareStatement("INSERT INTO messages(messages, time, accountID, chatID) VALUES(?, ?, ?, ?)");
            ps.setString(1, message.getMessage());
            ps.setLong(2, message.getTime());
            ps.setInt(3, message.getAccount().getAccountID());
            updated = ps.executeUpdate();
        } catch (SQLException ex) {}
        finally {
            CloseStreams.ClosePreparedStatement(ps);
        }
        return updated == 1;
    }

    @Override
    public boolean updateMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteMessage(int messageID) {
        int updated = 0;
        try {
            ps = con.prepareStatement("DELETE FROM messages "
                    + "WHERE messageID = ?");
            ps.setInt(1,messageID);
            updated = ps.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        finally{
            CloseStreams.ClosePsRs(ps, rs);
        }
        return updated == 1;
    }

    public Message extractMessagesFromResultSet(ResultSet rs) throws SQLException {
        Message message = new Message();
        message.setMessageID(rs.getInt("messageID"));
        message.setMessage(rs.getString("message"));
        message.setTime(rs.getLong("time"));
        return message;
    }

    @Override
    public int getMessageSender(int messageID) {
        try {
            ps = con.prepareStatement("SELECT accountID FROM messages "
                    + "WHERE messageID = ?");
            ps.setInt(1, messageID);
            if (rs.next()) {
                return rs.getInt("accountID");
            }
        } catch (SQLException ex) {}
        finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
        return 0;
    }
}
