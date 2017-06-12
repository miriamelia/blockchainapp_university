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

    private static final String DB_NAME = "TalentInvest.db";
    private static final int DB_Version = 1;

    private static final String tableUser = "CREATE TABLE User (userID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, userName TEXT NOT NULL, password TEXT NOT NULL)";
    private static final String tableProjekt = "CREATE TABLE Projekt(projektID INT PRIMARY KEY NOT NULL, projektName TEXT NOT NULL, projektType TEXT NOT NULL, description    TEXT    NOT NULL, minFunding     REAL    NOT NULL, endOfFundingPeriod TEXT NOT NULL, startOfPayback TEXT NOT NULL, interestRate REAL NOT NULL)";
    private static final String tableMapping = "CREATE TABLE Mapping(mapID INT PRIMARY KEY NOT NULL, projektID  INT NOT NULL, userID INT NOT NULL, amount real,type TEXT NOT NULL, FOREIGN KEY(projektID) REFERENCES Projekt(projektID), FOREIGN KEY(userID) REFERENCES User(userID))";

    //Variables needed in other classes
    protected static final String TABLE_USER = "User";
    protected static final String USER_COL1 = "userID";
    protected static final String USER_COL2 = "userName";
    protected static final String USER_COL3 = "password";

    protected static final String TABLE_PROJEKT = "Projekt";
    protected static final String Projekt_COL1 = "projektID";
    protected static final String Projekt_COL2 = "projektName";
    protected static final String Projekt_COL3 = "projektType";
    protected static final String Projekt_COL4 = "description";
    protected static final String Projekt_COL5 = "minFunding";
    protected static final String Projekt_COL6 = "endOfFundingPeriod";
    protected static final String Projekt_COL7 = "startOfPayback";
    protected static final String Projekt_COL8 = "interestRate";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_Version);
        Log.d(LOG_TAG, "DBHelper on create: " +getDatabaseName());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {            //excecutes whatever Query
        try {
            Log.d(LOG_TAG, "Create tables");
            db.execSQL(tableUser);
        } catch(Exception e) {
            Log.d(LOG_TAG, "Error creating table" +e.getMessage());
        } try {
            db.execSQL(tableProjekt);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Error creating table" + e.getMessage());
        } try {
            db.execSQL(tableMapping);
        } catch(Exception e) {
            Log.d(LOG_TAG, "Error creating table" +e.getMessage());
        }
        try {
            db.execSQL("INSERT INTO User VALUES(0,'Michael_Stein','1234')");
            db.execSQL("INSERT INTO User VALUES(1,'Manuela_Stein','1234')");
            db.execSQL("INSERT INTO User VALUES(2,'Hans_Stein','1234')");

            db.execSQL("INSERT INTO Projekt VALUES(0,'Masterstudium in Schottland','Master','Ich benötige Funding für meinen Master in Schottland',10000.0,'31.07.2017','02.07.2017',2.0)");
            db.execSQL("INSERT INTO Projekt VALUES(1,'Bachelorstudium in Bayreuth','Bachelor','Ich benötige Funding für meinen Bachelor in Bayreuth',1500.0,'31.07.2017','02.07.2017',1.0)");
            db.execSQL("INSERT INTO Projekt VALUES(2,'Auslandssemester in Sydney','Master','Ich benötige Funding für mein Auslandssemester in Sydney',5000.0,'31.07.2017','02.07.2017',3.0)");

            db.execSQL("INSERT INTO Mapping VALUES(0,0,0,0.0,'student')");
            db.execSQL("INSERT INTO Mapping VALUES(1,0,1,100.0,'investor')");
            db.execSQL("INSERT INTO Mapping VALUES(2,0,2,100.0,'investor')");
        }  catch(Exception e) {
            Log.d(LOG_TAG, "Error inserting content" +e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(LOG_TAG, "Upgrade Tables");

        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Projekt");
        db.execSQL("DROP TABLE IF EXISTS Mapping");
        onCreate(db);
    }
}
