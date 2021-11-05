package com.example.carpoolconnect;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class RegisterScreen extends AppCompatActivity {


    EditText username,password,repeat_password,fullname,age,email,phone;
    Button register;
    int result = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repeat_password = (EditText) findViewById(R.id.reenter_password);

        fullname = (EditText) findViewById(R.id.fullname);
        age = (EditText) findViewById(R.id.age);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);

        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repeat_password.getText().toString();
                String name = fullname.getText().toString();
                String age_ = age.getText().toString();
                String email_ = email.getText().toString();
                String phone_ = phone.getText().toString();

                String eduCheck = email_.substring(email_.length()-4);
                if(user.equals("") || pass.equals ("") || repass.equals("")){
                    Toast.makeText(RegisterScreen.this, "Please fill all required fields!", Toast.LENGTH_SHORT).show();
                }
                else if(!eduCheck.equals(".edu")){
                    Toast.makeText(RegisterScreen.this, "A .edu email is required!", Toast.LENGTH_SHORT).show();
                }
                else if(parseInt(age_) < 18){
                    Toast.makeText(RegisterScreen.this, "You must be 18 years or older to register!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        // Connecting to backend here:

                        // Sending request
                        OkHttpClient client = new OkHttpClient();               // Launching client
                        String url = Global.url + "api/Profile/create";         // Link to backend

                        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                        JSONObject data = new JSONObject();
                        try {
                            data.put("user", user);
                            data.put("pass", pass);
                            data.put("name", name);
                            data.put("age", parseInt(age_));
                            data.put("email", email_);
                            data.put("phone", phone_);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RequestBody body = RequestBody.create(JSON, data.toString());
                        Log.d("MESSAGE:", data.toString());
                        Request request = new Request.Builder().url(url).post(body).build();

                        // Handling response
                        CountDownLatch latch = new CountDownLatch(1);
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                result = parseInt(response.body().string());
                                Log.d("MESSAGE", Integer.toString(result));
                                latch.countDown();
                            }

                        });

                        try {
                            latch.await();
                        } catch (java.lang.InterruptedException e) {
                            e.printStackTrace();
                        }


                        if (result == 0) {
                            Toast.makeText(RegisterScreen.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                            startActivity(intent);
                        }
                        else if (result == -1) {
                            Toast.makeText(RegisterScreen.this, "User already exists! \nPlease sign-in instead!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterScreen.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(RegisterScreen.this, "The passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
