package com.example.moviesreview.ui.details;

import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.remote.ApiRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailsViewModel extends ViewModel {

int id ;
    public void setid (int id){

        this.id=id ;
    }

    public LiveData<Movie_Api> getSelectedMovie (){
        return ApiRepo.getInstance().getSelectedMovie(id);
    }
}
