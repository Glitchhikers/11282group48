package com.example.CarpoolConnectBackend.api;

import com.example.CarpoolConnectBackend.model.TestModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

// To link our to backend from Android Studio, type in "http://10.0.2.2:8080" followed by the filepath to the api you want
//          ^^ Do NOT use https

// To view the JSON in your browser, type in "localhost:8080" followed by the filepath



@RequestMapping("api/sendStringTest")                   // Path to the JSON this class exports
@RestController                                         // Indicates this is a controller/API level class
public class sendStringTest {
    private final String textToSend = "You got this string from the backend!";


    // Get request. This function returns a list of testModel objects, then exports them in JSON format.
    @GetMapping
    public ArrayList<TestModel> sendStringToJSON() {                        // Everything from here is just normal Java code
        ArrayList<TestModel> x = new ArrayList<TestModel>();
        for (int i = 0; i < 3; i++) {
            x.add(new TestModel(i));
        }

        return x;
    }
}
