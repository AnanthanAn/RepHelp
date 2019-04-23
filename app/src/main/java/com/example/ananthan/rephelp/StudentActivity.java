package com.example.ananthan.rephelp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ananthan.rephelp.data.StudentContract;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        final EditText sEditText = findViewById(R.id.sET);
        Button saveButton = findViewById(R.id.saveStudentB);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = sEditText.getText().toString().trim();
                ContentValues values =new ContentValues();
                values.put(StudentContract.StudentEntry.COLOUMN_NAME,sName);
                getContentResolver().insert(StudentContract.StudentEntry.CONTENT_URI,values);

                sEditText.getText().clear();

            }
        });

    }
}
