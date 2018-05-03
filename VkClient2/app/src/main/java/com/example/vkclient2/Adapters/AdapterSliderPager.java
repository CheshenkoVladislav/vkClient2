package com.example.vkclient2.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.vkclient2.Data.Images;
import com.example.vkclient2.Fragment.BigFotoFragment;
import java.util.List;

public class AdapterSliderPager extends FragmentPagerAdapter {
    List<Integer>resInts;
    private static final String TAG = "AdapterSliderPager";
    public AdapterSliderPager(FragmentManager fm) {
        super(fm);
        resInts = Images.resInts;
    }

    @Override
    public Fragment getItem(int position) {
        BigFotoFragment fragment = new BigFotoFragment();
        Log.d(TAG, "getItem : " + resInts.get(position));
        Log.d(TAG, "getItem SIZE: " + resInts.size());
        fragment.setImageUrl(resInts.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return resInts.size();
    }
}
