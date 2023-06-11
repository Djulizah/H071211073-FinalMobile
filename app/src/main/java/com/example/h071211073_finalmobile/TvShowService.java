package com.example.h071211073_finalmobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvShowService {
    @GET("tv/top_rated")
    Call<TvShowResponse> getTopRatedTvShows(
            @Query("api_key") String apiKey
    );
}
