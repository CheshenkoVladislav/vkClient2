package com.example.vkclient2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vkclient2.Data.Friend;
import com.example.vkclient2.Data.Friends.Items;
import com.example.vkclient2.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterFriendList extends BaseAdapter {
    private List<Friend> friendList = new ArrayList<>();
    private Context context;

    public AdapterFriendList(Context context) {
        this.context = context;
        friendList.add(0, new Friend("Заголовок"));
    }
    public void setFriendList(List<Items>itemsList){
        for (int i = 0; i < itemsList.size(); i++) {
            friendList.add(new Friend(convertToFriendName(itemsList.get(i))));
        }
    }

    private String convertToFriendName(Items items) {
        return items.getFirst_name() + " " +
                items.getLast_name();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else return 1;
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 0)return false;
        else return true;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_header, parent, false);
            convertView.isEnabled();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);
            TextView text = convertView.findViewById(R.id.textItem);
            text.setText(friendList.get(position).textView);
        }
        return convertView;
    }
}
