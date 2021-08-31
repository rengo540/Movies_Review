package com.example.moviesreview.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviesreview.R;
import com.example.moviesreview.data.model.api.Movie_Api;
import com.example.moviesreview.data.remote.ApiClient;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.movieVH>  {

private List<Movie_Api> movies=new ArrayList();
Context context;
RecyclerViewClickListener listener ;

public MainAdapter(Context context)
        {this.context=context;
        }

public void setList(List<Movie_Api> movies,RecyclerViewClickListener listener ) {
        this.movies = movies;
        this.listener=listener;
        notifyDataSetChanged();
        }


class movieVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView movieTitle ;
    ImageView movieImg ;

    public movieVH(@NonNull View itemView) {

        super(itemView);
       movieImg=itemView.findViewById(R.id.movieImg);
        movieTitle=itemView.findViewById(R.id.movieTitle);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition());
    }
}


    @NonNull
    @Override
    public movieVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);

        movieVH b = new movieVH(v);

        return b;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.movieVH movieVH, int position) {
       //Picasso.get().load(movies.get(position).getBackdrop_path()).fit().centerInside().into( movieVH.movieImg );
        movieVH.movieTitle.setText(movies.get(position).getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(ApiClient.getInstance().getImgUrl() + movies.get(position).getBackdrop_path())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(movieVH.movieImg);

    }




    @Override
    public int getItemCount() {
        return movies.size();
    }


    public interface RecyclerViewClickListener{
        void onClick (View view,int position);
    }
}
