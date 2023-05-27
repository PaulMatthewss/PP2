package com.example.pp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class App_Database extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "AppDB.db";
    private static final int DATABASE_VERSION = 1;

    //Table for Groups
    private static final String TABLE_GROUPS = "groups";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_GROUP_NAME = "group_name";
    private static final String COLUMN_GROUP_YEAR = "group_year";

    public App_Database (@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate (SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_GROUPS +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GROUP_NAME + " TEXT, " +
                COLUMN_GROUP_YEAR + " DATE);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade (SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        onCreate(db);
    }
    void addGroup(String group_name, String group_year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GROUP_NAME, group_name);
        cv.put(COLUMN_GROUP_YEAR, group_year);
        long result = db.insert(TABLE_GROUPS, null, cv);
        if(result == -1){
            Toast.makeText(context, "Ошибка записи", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_GROUPS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
