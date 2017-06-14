package com.example.mintlux.crowdfunding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by mintlux on 6/5/17.
 */

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Button btn_Letztedreimonate = (Button) findViewById(R.id.btn_letztedreimonate);
        btn_Letztedreimonate.setOnClickListener(this);
    // Button für die 3 Monatsansicht
        Button btn_all = (Button) findViewById(R.id.btn_all);
        btn_all.setOnClickListener(this);
    // Button für die Gesamtansicht


    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        switch (b.getId()) {
            case R.id.btn_letztedreimonate:
                startActivity(new Intent(TransactionActivity.this, TransactionMain.class));
                break;
            case R.id.btn_all:
                startActivity(new Intent(TransactionActivity.this, TransactionMain.class));
                break;
        }
    }


}
