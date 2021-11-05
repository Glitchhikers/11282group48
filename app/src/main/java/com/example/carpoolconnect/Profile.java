package com.example.carpoolconnect;

public class Profile {

    private String user = "";
    private String pass = "";
    private String name = "";
    private int age = 18;
    private String email = "";
    private String phone = "";

    public Profile() {
        user = "";
        pass = "";
        name = "";
        age = 18;
        email = "";
        phone = "";
    }


    // Getters and Setters

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

