package com.example.carpoolconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DriveScreenConfirm extends AppCompatActivity {

    Button  posts_bar, profile, home, post;
    TextView name_string, location_string;
    DBHelper userDB;
    EditText dateT;
    ImageView pfp;
    PostDBHelper postDB;
    GoogleMapsScreen map;
    LoginScreen loginClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_screen_confirm);

        posts_bar = (Button) findViewById(R.id.posts_bar);
        post = (Button) findViewById(R.id.postb);
        profile = (Button) findViewById(R.id.profile);
        home = (Button) findViewById(R.id.home);
        dateT = (EditText) findViewById(R.id.date);
        name_string = (TextView) findViewById(R.id.name_text);
        location_string = (TextView) findViewById(R.id.location);
        pfp = (ImageView) findViewById(R.id.preview_image);

        userDB = new DBHelper(this);
        map = new GoogleMapsScreen();

        String fullname = userDB.getName(LoginScreen.user);
        name_string.setText(fullname);
        location_string.setText(GoogleMapsScreen.location);

        String uriimage = userDB.getImage(LoginScreen.user);
        Uri profile_image = Uri.parse(uriimage);

        Picasso.with(DriveScreenConfirm.this).load(profile_image).into(pfp);

        postDB = new PostDBHelper(this);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateT.getText().toString();

                if(date.equals("")){
                    Toast.makeText(DriveScreenConfirm.this, "Please fill in the date!", Toast.LENGTH_SHORT).show();
                }

                Boolean postResult = postDB.insertData(fullname,GoogleMapsScreen.location,date);

                if(postResult == true){
                    Toast.makeText(DriveScreenConfirm.this, "Post successful!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(DriveScreenConfirm.this, "You can only have one post at time!", Toast.LENGTH_SHORT).show();
                }

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