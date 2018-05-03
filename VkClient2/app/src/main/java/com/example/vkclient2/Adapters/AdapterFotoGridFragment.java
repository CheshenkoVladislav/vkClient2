package com.example.vkclient2.Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;
import com.example.vkclient2.SupportInterfaces.OnClickHolder;

import java.util.List;

public class AdapterFotoGridFragment extends RecyclerView.Adapter<AdapterFotoGridFragment.FotoHolder> {
    private static final String TAG = "AdapterFotoGridFragment";
    List<Integer> resInts;

    private OnClickHolder clickHandler;
    public void setClickHandler(OnClickHolder clickHandler) {this.clickHandler = clickHandler;}

    public AdapterFotoGridFragment(List<Integer>resInts){
        this.resInts = resInts;
    }

    @NonNull
    @Override
    public FotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new FotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoHolder holder, @SuppressLint("RecyclerView") int position) {
        MainActivity.currentFragmentNumber = position;
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return resInts.size();
    }

    class FotoHolder extends RecyclerView.ViewHolder{
        ImageView cardImage;
        FotoHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
        }
        void bind(int position){
            cardImage.setImageResource(resInts.get(position));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick FOTOHOLDER");
                    clickHandler.openSlider(v);
                }
            });
        }
    }
}
