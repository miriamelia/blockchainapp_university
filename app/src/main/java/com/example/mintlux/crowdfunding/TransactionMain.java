package com.example.mintlux.crowdfunding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.AbstractCollection;

/**
 * Created by mintlux on 6/12/17.
 */

public class TransactionMain extends AppCompatActivity {
    String [] Kredit = {"1000" , "1500", "1500"};
    String [] Projekt = {"Master im Ausland", "Auslandssemeter", "Bachelor in Bayreuth"};
    String[] Talent = {"Horst Stein", "Rainer Zufall", "Manuela Stein"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();

        listView.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Kredit.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_layout,null);

            TextView textView_projekt = (TextView)view.findViewById(R.id.textView_projekt);
            TextView textView_talent = (TextView)view.findViewById(R.id.textView_talent);
            TextView textView_kredit = (TextView)view.findViewById(R.id.textView_kredit);

            textView_projekt.setText(Projekt[i]);
            textView_talent.setText(Talent[i]);
            textView_kredit.setText(Kredit[i]);

            return view;
        }
    }
}
