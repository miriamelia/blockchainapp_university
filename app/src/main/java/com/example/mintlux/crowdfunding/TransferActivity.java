package com.example.mintlux.crowdfunding;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by mintlux on 6/5/17.
 */

public class TransferActivity extends MenuAction {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

}
