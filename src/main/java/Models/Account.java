package Models;

import lombok.Builder;

@Builder
public class Account {
    private int accountID;
    private String name, surname, password, username;
    
    public Account(){}
    
    public Account(String name, String surname, String password, String username) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
    }
    
    public Account(int accountID, String name, String surname, String password, String username) {
        this.accountID = accountID;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
    }

    public int getAccountID() {
        return accountID;
    }
    
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}