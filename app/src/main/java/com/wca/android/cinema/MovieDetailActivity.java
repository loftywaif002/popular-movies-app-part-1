package com.wca.android.cinema;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import DetailFragments.BackdropFragment;
import DetailFragments.PopularityFragment;
import DetailFragments.PosterFragment;


public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    public static final String BASE_URL ="https://image.tmdb.org/t/p/w185";

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Intent intent = getIntent();
        Movie mov_intent = (Movie) intent.getSerializableExtra("detail");

        String full_backdrop_url = BASE_URL + mov_intent.getBackdropPath();
        String full_poster_url = BASE_URL + mov_intent.getPosterPath();
        String movie_title = mov_intent.getTitle();
        Double vote_avg = mov_intent.getVoteAverage();
        Float mvote_avg = (float) ((vote_avg / 10) * 5); //Converting in a 5 scale
        String rating_text = "TMDB: "+ String.valueOf(vote_avg)+" / 10";

        String release_date = mov_intent.getReleaseDate();

        String inputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(release_date);
            Log.e(TAG,formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        BackdropFragment backdropFragment = (BackdropFragment) getFragmentManager().findFragmentById(R.id.fragment_backdrop);
        PosterFragment posterFragment  = (PosterFragment) getFragmentManager().findFragmentById(R.id.fragment_poster);
        PopularityFragment popularityFragment = (PopularityFragment) getFragmentManager().findFragmentById(R.id.fragment_rating);

        backdropFragment.set_backdrop(full_backdrop_url);
        posterFragment.set_poster(full_poster_url);

        popularityFragment.set_title_rating(movie_title,mvote_avg,rating_text,formatter.format(date));

    }

}
