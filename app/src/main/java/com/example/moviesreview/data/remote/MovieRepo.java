package com.example.moviesreview.data.remote;

import android.content.Context;

import com.example.moviesreview.data.local.MovieDatabase;
import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.model.db.Movie_db;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {

   public MutableLiveData<ResultMovieData> movies ;
   public MutableLiveData<Movie_Api> selectedMovie ;
    public MutableLiveData<ResultMovieData> Smovies ;
    public MutableLiveData<List<Movie_db>> favMovies ;

   private static MovieRepo Instance ;





   public MovieRepo(){
       movies = new MutableLiveData<>();
       selectedMovie=new MutableLiveData<>();
       Smovies=new MutableLiveData<>();
       favMovies=new MutableLiveData<>();

   }


   public static MovieRepo getInstance(){
       if(Instance==null){
           Instance =new MovieRepo();

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


    public MutableLiveData<Movie_Api> getSelectedMovie(int id){
        ApiClient.getInstance().getMovieDetails(id).enqueue(new Callback<Movie_Api>() {
            @Override
            public void onResponse(Call<Movie_Api> call, Response<Movie_Api> response) {
                selectedMovie.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie_Api> call, Throwable t) {

            }
        });
        return selectedMovie ;
    }


    public MutableLiveData<ResultMovieData> getSimilarMovies (int id){
       ApiClient.getInstance().getSimilarMovies(id).enqueue(new Callback<ResultMovieData>() {
           @Override
           public void onResponse(Call<ResultMovieData> call, Response<ResultMovieData> response) {
               Smovies.setValue(response.body());
           }

           @Override
           public void onFailure(Call<ResultMovieData> call, Throwable t) {

           }
       });

       return Smovies;
    }


    public void insertFavouriteMovie (Context context,int id , String title,String poster )
    {
        MovieDatabase.getInstance(context).dao().insert(new Movie_db(id,title,poster)).
                subscribeOn(Schedulers.computation()).
                subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }

    public MutableLiveData<List<Movie_db>> getAllFavMovies (Context context){

       MovieDatabase.getInstance(context).dao().getAllMovies().subscribeOn(Schedulers.computation())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new SingleObserver<List<Movie_db>>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onSuccess(@NonNull List<Movie_db> movie_dbs) {
                       favMovies.setValue(movie_dbs);

                   }

                   @Override
                   public void onError(@NonNull Throwable e) {

                   }
               });

        return favMovies;

    }

    public void deleteFromFav (Context context,int id ){
       MovieDatabase.getInstance(context).dao().delete(id).subscribeOn(Schedulers.computation())
               .subscribe(new CompletableObserver() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onComplete() {

                   }

                   @Override
                   public void onError(@NonNull Throwable e) {

                   }
               });

    }




}
