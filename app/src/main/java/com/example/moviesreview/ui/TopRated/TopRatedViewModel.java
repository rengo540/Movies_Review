package com.example.moviesreview.ui.TopRated;

import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.remote.MovieRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TopRatedViewModel extends ViewModel {


    public LiveData<ResultMovieData> getTopRatedData (){

       return MovieRepo.getInstance().getTopRatedMovies();
    }
}
