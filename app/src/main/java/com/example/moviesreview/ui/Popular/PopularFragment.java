package com.example.moviesreview.ui.Popular;

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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.popular_fragment,container,false);


        popularViewModel = ViewModelProviders.of(this).get(PopularViewModel.class);

        popularViewModel.getPop();

        recyclerView = rootView.findViewById(R.id.PopularRecycler);
        adapter =new MainAdapter(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager( mLayoutManager);
        recyclerView.setAdapter(adapter);

        listener=new MainAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(),"this done ",Toast.LENGTH_SHORT).show();

            }
        };


        popularViewModel.getPop().observe(getViewLifecycleOwner(), new Observer<ResultMovieData>() {
            @Override
            public void onChanged(ResultMovieData resultMovieData) {

                adapter.setList( resultMovieData.getResults(),listener);

            }
        });
        return  rootView;
    }
}
