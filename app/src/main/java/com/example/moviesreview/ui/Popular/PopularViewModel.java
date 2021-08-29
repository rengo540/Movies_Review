package com.example.moviesreview.ui.Popular;

import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.remote.ApiClient;
import com.example.moviesreview.data.remote.ApiRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularViewModel extends ViewModel {



    public LiveData<ResultMovieData> getPop (){

        return    ApiRepo.getInstance().getPopularMovies();

    }
}
