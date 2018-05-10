package com.example.vkclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vkclient2.Adapters.AdapterFriendList;
import com.example.vkclient2.Data.Friend;
import com.example.vkclient2.Data.Friends.Root;
import com.example.vkclient2.Fragment.FotoGridFragment;
import com.example.vkclient2.SupportClasses.App;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * !!!TODO: SORRY FOR MY ENGLISH!!!
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public TextView categoryTextView;
    public TextView friendNameTextView;
    private ListView friendList;
    public static int currentFragmentNumber;
    private AdapterFriendList adapterFriendList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        categoryTextView = findViewById(R.id.categoryTextView);
        friendNameTextView = findViewById(R.id.friendNameTextView);

        friendList = findViewById(R.id.friend_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FAB Handler
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        categoryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick CATEGORY");
                showPopupMenu(v);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!VKSdk.isLoggedIn()){
            loggIn();}
            else initStartFragment();
    }
    private void loggIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    private void initStartFragment() {
        //initFriendList
        adapterFriendList = new AdapterFriendList(this);
        initFriends();
        friendList.setAdapter(adapterFriendList);
        //Transaction on start Fragment
        FotoGridFragment startFragment = new FotoGridFragment();
        startFragment.setUserId(Integer.parseInt(VKAccessToken.currentToken().userId));
//        startFragment.setUserName();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer,startFragment)
                .commit();
    }


    /**
     * Init local friendList
     */
    private List<Friend> initFriends() {
        String FIELDS = "first_name,last_name";
        String ORDER = "name";
        App.getApi().getFriendList(FIELDS,ORDER,VKAccessToken.currentToken().accessToken,
                BuildConfig.VERSION).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                adapterFriendList.setFriendList(response.body().getResponse().getItemsList());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
        List<Friend>friendList = new ArrayList<>();
        return friendList;
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_photo :
                        Log.d(TAG, "onMenuItemClick: 1");
                        FotoGridFragment newFragmentFoto = new FotoGridFragment();
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer,newFragmentFoto)
                            .commit();
                        return true;
                    case R.id.nav_video :
                        Log.d(TAG, "onMenuItemClick: 2");
                        FotoGridFragment newFragmentVideo = new FotoGridFragment();
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer,newFragmentVideo)
                            .commit();
                        return true;
                    case R.id.nav_news_feed :
                        Log.d(TAG, "onMenuItemClick: 3");
                        FotoGridFragment newFragmentNews = new FotoGridFragment();
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer,newFragmentNews)
                            .commit();
                        return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
