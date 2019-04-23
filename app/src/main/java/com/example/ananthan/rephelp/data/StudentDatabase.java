package com.example.ananthan.rephelp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ananthan on 08-01-2019.
 */

public class StudentDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="rephelp.db";
    public static final int DATABASE_VERSION =1;


    public StudentDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_SQL ="CREATE TABLE "+StudentContract.StudentEntry.TABLE_NAME+"("+ StudentContract.StudentEntry.COLOUMN_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+ StudentContract.StudentEntry.COLOUMN_NAME+" TEXT NOT NULL);";


        String CREATE_SUBJECTS_SQL ="CREATE TABLE "+StudentContract.StudentEntry.SUBJECT_TABLE_NAME+"("+ StudentContract.StudentEntry.SUBJECT_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, "+ StudentContract.StudentEntry.COLUMN_SUBJECT_NAME+" TEXT NOT NULL, "+ StudentContract.StudentEntry.COLUMN_COST+
                " INTEGER NOT NULL, "+ StudentContract.StudentEntry.COLUMN_LIST_ROLL+" TEXT NOT NULL);";
        sqLiteDatabase.execSQL(CREATE_SUBJECTS_SQL);
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
