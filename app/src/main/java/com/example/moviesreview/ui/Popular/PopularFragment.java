package com.example.moviesreview.ui.Popular;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesreview.R;
import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.model.api.ResultMovieData;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PopularFragment extends Fragment {
    Button button;

    RecyclerView recyclerView;
PopularViewModel popularViewModel;
    TextView textView;
    MainAdapter adapter;

    MainAdapter.RecyclerViewClickListener listener;
    List<Movie_Api> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.popular_fragment,container,false);


        list=new ArrayList<>();
        popularViewModel = ViewModelProviders.of(this).get(PopularViewModel.class);

        popularViewModel.getPop();

        recyclerView = rootView.findViewById(R.id.PopularRecycler);
        adapter =new MainAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager( mLayoutManager);
        recyclerView.setAdapter(adapter);




        popularViewModel.getPop().observe(getViewLifecycleOwner(), new Observer<ResultMovieData>() {
            @Override
            public void onChanged(ResultMovieData resultMovieData) {
                list=resultMovieData.getResults();

                adapter.setList( resultMovieData.getResults(),listener);

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

        return  rootView;
    }
}
