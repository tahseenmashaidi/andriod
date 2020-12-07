package portal.bloood.portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.portal.R;

public class MainActivity extends AppCompatActivity {
    Button donor_btn,recipient_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donor_btn=(Button) findViewById(R.id.donor);
        recipient_btn=(Button) findViewById(R.id.recipient);
        donor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                intent.putExtra("type","Donor");
                startActivity(intent);
               }
        });
        recipient_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                intent.putExtra("type","Recipient");
                startActivity(intent);
            }
        });
    }
}
