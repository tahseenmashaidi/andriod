package pk.edu.riu.e4031.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import pk.edu.riu.e4031.sqlitedatabase.DatabaseContract.Books;

public class DatabaseAdapter {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public DatabaseAdapter(Context c) {
        dbHelper=new DatabaseHelper(c);
        db=dbHelper.getWritableDatabase();
    }

    public int updateBook(long id,String title,String author) {
        ContentValues values=new ContentValues();
        values.put(Books.COL_TITLE,title);
        values.put(Books.COL_AUTHOR,author);
        String orderBy=Books.COL_TITLE+" ASC";
        String[] whereArgs={String.valueOf(id)};
        return db.update(Books.TABLE_NAME,values, Books._ID+"=?",whereArgs);
    }

    public int deleteBook(long id) {
        String orderBy=Books.COL_TITLE+" ASC";
        String[] whereArgs={String.valueOf(id)};
        return db.delete(Books.TABLE_NAME,Books._ID+"=?",whereArgs);
    }

    public Cursor viewBook(long id) {
        String[] columns={Books._ID,Books.COL_TITLE,Books.COL_AUTHOR};
        String orderBy=Books.COL_TITLE+" ASC";
        String[] whereArgs={String.valueOf(id)};
        return db.query(Books.TABLE_NAME,columns,Books._ID+"=?",whereArgs,null,null,null);
    }

    public Cursor viewAllBook() {
        String[] columns={Books._ID,Books.COL_TITLE,Books.COL_AUTHOR};
        String orderBy=Books.COL_TITLE+" ASC";
        return db.query(Books.TABLE_NAME,columns,null,null,null,null,orderBy);
    }
    public long addNewBook(String bookTitle, String bookAuthor) {
        ContentValues values=new ContentValues();
        values.put(Books.COL_TITLE,bookTitle);
        values.put(Books.COL_AUTHOR,bookAuthor);
        long id=db.insert(Books.TABLE_NAME,null,values);
        return id;
    }

}
