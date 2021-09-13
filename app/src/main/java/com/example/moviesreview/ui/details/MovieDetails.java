package com.example.moviesreview.ui.details;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesreview.R;
import com.example.moviesreview.data.local.MovieDatabase;
import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.model.db.Movie_db;
import com.example.moviesreview.data.remote.ApiClient;
import com.example.moviesreview.ui.FavouriteAdapter;
import com.example.moviesreview.ui.MainAdapter;
import com.example.moviesreview.ui.NowPlaying.NowPlayingViewModel;
import com.example.moviesreview.ui.SimilarMoviesAdapter;
import com.example.moviesreview.ui.favourite.FavouriteFragment;
import com.example.moviesreview.ui.favourite.FavouriteViewModel;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity {

  public int movieId;
TextView movieTitle ;
TextView status ;
TextView overview ;
TextView genres ;
ImageView imageView;
RatingBar ratingBar ;
Button favBtn ;
boolean isPlay ;
RecyclerView similarMoviesRecycler;

SimilarMoviesAdapter similarMoviesAdapter;
    MovieDetailsViewModel movieDetailsViewModel;
    FavouriteViewModel favouriteViewModel;


List<Movie_db> favList ;
    Movie_Api movieApi ;
    String tt="reeengo";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);




        //get movie id that user choice
        movieId =getIntent().getIntExtra("idMovie",0);


        //inflate views
        movieTitle =findViewById(R.id.d_movieTitle);
        status =findViewById(R.id.movieStatus);
        overview=findViewById(R.id.overView);
        imageView=findViewById(R.id.backdrop_img);
        ratingBar=findViewById(R.id.ratingBar);
        genres =findViewById(R.id.movieGeners);
        favBtn =findViewById(R.id.favBtn);


        movieApi=new Movie_Api();
favList=new ArrayList<>();


        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.setid(movieId);

        movieDetailsViewModel.getSelectedMovie();




        movieDetailsViewModel.getSelectedMovie().observe(this, new Observer<Movie_Api>() {
            @Override
            public void onChanged(Movie_Api movie_api) {
                movieApi=movie_api;

                movieTitle.setText(movie_api.getTitle());
                status.setText(movie_api.getStatus());
                overview.setText(movie_api.getOverview());
                Picasso.Builder builder=new Picasso.Builder(getApplicationContext());
                builder.downloader(new OkHttp3Downloader(getApplicationContext()));
                builder.build().load(ApiClient.getInstance().getImgUrl() +  movie_api.getBackdrop_path())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(imageView);
                ratingBar.setRating(movie_api.getVote_average()/3);
                int i =1;
               genres.setText(movie_api.getGenres().get(0).getName());



            }
        });






/*  this give me error
movieDetailsViewModel.getSimilarMovies().observe(this, new Observer<ResultMovieData>() {
    @Override
    public void onChanged(ResultMovieData resultMovieData) {

        list=resultMovieData.getResults();
    }
});
 */





        favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);

        favouriteViewModel.getFavMovies(this);


        favouriteViewModel.getFavMovies(this).observe(this, new Observer<List<Movie_db>>() {
            @Override
            public void onChanged(List<Movie_db> movie_dbs) {
                favList=movie_dbs;

            }
        });




        favBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                //check if movie there was in favourites or not
                for(int i=0; i<favList.size() ;i++){
                    if(favList.get(i).getId()==movieId) {

                        isPlay=true;
                        break;
                    }
                    else {
                        isPlay=false;
                    }
                }


               if(isPlay)
               {
                   movieDetailsViewModel.deleteFavMovie(getApplicationContext(),movieId);
                   Toast.makeText(getApplicationContext(), "remove from favourite", Toast.LENGTH_SHORT).show();

               }else {
                   movieDetailsViewModel.insertToFavourite(getApplicationContext(), movieId, movieApi.getTitle(),movieApi.getPosterPath());
                   Toast.makeText(getApplicationContext(),"added to favouite", Toast.LENGTH_SHORT).show();
               }
            }
        });



    }
}