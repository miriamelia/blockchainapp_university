package com.example.mintlux.crowdfunding;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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

    Toolbar toolbar;

    DrawerLayout drawerLayoutgesamt;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;

    ImageView ivanzeige;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);

        dataSource = new ProjectDataHandler(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        showAllProjects();

        dataSource.close();

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        drawerLayoutgesamt = (DrawerLayout) findViewById(R.id.drawerlayoutgesamt);
        drawerToggle = new ActionBarDrawerToggle(ProjectActivity.this, drawerLayoutgesamt,R.string.Auf, R.string.Zu);
        drawerLayoutgesamt.setDrawerListener(drawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            public void onBackPressed() {
                if (drawerLayoutgesamt.isDrawerOpen(GravityCompat.START)) {
                    drawerLayoutgesamt.closeDrawer(GravityCompat.START);
                } else {
                    this.onBackPressed();
                }
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();


                Intent intent;



                if (id == R.id.drawerViewItem1) {
                    // Handle the camera action
                    startActivity(new Intent(getApplicationContext(), TransactionActivity.class));
                } else if (id == R.id.drawerViewItem2) {
                    startActivity(new Intent(getApplicationContext(), TransferActivity.class));
                } else if (id == R.id.drawerViewItem3) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
                drawerLayoutgesamt.closeDrawer(GravityCompat.START);
                return true;



                }



        });

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();
    }

    public void showAllProjects() {
        List<Projekt> show = dataSource.getAllProjects();

        ArrayAdapter<Projekt> projektArrayAdapter = new ArrayAdapter<Projekt>(
                this, android.R.layout.simple_list_item_multiple_choice, show
        );

        listView = (ListView) findViewById(R.id.listViewAdapter);

        listView.setAdapter(projektArrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(new Configuration());
    }
}



