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
import android.widget.TextView;

import com.example.moviesreview.R;
import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.ui.MainAdapter;
import com.example.moviesreview.ui.NowPlaying.NowPlayingViewModel;

public class MovieDetails extends AppCompatActivity {

  public int movieId;
TextView textView;
    MovieDetailsViewModel movieDetailsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        movieId =getIntent().getIntExtra("idMovie",0);


textView=findViewById(R.id.teext);

movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
movieDetailsViewModel.setid(movieId);
movieDetailsViewModel.getSelectedMovie();


movieDetailsViewModel.getSelectedMovie().observe(this, new Observer<Movie_Api>() {
    @Override
    public void onChanged(Movie_Api movie_api) {
        textView.setText(movie_api.getTitle());
    }
});







    }
}