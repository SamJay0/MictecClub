package com.example.android.mictecclub.Data;

import android.net.Uri;

public class User {

    private Uri image=null;
    private String userName="";
    private String userEmail="";

    public User(){}

    public User(Uri image, String userName, String userEmail) {
        this.image = image;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public Uri getImage() {
        return image;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public String toString() {
        return image+"\n"+userName+"\n"+userEmail;
    }
}
