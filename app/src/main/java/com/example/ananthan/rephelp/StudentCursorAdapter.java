package com.example.ananthan.rephelp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ananthan.rephelp.data.StudentContract;

/**
 * Created by ananthu on 08-01-2019.
 */

public class StudentCursorAdapter extends CursorAdapter {
    public StudentCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView rollTV = view.findViewById(R.id.rollno);
        TextView nameTV = view.findViewById(R.id.nameitem);

        int rollnoIdx = cursor.getColumnIndex(StudentContract.StudentEntry.COLOUMN_ID);
        int nameIdx = cursor.getColumnIndex(StudentContract.StudentEntry.COLOUMN_NAME);

        String rollno = cursor.getString(rollnoIdx);
        String snamee = cursor.getString(nameIdx);

        rollTV.setText(rollno);
        nameTV.setText(snamee);

    }
}
