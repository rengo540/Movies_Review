package com.example.moviesreview.ui.details;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.schedulers.Schedulers;

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
import com.example.moviesreview.data.remote.ApiClient;
import com.example.moviesreview.ui.MainAdapter;
import com.example.moviesreview.ui.NowPlaying.NowPlayingViewModel;
import com.example.moviesreview.ui.SimilarMoviesAdapter;
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
boolean isPlay=false ;
RecyclerView similarMoviesRecycler;

SimilarMoviesAdapter similarMoviesAdapter;
    MovieDetailsViewModel movieDetailsViewModel;


    List<Movie_Api> list;

    Movie_Api movieApi ;
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


        list=new ArrayList<>();
        movieApi=new Movie_Api();


        similarMoviesRecycler = findViewById(R.id.similarMoviesRecycler);
        similarMoviesAdapter =new SimilarMoviesAdapter(this);
        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        similarMoviesRecycler.setLayoutManager( new LinearLayoutManager(this));
        similarMoviesRecycler.setAdapter(similarMoviesAdapter);


        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.setid(movieId);

        movieDetailsViewModel.getSelectedMovie();
        movieDetailsViewModel.getSimilarMovies();

        similarMoviesAdapter.setList(list);



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
              // genres.setText(movie_api.getGenres().get(0).getName());
              //genres.append(movie_api.getGenres().get(1).getName());
              /*  while (movie_api.getGenres().get(i).getId() == 0) {

                    genres.append(movie_api.getGenres().get(i).getName());
                    i++;

                }*/


            }
        });



/*
movieDetailsViewModel.getSimilarMovies().observe(this, new Observer<ResultMovieData>() {
    @Override
    public void onChanged(ResultMovieData resultMovieData) {

        list=resultMovieData.getResults();
    }
});
 */

        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(isPlay) {

}else {
    movieDetailsViewModel.insertToFavourite(getApplicationContext(), movieId, movieApi.getTitle());
    Toast.makeText(getApplicationContext(), "added to favourite", Toast.LENGTH_SHORT).show();
    favBtn.setBackgroundColor(R.drawable.background_design);
}

isPlay=!isPlay;

            }
        });



    }
}