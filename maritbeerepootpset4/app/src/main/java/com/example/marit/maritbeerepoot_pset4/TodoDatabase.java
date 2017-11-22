package com.example.marit.maritbeerepoot_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Marit on 20-11-2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {

    private static String databasename = "todos";
    private static int version = 2;
    private static TodoDatabase instance;

    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new TodoDatabase(context.getApplicationContext(), databasename, null, version);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed INTEGER);");
        ContentValues values =  new ContentValues();
        values.put("title", "lol");
        values.put("completed", 0);
        db.insert("todos",null, values);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "todos");
        onCreate(db);
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor lol = db.rawQuery("SELECT * FROM todos", null);
        return lol;
    }

    public void insert(String title, int completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("title", title);
        values.put("completed", completed);
        db.insert("todos",null, values);
    }

    public void update(long id, int box) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", box);
        db.update("todos", values, "_id=" + id, null);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos", "_id=" + id, null);
    }
}
