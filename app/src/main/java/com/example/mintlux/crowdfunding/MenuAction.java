package com.example.mintlux.crowdfunding;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by mintlux on 6/5/17.
 */

public class MenuAction extends AppCompatActivity {


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
        switch(id) {
            case (R.id.action_settings):
                return true;
            case (R.id.action_profile):
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            case (R.id.action_transaction):
                startActivity(new Intent(this, TransactionActivity.class));
                return true;
            case (R.id.action_transfer):
                startActivity(new Intent(this, TransferActivity.class));
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
