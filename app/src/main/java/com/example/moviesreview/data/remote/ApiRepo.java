package com.example.moviesreview.data.remote;

import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepo {

   public MutableLiveData<ResultMovieData> movies ;

   private static ApiRepo Instance ;


   public ApiRepo (){
       movies = new MutableLiveData<>();

   }


   public static ApiRepo getInstance(){
       if(Instance==null){
           Instance =new ApiRepo();

       }
       return  Instance;
   }



   public MutableLiveData<ResultMovieData> getNowPlayingMovies(){
       ApiClient.getInstance().getNowPlayingMovies().enqueue(new Callback<ResultMovieData>() {
           @Override
           public void onResponse(Call<ResultMovieData> call, Response<ResultMovieData> response) {
               movies.setValue(response.body());
           }

           @Override
           public void onFailure(Call<ResultMovieData> call, Throwable t) {

           }
       });

return movies;
   }

    public MutableLiveData<ResultMovieData> getPopularMovies(){
        ApiClient.getInstance().getPopular().enqueue(new Callback<ResultMovieData>() {
            @Override
            public void onResponse(Call<ResultMovieData> call, Response<ResultMovieData> response) {
                movies.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResultMovieData> call, Throwable t) {

            }
        });

        return movies;
    }


    public MutableLiveData<ResultMovieData> getTopRatedMovies(){
        ApiClient.getInstance().getTopRated().enqueue(new Callback<ResultMovieData>() {
            @Override
            public void onResponse(Call<ResultMovieData> call, Response<ResultMovieData> response) {
                movies.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResultMovieData> call, Throwable t) {

            }
        });

        return movies;
    }






}
