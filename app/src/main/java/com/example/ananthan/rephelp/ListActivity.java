package com.example.ananthan.rephelp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ananthan.rephelp.data.StudentContract;

public class ListActivity extends AppCompatActivity {


    Cursor c =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = findViewById(R.id.infoListView);

        String [] projection ={
                StudentContract.StudentEntry.COLOUMN_ID,
                StudentContract.StudentEntry.COLOUMN_NAME
        };

        c = getContentResolver().query(StudentContract.StudentEntry.CONTENT_URI,projection,null,null,null);

        StudentCursorAdapter sadapter = new StudentCursorAdapter(this,c);

        listView.setAdapter(sadapter);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        c.close();
    }
}
