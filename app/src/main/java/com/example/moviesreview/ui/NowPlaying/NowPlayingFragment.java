package com.example.moviesreview.ui.NowPlaying;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesreview.R;
import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.ui.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NowPlayingFragment extends Fragment {

    RecyclerView recyclerView;
    NowPlayingViewModel nowPlayingViewModel;
     TextView textView;
    MainAdapter adapter;

    MainAdapter.RecyclerViewClickListener listener;
    List<Movie_Api> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.now_playing_fragment, container, false);


        list=new ArrayList<>();

         nowPlayingViewModel = ViewModelProviders.of(this).get(NowPlayingViewModel.class);

         nowPlayingViewModel.getMovies();

        recyclerView = rootView.findViewById(R.id.NowPlayingRecycler);
         adapter =new MainAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager( mLayoutManager);
        recyclerView.setAdapter(adapter);


        listener=new MainAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                int idMovie = list.get(position).getId();

                Intent intent =new Intent(getActivity(),)

            }
        };


        nowPlayingViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<ResultMovieData>() {
            @Override
            public void onChanged(ResultMovieData resultMovieData) {
                list=resultMovieData.getResults();
                adapter.setList(resultMovieData.getResults(),listener);
            }
        });


        return rootView;


    }

}
