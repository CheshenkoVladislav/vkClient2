package com.example.vkclient2.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkclient2.Adapters.AdapterFotoGridFragment;
import com.example.vkclient2.Data.Images;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;
import com.example.vkclient2.SupportInterfaces.OnClickHolder;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class FotoGridFragment extends Fragment {
    AdapterFotoGridFragment adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String categoryName = "Photo";
        MainActivity.textView.setText(categoryName);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_foto_grid,container,false);
        Images.resInts = initData();
        adapter = new AdapterFotoGridFragment(Images.resInts);
        adapter.setClickHandler(new ConnectToSlider());
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public List<Integer> initData(){
        List<Integer>resInts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i == 15)resInts.add(R.drawable.ic_menu_gallery);
            else resInts.add(R.drawable.ic_menu_camera);
        }
        return resInts;
    }

    class ConnectToSlider implements OnClickHolder{
        @Override
        public void openSlider(int position) {
            Log.d(TAG, "openSlider: GO FRAGMENT");
            SliderFragment slider = new SliderFragment();
            if (getFragmentManager() != null) {
                Log.d(TAG, "openSlider: TRANSACTION: " + getFragmentManager());
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
//                    .addSharedElement(view,"trans")
                        .replace(R.id.fragmentContainer, slider)
                        .commit();
            }
//            slider.setSharedElementEnterTransition(new CustomTransition());
//            slider.setEnterTransition(new Fade());
//            ft.commit();
        }
    }
}
