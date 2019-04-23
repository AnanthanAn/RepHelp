package com.example.ananthan.rephelp;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ananthan.rephelp.data.StudentContract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Created by ananthu on 09-01-2019.
 */

public class SubjectCursorAdapter extends CursorAdapter {
    public SubjectCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.subject_item,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView subTV = view.findViewById(R.id.subjectTV);
        TextView nosTV = view.findViewById(R.id.costTv);

        int nameIdx = cursor.getColumnIndex(StudentContract.StudentEntry.COLUMN_SUBJECT_NAME);
        int roll_Idx = cursor.getColumnIndex(StudentContract.StudentEntry.COLUMN_LIST_ROLL);
        int cost_Idx = cursor.getColumnIndex(StudentContract.StudentEntry.COLUMN_COST);

        String roll_list = cursor.getString(roll_Idx);

        String[] arr = roll_list.split(",");

        int count = arr.length;

        int cost = cursor.getInt(cost_Idx)*count;

        //Log.i("nos",String.valueOf(count));

        String snamee = cursor.getString(nameIdx);


        subTV.setText(snamee);
        nosTV.setText("Total : "+String.valueOf(count)+"    "+"Rs : "+String.valueOf(cost));


    }
}
