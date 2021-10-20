package com.example.carpoolconnect;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class StringFromAPITest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_from_apitest);

        getStringFromAPITest();
    }


    // This uses the okhttps3 library to connect to the backend
    public String text = "Testing 1-2 1-2.";
    private void getStringFromAPITest() {
        TextView textView = findViewById(R.id.label);
        textView.setText(text);

        OkHttpClient client = new OkHttpClient();
        String url = "http://10.0.2.2:8080/api/sendStringTest";

        Request request;
        request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    StringFromAPITest.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(myResponse);
                        }
                    });
                }
            }


        });

        // Functions onFailure and onResponse execute after this
        // If the test displays this on the screen something went wrong. It's supposed to show the contents of
        // the JSON from the backend.
        textView.setText("help pls");

    }
}