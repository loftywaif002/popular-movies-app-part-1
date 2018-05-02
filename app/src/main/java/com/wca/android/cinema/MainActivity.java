package com.wca.android.cinema;

import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;

    String popularMovies;
    String topRatedMovies;

    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopTopRatedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    //AsyncTask
    public class FetchMovies extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            popularMovies = "https://api.themoviedb.org/3/movie/popular?api_key=525e39d1c3568cd23cdaf0a3674918fa&language=en-US&page=1";
            topRatedMovies = "https://api.themoviedb.org/3/movie/top_rated?api_key=525e39d1c3568cd23cdaf0a3674918fa&language=en-US&page=1";

            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();

            return null;
        }
    }

}
