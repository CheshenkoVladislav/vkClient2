package com.example.vkclient2.SupportInterfaces;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VkApi {
    @GET("photos.getAll")
    Call<> getAllPhotos()
}
