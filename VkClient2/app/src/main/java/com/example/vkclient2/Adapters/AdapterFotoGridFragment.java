package com.example.vkclient2.Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vkclient2.Data.PhotoClass;
import com.example.vkclient2.Data.StaticClasses.PhotoListClass;
import com.example.vkclient2.Data.Photos.Items;
import com.example.vkclient2.MainActivity;
import com.example.vkclient2.R;
import com.example.vkclient2.SupportClasses.CropSquareTransformation;
import com.example.vkclient2.SupportInterfaces.SupportInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFotoGridFragment extends RecyclerView.Adapter<AdapterFotoGridFragment.FotoHolder> {
    private static final String TAG = "AdapterFotoGridFragment";
    private SupportInterface clickHandler;
    private Fragment fragment;
    public void setClickHandler(SupportInterface clickHandler) {this.clickHandler = clickHandler;}

    public AdapterFotoGridFragment(Fragment fragment){
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
        if (position == PhotoListClass.getPhotoList().size()-1)
            clickHandler.loadMorePhotos();
        //Hide/Show fab handler
        FloatingActionButton fab = ((MainActivity)fragment.getActivity()).getFab();
        if (position > 50) fab.show();
        else fab.hide();
    }

    @Override
    public int getItemCount() {
        return PhotoListClass.getPhotoList().size();
    }

    //Adapter public methods
    public void setPhotos(List<Items> itemList){
        for (int i = 0; i < itemList.size(); i++) {
            PhotoClass photo = new PhotoClass();
            photo.setSmallPhoto(itemList.get(i).getPhoto_604());
            if (itemList.get(i).getPhoto_2560() != null)
                photo.setBigPhoto(itemList.get(i).getPhoto_2560());
            else if (itemList.get(i).getPhoto_1280() != null)
                photo.setBigPhoto(itemList.get(i).getPhoto_1280());
            else if (itemList.get(i).getPhoto_807() != null)
                photo.setBigPhoto(itemList.get(i).getPhoto_807());
            else photo.setBigPhoto(itemList.get(i).getPhoto_604());
            PhotoListClass.getPhotoList().add(photo);
            notifyItemChanged(i);
        }
        Log.d(TAG, "SMALL PHOTOS: " + PhotoListClass.getPhotoList().size());
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
            Picasso.get()
                    .load(PhotoListClass.getPhotoList().get(position).getSmallPhoto())
                    .transform(new CropSquareTransformation())
                    .into(cardImage);
            cardImage.setTransitionName(String.valueOf(PhotoListClass.getPhotoList()
                    .get(position).getSmallPhoto()));
            fragment.startPostponedEnterTransition();
        }
        @Override
        public void onClick(View v) {
            MainActivity.currentPosition = getAdapterPosition();
            clickHandler.openSlider(getAdapterPosition(),v);
        }
    }
}
