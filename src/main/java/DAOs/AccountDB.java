package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.Controllers.AccountDAO;
import DBConnection.DBConnection;
import Models.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDB extends DBConnection implements AccountDAO {

    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Account getAccount(int accountID) {
        try {
            ps = con.prepareStatement("SELECT * FROM accounts "
                    + "WHERE accountID = ?");
            ps.setInt(1, accountID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractAccountFromResultSet(rs);
            } else {
                return Account.builder()
                        .name("not found").build();
            }
        } catch (SQLException ex) {
            return Account.builder()
                    .name("not found").build();
        } finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
    }

    @Override
    public Account getAccount(String username) {
        try {
            ps = con.prepareStatement("SELECT * FROM accounts "
                    + "WHERE username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractAccountFromResultSet(rs);
            } else {
                return Account.builder()
                        .name("not found").build();
            }
        } catch (SQLException ex) {
            return Account.builder()
                    .name("not found").build();
        } finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
    }

    @Override
    public boolean usernameExists(String username) {
        try {
            ps = con.prepareStatement("SELECT username FROM accounts "
                    + "WHERE username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            return false;
        } finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
    }

    @Override
    public boolean updateAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteAccount(int accountID) {
        int updated = 0;
        try {
            ps = con.prepareStatement("DELETE FROM accounts "
                    + "WHERE accountID = ?");
            ps.setInt(1, accountID);
            updated = ps.executeUpdate();
        } catch (SQLException ex) {
            return false;
        } finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
        return updated == 1;
    }

    @Override
    public boolean insertAccount(Account account) {
        int updated = 0;
        try {
            ps = con.prepareStatement("INSERT INTO accounts(name, surname, username, password) VALUES(?, ?, ?, ?)");
            updated = ps.executeUpdate();
        } catch (SQLException ex) {}
        finally {
            CloseStreams.ClosePreparedStatement(ps);
        }
        return updated == 1;
    }

    @Override
    public List<Account> allAccountInChat(int chatID) {
        List<Account> accounts = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM chat_members "
                    + "WHERE chatID = ?");
            ps.setInt(1, chatID);
            rs = ps.executeQuery();
            while (rs.next()) {
                try (PreparedStatement ps1 = con.prepareStatement("SELECT * FROM accounts "
                        + "WHERE accountID = " + rs.getInt("accountID") + ""); ResultSet rs1 = ps1.executeQuery()) {
                    accounts.add(extractAccountFromResultSet(rs1));
                }
            }
        } catch (SQLException ex) {}
        finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
        return accounts;
    }

    public Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
        return Account.builder()
                .accountID(rs.getInt("accountID"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .build();
    }

    @Override
    public String getPassword(String username) {
        try {
            ps = con.prepareStatement("SELECT * FROM accounts "
                    + "WHERE username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            return (rs.next()) ? rs.getString("password") : "";
        } catch (SQLException ex) {
        } finally {
            CloseStreams.ClosePsRs(ps, rs);
        }
        return "";
    }
}
