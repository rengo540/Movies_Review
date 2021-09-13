package com.example.moviesreview.ui.favourite;

import android.content.Context;

import com.example.moviesreview.data.model.db.Movie_db;
import com.example.moviesreview.data.remote.MovieRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FavouriteViewModel extends ViewModel {


    public LiveData<List<Movie_db>> getFavMovies (Context context){

        return MovieRepo.getInstance().getAllFavMovies(context);
    }


}
