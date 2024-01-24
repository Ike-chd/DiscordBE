package Services.Controllers;

import Models.Account;
import java.util.List;

public interface AccountCon {
    public Account getAccount(int accountID);
    public Account getAccount(String username);
    public boolean addAccount(Account account);
    public boolean updateAccount(Account account);
    public boolean deleteAccount(int accountID);
    public List<Account> allAccountsInChat(int chatID);
    public boolean verifyPassword(Account account);
}