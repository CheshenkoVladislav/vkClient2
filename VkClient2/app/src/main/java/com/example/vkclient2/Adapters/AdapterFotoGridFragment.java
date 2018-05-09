package com.example.vkclient2.Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vkclient2.Data.Photos.Items;
import com.example.vkclient2.Fragment.FotoGridFragment;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;
import com.example.vkclient2.SupportInterfaces.OnClickHolder;

import java.util.List;

public class AdapterFotoGridFragment extends RecyclerView.Adapter<AdapterFotoGridFragment.FotoHolder> {
    private static final String TAG = "AdapterFotoGridFragment";
    private List<Integer> resInts;
    private List<Items>itemList;
    private OnClickHolder clickHandler;
    private Fragment fragment;
    public void setClickHandler(OnClickHolder clickHandler) {this.clickHandler = clickHandler;}

    public AdapterFotoGridFragment(List<Integer> resInts,Fragment fragment){
        this.resInts = resInts;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public FotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new FotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return resInts.size();
    }

    //Adapter public methods
    public void setItems(List<Items>itemList){
//        Log.d(TAG, "ITEMS LIST: " + itemList.size());
        this.itemList = itemList;
    }

    /**********************HOLDER***********************************/

    class FotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView cardImage;
        int position;
        FotoHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
            itemView.setOnClickListener(this);
        }
        void bind(int position){
            this.position = position;
            setImage();
            cardImage.setTransitionName(String.valueOf(resInts.get(position)));
        }
        private void setImage(){
            cardImage.setImageResource(resInts.get(position));
            fragment.startPostponedEnterTransition();
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: ");
            MainActivity.currentFragmentNumber = getAdapterPosition();
            clickHandler.openSlider(getAdapterPosition(),v);
        }
    }
}
