package com.example.vkclient2.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkclient2.Adapters.AdapterFotoGridFragment;
import com.example.vkclient2.SupportClasses.App;
import com.example.vkclient2.BuildConfig;
import com.example.vkclient2.Data.PhotoListClass;
import com.example.vkclient2.Data.Photos.Root;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;
import com.example.vkclient2.SupportInterfaces.SupportInterface;
import com.vk.sdk.VKAccessToken;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FotoGridFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterFotoGridFragment adapter;
    SwipeRefreshLayout refresh;
    //User info
    private int userId;
    private String userName;
    public void setUserId(int userId) {this.userId = userId;}
    public void setUserName(String userName) {this.userName = userName;}

    private static final String TAG = "FotoGridFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String categoryName = "Photo";
        ((MainActivity)getActivity()).categoryTextView.setText(categoryName);
        ((MainActivity)getActivity()).friendNameTextView.setText(userName);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foto_grid,container,false);
        /**
         * Postpone transition needed for wait for create new Fragment
         */
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler);
        requestData();
        adapter = new AdapterFotoGridFragment(this);
        adapter.setClickHandler(new ConnectToSlider());
        recyclerView.setAdapter(adapter);
        prepareExitTransition();
        if (savedInstanceState == null) postponeEnterTransition();
        refresh = view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
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
     * Download data from server
     */
    public void requestData(){
        if (PhotoListClass.getPhotoList().size() != 0) {
            PhotoListClass.clearPhotoList();
        }
            App.getApi().getAllPhotos(Integer.parseInt(VKAccessToken.currentToken().userId), 1, 0,
                    VKAccessToken.currentToken().accessToken, BuildConfig.VERSION)
                    .enqueue(new Callback<Root>() {
                        @Override
                        public void onResponse(Call<Root> call, Response<Root> response) {
                            adapter.setPhotos(response.body().getResponse().getItems());
                            refresh.setRefreshing(false);

                        }

                        @Override
                        public void onFailure(Call<Root> call, Throwable t) {
                            refresh.setRefreshing(false);
                        }
                    });
        }

    /**
    *@openSlider is realisation click handler. This include this fragment with exclude on click view.*
     * Also this handler include setting transitionSet on next Fragment (Slider Fragment)
     * Click on View - begin transaction to next fragment (Slider Fragment)
     *@loadMorePhotos is realisation loading new data(photos). This method perform when loaded last ViewHolder;
     */
    class ConnectToSlider implements SupportInterface {
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

        @Override
        public void loadMorePhotos() {
                App.getApi().getAllPhotos(Integer.parseInt(VKAccessToken.currentToken().userId),1,
                        PhotoListClass.getPhotoList().size(),
                        VKAccessToken.currentToken().accessToken,
                        BuildConfig.VERSION).enqueue(new Callback<Root>() {
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
    }
