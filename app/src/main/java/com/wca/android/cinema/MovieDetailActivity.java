package com.wca.android.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import DetailFragments.BackdropFragment;
import DetailFragments.PosterFragment;


public class MovieDetailActivity extends AppCompatActivity {

    public static final String BASE_URL ="https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        Movie mov_intent = (Movie) intent.getSerializableExtra("detail");

        String full_backdrop_url = BASE_URL + mov_intent.getBackdropPath();
        String full_poster_url = BASE_URL + mov_intent.getPosterPath();
        String movie_title = mov_intent.getTitle();

        BackdropFragment backdropFragment = (BackdropFragment) getFragmentManager().findFragmentById(R.id.fragment_backdrop);
        PosterFragment posterFragment  = (PosterFragment) getFragmentManager().findFragmentById(R.id.fragment_poster);


        backdropFragment.set_backdrop(full_backdrop_url);
        posterFragment.set_poster(full_poster_url);

    }

}
