package com.example.vkclient2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
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
import com.example.vkclient2.Fragment.FotoGridFragment;
import com.vk.sdk.VKSdk;

import java.util.ArrayList;
import java.util.List;

/**
 * !!!TODO: SORRY FOR MY ENGLISH!!!
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static TextView textView;
    private ListView friendList;

    public static int currentFragmentNumber;

    private static Fragment currentFragment;
    public static Fragment getCurrentFragment() {return currentFragment;}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = findViewById(R.id.categoryTextView);
        friendList = findViewById(R.id.friend_list);

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
        textView.setOnClickListener(new View.OnClickListener() {
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
        //init friendList
        AdapterFriendList adapterFriendList = new AdapterFriendList(this,initFriends());
        friendList.setAdapter(adapterFriendList);

        //Transaction on start Fragment
        FotoGridFragment startFragment = new FotoGridFragment();
        currentFragment = startFragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer,startFragment)
                .commit();
    }


    /**
     * Init local friendList
     */
    private List<Friend> initFriends() {
        List<Friend>friendList = new ArrayList<>();
        friendList.add(new Friend("Vasya"));
        friendList.add(new Friend("Petya"));
        friendList.add(new Friend("Alyosha"));
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
                        currentFragment = newFragmentFoto;
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer,newFragmentFoto)
                            .commit();
                        return true;
                    case R.id.nav_video :
                        Log.d(TAG, "onMenuItemClick: 2");
                        FotoGridFragment newFragmentVideo = new FotoGridFragment();
                        currentFragment = newFragmentVideo;
                        getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer,newFragmentVideo)
                            .commit();
                        return true;
                    case R.id.nav_news_feed :
                        Log.d(TAG, "onMenuItemClick: 3");
                        FotoGridFragment newFragmentNews = new FotoGridFragment();
                        currentFragment = newFragmentNews;
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
