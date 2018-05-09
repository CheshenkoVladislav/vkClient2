package com.example.vkclient2.Adapters;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.SharedElementCallback;
import android.util.Log;

import com.example.vkclient2.Data.Images;
import com.example.vkclient2.Data.PhotoListClass;
import com.example.vkclient2.Fragment.BigFotoFragment;
import java.util.List;

public class AdapterSliderPager extends FragmentStatePagerAdapter {
    private static final String TAG = "AdapterSliderPager";

    public AdapterSliderPager(Fragment fragment) {
        super(fragment.getChildFragmentManager());
    }

    @Override
    public Fragment getItem(int position) {
        return BigFotoFragment.newInstance(
                PhotoListClass.getPhotoList().get(position).getBigPhoto(),
                PhotoListClass.getPhotoList().get(position).getSmallPhoto());
    }

    @Override
    public int getCount() {
        return PhotoListClass.getPhotoList().size();
    }

}
