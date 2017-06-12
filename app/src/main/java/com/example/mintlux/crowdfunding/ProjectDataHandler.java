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

public class ProjectDataHandler {

    private static final String LOG_TAG = ProjectDataHandler.class.getSimpleName();
    private SQLiteDatabase db;
    private DatabaseHelper dbHelp;

    public ProjectDataHandler(Context context) {
        Log.d(LOG_TAG, "DBSourceHandler creates DBHelper");
        dbHelp = new DatabaseHelper(context);
    }

    //DB

    public void open() {
        Log.d(LOG_TAG, "Refer to db ");
        db = dbHelp.getWritableDatabase();
        Log.d(LOG_TAG, "DB-Path: " +db.getPath());            //Path: /data/data/com.example.mintlux.crowdfunding/databases/TalentInvest.db

        Log.d(LOG_TAG, "Execute Create Table");
        dbHelp.onCreate(db);
    }

    public void close() {
        dbHelp.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    //Handle tableProjekt
    private String[] columnsProjekt = {
            DatabaseHelper.Projekt_COL1,
            DatabaseHelper.Projekt_COL2,
            DatabaseHelper.Projekt_COL3,
            DatabaseHelper.Projekt_COL4,
            DatabaseHelper.Projekt_COL5,
            DatabaseHelper.Projekt_COL6,
            DatabaseHelper.Projekt_COL7,
            DatabaseHelper.Projekt_COL8
    };

    public List<Projekt> getAllProjects() {
        List<Projekt> projektList = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHelper.TABLE_PROJEKT,                 //Liest alle user aus db
                columnsProjekt, null, null, null, null, null);

        cursor.moveToFirst();
        Projekt project;

        while(!cursor.isAfterLast()) {
            project = cursorToProject(cursor);
            projektList.add(project);
            Log.d(LOG_TAG, "ID: " + project.getProjektID() + ", Inhalt: " + project.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return projektList;
    }

    private Projekt cursorToProject(Cursor cursor) {                                      //wandelt Objekt aus DB in Objekt Klasse user um
        int idIndex = cursor.getColumnIndex(DatabaseHelper.Projekt_COL1);
        int idProjektName = cursor.getColumnIndex(DatabaseHelper.Projekt_COL2);
        int idProjektType = cursor.getColumnIndex(DatabaseHelper.Projekt_COL3);
        int idDescription = cursor.getColumnIndex(DatabaseHelper.Projekt_COL4);
        int idminFunding = cursor.getColumnIndex(DatabaseHelper.Projekt_COL5);
        int idendOfFundingPeriod = cursor.getColumnIndex(DatabaseHelper.Projekt_COL6);
        int idstartOfPayback = cursor.getColumnIndex(DatabaseHelper.Projekt_COL7);
        int idinterestRate = cursor.getColumnIndex(DatabaseHelper.Projekt_COL8);

        int id = cursor.getInt(idIndex);
        String name = cursor.getString(idProjektName);
        String type = cursor.getString(idProjektType);
        String description = cursor.getString(idDescription);
        double minFunding = cursor.getDouble(idminFunding);
        String endOfFundingPeriod = cursor.getString(idendOfFundingPeriod);
        String startOfPayback = cursor.getString(idstartOfPayback);
        double interestRate = cursor.getDouble(idinterestRate);

        Projekt project = new Projekt(id, name, type, description, minFunding, endOfFundingPeriod, startOfPayback, interestRate);

        return project;
    }

    public Projekt createProject(String projektName, String projektType, String description, double minFunding, String endOfFundingPeriod, String startOfPayback, double interestRate) {
        List<Projekt> list1 = this.getAllProjects();
        Log.d(LOG_TAG,"CHECK USER" );

        for(int i = 0; i < list1.size(); i++) {
            Projekt p = list1.get(i);
            String checkName = p.getProjektName();
            if(checkName.equals(projektName)) {
                return p;
            }
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Projekt_COL2, projektName);
        values.put(DatabaseHelper.Projekt_COL3, projektType);
        values.put(DatabaseHelper.Projekt_COL4, description);
        values.put(DatabaseHelper.Projekt_COL5, minFunding);
        values.put(DatabaseHelper.Projekt_COL6, endOfFundingPeriod);
        values.put(DatabaseHelper.Projekt_COL7, startOfPayback);
        values.put(DatabaseHelper.Projekt_COL8, interestRate);


        long insertId = db.insert(DatabaseHelper.TABLE_PROJEKT, null, values);

        Cursor cursor = db.query(DatabaseHelper.TABLE_PROJEKT,
                columnsProjekt, DatabaseHelper.Projekt_COL1 + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();

        Projekt project = cursorToProject(cursor);
        cursor.close();


        return project;
    }

}
