package com.example.moviesreview.data.local;


import com.example.moviesreview.data.model.db.Movie_db;

import java.util.List;

import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@androidx.room.Dao
public interface Dao {

    @Insert
     Completable insert (Movie_db movie_db);

    @Query("select * from movie_table")
    Single<List<Movie_db>> getAllMovies ();


}
