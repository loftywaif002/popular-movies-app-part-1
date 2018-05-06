package com.wca.android.cinema;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;

    String popularMoviesURL;
    String topRatedMoviesURL;

    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopTopRatedList;

    public Boolean shouldCall = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE); //Hide Progressbar by Default

       //if(shouldCall) { // I know if the method has been called before
        // new FetchMovies().execute();
         // shouldCall = false;
       //}

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

            popularMoviesURL = "https://api.themoviedb.org/3/movie/popular?api_key=525e39d1c3568cd23cdaf0a3674918fa&language=en-US&page=1";
            topRatedMoviesURL = "https://api.themoviedb.org/3/movie/top_rated?api_key=525e39d1c3568cd23cdaf0a3674918fa&language=en-US&page=1";

            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();

            try {
                mPopularList = NetworkUtils.fetchData(popularMoviesURL);
                mTopTopRatedList = NetworkUtils.fetchData(topRatedMoviesURL);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            mProgressBar.setVisibility(View.INVISIBLE);

        }
    }

}
