package com.example.vkclient2.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkclient2.Adapters.AdapterSliderPager;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;

import java.util.List;

public class SliderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewPager pager = (ViewPager) inflater.inflate(R.layout.fragment_slider,container,false);
        AdapterSliderPager adapter = new AdapterSliderPager(getActivity().getSupportFragmentManager());
        foolViewPager(pager,adapter, MainActivity.currentFragmentNumber,getActivity().getSupportFragmentManager());
        return pager;
    }
    public static void foolViewPager(ViewPager pager, FragmentPagerAdapter adapter, int position, FragmentManager fm) {
        pager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return null;
            }
            @Override
            public int getCount() {
                return 0;
            }
        });
        pager.setCurrentItem(position, true);
        pager.setAdapter(adapter);
        pager.setCurrentItem(position);
    }
}
