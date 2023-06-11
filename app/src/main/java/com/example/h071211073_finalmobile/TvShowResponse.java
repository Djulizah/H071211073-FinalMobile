package com.example.h071211073_finalmobile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {
    @SerializedName("results")
    private List<TvShow> tvShowList;

    public List<TvShow> getTvShowList() {
        return tvShowList;
    }
}
