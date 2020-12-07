package pk.edu.riu.e4031.sqlitedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class BookActivity extends AppCompatActivity {
    long bid;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent i=getIntent();
        bid=i.getLongExtra("bookId",0);
        EditText et1=(EditText) findViewById(R.id.bookTitle);
        EditText et2=(EditText) findViewById(R.id.bookAuthor);

        databaseAdapter=new DatabaseAdapter(this);
        Cursor c=databaseAdapter.viewBook(bid);

        if (c!=null) {
            c.moveToFirst();
            et1.setText(c.getString(c.getColumnIndexOrThrow(DatabaseContract.Books.COL_TITLE)));
            et2.setText(c.getString(c.getColumnIndexOrThrow(DatabaseContract.Books.COL_AUTHOR)));
        }



    }

    public void deleteBook(View v) {
        int effectedRows=databaseAdapter.deleteBook(bid);
        Toast.makeText(this,effectedRows+" Deleted",
                Toast.LENGTH_SHORT).show();
        finish();

    }

    public void updateBook(View v) {
        EditText et1=(EditText) findViewById(R.id.bookTitle);
        EditText et2=(EditText) findViewById(R.id.bookAuthor);
        String title=et1.getText().toString();
        String author=et2.getText().toString();

        int effectedRows=databaseAdapter.updateBook(bid,title,author);
        Toast.makeText(this,effectedRows+" Updated",
                Toast.LENGTH_SHORT).show();
        finish();

    }

}
