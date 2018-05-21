package com.wca.android.cinema;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    final static String POPULAR_MOVIES = "pop_mov";
    final static String TOP_RATED_MOVIES = "topratedlist";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(View.INVISIBLE); //Hide Progressbar by Default
        new FetchMovies().execute();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> view, View view1, int position, long l) {
                Movie movie = (Movie) view.getAdapter().getItem(position);
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra("detail", movie);
                startActivity(intent);
            }
        });
    }

    //Save date before app is closed
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(POPULAR_MOVIES, mPopularList);
        outState.putSerializable(TOP_RATED_MOVIES, mTopTopRatedList);
        super.onSaveInstanceState(outState);
    }

    //Loading SavedInstance
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPopularList = (ArrayList<Movie>) savedInstanceState.getSerializable(POPULAR_MOVIES);
        mTopTopRatedList = (ArrayList<Movie>) savedInstanceState.getSerializable(TOP_RATED_MOVIES);
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
