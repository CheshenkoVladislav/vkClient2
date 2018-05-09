package com.example.vkclient2.Data;

import java.util.ArrayList;
import java.util.List;

public class PhotoListClass {
    private static List<PhotoClass> photoList = new ArrayList<>();
    public static List<PhotoClass> getPhotoList() {
        return photoList;
    }
    public static void setPhotoList(List<PhotoClass> photoList) {
        PhotoListClass.photoList = photoList;
    }
}
