package com.example.carpoolconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterScreen extends AppCompatActivity {

    EditText username,password,repeat_password,fullname,age,email,phone;
    Button register;

    DBHelper userDB;
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

        userDB = new DBHelper(this);

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
                else if(Integer.parseInt(age_) < 18){
                    Toast.makeText(RegisterScreen.this, "You must be 18 years or older to register!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                       Boolean userExists = userDB.checkUserExists(user);

                       if(userExists == false){
                           Boolean registrationResult = userDB.insertData(user,pass,name,age_,email_,phone_);

                           if(registrationResult == true){
                               Toast.makeText(RegisterScreen.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
                               startActivity(intent);
                           }
                           else{
                               Toast.makeText(RegisterScreen.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else{
                           Toast.makeText(RegisterScreen.this, "User already exists! \nPlease sign-in instead!", Toast.LENGTH_SHORT).show();
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
