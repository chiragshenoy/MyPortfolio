package chiragshenoy.myportfolio.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;

import java.util.ArrayList;

import butterknife.Bind;
import chiragshenoy.myportfolio.Adapters.MoviesListingAdapter;
import chiragshenoy.myportfolio.Api.MyPortfolioApis;
import chiragshenoy.myportfolio.Models.Collections.MovieList;
import chiragshenoy.myportfolio.Models.MovieModel;
import chiragshenoy.myportfolio.R;
import chiragshenoy.myportfolio.Utils.AppConstants;
import chiragshenoy.myportfolio.Utils.NetworkHelperUtils;

/**
 * Created by Chirag Shenoy on 12-Feb-16.
 */
public class SpotifyStreamerMovieListingActivity extends BaseActivity {

    GridLayoutManager gridLayoutManager;

    @Bind(R.id.rvMovies)
    RecyclerView rvMovies;

    private ArrayList<MovieModel> mMovieModels = new ArrayList<>();

    private MoviesListingAdapter mMovieListingAdapter;
    String sort_order = AppConstants.SORTING_MODE_POPULARITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpViewAndAdapter();
        getMoviesFromNetworkCall(sort_order);


    }

    private void setUpViewAndAdapter() {

        mMovieListingAdapter = new MoviesListingAdapter(getApplicationContext(), new MoviesListingAdapter.CardTapListener() {
            @Override
            public void onTap(View v, MovieModel movieModel) {
                Intent intent = new Intent(getApplicationContext(), MovieDetailsActivity.class);

                // Pass data object in the bundle and populate details activity.

                intent.putExtra(AppConstants.POSTER_PATH_EXTRA, movieModel.getPoster_path());
                intent.putExtra(AppConstants.BACKDROP_PATH_EXTRA, movieModel.getBackdrop_path());
                intent.putExtra(AppConstants.ORIGINAL_TITLE_EXTRA, movieModel.getOriginal_title());
                intent.putExtra(AppConstants.OVERVIEW_EXTRA, movieModel.getOverview());
                intent.putExtra(AppConstants.USER_RATING_EXTRA, movieModel.getVote_average());
                intent.putExtra(AppConstants.RELEASE_DATE_EXTRA, movieModel.getRelease_date());

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(SpotifyStreamerMovieListingActivity.this, v, getApplication().getResources().getString(R.string.movie_poster_transition));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(intent, options.toBundle());
                }
            }
        });

        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(gridLayoutManager);
        rvMovies.setAdapter(mMovieListingAdapter);

    }

    private void getMoviesFromNetworkCall(String sort_order) {

        MyPortfolioApis.requestMovieList(sort_order, new Response.Listener<MovieList>() {
            @Override
            public void onResponse(MovieList response) {
                processAndDisplay(response.getmMovieModels());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), R.string.generic_error, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void processAndDisplay(ArrayList<MovieModel> response) {
        mMovieModels.clear();
        mMovieModels.addAll(response);
        mMovieListingAdapter.setDataSet(mMovieModels);
        mMovieListingAdapter.notifyDataSetChanged();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_spotify_streamer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies_listing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuSortPopularity) {
            getMoviesFromNetworkCall(AppConstants.SORTING_MODE_POPULARITY);
            return true;
        } else if (id == R.id.menuSortRating) {
            getMoviesFromNetworkCall(AppConstants.SORTING_MODE_HIGHEST_RATED);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
