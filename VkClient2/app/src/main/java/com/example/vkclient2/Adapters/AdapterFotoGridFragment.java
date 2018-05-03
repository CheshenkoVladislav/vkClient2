package com.example.vkclient2.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vkclient2.R;

public class AdapterFotoGridFragment extends RecyclerView.Adapter<AdapterFotoGridFragment.FotoHolder> {
    private static final String TAG = "AdapterFotoGridFragment";

    @NonNull
    @Override
    public FotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new FotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class FotoHolder extends RecyclerView.ViewHolder{
        ImageView cardImage;
        public FotoHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
        }
        void bind(int position){
            cardImage.setImageResource(R.drawable.ic_menu_camera);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick FOTOHOLDER");

                }
            });
        }
    }
}
