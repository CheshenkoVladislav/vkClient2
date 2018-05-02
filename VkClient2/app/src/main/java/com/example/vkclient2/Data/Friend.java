package com.example.vkclient2.Data;

import android.widget.ImageView;
import android.widget.TextView;

public class Friend {
    public Friend(String textView) {
        this.textView = textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
    public ImageView imageView;
    public String textView;
}
