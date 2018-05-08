package com.example.vkclient2;

import android.app.Application;
import android.content.Intent;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

public class App extends Application{
    //Invalid token handler
    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                // VKAccessToken is invalid
            }
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(getApplicationContext());
        if (!VKSdk.isLoggedIn()){
            loggIn();}
    }
    private void loggIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}

