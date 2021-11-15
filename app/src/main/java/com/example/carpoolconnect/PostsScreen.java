package com.example.carpoolconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PostsScreen extends AppCompatActivity {

    Button posts_bar, profile, home;
    ImageView pfp;
    TextView name, dest;
    DBHelper userDB;
    PostDBHelper postDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_screen);

        posts_bar = (Button) findViewById(R.id.posts_bar);
        profile = (Button) findViewById(R.id.profile);
        home = (Button) findViewById(R.id.home);
        pfp = (ImageView) findViewById(R.id.pfp);
        name = (TextView) findViewById(R.id.placeholder_name);
        dest =  (TextView) findViewById(R.id.placeholder_dest);

        userDB = new DBHelper(this);
        postDB = new PostDBHelper(this);

        String finalname = userDB.getName(LoginScreen.user);
        name.setText(finalname);

        String location = postDB.getLocation(userDB.getName(LoginScreen.user));
        String date = postDB.getDate(userDB.getName(LoginScreen.user));


        String uriimage = userDB.getImage(LoginScreen.user);
        Uri profile_image = Uri.parse(uriimage);

        Picasso.with(PostsScreen.this).load(profile_image).into(pfp);

        dest.setText("is driving to "+location+" on "+date);

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