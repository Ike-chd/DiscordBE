package DAOs.Controllers;

import Models.Account;
import java.util.List;

public interface AccountDAO {
    public Account getAccount(int accountID);
    public Account getAccount(String username);
    public boolean usernameExists(String username);
    public boolean updateAccount(Account account);
    public boolean deleteAccount(int accountID);
    public boolean insertAccount(Account account);
    public List<Account> allAccountInChat(int chatID);
    public String getPassword(String username);
}