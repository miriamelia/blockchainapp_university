package com.example.mintlux.crowdfunding;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class MainActivity extends AppCompatActivity   implements OnClickListener{
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    protected DataSourceHandler dataSource;
    private SQLiteDatabase db;
    private DatabaseHelper help;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataSource = new DataSourceHandler(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        User user = dataSource.createUser("Lisa", "Löwe");
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + user.getUserID() + ", Inhalt: " + user.toString());

        User user2 = dataSource.createUser("Leon", "Hund");
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + user2.getUserID() + ", Inhalt: " + user2.toString());


        //Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        //showAllListEntries();

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        //Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        //dataSource.close();
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        switch (b.getId()) {
            case R.id.btnRegister:
                AlertDialog ad = new AlertDialog.Builder(MainActivity.this).create();
                ad.setTitle("Registration");
                ad.setMessage("...coming soon");
                ad.show();
                break;
            case R.id.btnLogin:
                EditText ausgabe1 = (EditText) findViewById(R.id.usernameText);
                String username = ausgabe1.getText().toString();
                EditText ausgabe2 = (EditText) findViewById(R.id.passwordText);
                String password = ausgabe2.getText().toString();
                Log.d(LOG_TAG, "LOGIN " +username +" " +password);

                if (username.isEmpty() || password.isEmpty()) {
                    Log.d(LOG_TAG, "Empty");
                    AlertDialog da = new AlertDialog.Builder(MainActivity.this).create();
                    da.setTitle("Login");
                    da.setMessage("Please enter data");
                    da.show();
                    break;
                }
                if(!(checkAllListEntries(username, password))) {
                    Log.d(LOG_TAG, "Wrong");
                    AlertDialog d = new AlertDialog.Builder(MainActivity.this).create();
                    d.setTitle("Login");
                    d.setMessage("Username or pw wrong");
                    d.show();
                    break;
                }
                Log.d(LOG_TAG, "Check");
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));

                break;
        }
    }

    public void showAllListEntries () {
        List<User> userList = dataSource.getAllUsers();

        ArrayAdapter<User> userArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                userList);

        /*ListView usersListView = (ListView) findViewById(R.id.listViewAdapter);
        usersListView.setAdapter(userArrayAdapter);*/
    }

    public boolean checkAllListEntries(String username, String password) {
        List<User> userList = dataSource.getAllUsers();
        Log.d(LOG_TAG,"CHECK USER" );

        for(int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            String checkName = u.getUserName();
            String checkPw = u.getPassword();
            if(checkName.equals(username) && checkPw.equals(password))
                return true;
        }
        return false;
    }
}



