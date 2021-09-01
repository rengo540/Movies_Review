package com.example.moviesreview.data.model.api;

import com.google.gson.annotations.SerializedName;


public class Genres {

    @SerializedName("id")
    private int id ;
    @SerializedName("name")
    private String name ;


    public Genres (){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
