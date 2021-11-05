package com.example.carpoolconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginScreen extends AppCompatActivity {

    EditText username, password;
    Button login;
    String result;
    Profile profile;

    DBHelper userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        login = (Button) findViewById(R.id.login2);

        userDB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginScreen.this, "Please fill out all required fields!", Toast.LENGTH_SHORT).show();
                }
                else{
                    // Sending request to backend:
                    OkHttpClient client = new OkHttpClient();
                    String url = Global.url + "api/Profile/login";

                    MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                    JSONObject data = new JSONObject();
                    try {
                        data.put("user", user);
                        data.put("pass", pass);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RequestBody body = RequestBody.create(JSON, data.toString());
                    Request request = new Request.Builder().url(url).post(body).build();

                    // Handling Response
                    CountDownLatch latch = new CountDownLatch(1);
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            result = response.body().string();

                            latch.countDown();
                        }
                    });

                    try {
                        latch.await();
                    } catch (java.lang.InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.d("MESSAGE", result);
                    if (result == null || result == "") {
                        Toast.makeText(LoginScreen.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Gson gson = new Gson();
                        profile = gson.fromJson(result, Profile.class);
                        Toast.makeText(LoginScreen.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}