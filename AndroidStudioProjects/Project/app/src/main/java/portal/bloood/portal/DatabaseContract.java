package portal.bloood.portal;
import android.provider.BaseColumns;
public final class DatabaseContract {
    public DatabaseContract() {}

    protected static abstract class User implements BaseColumns {
        public static final String table_name = "register_user";
        public static final String Col_2 = "full_name";
        public static final String Col_3 = "email";
        public static final String Col_4 = "type";
        public static final String Col_5 = "password";
        public static final String Col_6 = "address";
        public static final String Col_7 = "phone_no";
        public static final String Col_8 = "blood_group";
    }
    protected static abstract class Request implements BaseColumns {
        public static final String table_name = "Blood_requests";
        public static final String Request_to = "Request_to";
        public static final String Request_by= "Request_by";
        public static final String Col_Blood_Type = "blood_type";
        public static final String Col_Request_count = "request_count";
    }
}
