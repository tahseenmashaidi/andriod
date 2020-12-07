package portal.bloood.portal;

import portal.bloood.portal.DatabaseContract.User;
import portal.bloood.portal.DatabaseContract.Request;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String database_name = "Blood_Portal";
    public static final int DATABASE_VERSION=1;
    public static final String Create_User_table = " CREATE TABLE "
            + User.table_name + " ( "
            + User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + User.Col_2 + " TEXT, "
            + User.Col_3 + " TEXT, "
            + User.Col_4 + " TEXT, "
            + User.Col_5 + " TEXT, "
            + User.Col_6 + " TEXT, "
            + User.Col_7 + " TEXT, "
            + User.Col_8 + " TEXT" + " ) ";
    public  static final String Create_Request_Table=" CREATE TABLE "
            + Request.table_name + " ( "
            + Request._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Request.Request_by + " TEXT, "
            + Request.Request_to + " TEXT, "
            + Request.Col_Blood_Type + " TEXT, "
            + Request.Col_Request_count + " INTEGER" + " ) ";

    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_User_table);
        db.execSQL(Create_Request_Table);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS register_user");
        db.execSQL("DROP TABLE IF EXISTS Blood_requests");
        onCreate(db);
    }
}
