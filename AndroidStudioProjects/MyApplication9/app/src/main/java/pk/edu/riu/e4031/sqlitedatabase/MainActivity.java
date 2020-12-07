package pk.edu.riu.e4031.sqlitedatabase;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseAdapter=new DatabaseAdapter(this);
    }

    public void addBook(View v) {
        EditText et1=(EditText) findViewById(R.id.editTitle);
        String bookTitle=et1.getText().toString();
        EditText et2=(EditText) findViewById(R.id.editAuthor);
        String bookAuthor=et2.getText().toString();

        long id=databaseAdapter.addNewBook(bookTitle, bookAuthor);

        et1.setText("");
        et2.setText("");
        Toast.makeText(this,"Record Inserted: "+id,
                Toast.LENGTH_SHORT).show();
    }

    public void viewAll(View v) {
        Intent i=new Intent(this,AllBookActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
