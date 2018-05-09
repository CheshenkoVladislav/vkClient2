package com.example.vkclient2.Data.Photos;

public class Sizes {
    private String src;

    private int width;

    private int height;

    private String type;

    public void setSrc(String src){
        this.src = src;
    }
    public String getSrc(){
        return this.src;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public int getWidth(){
        return this.width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return this.height;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
