package com.example.carpoolconnect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import com.squareup.picasso.Picasso;

public class ProfileScreen extends AppCompatActivity {
    Button BSelectImage, posts_bar, profile, home;

    // One Preview Image
    ImageView IVPreviewImage;

    TextView name_string, age_string, email_string, phone_string;
    DBHelper userDB;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();

            userDB = new DBHelper(this);
            userDB.setImage(LoginScreen.user, selectedImage.toString());
            IVPreviewImage.setImageURI(selectedImage);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        name_string = (TextView) findViewById(R.id.name_text);
        age_string = (TextView) findViewById(R.id.age);
        email_string = (TextView) findViewById(R.id.email);
        phone_string = (TextView) findViewById(R.id.phone);
        userDB = new DBHelper(this);
        posts_bar = (Button) findViewById(R.id.posts_bar);
        profile = (Button) findViewById(R.id.profile);
        home = (Button) findViewById(R.id.home);

        String test = userDB.getName(LoginScreen.user);
        name_string.setText(test);

        String age = userDB.getAge(LoginScreen.user);
        age_string.setText("Age:   "+age);

        String email = userDB.getEmail(LoginScreen.user);
        email_string.setText("Email:   "+email);

        String phone = userDB.getPhone(LoginScreen.user);
        phone_string.setText("Phone:   "+phone);

        BSelectImage = findViewById(R.id.change_image);
        IVPreviewImage = findViewById(R.id.preview_image);

        String uriimage = userDB.getImage(LoginScreen.user);
        Uri profile_image = Uri.parse(uriimage);

        Picasso.with(ProfileScreen.this).load(profile_image).into(IVPreviewImage);

        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
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