package com.example.vkclient2.Data;

import android.widget.ImageView;
import android.widget.TextView;

public class Friend {
    private String fullName;
    private ImageView imageView;

    private int userId;

    public Friend(String textView,int userId) {
        this.fullName = textView;
        this.userId = userId;
    }

    public String getFullName() {return fullName;}
    public int getUserId() {return userId;}
    public ImageView getImageView() {return imageView;}
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
