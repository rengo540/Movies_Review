package com.example.moviesreview.data.remote;

import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("movie/now_playing")
    public Call<ResultMovieData> getNowPlayingMovies (@Query("api_key") String apiKey,
                                                      @Query("language") String language,
                                                      @Query("page") int page);

    @GET("movie/popular")
    public Call<ResultMovieData> getPopularMovies(@Query("api_key") String apiKey,
                                                        @Query("language") String language,
                                                        @Query("page") int page);

    @GET("movie/top_rated")
    public Call<ResultMovieData> getTopRatedMovies(@Query("api_key") String apiKey,
                                                  @Query("language") String language,
                                                  @Query("page") int page);

    @GET("movie/{movie_id}")
    public Call<Movie_Api> getMovieDetails( @Path("movie_id") int id
                                           , @Query("api_key") String apiKey,
                                            @Query("language") String language);

}
