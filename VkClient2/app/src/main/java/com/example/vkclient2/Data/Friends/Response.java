package com.example.vkclient2.Data.Friends;

import java.util.List;

public class Response {
    private int count;
    private List<Items> items;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Items> getItemsList() {
        return items;
    }

    public void setItemsList(List<Items> itemsList) {
        this.items = items;
    }
}