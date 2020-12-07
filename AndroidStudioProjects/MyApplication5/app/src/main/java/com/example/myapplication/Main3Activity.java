package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main3Activity extends AppCompatActivity {
    String[] sports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sports = getResources().getStringArray(R.array. sports_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_dropdown_item, sports);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        int selected_sport=spinner.getSelectedItemPosition();
        spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int  pos, long id)
            {

                Intent intent=new Intent(getApplicationContext(),Main3Activity.class);  intent.putExtra("v1", String.valueOf(pos));  startActivity(intent);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

            }
        });


    }
}
