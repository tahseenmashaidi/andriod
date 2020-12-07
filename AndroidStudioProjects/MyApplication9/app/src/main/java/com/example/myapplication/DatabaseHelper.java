package pk.edu.riu.e4031.sqlitedatabase;

import pk.edu.riu.e4031.sqlitedatabase.DatabaseContract.Books;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="mybooks.db";
    public static final int DATABASE_VERSION=1;

    public static final String CREATE_BOOKS_TABLE="CREATE TABLE " +
            Books.TABLE_NAME + "(" +
            Books._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            Books.COL_TITLE + " TEXT, "+
            Books.COL_AUTHOR + " TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOKS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
