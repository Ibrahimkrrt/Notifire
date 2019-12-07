package com.example.oumaima.notifire.models;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int markId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int mark) {
        this.markId = mark;
    }

    public User() {
    }
}
