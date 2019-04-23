package com.example.ananthan.rephelp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ananthan.rephelp.data.StudentContract;
import com.example.ananthan.rephelp.data.StudentDatabase;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int URL_LOAD = 0;

    StudentDatabase subdata;
    SubjectCursorAdapter subjectAdap;
    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddActivity.class);
                startActivity(intent);
            }
        });



        ListView subjectLV = findViewById(R.id.subjectLV);

        subdata = new StudentDatabase(this);

        String[] proj = {
                StudentContract.StudentEntry.SUBJECT_ID,
                StudentContract.StudentEntry.COLUMN_SUBJECT_NAME,
                StudentContract.StudentEntry.COLUMN_COST,
                StudentContract.StudentEntry.COLUMN_LIST_ROLL};

        c =getContentResolver().query(StudentContract.StudentEntry.SUBJECT_URI,
                proj,
                null,
                null,
                null);

        Log.i("Column counttttttt",String.valueOf(c.getColumnCount()));

        subjectAdap = new SubjectCursorAdapter(this,c);

        subjectLV.setAdapter(subjectAdap);

        subjectLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"you clicked on"+i,Toast.LENGTH_SHORT).show();
                //int idx = i+1;
                Intent intent = new Intent(getApplicationContext(),DViewActivity.class);
                intent.putExtra("clickedid",l);
                startActivity(intent);
            }
        });

        subjectLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final long delete_id = l;

                Toast.makeText(getApplicationContext(),"long click",Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Delete")
                        .setMessage("Nice aayitang Ozhivakkuvanalle ?")
                        .setPositiveButton("Yep", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String where = StudentContract.StudentEntry.SUBJECT_ID+"=?";
                                getContentResolver().delete(StudentContract.StudentEntry.SUBJECT_URI,
                                        where,
                                        new String[]{String.valueOf(delete_id)});
                            }
                        })
                        .setNegativeButton("Nop",null)
                        .show();

                return true;
            }
        });

        getLoaderManager().initLoader(URL_LOAD,null,this);

    }

    @Override
    protected void onDestroy() {
        c.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.nstudent){
            Intent intent = new Intent(getApplicationContext(),StudentActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.infoStudent){
            Intent intent = new Intent(getApplicationContext(),ListActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.credits){
            Intent intent = new Intent(getApplicationContext(),CreditsActivity.class);
            startActivity(intent);
        }


        return  true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String[] subproj={
                StudentContract.StudentEntry.SUBJECT_ID,
                StudentContract.StudentEntry.COLUMN_SUBJECT_NAME,
                StudentContract.StudentEntry.COLUMN_COST,
                StudentContract.StudentEntry.COLUMN_LIST_ROLL};

        return new CursorLoader(this,
                StudentContract.StudentEntry.SUBJECT_URI,
                subproj,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        subjectAdap.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        subjectAdap.swapCursor(null);
    }

}
