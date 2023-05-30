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
    private static final String DATABASE_NAME = "app_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SUBJECT_TABLE = "subjects";
    private static final String SUBJECT_ID = "_id";
    private static final String SUBJECT_NAME = "subject_name";
    private static final String SUBJECT_LANG = "subject_lang";
    private static final String SUBJECT_IDE = "subject_ide";
    private static final String GROUP_TABLE = "groups";
    private static final String GROUP_NAME = "group_name";
    private static final String GROUP_YEAR = "group_year";

    public App_Database (@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        String query = "CREATE TABLE " + SUBJECT_TABLE +
                " (" + SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SUBJECT_NAME + " TEXT, " +
                SUBJECT_LANG + " TEXT, " +
                SUBJECT_IDE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + SUBJECT_TABLE);
        onCreate(db);
    }

    void addSubject(String name, String lang, String ide) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SUBJECT_NAME, name);
        cv.put(SUBJECT_LANG, lang);
        cv.put(SUBJECT_IDE, ide);
        long result = db.insert(SUBJECT_TABLE, null, cv);
        if(result == -1){
            Toast.makeText(context, "Ошибка записи", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + SUBJECT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateSubject(String row_id, String name, String lang, String ide) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SUBJECT_NAME, name);
        cv.put(SUBJECT_LANG, lang);
        cv.put(SUBJECT_IDE, ide);
        long result = db.update(SUBJECT_TABLE, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Запись обновилась", Toast.LENGTH_SHORT).show();
        }
    }

    public void addGroup(String group_name, String group_year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(GROUP_NAME,group_name);
        cv.put(GROUP_YEAR,group_year);
        long result = db.insert(GROUP_TABLE, null, cv);
        if(result == -1){
            Toast.makeText(context, "Ошибка записи", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Запись добавлена", Toast.LENGTH_SHORT).show();
        }
    }
}
