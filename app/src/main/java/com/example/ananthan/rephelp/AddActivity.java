package com.example.ananthan.rephelp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ananthan.rephelp.data.StudentContract;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText titleET = findViewById(R.id.titleET);
        final EditText costET = findViewById(R.id.costET);
        final EditText rollET = findViewById(R.id.rollnoET);

        Button subSave =findViewById(R.id.subB);

        try {
            subSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String stitle = titleET.getText().toString().trim();
                    int scost = Integer.parseInt(costET.getText().toString().trim());
                    String sroll = rollET.getText().toString().trim();

                    ContentValues svalues = new ContentValues();

                    svalues.put(StudentContract.StudentEntry.COLUMN_SUBJECT_NAME, stitle);
                    svalues.put(StudentContract.StudentEntry.COLUMN_COST, scost);
                    svalues.put(StudentContract.StudentEntry.COLUMN_LIST_ROLL, sroll);

                    getContentResolver().insert(StudentContract.StudentEntry.SUBJECT_URI, svalues);



                    titleET.getText().clear();
                    costET.getText().clear();
                    rollET.getText().clear();

                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Fill all the fields..", Toast.LENGTH_SHORT).show();
        }

    }
}
