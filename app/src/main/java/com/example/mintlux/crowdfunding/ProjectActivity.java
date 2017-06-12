package com.example.mintlux.crowdfunding;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

/**
 * Created by mintlux on 6/12/17.
 */

public class ProjectActivity extends AppCompatActivity {
    public static final String LOG_TAG = ProjectActivity.class.getSimpleName();
    protected ProjectDataHandler dataSource;
    private SQLiteDatabase db;
    private DatabaseHelper help;
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);

        dataSource = new ProjectDataHandler(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        showAllProjects();

        dataSource.close();
    }

    public void showAllProjects() {
        List<Projekt> show = dataSource.getAllProjects();

        ArrayAdapter<Projekt> projektArrayAdapter = new ArrayAdapter<Projekt>(
                this, android.R.layout.simple_list_item_multiple_choice, show
        );

        listView = (ListView) findViewById(R.id.listViewAdapter);

        listView.setAdapter(projektArrayAdapter);
    }




}
