package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {

    Integer noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Get EditText view
        EditText editText = (EditText) findViewById(R.id.editText);

        // Get Intent
        Intent intent = getIntent();

        // Get value of "noteid" from intent
        String id = intent.getStringExtra("noteid");
        noteid = Integer.parseInt(id);

        if (noteid != -1) {
            // Display content of note
            Note note = MainActivity2.notes.get(noteid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void onButtonClick(View view) {
        // Save Button
        // Get editText and the content the user entered
        EditText editText = (EditText) findViewById(R.id.editText);
        String content = editText.getText().toString();

        // Initialize SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        // Initialize DBHelper class
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // Set username from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(title, date, content, username);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        // Go to second activity
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}