package com.example.moviesreview.ui.NowPlaying;

import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.remote.ApiClient;
import com.example.moviesreview.data.remote.ApiRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingViewModel extends ViewModel {


    public NowPlayingViewModel(){

    }

    public LiveData<ResultMovieData> getMovies (){
      return   ApiRepo.getInstance().getNowPlayingMovies();

    }


}
