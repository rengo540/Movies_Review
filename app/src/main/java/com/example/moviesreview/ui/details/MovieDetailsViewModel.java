package com.example.moviesreview.ui.details;

import android.content.Context;

import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.remote.MovieRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailsViewModel extends ViewModel {

int id ;
    public void setid (int id){

        this.id=id ;
    }

    public LiveData<Movie_Api> getSelectedMovie (){
        return MovieRepo.getInstance().getSelectedMovie(id);
    }

    public LiveData<ResultMovieData> getSimilarMovies (){
        return MovieRepo.getInstance().getSimilarMovies(id);
    }

    public void insertToFavourite (Context context,int id ,String title,String poster){
        MovieRepo.getInstance().insertFavouriteMovie(context,id,title,poster);
    }

    public void deleteFavMovie (Context context,int id ){
        MovieRepo.getInstance().deleteFromFav(context,id);
    }
}
