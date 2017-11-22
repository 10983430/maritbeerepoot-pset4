package com.example.marit.maritbeerepoot_pset4;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Marit on 22-11-2017.
 */

public class TodoAdapter extends CursorAdapter {
    public TodoAdapter(Context context, Cursor cursor) {
        super(context, cursor, R.layout.row_todo);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        Log.d("test", "hiiiiiii");
        return LayoutInflater.from(context).inflate(R.layout.row_todo, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        // Find the textview and checkbox
        Log.d("test", "hiiiiiii");
        TextView todo = view.findViewById(R.id.textView2);
        //TextView test = view.findViewById(R.id.textView);
        CheckBox box = view.findViewById(R.id.checkBox);
        String lol = cursor.getString(cursor.getColumnIndex("title"));
        Log.d("test", lol);
        // Set the textview and checkbox
        todo.setText(cursor.getString(cursor.getColumnIndex("title")));
        //test.setText(cursor.getString(cursor.getColumnIndex("title")));
        Integer bool = cursor.getInt(cursor.getColumnIndex("completed"));

        // Check if the box has to be checked or not
        if (bool == 1){
            box.setChecked(true);
        }
        else{
            box.setChecked(false);
        }

    }
}
