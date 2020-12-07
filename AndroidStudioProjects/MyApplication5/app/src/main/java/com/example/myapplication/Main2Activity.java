package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class Main2Activity extends AppCompatActivity {
    // You can declare outside method, But don’t initialize
    String[] alphabets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Get values from XML resource file
         alphabets = getResources().getStringArray(R.array.alphabets_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, alphabets);
        GridView gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int  position, long id)
            {

                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);  intent.putExtra("v1", String.valueOf(position));  startActivity(intent);


            }
        });

    }
}
