package pk.edu.riu.e4031.sqlitedatabase;

import android.provider.BaseColumns;

public final class DatabaseContract {
    public DatabaseContract() {}

    protected static abstract class Books implements BaseColumns {
        public static final String TABLE_NAME="books";
        public static final String COL_TITLE="title";
        public static final String COL_AUTHOR="author";
    }
}
