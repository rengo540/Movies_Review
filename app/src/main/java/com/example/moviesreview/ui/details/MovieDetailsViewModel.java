package com.example.moviesreview.ui.details;

import android.content.Context;

import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.remote.ApiRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailsViewModel extends ViewModel {

int id ;
    public void setid (int id){

        this.id=id ;
    }

    public LiveData<Movie_Api> getSelectedMovie (){
        return ApiRepo.getInstance().getSelectedMovie(id);
    }

    public LiveData<ResultMovieData> getSimilarMovies (){
        return ApiRepo.getInstance().getSimilarMovies(id);
    }

    public void insertToFavourite (Context context,int id ,String title){
        ApiRepo.getInstance().insertFavouriteMovie(context,id,title);
    }
}
