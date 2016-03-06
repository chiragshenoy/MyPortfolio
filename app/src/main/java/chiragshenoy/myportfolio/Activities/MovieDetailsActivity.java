package chiragshenoy.myportfolio.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import chiragshenoy.myportfolio.R;
import chiragshenoy.myportfolio.Utils.AppConstants;

/**
 * Created by Chirag Shenoy on 06-Mar-16.
 */
public class MovieDetailsActivity extends BaseActivity {

    @Bind(R.id.ivMoviePoster)
    ImageView ivMoviePoster;

    @Bind(R.id.ivBackDrop)
    ImageView ivBackDrop;

    @Bind(R.id.tv_original_title)
    TextView tv_original_title;

    @Bind(R.id.tv_rating)
    TextView tv_rating;

    @Bind(R.id.tv_overview)
    TextView tv_overview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateViews();

    }

    private void populateViews() {
        Intent intent = getIntent();

        String poster_path = intent.getStringExtra(AppConstants.POSTER_PATH_EXTRA);
        String back_drop_path = intent.getStringExtra(AppConstants.BACKDROP_PATH_EXTRA);
        String original_title = intent.getStringExtra(AppConstants.ORIGINAL_TITLE_EXTRA);
        String rating = intent.getStringExtra(AppConstants.USER_RATING_EXTRA);
        String overview = intent.getStringExtra(AppConstants.OVERVIEW_EXTRA);

        Picasso.with(this).load(AppConstants.TMDB_POSTER_PATH_BASE_URL + poster_path).into(ivMoviePoster);
        Picasso.with(this).load(AppConstants.TMDB_BACKDROP_PATH_BASE_URL + back_drop_path).into(ivBackDrop);

        if (original_title.length() > 15) {
            tv_original_title.setTextSize(22);
        }

        if (original_title.length() > 22) {
            tv_original_title.setTextSize(16);
        }

        tv_original_title.setText(original_title);
        tv_rating.setText(rating);
        tv_overview.setText(overview);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_details;
    }

}
