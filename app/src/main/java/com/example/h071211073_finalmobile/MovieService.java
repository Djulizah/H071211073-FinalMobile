package com.example.h071211073_finalmobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(
      @Query("api_key") String apiKey
    );
}
