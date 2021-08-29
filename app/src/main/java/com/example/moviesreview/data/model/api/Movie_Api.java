package com.example.moviesreview.data.model.api;

import com.google.gson.annotations.SerializedName;

public class Movie_Api {

    /*private String title ;
    private int id;
    private String status ;
    private double vote_average;
    private long budget ;
    private String backdrop_path;
*/


    @SerializedName("id")
    private Integer id;
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


    public Movie_Api()
    {}

    public Movie_Api(String title , int id,String backdrop_path){
        this.title=title;
        this.id=id;
        this.backdrop_path=backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
