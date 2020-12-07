package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Print extends AppCompatActivity {
    List<Integer> num_array = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        Intent intent=getIntent();
        num_array=intent.getIntegerArrayListExtra("array");
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, num_array);
        final ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
    }
}
