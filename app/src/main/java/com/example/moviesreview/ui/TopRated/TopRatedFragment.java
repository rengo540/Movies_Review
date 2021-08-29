package com.example.moviesreview.ui.TopRated;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviesreview.R;
import com.example.moviesreview.data.model.api.ResultMovieData;
import com.example.moviesreview.ui.MainAdapter;
import com.example.moviesreview.ui.Popular.PopularViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TopRatedFragment extends Fragment {

    RecyclerView recyclerView;
    TopRatedViewModel topRatedViewModel;
    TextView textView;
    MainAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.top_rated_fragment,container,false);


        topRatedViewModel = ViewModelProviders.of(this).get(TopRatedViewModel.class);

        topRatedViewModel.getTopRatedData();

        recyclerView = rootView.findViewById(R.id.TopRatedRecycler);
        adapter =new MainAdapter(getContext());
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        topRatedViewModel.getTopRatedData().observe(getViewLifecycleOwner(), new Observer<ResultMovieData>() {
            @Override
            public void onChanged(ResultMovieData resultMovieData) {

                adapter.setList( resultMovieData.getResults());

            }
        });
        return  rootView;


    }
}
