package com.example.marit.maritbeerepoot_pset4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private TodoAdapter adapter;
    private TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get database and initiate listview
        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor database = db.selectAll();
        makelistview(database);

        // Create listeners
        ListView listview = findViewById(R.id.ListView);
        listview.setOnItemClickListener(new Click());
        listview.setOnItemLongClickListener(new Hold());
    }

    public void makelistview(Cursor cursor) {
        ListView listview = findViewById(R.id.ListView);
        adapter = new TodoAdapter(this, cursor);
        listview.setAdapter(adapter);
    }

    public void addItem(View view) {
        db = TodoDatabase.getInstance(getApplicationContext());

        // Get the text
        EditText inputfield = (EditText) findViewById(R.id.Textfield);
        String input = inputfield.getText().toString();

        // Empty the field
        inputfield.setText("");

        // Check if something is inputted, if yes, add it to database
        if (input.length() > 0) {
            db.insert(input, 0);
            updateData();
        }
    }

    private void updateData(){
        db = TodoDatabase.getInstance(getApplicationContext());
        ListView list = findViewById(R.id.ListView);

        // Update the cursor and send the new one to the adapter
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
        list.setAdapter(adapter);
    }

    private class Click implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int Int, long idl) {
            CheckBox bool = view.findViewById(R.id.checkBox);
            Cursor cursor = db.selectAll();
            cursor.move(Int+1);

            // Check if it needs to be checked or unchecked when clicked
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            if (bool.isChecked()) {
                db.update(id, 0);
            }
            else {
                db.update(id, 1);
            }
            updateData();
        }
    }

    private class Hold implements AdapterView.OnItemLongClickListener{

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int Int, long idl) {
            Cursor cursor = db.selectAll();
            cursor.move(Int+1);

            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            db.delete(id);
            updateData();
            return false;
        }
    }
}
