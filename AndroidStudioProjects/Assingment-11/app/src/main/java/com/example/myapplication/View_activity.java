package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class View_activity extends AppCompatActivity {
    Button update_btn,delete_btn,back_btn;
    EditText id, f_name,l_name,gmail;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_activity);
        image=(ImageView) findViewById(R.id.avatar1);
        f_name=(EditText) findViewById(R.id.edit_text1);
        l_name=(EditText) findViewById(R.id.edit_text2);
        gmail=(EditText) findViewById(R.id.edit_text3);
        id=(EditText)  findViewById(R.id.edit_text4);
        update_btn=(Button) findViewById(R.id.button_1);
        delete_btn=(Button) findViewById(R.id.button_2);
        back_btn=(Button) findViewById(R.id.button_3);
        id.setText(getIntent().getStringExtra("ID"));
        f_name.setText(getIntent().getStringExtra("First_name"));
        l_name.setText((getIntent().getStringExtra("Last_name")));
        gmail.setText((getIntent().getStringExtra("Email")));
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("Image")).into(image);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
