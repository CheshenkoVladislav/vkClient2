package com.example.vkclient2.Data.Photos;

import java.util.List;

public class Response {
    private int count;

    private List<Items> items;

    private int more;

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setItems(List<Items> items){
        this.items = items;
    }
    public List<Items> getItems(){
        return this.items;
    }
    public void setMore(int more){
        this.more = more;
    }
    public int getMore(){
        return this.more;
    }
}
