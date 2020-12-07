package portal.bloood.portal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.portal.R;

public class DonationEvents extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Blood Donation Event");
        setContentView(R.layout.activity_donation_events);
        WebView web=(WebView) findViewById(R.id.webView1);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(getIntent().getStringExtra("URL"));
    }
}
