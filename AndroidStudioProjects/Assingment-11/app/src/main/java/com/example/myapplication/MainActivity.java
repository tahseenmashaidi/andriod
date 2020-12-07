package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String apiUrl = "https://reqres.in/api/users/1";
    String text1, text2, text3;
    Button btn_create, btn_veiw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_create = (Button) findViewById(R.id.create_btn);
        btn_veiw = (Button) findViewById(R.id.view_btn);
        btn_veiw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Create_activity.class);
                startActivity(intent);
            }
        });
    }

    public class MyAsyncTasks extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            // implement API in background and store the response in current variable
            String current = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiUrl);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);

                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;

        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("data", s);
            try {
                // JSON Parsing of data
                JSONObject jo = new JSONObject(s);
                JSONObject oneObject = jo.getJSONObject("data");
                Intent intent = new Intent(getApplicationContext(), View_activity.class);
                intent.putExtra("ID",oneObject.getString("id"));
                intent.putExtra("First_name",oneObject.getString("first_name"));
                intent.putExtra("Last_name",oneObject.getString("last_name"));
                intent.putExtra("Email",oneObject.getString("email"));
                intent.putExtra("Image",oneObject.getString("avatar"));
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
