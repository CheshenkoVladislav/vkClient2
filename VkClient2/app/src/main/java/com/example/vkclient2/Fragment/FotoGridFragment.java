package com.example.vkclient2.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
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
        return inflater.inflate(R.layout.fragment_foto_grid,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        Images.resInts = initData();
        adapter = new AdapterFotoGridFragment(Images.resInts);
        adapter.setClickHandler(new ConnectToSlider());
        recyclerView.setAdapter(adapter);
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
        public void openSlider(View view) {
            SliderFragment slider = new SliderFragment();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
//                    .addSharedElement(view,"trans")
                    .replace(R.id.fragmentContainer,slider)
                    .commit();

//            slider.setSharedElementEnterTransition(new CustomTransition());
//            slider.setEnterTransition(new Fade());
//            ft.commit();
        }
    }
}
