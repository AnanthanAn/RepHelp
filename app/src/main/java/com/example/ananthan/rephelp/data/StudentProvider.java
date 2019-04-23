package com.example.ananthan.rephelp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.ananthan.rephelp.StudentActivity;

/**
 * Created by ananthu on 08-01-2019.
 */

public class StudentProvider extends ContentProvider {

    StudentDatabase sdatabase;
    private static final int STUDENTS = 100;
    private static final int SUBJECTS = 101;
    private static final int STUDENTS_ID = 102;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(StudentContract.StudentEntry.CONTENT_AUTHORITY, StudentContract.StudentEntry.TABLE_NAME,STUDENTS);
        sUriMatcher.addURI(StudentContract.StudentEntry.CONTENT_AUTHORITY, StudentContract.StudentEntry.SUBJECT_TABLE_NAME,SUBJECTS);
        //sUriMatcher.addURI(StudentContract.StudentEntry.CONTENT_AUTHORITY, StudentContract.StudentEntry.TABLE_NAME);


    }

    @Override
    public boolean onCreate() {
        sdatabase = new StudentDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        SQLiteDatabase mdatbase = sdatabase.getReadableDatabase();

        int match = sUriMatcher.match(uri);

        Cursor c =null;

        switch (match){
            case STUDENTS:
                c =mdatbase.query(StudentContract.StudentEntry.TABLE_NAME,strings,s,strings1,null,null,null);
                break;
            case SUBJECTS:
                c =mdatbase.query(StudentContract.StudentEntry.SUBJECT_TABLE_NAME,strings,s,strings1,null,null,null);
                break;
            default:
                throw new IllegalArgumentException();
        }
        c.setNotificationUri(getContext().getContentResolver(),uri);
        //mdatbase.close();
        return c;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        SQLiteDatabase mdatabase = sdatabase.getWritableDatabase();

        int match = sUriMatcher.match(uri);

        long id;
        switch (match) {
            case STUDENTS:
                id = mdatabase.insert(StudentContract.StudentEntry.TABLE_NAME, null, contentValues);

                Toast.makeText(getContext(), "Student inserted to RollNo :" + id, Toast.LENGTH_SHORT).show();
                break;


            case SUBJECTS:
                id = mdatabase.insert(StudentContract.StudentEntry.SUBJECT_TABLE_NAME, null, contentValues);

                Toast.makeText(getContext(), "Subject inserted to :" + id, Toast.LENGTH_SHORT).show();

                break;
            default:
                throw new IllegalArgumentException();

        }

        getContext().getContentResolver().notifyChange(uri,null);
        //mdatabase.close();
        return ContentUris.withAppendedId(StudentContract.StudentEntry.CONTENT_URI, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        SQLiteDatabase mdatabase = sdatabase.getWritableDatabase();

        int delete_id =mdatabase.delete(StudentContract.StudentEntry.SUBJECT_TABLE_NAME,s,strings);

        getContext().getContentResolver().notifyChange(uri,null);

        return delete_id;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
