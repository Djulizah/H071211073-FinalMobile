package com.example.h071211073_finalmobile;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Bookmark implements Parcelable {
    private int id;
    private String posterPath;
    private String title;
    private String date;
    private double voteAverage;
    private String backdropPath;
    private String overview;

    public Bookmark(int id, String posterPath, String title, String date, double voteAverage, String backdropPath, String overview) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.date = date;
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

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    protected Bookmark(Parcel in) {
        id = in.readInt();
        posterPath = in.readString();
        title = in.readString();
        date = in.readString();
        voteAverage = in.readDouble();
        backdropPath = in.readString();
        overview = in.readString();
    }

    public static final Creator<Bookmark> CREATOR = new Creator<Bookmark>() {
        @Override
        public Bookmark createFromParcel(Parcel in) {
            return new Bookmark(in);
        }

        @Override
        public Bookmark[] newArray(int size) {
            return new Bookmark[size];
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
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeDouble(voteAverage);
        parcel.writeString(backdropPath);
        parcel.writeString(overview);
    }
}
