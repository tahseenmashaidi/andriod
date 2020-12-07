package portal.bloood.portal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.portal.R;

public class Login extends AppCompatActivity {
    EditText username_text,password_text;
    Button login_btn;
    DatabaseAdapter DBA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBA=new DatabaseAdapter(this);
        login_btn=(Button) findViewById(R.id.login);
        username_text=(EditText) findViewById(R.id.username1);
        password_text=(EditText) findViewById(R.id.password1);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1=username_text.getText().toString();
                String password1=password_text.getText().toString();
                Cursor cursor=DBA.confirm_user(username1);

                if((cursor==null || cursor.getCount()==0 )) {
                    new AlertDialog.Builder(Login.this).setTitle("Error").setMessage("There are some Error.Try again").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }
                    }).show();
                }else {
                    cursor.moveToFirst();
                    Log.d("pass1", cursor.getString(4));
                    Log.d("pass2",password1);
                    if(password1.equals(cursor.getString(4))){
                        String name = cursor.getString(cursor.getColumnIndex(DatabaseContract.User.Col_4));
                        if (name.equals("Donor")) {
                            Intent intent = new Intent(getApplicationContext(), Donor.class);
                            intent.putExtra("name", cursor.getString(1));
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), Recipient.class);
                            intent.putExtra("name", cursor.getString(1));
                            Log.d("RES",  cursor.getString(1));
                            startActivity(intent);
                        }
                    }else{
                        new AlertDialog.Builder(Login.this).setTitle("Error").setMessage("Password not match.Try again").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }
                        }).show();
                    }

                }
            }
        });
    }
    public void forgotPassword(View view) {

    }

    public void not_register(View view) {
        Intent intent=new Intent(getApplicationContext(),Registration.class);
        intent.putExtra("typo",getIntent().getStringExtra("type"));
        startActivity(intent);
    }
}

