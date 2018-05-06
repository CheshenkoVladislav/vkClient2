package com.example.vkclient2.Fragment;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
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
    private static final String TAG = "BigFotoFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_big_foto,container,false);
        ImageView imageView = view.findViewById(R.id.bigPhotoContainer);
        imageView.setTransitionName(String.valueOf(imageUrl));
        imageView.setImageResource(imageUrl);
        getParentFragment().startPostponedEnterTransition();
        Log.d(TAG, "TRANSITION NAME: " + imageView.getTransitionName());
        return view;
    }


//        setEnterTransition(transition);
//        setSharedElementReturnTransition(new Fade());
}
