package com.example.vkclient2.SupportInterfaces;


import com.example.vkclient2.Data.Friends.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkApi {
    @GET("photos.getAll")
    Call<com.example.vkclient2.Data.Photos.Root> getAllPhotos(@Query("owner_id")int id, @Query("extended")int ext,
                                                              @Query("offset")int offset, @Query("access_token")String token,
                                                              @Query("v")String version);

    @GET("friends.get")
    Call<Root> getFriendList (@Query("fields")String fields,
                                  @Query("order")String order,
                                  @Query("access_token")String token,
                                  @Query("v")String version);
}
