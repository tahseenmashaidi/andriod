package pk.edu.riu.e4031.sqlitedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class AllBookActivity extends AppCompatActivity {
    pk.edu.riu.e4031.sqlitedatabase.DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);
        databaseAdapter=new pk.edu.riu.e4031.sqlitedatabase.DatabaseAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView allBooks=(ListView) findViewById(R.id.listView);

        Cursor c=databaseAdapter.viewAllBook();
        BooksCursorAdapter booksCursorAdapter=new BooksCursorAdapter(this,c);

        allBooks.setAdapter(booksCursorAdapter);

        allBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),BookActivity.class);
                i.putExtra("bookId",id);
                startActivity(i);

            }
        });

    }
}
