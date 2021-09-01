package com.example.moviesreview.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.moviesreview.R;
import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.data.remote.ApiClient;
import com.example.moviesreview.ui.MainAdapter;
import com.example.moviesreview.ui.NowPlaying.NowPlayingViewModel;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

  public int movieId;
TextView movieTitle ;
TextView status ;
TextView overview ;
TextView genres ;
ImageView imageView;
RatingBar ratingBar ;

    MovieDetailsViewModel movieDetailsViewModel;
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






movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
movieDetailsViewModel.setid(movieId);
movieDetailsViewModel.getSelectedMovie();


movieDetailsViewModel.getSelectedMovie().observe(this, new Observer<Movie_Api>() {
    @Override
    public void onChanged(Movie_Api movie_api) {

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
      //genres.append(movie_api.getGenres().get(1).getName());
        while (movie_api.getGenres().get(i).getId() == 0) {

            genres.append(movie_api.getGenres().get(i).getName());
            i++;

        }




    }
});







    }
}