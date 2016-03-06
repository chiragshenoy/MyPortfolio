package chiragshenoy.myportfolio.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import chiragshenoy.myportfolio.Models.MovieModel;
import chiragshenoy.myportfolio.R;
import chiragshenoy.myportfolio.Utils.AppConstants;

/**
 * Created by Chirag Shenoy on 17-Feb-16.
 */
public class MoviesListingAdapter extends RecyclerView.Adapter<MoviesListingAdapter.ViewHolder> {

    private final Context mContext;
    private final CardTapListener mCardTapListener;
    private List<MovieModel> mDataSet = new ArrayList<>();


    public MoviesListingAdapter(Context context, CardTapListener cardTapListener) {
        this.mContext = context;
        this.mCardTapListener = cardTapListener;
    }


    public void setDataSet(List<MovieModel> movies) {
        mDataSet = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_movie, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final MovieModel movieModelItem = mDataSet.get(position);
        
        if (movieModelItem.getTitle() != null)
            holder.tvMovieTitle.setText(movieModelItem.getTitle());

        if (movieModelItem.getPoster_path() != null)
            Picasso.with(mContext).load(AppConstants.TMDB_POSTER_PATH_BASE_URL + movieModelItem.getPoster_path()).into(holder.ivMovieThumbnail);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public interface CardTapListener {
        void onTap(View v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ivMovieThumbnail)
        ImageView ivMovieThumbnail;

        @Bind(R.id.tvMovieTitle)
        TextView tvMovieTitle;

        @Bind(R.id.cvMovie)
        CardView cvMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

