package DetailFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wca.android.cinema.R;

public class Tilte_rating_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.popularity_fragment, container, false);
        return view;
    }

    public void set_poster(String title, Double rating) {
        TextView text_view = (TextView) getView().findViewById(R.id.movie_title);
        text_view.setText(title);
    }
}
