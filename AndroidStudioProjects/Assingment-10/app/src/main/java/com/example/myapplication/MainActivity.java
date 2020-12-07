package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ArrayList<Integer> num_array = new ArrayList<Integer>();
    EditText number;
    Button add_btn, sort_btn, print_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = (EditText) findViewById(R.id.edit_text);
        add_btn = (Button) findViewById(R.id.button1);
        sort_btn = (Button) findViewById(R.id.button2);
        print_btn = (Button) findViewById(R.id.button3);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_array.add(Integer.parseInt(number.getText().toString()));
                number.setText("");
                Toast toast = Toast.makeText(getApplicationContext(), "Add Successfully", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = "5";
                runner.execute(sleepTime);
            }
        });
        print_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Print.class);
                intent.putIntegerArrayListExtra("array", num_array);
                startActivity(intent);

            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String current;
        ProgressDialog progressDialog;
        Integer[] array;
        @Override
        protected String doInBackground(String... params) {
            array = new Integer[num_array.size()];
            array = num_array.toArray(array);
            boolean sorted = false;
            int temp;
            while(!sorted) {
                sorted = true;
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i] > array[i+1]) {
                        temp = array[i];
                        array[i] = array[i+1];
                        array[i+1] = temp;
                        sorted = false;
                    }
                }
            }
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {

                int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                current = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                current = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                current = e.getMessage();
            }
            return current;
        }
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(array));
            Intent intent = new Intent(getApplicationContext(), Print.class);
            intent.putIntegerArrayListExtra("array", arrayList);
            startActivity(intent);
        }
        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "Sorting Array", "Wait for 5 seconds");
        }
    }
}
