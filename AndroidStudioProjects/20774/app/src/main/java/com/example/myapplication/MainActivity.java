package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3;
    Button btn;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String gender;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Enter Information");

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.text, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        et1= (EditText) findViewById(R.id.f_name);
        et2= (EditText) findViewById(R.id.l_name);
        et3= (EditText) findViewById(R.id.gmail);
        btn=(Button) findViewById(R.id.btn_submit);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String name=et1.getText().toString();
                String lname=et2.getText().toString();
                String gmail=et3.getText().toString();
                Spinner spinner = (Spinner)findViewById(R.id.spinner1);
                String qualification = spinner.getSelectedItem().toString();
                Intent intent=new Intent(MainActivity.this,details.class);
                intent.putExtra("Name",name);
                intent.putExtra("Lname",lname);
                intent.putExtra("Gmail",gmail);
                intent.putExtra("Gender",gender);
                intent.putExtra("Qualification",qualification);
                startActivity(intent);
            }
        });
    }
    public void register_click(View view) {
        Intent i = new Intent(getApplicationContext(),details.class);
        startActivity(i);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioMale:
                if (checked)
                    gender="Male";
                    break;
            case R.id.radioFemale:
                if (checked)
                    gender="Female";
                    break;
        }
    }
}
