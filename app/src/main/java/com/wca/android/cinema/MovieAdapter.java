package com.wca.android.cinema;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private static final String TAG = MovieAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<Movie> list;
    public static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.mContext = context;
        this.list = movieList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Movie getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * We are creating a  View manually.
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        Movie movies = getItem(position);
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);
            relativeLayout.addView(imageView);
        } else {
            imageView = (ImageView) convertView;
        }

        //load data into the ImageView using Picasso
        Picasso.get().load(MOVIE_BASE_URL + movies.getPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .into(imageView);

        return imageView;
    }


}
