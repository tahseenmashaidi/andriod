package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String message=getIntent().getStringExtra("value");
        int value=Integer.parseInt(message);
        value=value*value;
        Intent intent=new Intent();
        intent.putExtra("MESSAGE",String.valueOf(value));
        setResult(3,intent);
        finish();
    }

}
