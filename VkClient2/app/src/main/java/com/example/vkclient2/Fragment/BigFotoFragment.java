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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BigFotoFragment extends Fragment {
    private String imageUrl;
    private String transitionName;
    private static final String TAG = "BigFotoFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_big_foto,container,false);
        ImageView imageView = view.findViewById(R.id.bigPhotoContainer);
        imageView.setTransitionName(String.valueOf(transitionName));
        Picasso.get()
                .load(imageUrl)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        getParentFragment().startPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {
                        getParentFragment().startPostponedEnterTransition();
                    }
                });
        return view;
    }

    public static BigFotoFragment newInstance(String imageUrl, String transitionName){
        BigFotoFragment fragment = new BigFotoFragment();
        fragment.imageUrl = imageUrl;
        fragment.transitionName = transitionName;
        return fragment;
    }
}
