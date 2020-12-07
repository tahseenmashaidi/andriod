package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.String.valueOf;

public class Create_activity extends AppCompatActivity {
    String apiUrl ="https://reqres.in/api/users/1";
    String F_name,L_name,Gmail,Avatar;
    ImageView imageView;
    Button create_btn;
    EditText first_name,last_name,gmail;
    JSONObject post_dict = new JSONObject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);
        imageView=(ImageView) findViewById(R.id.avatar);
        first_name=(EditText) findViewById(R.id.f_name);
        last_name=(EditText) findViewById(R.id.l_name);
        gmail=(EditText) findViewById(R.id.editgmail);
        create_btn=(Button) findViewById(R.id.create_user);

        F_name=first_name.getText().toString();
        L_name=last_name.getText().toString();
        Gmail=gmail.getText().toString();
        Avatar=imageView.getDrawable().toString();
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("data", imageView.getDrawable().toString());

                try {
//                    post_dict.put("id" , "1");
                    post_dict.put("first_name", first_name.getText().toString());
                    post_dict.put("last_name", last_name.getText().toString());
                    post_dict.put("email",gmail.getText().toString());
                    post_dict.put("avatar",imageView.getDrawable());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("msg",post_dict.toString());
                if (post_dict.length() > 0) {
                    SendJsonDataToServer myAsyncTasks= new SendJsonDataToServer();
                    myAsyncTasks.execute(valueOf(post_dict));
                }
            }
        });
    }
    private class SendJsonDataToServer extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://reqres.in/api/users");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);
                String jsonInputString = valueOf(post_dict);

                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    Log.d("Done", response.toString());
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

    }

}
