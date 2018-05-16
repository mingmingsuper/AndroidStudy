package com.lmm.databindingdemo.dataModel;

public class User {
    public String firstName;
    public String secondname;
    public int age;

    public User(String firstName, String secondName, int age) {
        this.firstName = firstName;
        this.secondname = secondName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondname() {
        return secondname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }
}
