package com.example.ananthan.rephelp.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ananthu on 08-01-2019.
 */

public final class StudentContract {

    public static abstract class StudentEntry implements BaseColumns{

        public static final String TABLE_NAME = "students";
        public static final String COLOUMN_ID = BaseColumns._ID;
        public static final String COLOUMN_NAME = "name";
        public static final String CONTENT_AUTHORITY = "com.example.ananthan.rephelp";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,"students");


        public static final String SUBJECT_TABLE_NAME ="subjects";
        public static final String SUBJECT_ID =BaseColumns._ID;
        public static final String COLUMN_SUBJECT_NAME ="subname";
        public static final String COLUMN_LIST_ROLL ="studentrolls";
        public static final String COLUMN_COST ="cost";
        public static final Uri SUBJECT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,"subjects");


    }

}
