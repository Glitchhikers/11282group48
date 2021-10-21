package com.example.CarpoolConnectBackend.model;

// This is a simple object to test JSONs and see if the app can connect to the backend
// When the JSON sends data for a TestModel object, it sends the values of each class variable

public class TestModel {
    private int ID = 1;
    private String name = "String " + ID + ". You connected to backend!";

    public TestModel(int inputID) {
        ID = inputID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
