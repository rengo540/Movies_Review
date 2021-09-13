package com.example.moviesreview.ui.favourite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviesreview.R;
import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.db.Movie_db;
import com.example.moviesreview.ui.FavouriteAdapter;
import com.example.moviesreview.ui.MainAdapter;
import com.example.moviesreview.ui.NowPlaying.NowPlayingViewModel;
import com.example.moviesreview.ui.details.MovieDetails;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavouriteFragment extends Fragment {

    FavouriteViewModel favouriteViewModel ;
    RecyclerView recyclerView;
    FavouriteAdapter adapter;
    MainAdapter.RecyclerViewClickListener listener;

    List<Movie_db> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.favourite_fragment,container,false);


        list=new ArrayList<>();

        favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);

        favouriteViewModel.getFavMovies(getContext());

        recyclerView = rootView.findViewById(R.id.favouriteRecycler);
        adapter =new FavouriteAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);

        recyclerView.setLayoutManager( mLayoutManager);
        recyclerView.setAdapter(adapter);

        favouriteViewModel.getFavMovies(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Movie_db>>() {
            @Override
            public void onChanged(List<Movie_db> movie_dbs) {
                list=movie_dbs;

                adapter.setList(movie_dbs,listener);
            }
        });


        listener=new MainAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                int idMovie = list.get(position).getId();

                Intent intent =new Intent(getActivity(), MovieDetails.class);
                intent.putExtra("idMovie",idMovie);
                startActivity(intent);

            }
        };


        return rootView;
    }
}
