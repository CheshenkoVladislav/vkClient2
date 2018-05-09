package com.example.vkclient2.SupportInterfaces;

import com.example.vkclient2.Data.Photos.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkApi {
    @GET("photos.getAll")
    Call<Root> getAllPhotos(@Query("owner_id")int id, @Query("extended")int ext,
                            @Query("offset")int offset, @Query("access_token")String token,
                            @Query("v")String version);
}
