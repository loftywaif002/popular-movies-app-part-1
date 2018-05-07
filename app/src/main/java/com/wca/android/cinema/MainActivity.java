package com.wca.android.cinema;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    String myApiKey = BuildConfig.API_KEY;


    @BindView(R.id.movies_grid) GridView mGridView;
    @BindView(R.id.indeterminateBar)
    ProgressBar mProgressBar;

    String popularMoviesURL;
    String topRatedMoviesURL;

    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopTopRatedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE); //Hide Progressbar by Default
        new FetchMovies().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pop_movies) {

            refreshList(mPopularList);
        }

        if (id == R.id.top_movies) {

            refreshList(mTopTopRatedList);
        }

        return super.onOptionsItemSelected(item);
    }


    private void refreshList(ArrayList<Movie> list) {
        MovieAdapter adapter = new MovieAdapter(MainActivity.this, list);
        mGridView.invalidateViews();
        mGridView.setAdapter(adapter);
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


            popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+myApiKey;
            topRatedMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key="+myApiKey;

            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();

            try {
                if(NetworkUtils.networkStatus(MainActivity.this)){
                    mPopularList = NetworkUtils.fetchData(popularMoviesURL); //Get popular movies
                    mTopTopRatedList = NetworkUtils.fetchData(topRatedMoviesURL); //Get top rated movies
                }else{
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            mProgressBar.setVisibility(View.INVISIBLE);

            //Load popular movies by default
            MovieAdapter adapter = new MovieAdapter(MainActivity.this, mPopularList);
            mGridView.setAdapter(adapter);
        }
    }
}
