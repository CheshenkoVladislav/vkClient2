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
import com.example.vkclient2.App;
import com.example.vkclient2.BuildConfig;
import com.example.vkclient2.CustomTransition;
import com.example.vkclient2.Data.Images;
import com.example.vkclient2.Data.Photos.Root;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;
import com.example.vkclient2.SupportInterfaces.OnClickHolder;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKParameters;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class FotoGridFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterFotoGridFragment adapter;
    private static final String TAG = "FotoGridFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String categoryName = "Photo";
        MainActivity.textView.setText(categoryName);
        Log.d(TAG, "onCreate: ");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_foto_grid,container,false);
        Images.resInts = initData();
//        requestData();
        adapter = new AdapterFotoGridFragment(Images.resInts,this);
        adapter.setClickHandler(new ConnectToSlider());
        recyclerView.setAdapter(adapter);
        prepareExitTransition();
        /**
         * Postpone transition needed for wait for create new Fragment
         */
        if (savedInstanceState == null) postponeEnterTransition();
        return recyclerView;
    }

    private void prepareExitTransition() {
        setExitTransition(TransitionInflater.from(getContext())
                .inflateTransition(R.transition.exit_transition));
    }

    /**
     * Initialisation local data
     * @ForTest
     */
    public List<Integer> initData(){
        List<Integer>resInts = new ArrayList<>();
        resInts.add(R.drawable.vk);
        resInts.add(R.drawable.ic_menu_camera);
        resInts.add(R.drawable.ic_menu_slideshow);
        resInts.add(R.drawable.side_nav_bar);
        resInts.add(R.drawable.ic_menu_send);

        return resInts;
    }
    /**
     * Download data from server
     */
    public void requestData(){
        App.getApi().getAllPhotos(Integer.parseInt(VKAccessToken.currentToken().userId),1,0,VKAccessToken.currentToken().accessToken, BuildConfig.VERSION)
                .enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        adapter.setItems(response.body().getResponse().getItems());

                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {

                    }
                });
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
//            ((TransitionSet) MainActivity.getCurrentFragment().getExitTransition()).excludeTarget(v, true);
//                Log.d(TAG, "TRANSITION NAME: " + v.getTransitionName());
//                Log.d(TAG, "VIEW: " + v);
                getFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .addSharedElement(imageView,imageView.getTransitionName())
                        .replace(R.id.fragmentContainer, slider)
                        .commit();
            }
    }
}
