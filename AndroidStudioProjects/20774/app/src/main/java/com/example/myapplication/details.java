package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class details extends AppCompatActivity {
    TextView tv,tv1,tv2,tv3,tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setTitle("Information You Enter");

        tv=(TextView) findViewById(R.id.tv_fname);
        tv.setText("Welcome "+getIntent().getStringExtra("Name"));

        tv1=(TextView) findViewById(R.id.tv_ful_name);
        tv1.setText("FULL Name: "+getIntent().getStringExtra("Name")+" "+getIntent().getStringExtra("Lname"));

        tv4=(TextView) findViewById(R.id.tv_gmail);
        tv4.setText("Gmail: "+getIntent().getStringExtra("Gmail"));

        tv2=(TextView) findViewById(R.id.d_gender);
        tv2.setText("Gender: "+getIntent().getStringExtra("Gender"));

        tv3=(TextView) findViewById(R.id.d_qualification);
        tv3.setText("Qualification: "+getIntent().getStringExtra("Qualification"));
    }
}
