package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button get_btn;
    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_btn =(Button) findViewById(R.id.btn);
        textView=(TextView) findViewById(R.id.textview);
        editText=(EditText) findViewById(R.id.edittext);
        get_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String value= editText.getText().toString();
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("value",value);
                startActivityForResult(intent,3);
            }
        });
    }
    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==3){
            String message=data.getStringExtra("MESSAGE");
            textView.setText(message);
        }
    }

}
