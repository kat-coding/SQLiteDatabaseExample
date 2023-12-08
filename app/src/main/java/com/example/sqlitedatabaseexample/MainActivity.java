package com.example.sqlitedatabaseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonInsert, buttonDelete, buttonUpdate, buttonDisplay;
    EditText editTextName, editTextID, editTextEmail;

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }
    public void insertButton(View view){
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        dbManager.insert(name, email);
    }
    public void displayButton(View view){
        Cursor cursor = dbManager.fetch();
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL));
                Log.i("Kat_tag", "ID: "+id+" NAME: "+name+ " EMAIL: "+email);
            }while(cursor.moveToNext());
        }
    }
    public void updateButton(View view){
        int id = Integer.parseInt(editTextID.getText().toString());
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        dbManager.update(id, name, email);
    }

    public void deleteButton(View view){
        int id = Integer.parseInt(editTextID.getText().toString());
        dbManager.delete(id);
    }
    private void initialize(){
        buttonDisplay = findViewById(R.id.buttonDisplay);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextID = findViewById(R.id.editTextID);
        editTextName = findViewById(R.id.editTextName);

        dbManager = new DatabaseManager(this);
        dbManager.open();
    }
}