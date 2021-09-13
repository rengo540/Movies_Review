package com.example.moviesreview.data.local;

import android.content.Context;

import com.example.moviesreview.data.model.db.Movie_db;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


@Database(entities = {Movie_db.class} , version = 1)
abstract public class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance ;
    public abstract Dao dao ();

    public static MovieDatabase getInstance(Context context){

        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),MovieDatabase.class,"movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
