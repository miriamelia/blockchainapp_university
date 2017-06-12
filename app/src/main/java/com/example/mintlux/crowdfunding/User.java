package com.example.mintlux.crowdfunding;

import android.content.ContentValues;

/**
 * Created by mintlux on 6/12/17.
 */

public class User {
    private String userName;
    private String password;
    private int userID;
    ContentValues values = new ContentValues();

    public User(int userID, String userName, String password) {
        this.userID = userID;
        setUserName(userName);
        setPassword(password);
    }

    public User(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        this.userID = values.getAsInteger(DatabaseHelper.TABLE_USER);
    }


    public int getUserID() {
        return this.userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    private void setUserName(String userName) {
        if(checkUserName(userName)) {
            this.userName = userName;
        }
    }

    private void setPassword(String password) {
        if(checkPassword(password)) {
            this.password = password;
        }
    }

    private boolean checkUserName(String userName) {
        if(userName == null || userName.isEmpty())
            return false;
        return true;
    }

    private boolean checkPassword(String password) {
        if(password == null || password.isEmpty())
            return false;
        return true;
    }

    public String toString() {
        return this.userName + " " + this.password;
    }
}
