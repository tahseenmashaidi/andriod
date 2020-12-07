package portal.bloood.portal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.portal.R;

public class Registration extends AppCompatActivity {
    Button register_btn;
    String blood_group;
    Spinner spinner;
    EditText full_name_text,email_text,address_text,phone_no_text,password_text;
    DatabaseAdapter databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        databaseAdapter=new DatabaseAdapter(this);
        register_btn=(Button) findViewById(R.id.register);
        full_name_text=(EditText) findViewById(R.id.full_name);
        email_text=(EditText) findViewById(R.id.email);
        address_text=(EditText) findViewById(R.id.address);
        phone_no_text=(EditText) findViewById(R.id.phone_no);
        password_text=(EditText) findViewById(R.id.password);
        spinner=(Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.blood_type));
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int  pos, long id)
            {
                blood_group= spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname,email,type,password,address,phone;
                fname=full_name_text.getText().toString();
                email=email_text.getText().toString();
                type=getIntent().getStringExtra("typo");
                password=password_text.getText().toString();
                address=address_text.getText().toString();
                phone=phone_no_text.getText().toString();

                if(databaseAdapter.confirm_user_name(fname)&&databaseAdapter.confirm_user_email(email)){
                    databaseAdapter.add_User(fname,email,type,password,address,phone,blood_group);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }else {
                    new AlertDialog.Builder(Registration.this).setTitle("Error")
                            .setMessage("FUll name or Username Already exist.Try Again")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }

            }
        });
    }
}
