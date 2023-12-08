package com.example.sqlitedatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

//Talks to database
public class DatabaseManager {
    //fields
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    //constructor
    public DatabaseManager(Context context){
        this.context = context;
    }

    //initiate dbhelper and database
    public DatabaseManager open(){
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    //closes db
    public void close(){
        dbHelper.close();
    }
    //add to db
    public void insert(String name, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_NAME, name);
        contentValues.put(DatabaseHelper.COLUMN_EMAIL, email);
        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
        Toast.makeText(context, "Input inserted into database", Toast.LENGTH_LONG).show();
    }
    public Cursor fetch() {
        //column names
        String[] columns = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_EMAIL};
        //select columns from table
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE,columns, null,
                null, null, null, null);
        //return results in cursor
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void update(int id, String name, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_ID, id);
        contentValues.put(DatabaseHelper.COLUMN_EMAIL, email);
        contentValues.put(DatabaseHelper.COLUMN_NAME, name);
        database.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.COLUMN_ID+"="+id, null);
    }

    public void delete(int id){
        database.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.COLUMN_ID+"="+id, null);
        Toast.makeText(context, "Deleted database row with id "+id, Toast.LENGTH_LONG).show();
    }
}