package com.example.CarpoolConnectBackend.api;

import com.example.CarpoolConnectBackend.model.TestModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

// To link our to backend from Android Studio, type in "http://10.0.2.2:8080" followed by the filepath to the api you want
//          ^^ Do NOT use https

// To view the JSON in your browser, type in "localhost:8080" followed by the filepath



@RequestMapping("api/sendStringTest")                   // Path to the JSON this class exports
@RestController                                         // Indicates this is a controller/API level class
public class sendStringTest {
    private final String textToSend = "You got this string from the backend!";


    // Get request that actually reads from the database
    @GetMapping
    @Transactional
    public TestModel simpleGetRequest() {
        SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
        Session session = sFactory.openSession();
        session.beginTransaction();

        // Your Model class MUST have a default constructor or else this will throw an exception!!
        TestModel testModel = session.get(TestModel.class, 7);

        session.getTransaction().commit();
        session.close();

        return testModel;
    }



    // Simple post request, posts to database
    @PostMapping
    @Transactional
    public void writeToDatabaseTest() {
        TestModel testModel = new TestModel(7);

        SessionFactory sFactory = new Configuration().configure().buildSessionFactory();
        Session session = sFactory.openSession();
        session.beginTransaction();

        // This if statement prevents an exception from being thrown if the given record is already in the table
        if (session.get(TestModel.class, 7) == null) {
            session.save(testModel);
        }
        else {
            System.out.println("That primary key's already in the table homie");
        }

        session.getTransaction().commit();
        session.close();
    }





    // This is from the first sendStringTest class. From before I put the database in.

    // Get request. This function returns a list of testModel objects, then exports them in JSON format.
    /*
    @GetMapping
    @Transactional
    public ArrayList<TestModel> sendStringToJSON() {                        // Everything from here is just normal Java code
        ArrayList<TestModel> x = new ArrayList<TestModel>();
        for (int i = 0; i < 3; i++) {
            x.add(new TestModel(i));
        }



        return x;
    } */

}
