package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener( this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View src) {
        try {
            Toast.makeText(this,src.getId(),Toast.LENGTH_LONG).show();
            switch (src.getId()){
                case R.id.btn1:
                    startService(new Intent(MainActivity.this,MyService.class));
                    break;
                case R.id.btn2:
                    stopService(new Intent(MainActivity.this,MyService.class));
                    break;
            }
        }catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
