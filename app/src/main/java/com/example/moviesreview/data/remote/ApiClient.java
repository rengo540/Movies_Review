package com.example.moviesreview.data.remote;

import android.widget.ImageView;

import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

private static String BaseUrl ="https://api.themoviedb.org/3/";
private static String ApiKey ="f5b7308030cddd009f9fd319927c397f";
private static String ImgUrl = "https://image.tmdb.org/t/p/w500";
private ApiService apiService ;
private static ApiClient Instance ;
 Retrofit retrofit;



public ApiClient (){
    retrofit =new Retrofit.Builder().
             baseUrl(BaseUrl).
             addConverterFactory(GsonConverterFactory.create()).
             build();

    apiService = retrofit.create(ApiService.class);

}



public static ApiClient getInstance (){

    if(Instance==null){
        Instance = new ApiClient();
    }
    return Instance;
}

public String getImgUrl(){
    return ImgUrl;
}

public Call<ResultMovieData> getNowPlayingMovies (){  return apiService.getNowPlayingMovies(ApiKey,"en-US",1);
}

public Call<ResultMovieData> getPopular (){return  apiService.getPopularMovies(ApiKey,"en-US",1);}

public Call<ResultMovieData> getTopRated (){return  apiService.getTopRatedMovies(ApiKey,"en-US",1);}

public Call<Movie_Api> getMovieDetails (int id){ return  apiService.getMovieDetails(id,ApiKey, "en_US" ); }

public Call<ResultMovieData> getSimilarMovies (int id){ return  apiService.getSimilarMovies(id,ApiKey,"en-Us",1); }


}
