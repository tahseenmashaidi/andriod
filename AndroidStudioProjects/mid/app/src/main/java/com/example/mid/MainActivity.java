package com.example.mid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText text_height,text_weight;
    Button btn_height,btn_weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_height =(EditText) findViewById(R.id.text2);
        text_weight=(EditText) findViewById(R.id.text1);
        btn_height=(Button) findViewById(R.id.btn1);
        btn_weight=(Button) findViewById(R.id.btn2);
        btn_height.setOnClickListener(this);
        btn_weight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:{
                Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("value",text_height.getText().toString());
                startActivity(intent);
                break;
            }
            case R.id.btn2:{
                Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("value",text_weight.getText().toString());
                startActivity(intent);
                break;
            }
        }
    }
}
