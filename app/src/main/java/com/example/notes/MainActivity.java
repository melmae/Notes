package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

        // Check if already logged in, if so skip the login screen
        if (!sharedPreferences.getString("username", "").equals("")) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public void onButtonClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        EditText username = (EditText) findViewById(R.id.username);
        sharedPreferences.edit().putString("username", username.getText().toString()).apply();

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

}