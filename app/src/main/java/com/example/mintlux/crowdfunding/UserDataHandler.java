package com.example.mintlux.crowdfunding;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mintlux on 6/12/17.
 */

public class UserDataHandler {

    private static final String LOG_TAG = UserDataHandler.class.getSimpleName();
    private SQLiteDatabase db;
    private DatabaseHelper dbHelp;

    public UserDataHandler(Context context) {
        Log.d(LOG_TAG, "DBSourceHandler creates DBHelper");
        dbHelp = new DatabaseHelper(context);
    }

    //DB

    public void open() {
        Log.d(LOG_TAG, "Refer to db ");
        db = dbHelp.getWritableDatabase();                  //DB wird erstellt
        Log.d(LOG_TAG, "DB-Path: " +db.getPath());            //Path: /data/data/com.example.mintlux.crowdfunding/databases/TalentInvest.db

        Log.d(LOG_TAG, "Execute Create Table");
        dbHelp.onCreate(db);
    }

    public void close() {
        dbHelp.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    //Handle TableUser

    private String[] columnsUser = {
            DatabaseHelper.USER_COL1,
            DatabaseHelper.USER_COL2,
            DatabaseHelper.USER_COL3
    };

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,                 //Liest alle user aus db
                columnsUser, null, null, null, null, null);

        cursor.moveToFirst();
        User user;

        while(!cursor.isAfterLast()) {
            user = cursorToUser(cursor);
            userList.add(user);
            Log.d(LOG_TAG, "ID: " + user.getUserID() + ", Inhalt: " + user.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return userList;
    }

    public User createUser(String userName, String password) {                  //legt User an
        List<User> list1 = this.getAllUsers();
        Log.d(LOG_TAG,"CHECK USER" );

        for(int i = 0; i < list1.size(); i++) {
            User u = list1.get(i);
            String checkName = u.getUserName();
            if(checkName.equals(userName)) {
                return u;
            }
        }

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.USER_COL2, userName);
            values.put(DatabaseHelper.USER_COL3, password);


            long insertId = db.insert(DatabaseHelper.TABLE_USER, null, values);

            Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                    columnsUser, DatabaseHelper.USER_COL1 + "=" + insertId,
                    null, null, null, null);

            cursor.moveToFirst();

            User user = cursorToUser(cursor);
            cursor.close();


        return user;
    }

    public void removeFromDB(String username) {
        List<User> list = this.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            User u = list.get(i);
            String checkName = u.getUserName();
            if (checkName.equals(username)) {
                int help = u.getUserID();
                db.execSQL("DELETE FROM User WHERE userID=" + help);
                dbHelp.onUpgrade(db, 1, 2);
            }
        }
    }


    private User cursorToUser(Cursor cursor) {                                      //wandelt Objekt aus DB in Objekt Klasse user um
        int idIndex = cursor.getColumnIndex(DatabaseHelper.USER_COL1);
        int idUserName = cursor.getColumnIndex(DatabaseHelper.USER_COL2);
        int idPassword = cursor.getColumnIndex(DatabaseHelper.USER_COL3);

        String name = cursor.getString(idUserName);
        String password = cursor.getString(idPassword);
        int id = cursor.getInt(idIndex);

        User user = new User(id, name, password);

        return user;
    }



}
