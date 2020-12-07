package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] months = {"January", "February", "March", "April",  "May", "June", "July",	"August", "September", "October",  "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, months);
        ListView listview = (ListView) findViewById(R.id.listView1);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int  position, long id) {

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);  intent.putExtra("v1", String.valueOf(position));  startActivity(intent);

            }
        });

    }
}
