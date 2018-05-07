package com.example.vkclient2.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.widget.ImageView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vkclient2.Adapters.AdapterFotoGridFragment;
import com.example.vkclient2.CustomTransition;
import com.example.vkclient2.Data.Images;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;
import com.example.vkclient2.SupportInterfaces.OnClickHolder;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FotoGridFragment extends Fragment {
    AdapterFotoGridFragment adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String categoryName = "Photo";
        MainActivity.textView.setText(categoryName);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_foto_grid,container,false);
        Images.resInts = initData();
        adapter = new AdapterFotoGridFragment(Images.resInts,this);
        adapter.setClickHandler(new ConnectToSlider());
        recyclerView.setAdapter(adapter);
        prepareExitTransition();
        /**
         * Postpone transition needed for wait for create new Fragment
         */
        postponeEnterTransition();
        return recyclerView;
    }

    private void prepareExitTransition() {
        Transition transition = TransitionInflater.from(getContext())
                .inflateTransition(R.transition.exit_transition);
        setExitTransition(transition);
    }

    /**
     * Initialisation local data
     */
    public List<Integer> initData(){
        List<Integer>resInts = new ArrayList<>();
        resInts.add(R.drawable.ic_menu_gallery);
        resInts.add(R.drawable.ic_menu_camera);
        resInts.add(R.drawable.ic_menu_slideshow);
        resInts.add(R.drawable.side_nav_bar);
        resInts.add(R.drawable.ic_menu_send);

        return resInts;
    }

    /**
    *Realisation click handler. This include this fragment with exclude on click view.*
     * Also this handler include setting transitionSet on next Fragment (Slider Fragment)
     * Click on View - begin transaction to next fragment (Slider Fragment)
     */
    class ConnectToSlider implements OnClickHolder{
        @Override
        public void openSlider(int position,View v) {
            Log.d(TAG, "openSlider: GO FRAGMENT");
            ImageView imageView = v.findViewById(R.id.cardImage);
//            imageView.setTransitionName(String.valueOf(Images.resInts.get(position)));
            SliderFragment slider = new SliderFragment();
            ((TransitionSet) MainActivity.getCurrentFragment().getExitTransition()).excludeTarget(v, true);
                Log.d(TAG, "TRANSITION NAME: " + v.getTransitionName());
                Log.d(TAG, "VIEW: " + v);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .addSharedElement(imageView,imageView.getTransitionName())
                        .replace(R.id.fragmentContainer, slider)
                        .commit();
            }
    }
}
