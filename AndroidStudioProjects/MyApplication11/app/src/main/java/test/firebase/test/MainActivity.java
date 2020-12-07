package test.firebase.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button btn;
     FirebaseDatabase mydatabase;
     DatabaseReference dbreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydatabase=FirebaseDatabase.getInstance();
        dbreference=mydatabase.getReference();
        dbreference.setValue("User");
        et1=(EditText) findViewById(R.id.username_txt);
        et2=(EditText) findViewById(R.id.pass_txt);
        btn=(Button) findViewById(R.id.submit_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et1.getText().toString();
                String password=et2.getText().toString();
                dbreference.child("user1").child("name").setValue(name);
                dbreference.child("user1").child("pass").setValue(password);
            }
        });

    }
}
