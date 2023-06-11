package com.example.h071211073_finalmobile;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvShow implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("name")
    private String name;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("overview")
    private String overview;

    public TvShow(int id, String posterPath, String name, String firstAirDate, Double voteAverage, String backdropPath, String overview) {
        this.id = id;
        this.posterPath = posterPath;
        this.name = name;
        this.firstAirDate = firstAirDate;
        this.voteAverage = voteAverage;
        this.backdropPath = backdropPath;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getName() {
        return name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    protected TvShow(Parcel in) {
        id = in.readInt();
        posterPath = in.readString();
        name = in.readString();
        firstAirDate = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        backdropPath = in.readString();
        overview = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(posterPath);
        parcel.writeString(name);
        parcel.writeString(firstAirDate);
        if (voteAverage == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(voteAverage);
        }
        parcel.writeString(backdropPath);
        parcel.writeString(overview);
    }
}
