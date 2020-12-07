package com.example.class_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static android.graphics.Color.rgb;
import static java.lang.reflect.Array.getLength;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView text;
    Button btn1;
    String age,unit,abc,s;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.date);
        btn1 = (Button) findViewById(R.id.btn);
        text=(TextView) findViewById(R.id.text2);
       final   String age_unit=getIntent().getStringExtra("age");
       final String text_color=getIntent().getStringExtra("color");
       if(savedInstanceState != null){
           et.setText(savedInstanceState.getString("StringKey"));
       }
        try {
            if (text_color.equals("NULL")) {
                //do nothing
            }
            else if (text_color.equals("Black")) {
                btn1.setTextColor(rgb(19, 10, 11));
            }else if (text_color.equals("Red")) {
                btn1.setTextColor(rgb(255, 0, 0));
            } else if (text_color.equals("Blue")) {
                btn1.setTextColor(rgb(33, 66, 131));
            } else if (text_color.equals("Yellow")) {
                btn1.setTextColor(rgb(248, 239, 2));
            }
        } catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int d=0,m=0,y=0;
               s = String.valueOf(et.getText());
                String[] date1 = s.split("/");
                int day=Integer.parseInt(date1[2]);
                int month=Integer.parseInt(date1[1]);
                int year=Integer.parseInt(date1[0]);
                LocalDate localDate = LocalDate.now();
                String s2=DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate);
                String[] date2 = s2.split("/");
                int cday=Integer.parseInt(date2[2]);
                int cmonth=Integer.parseInt(date2[1]);
                int cyear=Integer.parseInt(date2[0]);
               if(year>cyear){
                   Toast.makeText(getApplicationContext(),"error in date of birth",Toast.LENGTH_LONG).show();
               }else {
                   if(day>=cday){
                       cday=cday+30;
                       d=cday-day;
                       cmonth=cmonth-1;
                   }else {
                       d=cday-day;
                   }
                   if (month>=cmonth){
                       cmonth=cmonth+12;
                       m=cmonth-month;
                       cyear=cyear-1;
                   }else {
                       m=cmonth-month;
                   }
                   if(year<cyear){
                       y=cyear-year;
                   }

                   try{

                       if(age_unit.equals("NULL")||age_unit.equals("Years")) {
                           age = String.valueOf(y);
                           unit = "Years";
                       }
                       else if(age_unit.equals("Months") ){
                           age = String.valueOf(m + (12 *y));
                           unit = "Months";
                       }
                       else if(age_unit.equals("Days")) {

                           age = String.valueOf(d+(30*m)+(365*y));
                           unit = "Days";
                       }
                   }catch (NullPointerException e){
                       age = String.valueOf(y);
                       unit = "Years";
                   }
                   Bundle bundle = new Bundle();
                   bundle.putString("date", age);
                   bundle.putString("color",text_color);
                   bundle.putString("unit",unit);
                   FragmentManager fragmentmanager=getSupportFragmentManager();
                   Fragment fragmentone=new Fragment();
                   FragmentTransaction transaction = fragmentmanager.beginTransaction();
                   transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                   transaction.replace(android.R.id.content, fragmentone).addToBackStack("homepage").commit();
                   fragmentone.setArguments(bundle);
               }
            }
        });

        //========= Code to get saved/ retrieve data ==============
        SharedPreferences sp = getSharedPreferences(MyPREFERENCES ,Context.MODE_PRIVATE);
        String sc  = sp.getString(Name,"null");
        et.setText(sc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting: {
                //======== Code to save data ===================
                SharedPreferences sp = getSharedPreferences(MyPREFERENCES ,Context.MODE_PRIVATE);
                sp.edit().putString(Name,String.valueOf(et.getText())).commit();
                //=========New activity ====================
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("StringKey", String.valueOf(et.getText()));
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
         abc = savedInstanceState.getString("StringKey");
    }

}