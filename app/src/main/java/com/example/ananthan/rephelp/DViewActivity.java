package com.example.ananthan.rephelp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ananthan.rephelp.data.StudentContract;
import com.example.ananthan.rephelp.data.StudentDatabase;

public class DViewActivity extends AppCompatActivity {

    Cursor cur,c = null;
    SQLiteDatabase database;
    TextView subTV,costTV,countTV;
    ListView dlist;
    long itemid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dview);

        StudentDatabase sdb = new StudentDatabase(this);
        database =sdb.getReadableDatabase();

        Intent intent =getIntent();
        itemid = intent.getLongExtra("clickedid",0);
        Log.i("clickd on", String.valueOf(itemid));

        subTV = findViewById(R.id.dvSubTV);
        costTV = findViewById(R.id.dvCostTv);
        dlist =findViewById(R.id.dviewLV);
        countTV =findViewById(R.id.countTV);

        String[] subproj={
                StudentContract.StudentEntry.SUBJECT_ID,
                StudentContract.StudentEntry.COLUMN_SUBJECT_NAME,
                StudentContract.StudentEntry.COLUMN_COST,
                StudentContract.StudentEntry.COLUMN_LIST_ROLL};
        String[] args = {String.valueOf(itemid)};

        c = getContentResolver().query(StudentContract.StudentEntry.SUBJECT_URI,subproj,
               StudentContract.StudentEntry.SUBJECT_ID+"=?",
                args,
                null,
                null);
       int nameIdx = c.getColumnIndex(StudentContract.StudentEntry.COLUMN_SUBJECT_NAME);
       int listrollIdx = c.getColumnIndex(StudentContract.StudentEntry.COLUMN_LIST_ROLL);
       //int Idx = c.getColumnIndex(StudentContract.StudentEntry.SUBJECT_ID);
       int costIdx = c.getColumnIndex(StudentContract.StudentEntry.COLUMN_COST);
       c.moveToFirst();
        String sub_name = c.getString(nameIdx);
       String roll_list =c.getString(listrollIdx);
       int cost = c.getInt(costIdx);
       c.close();

         cur = database.rawQuery("SELECT * FROM "+ StudentContract.StudentEntry.TABLE_NAME+" WHERE "+ StudentContract.StudentEntry.COLOUMN_ID+
        " IN ("+roll_list+");",null);
         int count = cur.getCount();
        StudentCursorAdapter sadapter = new StudentCursorAdapter(this,cur);

        dlist.setAdapter(sadapter);

        int total_cost = cost*count;
        subTV.setText(sub_name);
        costTV.setText("Rs = "+String.valueOf(total_cost));
        countTV.setText("Total = "+String.valueOf(count));
    }

    @Override
    protected void onDestroy() {
        cur.close();
        c.close();
        database.close();
        super.onDestroy();
    }
}
