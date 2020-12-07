package portal.bloood.portal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.portal.R;


public class Donor extends AppCompatActivity {
    Button btn6;
    LinearLayout linearLayout;
    DatabaseAdapter DBA;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        DBA=new DatabaseAdapter(this);
        setContentView(R.layout.activity_donor);
        btn6=(Button) findViewById(R.id.btn_request);
        listView=(ListView)findViewById(R.id.list_view4);
        linearLayout=(LinearLayout) findViewById(R.id.layout);
        linearLayout.setVisibility(View.INVISIBLE);
        TextView tv=(TextView) findViewById(R.id.donor_name);
        tv.setText(String.format("Welcome %s", getIntent().getStringExtra("name")));
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.getLayoutParams().height= ActionBar.LayoutParams.WRAP_CONTENT;
                if (listView.getVisibility()==View.VISIBLE){
                    listView.setVisibility(View.INVISIBLE);
                    listView.getLayoutParams().height= 0;
                }else {
                    listView.setVisibility(View.VISIBLE);
                    listView.getLayoutParams().height= ActionBar.LayoutParams.WRAP_CONTENT;
                }
                Cursor cursor1=DBA.allRequest1(getIntent().getStringExtra("name"));
                UserCursorAdapter userCursorAdapter=new UserCursorAdapter(getApplicationContext(),cursor1);
                userCursorAdapter.check(false);
                listView.setAdapter(userCursorAdapter);
            }
        });
    }
    public void btn1(View view) {
        Intent intent=new Intent(getApplicationContext(), DonationEvents.class);
        intent.putExtra("URL","https://www.bloodservice.fi/blood-donation/donate-blood/where-can-you-donate");
        startActivity(intent);
    }
    public void btn2(View view) {
        Intent intent=new Intent(getApplicationContext(), DonationEvents.class);
        intent.putExtra("URL","https://www.vitalant.org/Engage/Local-Events-Activities.aspx");
        startActivity(intent);
    }
    public void btn3(View view) {
        Intent intent=new Intent(getApplicationContext(), DonationEvents.class);
        intent.putExtra("URL","https://www.awarenessdays.com/awareness-days-calendar/world-blood-donor-day-2020/");
        startActivity(intent);
    }
    public void btn4(View view) {
        Intent intent=new Intent(getApplicationContext(), DonationEvents.class);
        intent.putExtra("URL","https://donate.nybc.org/donor/schedules/centers");
        startActivity(intent);
    }
    public void btn5(View view) {
        Intent intent=new Intent(getApplicationContext(), DonationEvents.class);
        intent.putExtra("URL","https://www.versiti.org/ways-to-give/blood-donation/locations");
        startActivity(intent);
    }

    public void Event_list(View view) {
        if (linearLayout.getVisibility()==View.VISIBLE){
            linearLayout.setVisibility(View.INVISIBLE);
            linearLayout.getLayoutParams().height= 0;
        }else if((linearLayout.getVisibility()==View.INVISIBLE)) {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.getLayoutParams().height= ActionBar.LayoutParams.WRAP_CONTENT;
        }

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

