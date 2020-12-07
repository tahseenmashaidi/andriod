package portal.bloood.portal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portal.R;


public class Recipient extends AppCompatActivity {
    ListView listView,listView1;
    TextView tv,tv1,tv2;
    Long user_id;
    boolean check;
    DatabaseAdapter DBA;
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);
        DBA=new DatabaseAdapter(this);
        tv=(TextView) findViewById(R.id.user_name);
        tv1=(TextView) findViewById(R.id.donor_list);
        tv2=(TextView) findViewById(R.id.request_list);
        listView1=(ListView) findViewById(R.id.list_view1);
        listView=(ListView) findViewById(R.id.list_view);
        listView.setVisibility(View.INVISIBLE);
        listView1.setVisibility(View.INVISIBLE);
        uname=getIntent().getStringExtra("name");
        tv.setText(String.format("Welcome %s",uname ));

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView.getVisibility()==View.VISIBLE){
                    listView.setVisibility(View.INVISIBLE);
                    listView.getLayoutParams().height= 0;
                }else {
                    listView.setVisibility(View.VISIBLE);
                    listView.getLayoutParams().height= ActionBar.LayoutParams.WRAP_CONTENT;
                }
                Cursor cursor=DBA.getall("Donor");
                UserCursorAdapter userCursorAdapter=new UserCursorAdapter(getApplicationContext(),cursor);
                userCursorAdapter.check(true);
                listView.setAdapter(userCursorAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getApplicationContext(),User_detail.class);
                        intent.putExtra("id",id);
                        intent.putExtra("recipient_name",uname);
                        Log.d("ID", String.valueOf(id));
                        startActivity(intent);
                    }
                });
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listView1.getVisibility()==View.VISIBLE){
                    listView1.setVisibility(View.INVISIBLE);
                    listView1.getLayoutParams().height= 0;
                }else {
                    listView1.setVisibility(View.VISIBLE);
                    listView1.getLayoutParams().height= ActionBar.LayoutParams.WRAP_CONTENT;
                }
                Cursor cursor1=DBA.allRequest(uname);
                UserCursorAdapter userCursorAdapter=new UserCursorAdapter(getApplicationContext(),cursor1);
                userCursorAdapter.check(false);
                listView1.setAdapter(userCursorAdapter);
                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        user_id= id;
                        new AlertDialog.Builder(Recipient.this).setTitle("Delete Request").setMessage("Are You sure ")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DBA.deleteRequest(user_id);
                                        Toast.makeText(getApplicationContext(),"Delete Successfully",Toast.LENGTH_SHORT).show();
                                        listView1.setVisibility(View.INVISIBLE);
                                    }})
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //do nothing
                                    }
                                })
                        .show();
                    }
                });
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
