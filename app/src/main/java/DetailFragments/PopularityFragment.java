package DetailFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wca.android.cinema.R;

public class PopularityFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.popularity_fragment, container, false);
        return view;
    }

    public void set_title_rating(String title, Float rating, String vote_avg_text, String rel_dt){

        TextView text_view = (TextView) getView().findViewById(R.id.movie_title);
        RatingBar ratingBar = (RatingBar) getView().findViewById(R.id.movie_rating);
        TextView rating_textView = (TextView) getView().findViewById(R.id.tmdb_rating);
        TextView rel_date = (TextView) getView().findViewById(R.id.release_date);

        ratingBar.setRating(rating);
        text_view.setText(title);
        rating_textView.setText(vote_avg_text);
        rel_date.setText(rel_dt);
    }
}
