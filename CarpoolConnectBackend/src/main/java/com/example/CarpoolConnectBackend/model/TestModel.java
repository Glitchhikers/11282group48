package com.example.CarpoolConnectBackend.model;

// This is a simple object to test JSONs and see if the app can connect to the backend
// When the JSON sends data for a TestModel object, it sends the values of each class variable

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// The @Entity, @Id, and @GeneratedValue are all for the database
@Entity
@Table(name = "TestModel")
public class TestModel {


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int ID = 1;
    private String name = "String " + ID + ". You connected to backend!";

    public TestModel() {
        ID = 1;
        name = "String " + ID + ". You connected to backend!";
    }

    public TestModel(int inputID) {
        ID = inputID;
        name = updateString();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String updateString() {
        return "String " + ID + ". You connected to backend!";
    }

}
