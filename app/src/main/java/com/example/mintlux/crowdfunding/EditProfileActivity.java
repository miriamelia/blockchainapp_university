package com.example.mintlux.crowdfunding;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by mintlux on 6/5/17.
 */

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_editprofile);

         Button btnTop = (Button) findViewById(R.id.btnTop);
         btnTop.setOnClickListener(this);

     }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        switch (b.getId()) {
            case R.id.btnTop:
                startActivity(new Intent(EditProfileActivity.this, ProjectActivity.class));
                break;

        }
    }
}
