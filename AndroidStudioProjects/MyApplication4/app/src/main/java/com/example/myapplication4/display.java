package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class display extends AppCompatActivity {
    TextView tv,tv1,tv2,tv3,tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        tv=(TextView) findViewById(R.id.textView1);
        tv.setText("Name: "+getIntent().getStringExtra("Name"));

        tv1=(TextView) findViewById(R.id.textView2);
        tv1.setText("Email: "+getIntent().getStringExtra("Email"));

        tv2=(TextView) findViewById(R.id.textView3);
        tv2.setText("Skill: "+getIntent().getStringExtra("Skill"));

        tv3=(TextView) findViewById(R.id.textView4);
        tv3.setText("Gender: "+getIntent().getStringExtra("Gender"));

        tv4=(TextView) findViewById(R.id.textView5);
        tv4.setText("Age: "+getIntent().getStringExtra("Age"));
    }
}
