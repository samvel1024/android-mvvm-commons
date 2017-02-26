package com.samvel1024.android_mvvm_commons_sample;

/**
 * @author Samvel Abrahamyan
 */

public class User {

    public final String name;
    public final String email;


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
