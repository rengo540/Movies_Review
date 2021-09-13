package com.example.moviesreview.ui.NowPlaying;

import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.remote.MovieRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NowPlayingViewModel extends ViewModel {


    public NowPlayingViewModel(){

    }

    public LiveData<ResultMovieData> getMovies (){
      return   MovieRepo.getInstance().getNowPlayingMovies();

    }


}
