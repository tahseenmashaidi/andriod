package portal.bloood.portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portal.R;

public class User_detail extends AppCompatActivity {
    Long uid;
    long count=1;
    String name,Rname;
    boolean check;
    DatabaseAdapter dba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        uid=getIntent().getLongExtra("id",0);
        name=getIntent().getStringExtra("recipient_name");
        final TextView et1=(TextView) findViewById(R.id.userTitle1);
        final TextView et2=(TextView) findViewById(R.id.user_Blood1);
        TextView et3=(TextView) findViewById(R.id.User_phoneNo1);
        final TextView et4=(TextView) findViewById(R.id.userAddress1);
        Button btn=(Button)findViewById(R.id.btnsend);
        dba=new DatabaseAdapter(this);
        final Cursor cursor=dba.getuser(uid);
            cursor.moveToFirst();
            Rname=cursor.getString(0);
            et1.setText(Rname);
            et2.setText(cursor.getString(3));
            et3.setText(cursor.getString(2));
            et4.setText(cursor.getString(1));
            btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(getApplicationContext(),"Request send",Toast.LENGTH_LONG).show();
                 Cursor cursor1=dba.checkRequest(name);
                 cursor1.moveToFirst();
                 Log.d("NUmer", String.valueOf(cursor1.getCount()));
                  if( cursor1.getCount()==0 ) {
                     Log.d("One", "true");
                     dba.request_blood(et1.getText().toString(),name,et2.getText().toString(),count);
                 }
                 else{
                      Log.d("Inner1", "REached");
                     for (int i = 1; i <=cursor1.getCount(); i++) {
                         Log.d("Inner2", cursor1.getString(2));
                         if (cursor1.getString(2).equals(Rname)) {
                            count = cursor1.getLong(4) + 1;
                            dba.updateRequest(cursor1.getLong(0), count);
                             check=true;
                            Log.d("find", String.valueOf(count));
                             Log.d("find1", String.valueOf(cursor.getLong(0)));
                        } else {
                            cursor1.moveToNext();
                        }
                    }
                      if(check==false){
                          Log.d("new", "new");
                          dba.request_blood(et1.getText().toString(),name,et2.getText().toString(),count);
                      }
                }
                 Intent intent=new Intent(getApplicationContext(),Recipient.class);
                 Log.d("name", name);
                 intent.putExtra("name",name);
                 startActivity(intent);
             }
         });
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
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
