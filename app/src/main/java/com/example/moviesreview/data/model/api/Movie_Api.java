package com.example.moviesreview.data.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie_Api {

    /*private String title ;
    private int id;
    private String status ;
    private double vote_average;
    private long budget ;
    private String backdrop_path;
*/


    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("status")
    private  String status ;
    @SerializedName("genres")
    private List<Genres> genres;
    @SerializedName("vote_average")
    private float vote_average ;


    public Movie_Api()
    {}

   public List<Genres> getGenres (){
        return genres;
   }

    public String getOriginalTitle() {
         return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getStatus() {
        return status;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getVote_average() {
        return vote_average;
    }
}
