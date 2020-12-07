package portal.bloood.portal;
import portal.bloood.portal.DatabaseContract.User;
import portal.bloood.portal.DatabaseContract.Request;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static java.lang.String.valueOf;


public class DatabaseAdapter {
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public DatabaseAdapter(Context c) {
        dbHelper = new DatabaseHelper(c);
        db = dbHelper.getWritableDatabase();
    }

    public void add_User(String fname, String email, String type, String password, String address, String phone, String blood_group) {
        ContentValues values = new ContentValues();
        values.put(User.Col_2, fname);
        values.put(User.Col_3, email);
        values.put(User.Col_4, type);
        values.put(User.Col_5, password);
        values.put(User.Col_6, address);
        values.put(User.Col_7, phone);
        values.put(User.Col_8, blood_group);
        db.insert(User.table_name, null, values);
    }
    public Cursor confirm_user(String email) {
        String[] columns={User._ID, User.Col_2, User.Col_3, User.Col_4, User.Col_5, User.Col_6, User.Col_7, User.Col_8};
        return db.query(User.table_name, columns, User.Col_3+"='"+email+"'",null,null,null,null);
    }
    public boolean confirm_user_name(String name) {
        String[] columns={User._ID, User.Col_2, User.Col_3, User.Col_4, User.Col_5, User.Col_6, User.Col_7, User.Col_8};
        Cursor cursor= db.query(User.table_name, columns, User.Col_2+"='"+name+"'",null,null,null,null);
        return cursor == null || cursor.getCount() == 0;
    }
    public boolean confirm_user_email(String email) {
        String[] columns={User._ID, User.Col_2, User.Col_3, User.Col_4, User.Col_5, User.Col_6, User.Col_7, User.Col_8};
        Cursor cursor= db.query(User.table_name, columns, User.Col_3+"='"+email+"'",null,null,null,null);
        return cursor == null || cursor.getCount() == 0;
    }
    public Cursor getall(String type) {
        String[] columns={User._ID, User.Col_2, User.Col_3, User.Col_4, User.Col_5, User.Col_6, User.Col_7, User.Col_8};
        return db.query(User.table_name, columns, User.Col_4+"='"+type+"'",null,null,null,null);
    }
    public  Cursor getuser(Long id){
        String[] columns={User.Col_2, User.Col_6, User.Col_7, User.Col_8};
        return db.query(User.table_name, columns, User._ID+"='"+id+"'",null,null,null,null);
    }

    public void request_blood(String request_to,String request_by,String type,Long count){
        ContentValues values = new ContentValues();
        values.put(Request.Request_to, request_to);
        values.put(Request.Request_by, request_by);
        values.put(Request.Col_Blood_Type, type);
        values.put(Request.Col_Request_count, count);
        db.insert(Request.table_name, null, values);
    }
    public Cursor allRequest(String type) {
        Log.d("NoW", type);
        String[] columns={Request._ID,Request.Request_by, Request.Request_to, Request.Col_Blood_Type, Request.Col_Request_count};
        return db.query(Request.table_name, columns, Request.Request_by+"='"+type+"'",null,null,null,null);
    }
    public Cursor allRequest1(String request_to) {
        String[] columns={Request._ID,Request.Request_by, Request.Request_to, Request.Col_Blood_Type, Request.Col_Request_count};
        return db.query(Request.table_name, columns, Request.Request_to+"='"+request_to+"'",null,null,null,null);
    }
    public Cursor checkRequest(String name1) {
        String[] columns={Request._ID,Request.Request_by, Request.Request_to, Request.Col_Blood_Type, Request.Col_Request_count};
        return db.query(Request.table_name, columns, Request.Request_by+"='"+name1+"'",null,null,null,null);
    }
    public void updateRequest(long id,long num){
        ContentValues values = new ContentValues();
        values.put(Request.Col_Request_count, num);
        db.update(Request.table_name,values,Request._ID+"='"+id+"'",null);

    }
    public int deleteRequest(long id) {
        String[] whereArgs={valueOf(id)};
        return db.delete(Request.table_name,Request._ID+"=?",whereArgs);
    }
}