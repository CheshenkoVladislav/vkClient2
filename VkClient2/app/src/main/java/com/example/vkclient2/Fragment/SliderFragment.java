package com.example.vkclient2.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vkclient2.Adapters.AdapterSliderPager;
import com.example.vkclient2.Data.StaticClasses.PhotoListClass;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;

import java.util.List;
import java.util.Map;

public class SliderFragment extends Fragment {
    private static final String TAG = "SliderFragment";
    ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_slider, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = view.findViewById(R.id.sliderPager);
        TextView imageCounter = view.findViewById(R.id.imageCounter);
        LinearLayout footer = view.findViewById(R.id.footer);
        footer.setVisibility(View.VISIBLE);
        AdapterSliderPager adapter = new AdapterSliderPager(this);
        pager.setAdapter(adapter);
        pager.setCurrentItem(MainActivity.currentPosition);
        imageCounter.setText(MainActivity.currentPosition + "/" + PhotoListClass.getPhotoQuantity());
        prepareTransition();
        if (savedInstanceState == null) postponeEnterTransition();
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
                MainActivity.currentPosition = position;
                imageCounter.setText("" + position + "/" + PhotoListClass.getPhotoQuantity());
            }
           });
    }

    void prepareTransition() {
        Transition transition = TransitionInflater.from(getContext())
                .inflateTransition(R.transition.shared_transition);
        setSharedElementEnterTransition(transition);
//        setEnterTransition(transition);
        setEnterSharedElementCallback(
                new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        // Locate the image view at the primary fragment (the ImageFragment that is currently
                        // visible). To locate the fragment, call instantiateItem with the selection position.
                        // At this stage, the method will simply return the fragment at the position and will
                        // not create a new one.
                        Fragment currentFragment = (Fragment) pager.getAdapter()
                                .instantiateItem(pager, MainActivity.currentPosition);
                        View view = currentFragment.getView();
                        if (view == null) {
                            return;
                        }

                        // Map the first shared element name to the child ImageView.
                        sharedElements.put(names.get(0), view.findViewById(R.id.bigPhotoContainer));
                    }
                });
    }
}

