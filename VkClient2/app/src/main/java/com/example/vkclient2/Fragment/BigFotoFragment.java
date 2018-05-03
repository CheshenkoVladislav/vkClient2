package com.example.vkclient2.Fragment;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vkclient2.R;
import com.squareup.picasso.Picasso;

public class BigFotoFragment extends Fragment {
    private int imageUrl;
    public int getImageUrl() {return imageUrl;}
    public void setImageUrl(int imageUrl) {this.imageUrl = imageUrl;}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_big_foto,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView = view.findViewById(R.id.bigPhotoContainer);
        Picasso.get().load("https://i.stack.imgur.com/38ZZO.png")
                .resize(200,200)
                .into(imageView);
    }
}
