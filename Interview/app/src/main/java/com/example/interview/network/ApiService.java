package com.example.interview.network;



import com.example.interview.dto.ImageDTO;

import java.util.SplittableRandom;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/")
    Call<ImageDTO> getData(@Query("key") String key,
                           @Query("q") String q,
                           @Query("image_type") String imageType,
                           @Query("pretty") String pretty);
}
