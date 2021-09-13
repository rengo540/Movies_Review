package com.example.moviesreview.ui.Popular;

import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.remote.MovieRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class PopularViewModel extends ViewModel {



    public LiveData<ResultMovieData> getPop (){

        return    MovieRepo.getInstance().getPopularMovies();

    }
}
