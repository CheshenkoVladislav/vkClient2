package com.example.vkclient2.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkclient2.Adapters.AdapterFotoGridFragment;
import com.example.vkclient2.App;
import com.example.vkclient2.BuildConfig;
import com.example.vkclient2.Data.Images;
import com.example.vkclient2.Data.PhotoListClass;
import com.example.vkclient2.Data.Photos.Root;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;
import com.example.vkclient2.SupportInterfaces.OnClickHolder;
import com.vk.sdk.VKAccessToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        requestData();
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

        setExitSharedElementCallback(
        new SharedElementCallback() {
          @Override
          public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            // Locate the ViewHolder for the clicked position.
            RecyclerView.ViewHolder selectedViewHolder = recyclerView
                .findViewHolderForAdapterPosition(MainActivity.currentFragmentNumber);
            if (selectedViewHolder == null || selectedViewHolder.itemView == null) {
              return;
            }

            // Map the first shared element name to the child ImageView.
            sharedElements
                .put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.cardImage));
          }
        });
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
        if (PhotoListClass.getPhotoList().size() == 0) {
            App.getApi().getAllPhotos(Integer.parseInt(VKAccessToken.currentToken().userId), 1, 0, VKAccessToken.currentToken().accessToken, BuildConfig.VERSION)
                    .enqueue(new Callback<Root>() {
                        @Override
                        public void onResponse(Call<Root> call, Response<Root> response) {
                            adapter.setPhotos(response.body().getResponse().getItems());

                        }

                        @Override
                        public void onFailure(Call<Root> call, Throwable t) {

                        }
                    });
        }
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
            Log.d(TAG, "imageViewTrans: " + imageView.getTransitionName());
//            imageView.setTransitionName(String.valueOf(Images.resInts.get(position)));
            SliderFragment slider = new SliderFragment();
//            ((TransitionSet) MainActivity.getCurrentFragment().getExitTransition()).excludeTarget(v, true);
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
