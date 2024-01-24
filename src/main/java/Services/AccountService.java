package Services;

import DAOs.Controllers.AccountDAO;
import Models.Account;
import Services.Controllers.AccountCon;
import java.util.List;

public class AccountService implements AccountCon{
    private AccountDAO adao;

    @Override
    public Account getAccount(int accountID) {
        Account account = adao.getAccount(accountID);
        account.setPassword("");
        return account;
    }
    
    @Override
    public Account getAccount(String username){
        Account account = adao.getAccount(username);
        account.setPassword("");
        return account;
    }

    @Override
    public boolean addAccount(Account account) {
        if(!adao.usernameExists(account.getUsername())){
            return adao.insertAccount(account);
        }
        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        return adao.updateAccount(account);
    }

    @Override
    public boolean deleteAccount(int accountID) {
        return adao.deleteAccount(accountID);
    }

    @Override
    public List<Account> allAccountsInChat(int chatID) {
        return adao.allAccountInChat(chatID);
    }

    @Override
    public boolean verifyPassword(Account account) {
        return adao.getPassword(account.getUsername()).equals(account.getPassword());
    }
}