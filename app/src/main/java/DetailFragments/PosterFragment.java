package DetailFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wca.android.cinema.R;

public class PosterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.poster_fragment, container, false);
        return view;
    }

    public void set_poster(String url) {
        ImageView view = (ImageView) getView().findViewById(R.id.movie_poster);
        Picasso.get()
                .load(url)
                .into(view);
    }
}
