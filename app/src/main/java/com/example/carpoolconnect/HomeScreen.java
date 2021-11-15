package com.example.carpoolconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    Button drive, ride, post, posts_bar, profile, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        drive = (Button) findViewById(R.id.drive);
        ride = (Button) findViewById(R.id.ride);
        post = (Button) findViewById(R.id.posts);
        posts_bar = (Button) findViewById(R.id.posts_bar);
        profile = (Button) findViewById(R.id.profile);
        home = (Button) findViewById(R.id.home);

        drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GoogleMapsScreen.class);
                startActivity(intent);
            }
        });
        ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GoogleMapsScreen.class);
                startActivity(intent);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PostsScreen.class);
                startActivity(intent);
            }
        });
        posts_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PostsScreen.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProfileScreen.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
                startActivity(intent);
            }
        });
    }
}