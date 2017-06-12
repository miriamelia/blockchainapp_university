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
import java.util.List;



public class MainActivity extends AppCompatActivity   implements OnClickListener{
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    protected UserDataHandler dataSource;
    private SQLiteDatabase db;
    private DatabaseHelper help;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataSource = new UserDataHandler(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        //dataSource.removeFromDB("Michael_Stein");

       /* User user = dataSource.createUser("Lisa", "Löwe");
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + user.getUserID() + ", Inhalt: " + user.toString());

        User user2 = dataSource.createUser("Leon", "Hund");
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + user2.getUserID() + ", Inhalt: " + user2.toString());
*/

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

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
                    //ausgabe1.setText("");
                    ausgabe2.setText("");
                    break;
                }
            Log.d(LOG_TAG, "Check");
            startActivity(new Intent(MainActivity.this, ProjectActivity.class));
            Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
            //dataSource.close();                                                       nicht nötig, sonst erneute Anmeldung nicht mgl
            break;
        }
    }

    public boolean checkAllListEntries(String username, String password) {
        List<User> userList = dataSource.getAllUsers();
        Log.d(LOG_TAG,"CHECK USER" );

        for(int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            String checkName = u.getUserName();
            String checkPassword = u.getPassword();
            if(checkName.equals(username) && checkPassword.equals(password))
                return true;
        }
        return false;
    }
}



