package pk.edu.riu.e4031.sqlitedatabase;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class BooksCursorAdapter extends CursorAdapter {
    public BooksCursorAdapter(Context context, Cursor cursor) {
        super(context,cursor,0);

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.all_books,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvId=(TextView) view.findViewById(R.id.txt_id);
        TextView tvTitle=(TextView) view.findViewById(R.id.txt_title);
        TextView tvAuthor=(TextView) view.findViewById(R.id.txt_author);

        tvId.setText(String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.Books._ID))));
        tvTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Books.COL_TITLE)));
        tvAuthor.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Books.COL_AUTHOR)));
    }
}
