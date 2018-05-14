package com.example.vkclient2.Data.StaticClasses;

import com.example.vkclient2.Data.PhotoClass;

import java.util.ArrayList;
import java.util.List;

public class PhotoListClass {
    //Quantity
    private static int photoQuantity;
    public static int getPhotoQuantity() {return photoQuantity;}
    public static void setPhotoQuantity(int photoQuantity) {PhotoListClass.photoQuantity = photoQuantity;}
    //PhotoList
    private static List<PhotoClass> photoList = new ArrayList<>();
    public static List<PhotoClass> getPhotoList() {
        return photoList;
    }
    public static void clearPhotoList(){
        PhotoListClass.photoList = new ArrayList<>();
        photoQuantity = 0;
    }
}
