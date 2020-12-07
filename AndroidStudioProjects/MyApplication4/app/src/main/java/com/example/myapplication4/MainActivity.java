package com.example.myapplication4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String [] country;
    SeekBar sb;
    String gender="",final_selection="",Cname;
    int age;
    boolean check=false;
    ArrayList<String> Selection =new ArrayList<String>();
    TextView tv4,tv_error,Rerror;
    EditText et_name,et_mail;
    Button btn;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        country=getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_dropdown_item, country);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int  pos, long id)
            {
                if(pos>0) {
                    Cname = String.valueOf(pos);
                    check=true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sb= (SeekBar) findViewById(R.id.seekBar);
        tv4=(TextView) findViewById(R.id.textView4);
        et_name=(EditText) findViewById(R.id.editName);
        et_mail=(EditText) findViewById(R.id.editemail);
        btn=(Button) findViewById(R.id.submit_btn);
        tv_error=(TextView) findViewById(R.id.skill_error);
        Rerror=(TextView) findViewById(R.id.radio_error);


        sb.setMin(10);
        sb.setMax(70);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar sb,int progress, boolean fromUser) {
                tv4.setText(String.valueOf(progress));
                age=Integer.parseInt(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar sb) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar sb) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( et_name.length()==0){
                    et_name.setError("Enter name");
                }
                if(et_mail.length()==0){
                    et_mail.setError("Enter Email");
                }
                if(Selection.isEmpty()){
                    tv_error.setText("Select at least one skill");
                }
                if(gender.equals("") ){
                    Rerror.setError("Select at least One");
                }
                if(!check){
                    Toast toast = Toast.makeText(getApplicationContext(), "Select country", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(age <10){
                    tv4.setError("add age");
                }
                else{
                    for(String selection : Selection){
                        final_selection=final_selection + selection+" , ";
                    }
                    Intent i = new Intent(getApplicationContext(),display.class);
                    i.putExtra("Name",et_name.getText().toString());
                    i.putExtra("Email",et_mail.getText().toString());
                    i.putExtra("Skill",final_selection);
                    i.putExtra("Gender",gender);
                    i.putExtra("Age",tv4.getText().toString() );
                    startActivity(i);
                }
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Intent i = new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(i);
        }
    }
    public void skill(View view) {
        boolean checked=((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.checkBox:
                if(checked){
                    Selection.add("Designing");
                }else {
                    Selection.remove("Designing");
                }
                break;
            case R.id.checkBox2:
                if(checked){
                    Selection.add("Software Engineering");
                }else {
                    Selection.remove("Software Engineering");
                }
                break;
            case R.id.checkBox3:
                if(checked){
                    Selection.add("Programming");
                }else {
                    Selection.remove("Programming");
                }
                break;
            case R.id.checkBox4:
                if(checked){
                    Selection.add("Web App Development");
                }else {
                    Selection.remove("Web App Development");
                }
                break;
        }
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
