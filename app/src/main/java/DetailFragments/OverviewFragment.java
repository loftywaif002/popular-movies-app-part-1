package DetailFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wca.android.cinema.R;

public class OverviewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * DetailFragment uses a custom layout referenced via R.layout.fragment_detail. The
         * layout is inflated in a container that can either be the content view defined
         * by DetailActivity (when the device is in portrait mode) or as part of
         * res/layout-land/activity_main.xml (when the device is in landscape mode).
         */
        View view = inflater.inflate(R.layout.overview, container, false);
        return view;
    }

    public void set_overview(String overview_text) {

        TextView textView = (TextView) getView().findViewById(R.id.overview_text);
        textView.setText(overview_text);
    }
}
