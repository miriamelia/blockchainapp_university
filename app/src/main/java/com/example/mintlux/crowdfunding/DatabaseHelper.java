package com.example.mintlux.crowdfunding;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mintlux on 6/11/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();

    protected static final String DB_NAME = "TalentInvest.db";
    protected static final int DB_Version = 1;
    protected static final String TABLE_USER = "User";
    protected static final String COL_1 = "userID";
    protected static final String COL_2 = "userName";
    protected static final String COL_3 = "password";

    protected final String createTableUser = "CREATE TABLE IF NOT EXISTS " +TABLE_USER +" ("+COL_1 +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+COL_2 +" TEXT NOT NULL," +COL_3 +" TEXT NOT NULL" +")";

   /* protected  final String insertUser1 = "INSERT INTO " +TABLE_USER +" VALUES(0,'Michael_Stein','1234')";
    protected  final String insertUser2 = "INSERT INTO " +TABLE_USER +" VALUES(1,'Manuela_Stein','1234')";
    protected  final String insertUser3 = "INSERT INTO " +TABLE_USER +" VALUES(2,'Hans_Stein','1234')";
*/

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_Version);
        //SQLiteDatabase db = this.getWritableDatabase();             //creates db and table
        Log.d(LOG_TAG, "DBHelper on create: " +getDatabaseName());


    }

    @Override
    public void onCreate(SQLiteDatabase db) {            //excecutes whatever Query
        try {
            Log.d(LOG_TAG, "Table wird mit " +createTableUser +" angelegt Falls does not exist");
            db.execSQL(createTableUser);
        } catch(Exception e) {
            Log.d(LOG_TAG, "Anlegen DB fehlgeschlagen" +e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_USER);
        onCreate(db);
    }
}
