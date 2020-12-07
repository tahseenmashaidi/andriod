package portal.bloood.portal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.portal.R;

public class UserCursorAdapter extends CursorAdapter {
    private Boolean turn;
    public UserCursorAdapter(Context context,Cursor cursor){
        super(context,cursor,0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.all_user,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvid = (TextView) view.findViewById(R.id.text_id);
        TextView tvname = (TextView) view.findViewById(R.id.txt_name);
        TextView tvblood_type = (TextView) view.findViewById(R.id.txt_blood_type);
        TextView tvphone = (TextView) view.findViewById(R.id.txt_phone);
        TextView tvaddress = (TextView) view.findViewById(R.id.txt_address);
        TextView tvname1 = (TextView) view.findViewById(R.id.txt_name1);
        TextView tvphone1 = (TextView) view.findViewById(R.id.txt_phone1);
        TextView tvaddress1 = (TextView) view.findViewById(R.id.txt_address1);
        if(turn) {
            tvname1.setText("Name:");
            tvaddress1.setText("Address:");
            tvphone1.setText("Phone NO:");
            tvid.setText(String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.User._ID))));
            tvname.setText(String.valueOf(cursor.getString(1)));
            tvaddress.setText(cursor.getString(5));
            tvphone.setText(cursor.getString(6));
            tvblood_type.setText(cursor.getString(7));
        }
        else {
            Log.d("new", "check");
            tvname1.setText("Request To:");
           tvaddress1.setText("Request By:");
            tvphone1.setText("Request Time:");
            tvid.setText(String.valueOf(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.Request._ID))));
            tvname.setText(String.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Request.Request_to))));
            tvblood_type.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Request.Col_Blood_Type)));
            tvphone.setText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Request.Col_Request_count)));
            tvaddress.setText(cursor.getString(1));
        }
    }
    public void check(boolean set){
        turn=set;
    }
}
