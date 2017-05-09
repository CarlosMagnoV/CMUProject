package pt.ulisboa.tecnico.cmov.projectcmu;


import android.provider.BaseColumns;

public class PostContract {

    public static final String DB_NAME = "pt.ulisboa.tecnico.cmov.projectcmu";
    public static final int DB_VERSION = 1;

    public class PostEntry implements BaseColumns {

        public static final String TABLE = "posts";
        public static final String COL_TASK_TITLE = "title";
        public static final String COL_TASK_BODY = "body";
    }
}
