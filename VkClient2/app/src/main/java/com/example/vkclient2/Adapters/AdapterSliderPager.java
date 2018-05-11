package com.example.vkclient2.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vkclient2.Data.StaticClasses.PhotoListClass;
import com.example.vkclient2.Fragment.BigFotoFragment;

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
