package com.example.carpoolconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Starts activity that gets a string from our backend and displays it on the screen
        Intent startStringFromAPITest = new Intent(this, StringFromAPITest.class);
        startActivity(startStringFromAPITest);
    }


}