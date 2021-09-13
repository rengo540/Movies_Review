package com.example.moviesreview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.moviesreview.ui.NowPlaying.NowPlayingFragment;
import com.example.moviesreview.ui.Popular.PopularFragment;
import com.example.moviesreview.ui.TopRated.TopRatedFragment;
import com.example.moviesreview.ui.favourite.FavouriteFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar ;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.nav_drawer);
        toolbar = findViewById(R.id.toolbar);
        NavigationView navigationView =findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new NowPlayingFragment());

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_draw_open,R.string.navigation_draw_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new NowPlayingFragment()).commit();
            navigationView.setCheckedItem(R.id.NowPlaying);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.NowPlaying:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new NowPlayingFragment()).commit();
                break;
            case R.id.Popular:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new PopularFragment()).commit();
                break;
            case R.id.TopRated:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new TopRatedFragment()).commit();
                break;

            case R.id.favourite:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container,new FavouriteFragment()).commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    /* @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }*/
}