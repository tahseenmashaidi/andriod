package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Double[] number = {10.5, 27.5, 20.0, 10.75, 10.696, 10.7, 10.0};
    Double[] new_array= new Double[10];
    Button go_btn;
    EditText et;
    private AdapterView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(this, android.R.layout.simple_list_item_1, number);

        final ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);
        go_btn = (Button) findViewById(R.id.btn);
        et = (EditText) findViewById(R.id.edit_text);
        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=Integer.parseInt(et.getText().toString());
                for (int i=0;i<=number.length;i++){
                    if(num== number[i].intValue()) {
                        new_array[i]=number[i];
//
                    }
                }
            }
        });

        listView.setAdapter(null);
        ArrayAdapter<Double> adapter1 = new ArrayAdapter<Double>(this, android.R.layout.simple_list_item_1, new_array);
        listview.setAdapter(adapter1);
        adapter.notifyDataSetChanged();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int  value=Integer.parseInt( String.valueOf(position));
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("value",Integer.toString(value*value));
                startActivity(intent);
            }
        });
    }
}
