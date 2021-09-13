package com.example.moviesreview.data.model.db;

import com.example.moviesreview.data.model.api.Genres;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie_db  {

    @PrimaryKey(autoGenerate = false)
    private int id;

    private String title;
    private String posterPath;



    public Movie_db(int id, String title,String posterPath) {
        this.id = id;
        this.title = title;
        this.posterPath=posterPath;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
